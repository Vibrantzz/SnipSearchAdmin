package com.example.vibrantzz3.snipsearchadmin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class fillForm3a extends AppCompatActivity {

    String ServerURL = "http://test.epoqueapparels.com/Salon/Salon_App/indexsalonmaster.php" ;
    String HttpURL = "http://test.epoqueapparels.com/CRUD/getSalonHome.php";
    String HttpURLForHours = "http://test.epoqueapparels.com/CRUD/getSalonAdminEditFillForm3a.php";
    TextView already_user;
    HttpParse httpParse = new HttpParse();
    String ParseResult ;
    String FinalJSonObject ;
    HashMap<String,String> ResultHash = new HashMap<>();
    EditText name, email, password;
    ImageView go;
    String id2 = "1";
    String id1 = "0";
    String first, second, third;
    String id, TempEmailStore, IDHolder;
    ImageView femaleimg, femaleimgpress, maleimg, maleimgpress, uniseximg, uniseximgpress;
    //--for user register---
    protected String enteredUsername, enteredPassword, enteredEmail;
    private final String serverUrl = "http://test.epoqueapparels.com/Salon/Salon_App/index1.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide title bar
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent intent = getIntent();
        TempEmailStore = intent.getExtras().getString("email");
        IDHolder = intent.getExtras().getString("idholder");
        id = intent.getExtras().getString("id");

        if(id.equals("1")){
            /*this.overridePendingTransition(R.anim.animation_left,
                    R.anim.animation_right);*/
        }else if(id.equals("0")) {
            this.overridePendingTransition(R.anim.anim_slide_in_left,
                    R.anim.anim_slide_out_left);

        }
        //to get in full screen
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fillform3a);


        femaleimg = (ImageView) findViewById(R.id.femaleimg);
        femaleimgpress = (ImageView) findViewById(R.id.femaleimgpress);
        maleimg = (ImageView) findViewById(R.id.maleimg);
        maleimgpress = (ImageView) findViewById(R.id.maleimgpress);
        uniseximg = (ImageView) findViewById(R.id.uniseximg);
        uniseximgpress = (ImageView) findViewById(R.id.uniseximgpress);
        go = (ImageView) findViewById(R.id.go);


        //final String token = TokenManager.getInstance(this).getDeviceToken();
        /*go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Intent intent = new Intent(fillForm3a.this, fillForm4.class);
                intent.putExtra("id", id1);
                intent.putExtra("id1", first);
                intent.putExtra("id2", second);
                intent.putExtra("id3", third);
                startActivity(intent);
                finish();*//*

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

        HttpWebCallForSalon(IDHolder);

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
                    first = "0";
                }
                else
                {
                    femaleimgpress.setVisibility(View.VISIBLE);
                    first = "1";
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
                    second = "0";
                }
                else
                {
                    maleimgpress.setVisibility(View.VISIBLE);
                    second = "2";
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
                    third = "0";
                }
                else
                {
                    uniseximgpress.setVisibility(View.VISIBLE);
                    third = "3";
                }
            }
        });

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
                new GetHttpResponseForSR(fillForm3a.this).execute();

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

                    if(!"No Results Found.".equals(FinalJSonObject))
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(FinalJSonObject);

                            JSONObject jsonObject, jsonObject1, jsonObject2;

                            jsonObject = jsonArray.getJSONObject(0);
                            jsonObject1 = jsonArray.getJSONObject(1);
                            jsonObject2 = jsonArray.getJSONObject(2);

                            first = jsonObject.getString("gender_id").toString();
                            second = jsonObject1.getString("gender_id").toString();
                            third = jsonObject2.getString("gender_id").toString();


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
                    }else{
                        Toast.makeText(fillForm3a.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(fillForm3a.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
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

            if(!"No Results Found.".equals(FinalJSonObject)) {
                if ("1".equals(first)) {
                    femaleimgpress.setVisibility(View.VISIBLE);
                } else if ("11".equals(first)) {
                    femaleimgpress.setVisibility(View.INVISIBLE);
                }
                if ("2".equals(second)) {
                    maleimgpress.setVisibility(View.VISIBLE);
                } else if ("12".equals(second)) {
                    maleimgpress.setVisibility(View.INVISIBLE);
                }
                if ("3".equals(third)) {
                    uniseximgpress.setVisibility(View.VISIBLE);
                } else if ("13".equals(third)) {
                    uniseximgpress.setVisibility(View.INVISIBLE);
                }

            }else
            {
                Toast.makeText(fillForm3a.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
            }

//            StudentListView.setVisibility(View.VISIBLE);

//            ListAdapterClass adapter = new ListAdapterClass(studentList, context);

//            StudentListView.setAdapter(adapter);
        }
    }


    public void GetUpdatefillform3a(View v){
        if(first == null)
        {
            first = "11";
        }
        if(second == null)
        {
            second = "12";
        }
        if (third == null)
        {
            third = "13";
        }
        InsertData(IDHolder,first,second,third);

        Intent intent = new Intent(fillForm3a.this, fillForm4.class);
        intent.putExtra("email",TempEmailStore);
        intent.putExtra("idholder",IDHolder);
        intent.putExtra("id", id1);
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

                Toast.makeText(fillForm3a.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(salon_id);
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(fillForm3a.this, fillForm2.class);
        this.overridePendingTransition(R.anim.animation_leave,
                R.anim.animation_enter);
        intent.putExtra("id", id2);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);
        finish();

    }
}