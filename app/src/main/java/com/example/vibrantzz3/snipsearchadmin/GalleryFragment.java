package com.example.vibrantzz3.snipsearchadmin;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GalleryFragment extends Fragment {
    View view;
    List<Gallery> GalleryData;
    String id;
    GalleryRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private static final String url_menu = "http://test.epoqueapparels.com/Salon/Salon_App/salongallery.php";
    JSONObject jsonObject;
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_PHOTO = "photo";

    public GalleryFragment() {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_gallery,container,false);
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        id = args.getString("idholder");

        GalleryData=new ArrayList<>();
        new WelcomeAsyncTask().execute();
        return view;

    }



    private class WelcomeAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar

        }

        @Override
        protected String doInBackground(String... params) {
            HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            httpParams.put(TAG_ID, id);
            jsonObject = httpJsonParser.makeHttpRequest(
                    url_menu , "GET", httpParams);

            return null;
        }

        protected void onPostExecute(final String result) {



            try {
                JSONArray jArray = jsonObject.getJSONArray(TAG_PROFILE);

                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    //Parse the JSON response
                    GalleryData.add(new Gallery( json_data.getString(TAG_ID),json_data.getString(TAG_PHOTO)));
                }


                myrv=(RecyclerView) view.findViewById(R.id.gallery_recycler);
                myAdapter=new GalleryRecyclerViewAdapter(getContext(),GalleryData);
                myrv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                myrv.setAdapter(myAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


}







