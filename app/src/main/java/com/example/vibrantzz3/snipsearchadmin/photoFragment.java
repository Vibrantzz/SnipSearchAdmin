package com.example.vibrantzz3.snipsearchadmin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vibrantzz3 on 2/5/2018.
 */

public class photoFragment extends Fragment {
    View view;
    List<Photo> photoData;
    PhotoRecyclerViewAdapter myAdapter;
    RecyclerView myrv;
    private static final String url_beauty = "http://test.epoqueapparels.com/Salon/Salon_App/beauty.php";
    JSONObject jsonObject;
    private static final String TAG_PROFILE = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_RATING = "rating";
    private static final String TAG_PIC = "profilepic";
    private static final String TAG_RCOUNT = "rcount";
    String uid;

    public photoFragment() {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.photo_fragment, container, false);

        super.onCreate(savedInstanceState);
        //final Bundle args = getArguments();
        //uid = args.getString("id");

        photoData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            //Parse the JSON response
            photoData.add(new Photo(TAG_NAME, TAG_LOCATION, TAG_RATING, TAG_PIC, TAG_ID, uid, TAG_RCOUNT));
        }


        myrv = (RecyclerView) view.findViewById(R.id.photo_recycler);
        PhotoRecyclerViewAdapter myAdapter = new PhotoRecyclerViewAdapter(getContext(), photoData);
        myrv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        myrv.setAdapter(myAdapter);
        return view;

    }


}

