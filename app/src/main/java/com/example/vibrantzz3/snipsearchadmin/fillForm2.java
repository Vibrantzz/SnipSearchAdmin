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

public class fillForm2 extends AppCompatActivity {

    String ServerURL = "http://test.epoqueapparels.com/Salon/Salon_App/indexsalonmaster.php" ;
    String HttpURLForHours = "http://test.epoqueapparels.com/CRUD/getSalonAdminEditFillForm1.php";
    TextView already_user;
    String id, IDHolder;
    String FinalJSonObject ;
    String ParseResult;
    HashMap<String,String> ResultHash = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String id2 = "1";
    String id1 = "0";
    String CountryHolder, AddressHolder, CityHolder, PinCodeHolder, StateHolder;
    String TempCountry, TempAddress, TempCity, TempPincode, TempState, TempEmailStore;
    EditText country, address, city, pincode, state;
    ImageView go;
    ImageView btn_sign_up;
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
        setContentView(R.layout.fillform2);


        country = (EditText) findViewById(R.id.input_country);
        address = (EditText) findViewById(R.id.input_address);
        city = (EditText) findViewById(R.id.input_city);
        pincode = (EditText) findViewById(R.id.input_pincode);
        state = (EditText) findViewById(R.id.input_state);

        go = (ImageView) findViewById(R.id.go);
        //final String token = TokenManager.getInstance(this).getDeviceToken();
        /*go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fillForm2.this, fillForm3.class);
                intent.putExtra("id", id1);
                startActivity(intent);
                finish();

            }
        });*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.backarrow5);
        //toolbar.setTitle("Please fill your address details");
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
                new GetHttpResponseForSR(fillForm2.this).execute();

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
                                CountryHolder = jsonObject.getString("country").toString() ;
                                AddressHolder = jsonObject.getString("address").toString() ;
                                CityHolder = jsonObject.getString("city").toString() ;
                                PinCodeHolder = jsonObject.getString("pincode").toString() ;
                                StateHolder = jsonObject.getString("state").toString() ;


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

            country.setText(CountryHolder);
            address.setText(AddressHolder);
            city.setText(CityHolder);
            pincode.setText(PinCodeHolder);
            state.setText(StateHolder);
        }
    }

    public void GetUpdateFillForm2(View view){
        //TempName = name.getText().toString();
        TempCountry = country.getText().toString();
        TempAddress = address.getText().toString();
        TempCity = city.getText().toString();
        TempPincode = pincode.getText().toString();
        TempState = state.getText().toString();

        Intent intent = new Intent(fillForm2.this, fillForm3a.class);
        intent.putExtra("email",TempEmailStore);
        intent.putExtra("idholder",IDHolder);
        intent.putExtra("id", id1);
        startActivity(intent);
        finish();


        InsertData(TempCountry, TempAddress, TempCity, TempPincode, TempState, TempEmailStore);//, TempOwner, TempContact, TempPassword, TempEmail);

    }

    public void InsertData(final String country, final String address, final String city, final String pincode, final String state, final String email) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String NameHolder = country ;
                String NameHolder1 = address ;
                String NameHolder2 = city ;
                String NameHolder3 = pincode ;
                String NameHolder4 = state ;
                String NameHolder5 = email ;





                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("country", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("address", NameHolder1));
                nameValuePairs.add(new BasicNameValuePair("city", NameHolder2));
                nameValuePairs.add(new BasicNameValuePair("pincode", NameHolder3));
                nameValuePairs.add(new BasicNameValuePair("state", NameHolder4));
                nameValuePairs.add(new BasicNameValuePair("email", NameHolder5));



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

                Toast.makeText(fillForm2.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

                /*Intent intent = new Intent(fillForm2.this, fillForm3.class);
                intent.putExtra("email",TempEmailStore);
                intent.putExtra("id", id1);
                startActivity(intent);
                finish();*/

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(country);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(fillForm2.this, fillForm1.class);
        intent.putExtra("id", id2);
        intent.putExtra("idholder", IDHolder);
        this.overridePendingTransition(R.anim.animation_leave,
                R.anim.animation_enter);
        startActivity(intent);
        finish();

    }
}