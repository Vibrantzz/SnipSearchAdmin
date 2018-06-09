package com.example.vibrantzz3.snipsearchadmin;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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

import com.squareup.picasso.Picasso;

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
import java.util.jar.Attributes;

public class editForm1 extends AppCompatActivity {

    TextView already_user;
    String NameHolder, ImageHolder, ContactHolder, EmailHolder;
    //EditText name, email, password;
    ImageView go, logo;
    String FinalJSonObject ;
    String ParseResult;
    HashMap<String,String> ResultHash = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String id1 = "0", IDHolder;
    ImageView btn_sign_up;
    EditText businessname, contactno, email;
    //--for user register---
    protected String enteredUsername, enteredPassword, enteredEmail;
    String ServerURL = "http://test.epoqueapparels.com/Salon/Salon_App/editindexsalonmaster.php" ;
    String HttpURLForHours = "http://test.epoqueapparels.com/CRUD/getSalonAdminEditFillForm1.php";
    private final String serverUrl = "http://test.epoqueapparels.com/Salon/Salon_App/index1.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide title bar
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        Intent intent = getIntent();
        //to get in full screen
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.editform1);


       /* go = (ImageView) findViewById(R.id.go);*/
        //final String token = TokenManager.getInstance(this).getDeviceToken();

        logo = (ImageView) findViewById(R.id.logo);
        businessname = (EditText)findViewById(R.id.input_businessname);
        contactno = (EditText) findViewById(R.id.input_phonenumber);
        email = (EditText) findViewById(R.id.input_email);

        /*go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editForm1.this, fillForm2.class);
                intent.putExtra("id", id1);
                startActivity(intent);
                finish();

            }
        });*/

        Intent intent = getIntent();
        IDHolder = intent.getExtras().getString("idholder");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.backarrow5);
        //toolbar.setTitle("Please add the details");
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
                new GetHttpResponseForSR(editForm1.this).execute();

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

                            JSONObject jsonObject;

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                jsonObject = jsonArray.getJSONObject(i);

                                // Storing Student Name, Phone Number, Class into Variables.
                                NameHolder = jsonObject.getString("name").toString() ;
                                ImageHolder = jsonObject.getString("profilepic").toString() ;
                                ContactHolder = jsonObject.getString("contactno").toString() ;
                                EmailHolder = jsonObject.getString("email").toString() ;

                            }


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

            businessname.setText(NameHolder);
            contactno.setText(ContactHolder);
            email.setText(EmailHolder);


            if(!"null".equals(ImageHolder)) {
                Picasso
                        .get()
                        .load(ImageHolder)
                        .into(logo);

                    /*Picasso
                            .get()
                            .load(ImageHolder)
                            .into(go);*/

                    /*Picasso
                            .with(context)
                            .load(ImageHolder)
                            .into(img);*/
            }



//            StudentListView.setVisibility(View.VISIBLE);

//            ListAdapterClass adapter = new ListAdapterClass(studentList, context);

//            StudentListView.setAdapter(adapter);
        }
    }

    public void GetEditfillform1(View v){

        InsertData(IDHolder,NameHolder,ContactHolder,EmailHolder);

        Intent intent = new Intent(editForm1.this, Settings.class);
        intent.putExtra("idholder",IDHolder);
        startActivity(intent);
        finish();


    }

    public void InsertData(final String id,final String name,final String contactno,final String email) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String NameHolder = id ;
                String NameHolder1 = name ;
                String NameHolder2 = contactno ;
                String NameHolder3 = email ;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("name", NameHolder1));
                nameValuePairs.add(new BasicNameValuePair("contactno", NameHolder2));
                nameValuePairs.add(new BasicNameValuePair("email", NameHolder3));


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

                Toast.makeText(editForm1.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(id);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(editForm1.this, Settings.class);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);
        finish();

    }
}