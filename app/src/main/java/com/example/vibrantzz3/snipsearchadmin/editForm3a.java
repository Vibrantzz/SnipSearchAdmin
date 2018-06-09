package com.example.vibrantzz3.snipsearchadmin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class editForm3a extends AppCompatActivity {

    String ServerURL = "http://test.epoqueapparels.com/Salon/Salon_App/editindexsalonmaster.php" ;
    String HttpURLForHours = "http://test.epoqueapparels.com/CRUD/getSalonAdminEditFillForm3a.php";
    String ParseResult , TempGender, first, second, third;
    String TempFirst, TempSecond, TempThird;
    String FinalJSonObject ;
    HashMap<String,String> ResultHash = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    TextView already_user;
    EditText name, email, password;
    ImageView go;
    String id2 = "1";
    String id1 = "0";
    String id, IDHolder;
    ImageView femaleimg, femaleimgpress, maleimg, maleimgpress, uniseximg, uniseximgpress;
    //--for user register---
    protected String enteredUsername, enteredPassword, enteredEmail;
    private final String serverUrl = "http://test.epoqueapparels.com/Salon/Salon_App/index1.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide title bar
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        //to get in full screen
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.editform3a);
        femaleimg = (ImageView) findViewById(R.id.femaleimg);
        femaleimgpress = (ImageView) findViewById(R.id.femaleimgpress);
        maleimg = (ImageView) findViewById(R.id.maleimg);
        maleimgpress = (ImageView) findViewById(R.id.maleimgpress);
        uniseximg = (ImageView) findViewById(R.id.uniseximg);
        uniseximgpress = (ImageView) findViewById(R.id.uniseximgpress);

        Intent intent = getIntent();
        IDHolder = intent.getExtras().getString("idholder");

        HttpWebCallForSalon(IDHolder);

        /*go = (ImageView) findViewById(R.id.go);*/
        //final String token = TokenManager.getInstance(this).getDeviceToken();
        /*go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editForm3a.this, fillForm4.class);
                intent.putExtra("id", id1);
                startActivity(intent);
                finish();

            }
        });*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.backarrow5);
//        toolbar.setTitle("Select Your Business type. (Select multiple)");
        //toolbar.setTitleTextColor(Color.rgb(0, 0, 0));;// your drawable
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        // -------------------validations-----------------

        //----------------------------------------------------------------------------------

        femaleimg.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = femaleimgpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    femaleimgpress.setVisibility(View.GONE);
                    TempFirst = "11";
                }
                else
                {
                    femaleimgpress.setVisibility(View.VISIBLE);
                    TempFirst = "1";
                }
            }
        });

        maleimg.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = maleimgpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    maleimgpress.setVisibility(View.GONE);
                    TempSecond = "12";
                }
                else
                {
                    maleimgpress.setVisibility(View.VISIBLE);
                    TempSecond = "2";
                }
            }
        });

        uniseximg.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = uniseximgpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    uniseximgpress.setVisibility(View.GONE);
                    TempThird = "13";
                }
                else
                {
                    uniseximgpress.setVisibility(View.VISIBLE);
                    TempThird = "3";
                }
            }
        });

    }

    public void GetEditfillform3a(View v){
        /*if(TempFirst == null)
        {
            TempFirst = "11";
        }
        if(TempSecond == null)
        {
            TempSecond = "12";
        }
        if (TempThird == null)
        {
            TempThird = "13";
        }*/
        InsertData(IDHolder,TempFirst,TempSecond,TempThird);

        Intent intent = new Intent(editForm3a.this, Settings.class);
        intent.putExtra("idholder",IDHolder);
        startActivity(intent);
        finish();


    }

    public void InsertData(final String salon_id,final String first,final String second,final String third) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String NameHolder = salon_id ;
                String NameHolder1 = first ;
                String NameHolder2 = second ;
                String NameHolder3 = third ;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("salon_id", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("gender_id", NameHolder1));
                nameValuePairs.add(new BasicNameValuePair("gender_id1", NameHolder2));
                nameValuePairs.add(new BasicNameValuePair("gender_id2", NameHolder3));


                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(ServerURL);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();
                } catch (ClientProtocolException e) {
                } catch (IOException e) {
                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(editForm3a.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(salon_id);
    }


    //Method to show current record Current Selected Record
    public void HttpWebCallForSalon(final String PreviousListViewClickedItem){

        class HttpWebCallFunction extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                // pDialog = ProgressDialog.show(HomeSalonUser.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                //pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;

                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponseForSR(editForm3a.this).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("id",params[0]);

                ParseResult = httpParse.postRequest(ResultHash, HttpURLForHours);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }

    // Parsing Complete JSON Object.
    private class GetHttpResponseForSR extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String JSonResult;


        public GetHttpResponseForSR(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServicesClass httpServicesClass = new HttpServicesClass(HttpURLForHours);
            try
            {
                httpServicesClass.ExecutePostRequest();

                if(httpServicesClass.getResponseCode() == 200)
                {
                    JSonResult = httpServicesClass.getResponse();

                    if(FinalJSonObject != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(FinalJSonObject);

                            JSONObject jsonObject, jsonObject1, jsonObject2;

                                jsonObject = jsonArray.getJSONObject(0);
                                jsonObject1 = jsonArray.getJSONObject(1);
                                jsonObject2 = jsonArray.getJSONObject(2);

                                TempFirst = jsonObject.getString("gender_id").toString();
                                TempSecond = jsonObject1.getString("gender_id").toString();
                                TempThird = jsonObject2.getString("gender_id").toString();


                            //firstgender = jsonArray(0).getJSONObject("legislator");
                                //Adding Student Name.
                               // TempGender =

                                /*TempAddress = jsonObject.getString("address").toString();
                                TempCity = jsonObject.getString("city").toString();
                                TempContact = jsonObject.getString("contact").toString();
                                TempPayment = jsonObject.getString("payment").toString();
                                TempFacilities = jsonObject.getString("facilites").toString();
                                TempType = jsonObject.getString("type").toString();
                                TempSpec = jsonObject.getString("spec").toString();*/



                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServicesClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
//            progressBar.setVisibility(View.GONE);

            if ("1".equals(TempFirst)) {
                femaleimgpress.setVisibility(View.VISIBLE);
            }else if("11".equals(TempSecond)){
                femaleimgpress.setVisibility(View.INVISIBLE);
            }
            if ("2".equals(TempSecond)) {
                maleimgpress.setVisibility(View.VISIBLE);
            }else if("12".equals(TempSecond)){
                maleimgpress.setVisibility(View.INVISIBLE);
            }
            if ("3".equals(TempThird)) {
                uniseximgpress.setVisibility(View.VISIBLE);
            }else if("13".equals(TempThird)){
                uniseximgpress.setVisibility(View.INVISIBLE);
            }

//            StudentListView.setVisibility(View.VISIBLE);

//            ListAdapterClass adapter = new ListAdapterClass(studentList, context);

//            StudentListView.setAdapter(adapter);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(editForm3a.this, Settings.class);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);
        finish();

    }
}