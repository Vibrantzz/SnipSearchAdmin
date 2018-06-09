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

public class fillForm4 extends AppCompatActivity {

    String ServerURL = "http://test.epoqueapparels.com/Salon/Salon_App/indexsalonmaster.php" ;
    String HttpURLForHours = "http://test.epoqueapparels.com/CRUD/getSalonAdminEditFillForm4.php";
    TextView already_user;
    EditText name, email, password;
    ImageView go;
    HttpParse httpParse = new HttpParse();
    String ParseResult ;
    String FinalJSonObject ;
    HashMap<String,String> ResultHash = new HashMap<>();
    String data7,data8,data9;
    String id2 = "1";
    String id1 = "0";
    String id, TempEmailStore, IDHolder, first, second, third, fourth;
    ImageView hairimg, hairimgpress, beautyimg, beautyimgpress, spaimg, spaimgpress, acadmeyimg, acadmeyimgpress;
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
        setContentView(R.layout.fillform4);


        hairimg = (ImageView) findViewById(R.id.hairimg);
        hairimgpress = (ImageView) findViewById(R.id.hairimgpress);
        beautyimg = (ImageView) findViewById(R.id.beautyimg);
        beautyimgpress = (ImageView) findViewById(R.id.beautyimgpress);
        spaimg = (ImageView) findViewById(R.id.spaimg);
        spaimgpress = (ImageView) findViewById(R.id.spaimgpress);
        acadmeyimg = (ImageView) findViewById(R.id.acadmeyimg);
        acadmeyimgpress = (ImageView) findViewById(R.id.acadmeyimgpress);
        go = (ImageView) findViewById(R.id.go);
        //final String token = TokenManager.getInstance(this).getDeviceToken();
        /*go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fillForm4.this, fillForm5.class);
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

        HttpWebCallForSalon(IDHolder);
        // -------------------validations-----------------

        //----------------------------------------------------------------------------------

        hairimg.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = hairimgpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    hairimgpress.setVisibility(View.GONE);
                    first = "11";
                }
                else
                {
                    hairimgpress.setVisibility(View.VISIBLE);
                    first = "1";
                }
            }
        });

        beautyimg.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = beautyimgpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    beautyimgpress.setVisibility(View.GONE);
                    second = "12";
                }
                else
                {
                    beautyimgpress.setVisibility(View.VISIBLE);
                    second = "2";
                }
            }
        });

        spaimg.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = spaimgpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    spaimgpress.setVisibility(View.GONE);
                    third = "13";
                }
                else
                {
                    spaimgpress.setVisibility(View.VISIBLE);
                    third = "3";
                }
            }
        });

        acadmeyimg.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = acadmeyimgpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    acadmeyimgpress.setVisibility(View.GONE);
                    fourth = "14";
                }
                else
                {
                    acadmeyimgpress.setVisibility(View.VISIBLE);
                    fourth = "4";
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
                new GetHttpResponseForSR(fillForm4.this).execute();

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

                            JSONObject jsonObject, jsonObject1, jsonObject2, jsonObject3;

                            jsonObject = jsonArray.getJSONObject(0);
                            jsonObject1 = jsonArray.getJSONObject(1);
                            jsonObject2 = jsonArray.getJSONObject(2);
                            jsonObject3 = jsonArray.getJSONObject(3);


                            first = jsonObject.getString("type_id").toString();
                            second = jsonObject1.getString("type_id").toString();
                            third = jsonObject2.getString("type_id").toString();
                            fourth = jsonObject3.getString("type_id").toString();


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
                        Toast.makeText(fillForm4.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(fillForm4.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
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
                    hairimgpress.setVisibility(View.VISIBLE);
                } else if ("11".equals(first)) {
                    hairimgpress.setVisibility(View.INVISIBLE);
                }
                if ("2".equals(second)) {
                    beautyimgpress.setVisibility(View.VISIBLE);
                } else if ("12".equals(second)) {
                    beautyimgpress.setVisibility(View.INVISIBLE);
                }
                if ("3".equals(third)) {
                    spaimgpress.setVisibility(View.VISIBLE);
                } else if ("13".equals(third)) {
                    spaimgpress.setVisibility(View.INVISIBLE);
                }
                if ("4".equals(fourth)) {
                    acadmeyimgpress.setVisibility(View.VISIBLE);
                } else if ("14".equals(fourth)) {
                    acadmeyimgpress.setVisibility(View.INVISIBLE);
                }

            }else
            {
                Toast.makeText(fillForm4.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
            }

//            StudentListView.setVisibility(View.VISIBLE);

//            ListAdapterClass adapter = new ListAdapterClass(studentList, context);

//            StudentListView.setAdapter(adapter);
        }
    }

    public void GetUpdatefillform4(View v){
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
        if (fourth == null)
        {
            fourth = "14";
        }

        InsertData(IDHolder,first,second,third,fourth);

        Intent intent = new Intent(fillForm4.this, fillForm5.class);
        intent.putExtra("email",TempEmailStore);
        intent.putExtra("idholder",IDHolder);
        intent.putExtra("id", id1);
        startActivity(intent);
        finish();
    }

    public void InsertData(final String salon_id,final String first,final String second,final String third,final String fourth) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String NameHolder = salon_id ;
                String NameHolder1 = first ;
                String NameHolder2 = second ;
                String NameHolder3 = third ;
                String NameHolder4 = fourth ;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("salon_id", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("type_id", NameHolder1));
                nameValuePairs.add(new BasicNameValuePair("type_id1", NameHolder2));
                nameValuePairs.add(new BasicNameValuePair("type_id2", NameHolder3));
                nameValuePairs.add(new BasicNameValuePair("type_id3", NameHolder4));



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

                Toast.makeText(fillForm4.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(salon_id);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(fillForm4.this, fillForm3a.class);
        this.overridePendingTransition(R.anim.animation_leave,
                R.anim.animation_enter);
        intent.putExtra("id", id2);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);
        finish();

    }
}