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

public class fillForm5a extends AppCompatActivity {

    String ServerURL = "http://test.epoqueapparels.com/Salon/Salon_App/indexsalonmaster.php" ;
    String HttpURLForHours = "http://test.epoqueapparels.com/CRUD/getSalonAdminEditFillForm5a.php";
    TextView already_user;
    EditText name, email, password;
    ImageView go;
    HttpParse httpParse = new HttpParse();
    String ParseResult ;
    String FinalJSonObject ;
    HashMap<String,String> ResultHash = new HashMap<>();
    String id2 = "1";
    String id1 = "0";
    String id, first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, TempEmailStore, IDHolder;
    ImageView parking,parkingpress,kidsfriendly,kidsfriendlypress,wifiavailability,wifiavailabilitypress,ac,acpress,appointment,appointmentpress;
    //ImageView bridal, bridalpress, mensgroom, mensgroompress, sareedraping, sareedrapingpress, homeservice , homeservicepress, makeup , makeuppress, skintreatment, skintreatmentpress, tanning, tanningpress, ayurvedic, ayurvedicpress, nailart, nailartpress;
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
        setContentView(R.layout.fillform5a);


        parking = (ImageView) findViewById(R.id.hairimg);
        parkingpress = (ImageView) findViewById(R.id.hairimgpress);
        kidsfriendly = (ImageView) findViewById(R.id.beautyimg);
        kidsfriendlypress = (ImageView) findViewById(R.id.beautyimgpress);
        wifiavailability = (ImageView) findViewById(R.id.spaimg);
        wifiavailabilitypress = (ImageView) findViewById(R.id.spaimgpress);
        ac = (ImageView) findViewById(R.id.acadmeyimg);
        acpress = (ImageView) findViewById(R.id.acadmeyimgpress);
        appointment = (ImageView) findViewById(R.id.apptimg);
        appointmentpress = (ImageView) findViewById(R.id.apptimgpress);

        /*waxing = (ImageView) findViewById(R.id.waxing);
        waxingpress = (ImageView) findViewById(R.id.waxingpress);
        threading = (ImageView) findViewById(R.id.threading);
        threadingpress = (ImageView) findViewById(R.id.threadingpress);
        shave = (ImageView) findViewById(R.id.shave);
        shavepress = (ImageView) findViewById(R.id.shavepress);*/
        go = (ImageView) findViewById(R.id.go);
        //final String token = TokenManager.getInstance(this).getDeviceToken();
        /*go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fillForm5.this, fillForm6.class);
                intent.putExtra("id", id1);
                startActivity(intent);
                finish();

            }
        });*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.backarrow5);
//        toolbar.setTitle("Select your categories. (Select  multiple)");
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

        parking.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = parkingpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    parkingpress.setVisibility(View.GONE);
                    first = "0";
                }
                else
                {
                    parkingpress.setVisibility(View.VISIBLE);
                    first = "1";
                }
            }
        });

        kidsfriendly.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = kidsfriendlypress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    kidsfriendlypress.setVisibility(View.GONE);
                    second ="0";
                }
                else
                {
                    kidsfriendlypress.setVisibility(View.VISIBLE);
                    second = "1";
                }
            }
        });

        wifiavailability.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = wifiavailabilitypress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    wifiavailabilitypress.setVisibility(View.GONE);
                    third = "0";
                }
                else
                {
                    wifiavailabilitypress.setVisibility(View.VISIBLE);
                    third = "1";
                }
            }
        });

        ac.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = acpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    acpress.setVisibility(View.GONE);
                    fourth = "0";
                }
                else
                {
                    acpress.setVisibility(View.VISIBLE);
                    fourth = "1";
                }
            }
        });

        appointment.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = appointmentpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    appointmentpress.setVisibility(View.GONE);
                    fifth = "0";
                }
                else
                {
                    appointmentpress.setVisibility(View.VISIBLE);
                    fifth = "1";
                }
            }
        });


        /*waxing.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = waxingpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    waxingpress.setVisibility(View.GONE);
                }
                else
                {
                    waxingpress.setVisibility(View.VISIBLE);
                }
            }
        });

        threading.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = threadingpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    threadingpress.setVisibility(View.GONE);
                }
                else
                {
                    threadingpress.setVisibility(View.VISIBLE);
                }
            }
        });

        shave.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = shavepress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    shavepress.setVisibility(View.GONE);
                }
                else
                {
                    shavepress.setVisibility(View.VISIBLE);
                }
            }
        });*/
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
                new GetHttpResponseForSR(fillForm5a.this).execute();

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

                        JSONObject jsonObject;

                        for(int i=0; i<jsonArray.length(); i++)
                        {

                            jsonObject = jsonArray.getJSONObject(i);

                            //Adding Student Name.
                            first = jsonObject.getString("is_parking").toString();
                            second = jsonObject.getString("is_kidfriendly").toString();
                            third = jsonObject.getString("is_wifi").toString();
                            fourth = jsonObject.getString("is_ac").toString();
                            fifth = jsonObject.getString("is_appt").toString();


                        }


                    }
                    catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(fillForm5a.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(fillForm5a.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
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
                parkingpress.setVisibility(View.VISIBLE);
            }else if("11".equals(first)){
                parkingpress.setVisibility(View.INVISIBLE);
            }
            if ("1".equals(second)) {
                kidsfriendlypress.setVisibility(View.VISIBLE);
            }else if("12".equals(second)){
                kidsfriendlypress.setVisibility(View.INVISIBLE);
            }
            if ("1".equals(third)) {
                wifiavailabilitypress.setVisibility(View.VISIBLE);
            }else if("13".equals(third)){
                wifiavailabilitypress.setVisibility(View.INVISIBLE);
            }
            if ("1".equals(fourth)) {
                acpress.setVisibility(View.VISIBLE);
            }else if("14".equals(fourth)){
                acpress.setVisibility(View.INVISIBLE);
            }
            if ("1".equals(fifth)) {
                appointmentpress.setVisibility(View.VISIBLE);
            }else if("15".equals(fifth)){
                appointmentpress.setVisibility(View.INVISIBLE);
            }

        }else
        {
            Toast.makeText(fillForm5a.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
        }

//            StudentListView.setVisibility(View.VISIBLE);

//            ListAdapterClass adapter = new ListAdapterClass(studentList, context);

//            StudentListView.setAdapter(adapter);
    }
}

    public void GetUpdatefillform5a(View v){
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
        if (fifth == null)
        {
            fifth = "15";
        }

        InsertData(IDHolder,first,second,third,fourth,fifth);

        Intent intent = new Intent(fillForm5a.this, fillForm6.class);
        intent.putExtra("email",TempEmailStore);
        intent.putExtra("idholder",IDHolder);
        intent.putExtra("id", id1);
        startActivity(intent);
        finish();
    }

    public void InsertData(final String id,final String first,final String second,final String third,final String fourth,final String fifth) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String NameHolder = id ;
                String NameHolder1 = first ;
                String NameHolder2 = second ;
                String NameHolder3 = third ;
                String NameHolder4 = fourth ;
                String NameHolder5 = fifth ;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("salon_id", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("is_parking", NameHolder1));
                nameValuePairs.add(new BasicNameValuePair("is_kidfriendly", NameHolder2));
                nameValuePairs.add(new BasicNameValuePair("is_wifi", NameHolder3));
                nameValuePairs.add(new BasicNameValuePair("is_ac", NameHolder4));
                nameValuePairs.add(new BasicNameValuePair("is_appt", NameHolder5));



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

                Toast.makeText(fillForm5a.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(id);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(fillForm5a.this, fillForm5.class);
        this.overridePendingTransition(R.anim.animation_leave,
                R.anim.animation_enter);
        intent.putExtra("id", id2);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);
        finish();

    }
}