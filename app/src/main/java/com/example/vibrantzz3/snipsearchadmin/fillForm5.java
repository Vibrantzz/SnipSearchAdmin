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
import android.widget.Button;
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

public class fillForm5 extends AppCompatActivity {

    String ServerURL = "http://test.epoqueapparels.com/Salon/Salon_App/indexsalonmaster.php" ;
    String HttpURLForHours = "http://test.epoqueapparels.com/CRUD/getSalonAdminEditFillForm5.php";
    TextView already_user;
    HttpParse httpParse = new HttpParse();
    String ParseResult ;
    String FinalJSonObject ;
    HashMap<String,String> ResultHash = new HashMap<>();
    EditText name, email, password;
    ImageView go;
    String id2 = "1";
    String id1 = "0";
    String id, first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, TempEmailStore, IDHolder;
    ImageView bridal, bridalpress, mensgroom, mensgroompress, sareedraping, sareedrapingpress, homeservice , homeservicepress, makeup , makeuppress, skintreatment, skintreatmentpress, tanning, tanningpress, ayurvedic, ayurvedicpress, nailart, nailartpress;
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
        setContentView(R.layout.fillform5);


        bridal = (ImageView) findViewById(R.id.bridal);
        bridalpress = (ImageView) findViewById(R.id.bridalpress);
        mensgroom = (ImageView) findViewById(R.id.haircut);
        mensgroompress = (ImageView) findViewById(R.id.haircutpress);
        nailart = (ImageView) findViewById(R.id.predicure);
        nailartpress = (ImageView) findViewById(R.id.predicurepress);
        sareedraping = (ImageView) findViewById(R.id.manicure);
        sareedrapingpress = (ImageView) findViewById(R.id.manicurepress);
        homeservice = (ImageView) findViewById(R.id.facial);
        homeservicepress = (ImageView) findViewById(R.id.facialpress);
        makeup = (ImageView) findViewById(R.id.footspa);
        makeuppress = (ImageView) findViewById(R.id.footspapress);
        skintreatment = (ImageView) findViewById(R.id.message);
        skintreatmentpress = (ImageView) findViewById(R.id.messagepress);
        tanning = (ImageView) findViewById(R.id.nailart);
        tanningpress = (ImageView) findViewById(R.id.nailartpress);
        ayurvedic = (ImageView) findViewById(R.id.haircolor);
        ayurvedicpress = (ImageView) findViewById(R.id.haircolorpress);
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

        bridal.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = bridalpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    bridalpress.setVisibility(View.GONE);
                    first = "11";
                }
                else
                {
                    bridalpress.setVisibility(View.VISIBLE);
                    first = "1";
                }
            }
        });

        mensgroom.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = mensgroompress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    mensgroompress.setVisibility(View.GONE);
                    second ="12";
                }
                else
                {
                    mensgroompress.setVisibility(View.VISIBLE);
                    second = "2";
                }
            }
        });

        nailart.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = nailartpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    nailartpress.setVisibility(View.GONE);
                    third = "13";
                }
                else
                {
                    nailartpress.setVisibility(View.VISIBLE);
                    third = "3";
                }
            }
        });

        sareedraping.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = sareedrapingpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    sareedrapingpress.setVisibility(View.GONE);
                    fourth = "14";
                }
                else
                {
                    sareedrapingpress.setVisibility(View.VISIBLE);
                    fourth = "4";
                }
            }
        });

        homeservice.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = homeservicepress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    homeservicepress.setVisibility(View.GONE);
                    fifth = "15";
                }
                else
                {
                    homeservicepress.setVisibility(View.VISIBLE);
                    fifth = "5";
                }
            }
        });

        makeup.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = makeuppress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    makeuppress.setVisibility(View.GONE);
                    sixth = "16";
                }
                else
                {
                    makeuppress.setVisibility(View.VISIBLE);
                    sixth = "6";
                }
            }
        });

        skintreatment.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = skintreatmentpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    skintreatmentpress.setVisibility(View.GONE);
                    seventh = "17";
                }
                else
                {
                    skintreatmentpress.setVisibility(View.VISIBLE);
                    seventh = "7";
                }
            }
        });

        tanning.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = tanningpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    tanningpress.setVisibility(View.GONE);
                    eighth = "18";
                }
                else
                {
                    tanningpress.setVisibility(View.VISIBLE);
                    eighth = "8";
                }
            }
        });

        ayurvedic.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = ayurvedicpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    ayurvedicpress.setVisibility(View.GONE);
                    ninth = "19";
                }
                else
                {
                    ayurvedicpress.setVisibility(View.VISIBLE);
                    ninth = "9";
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
                new GetHttpResponseForSR(fillForm5.this).execute();

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

                            JSONObject jsonObject, jsonObject1, jsonObject2, jsonObject3,jsonObject4,jsonObject5,jsonObject6,jsonObject7,jsonObject8;

                            jsonObject = jsonArray.getJSONObject(0);
                            jsonObject1 = jsonArray.getJSONObject(1);
                            jsonObject2 = jsonArray.getJSONObject(2);
                            jsonObject3 = jsonArray.getJSONObject(3);
                            jsonObject4 = jsonArray.getJSONObject(4);
                            jsonObject5 = jsonArray.getJSONObject(5);
                            jsonObject6 = jsonArray.getJSONObject(6);
                            jsonObject7 = jsonArray.getJSONObject(7);
                            jsonObject8 = jsonArray.getJSONObject(8);

                            first = jsonObject.getString("spec_id").toString();
                            second = jsonObject1.getString("spec_id").toString();
                            third = jsonObject2.getString("spec_id").toString();
                            fourth = jsonObject3.getString("spec_id").toString();
                            fifth = jsonObject4.getString("spec_id").toString();
                            sixth = jsonObject5.getString("spec_id").toString();
                            seventh = jsonObject6.getString("spec_id").toString();
                            eighth = jsonObject7.getString("spec_id").toString();
                            ninth = jsonObject8.getString("spec_id").toString();


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
                        Toast.makeText(fillForm5.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(fillForm5.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
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
                    bridalpress.setVisibility(View.VISIBLE);
                }else if("11".equals(first)){
                    bridalpress.setVisibility(View.INVISIBLE);
                }
                if ("2".equals(second)) {
                    mensgroompress.setVisibility(View.VISIBLE);
                }else if("12".equals(second)){
                    mensgroompress.setVisibility(View.INVISIBLE);
                }
                if ("3".equals(third)) {
                    nailartpress.setVisibility(View.VISIBLE);
                }else if("13".equals(third)){
                    nailartpress.setVisibility(View.INVISIBLE);
                }
                if ("4".equals(fourth)) {
                    sareedrapingpress.setVisibility(View.VISIBLE);
                }else if("14".equals(fourth)){
                    sareedrapingpress.setVisibility(View.INVISIBLE);
                }
                if ("5".equals(fifth)) {
                    homeservicepress.setVisibility(View.VISIBLE);
                }else if("15".equals(fifth)){
                    homeservicepress.setVisibility(View.INVISIBLE);
                }
                if ("6".equals(sixth)) {
                    makeuppress.setVisibility(View.VISIBLE);
                }else if("16".equals(sixth)){
                    makeuppress.setVisibility(View.INVISIBLE);
                }
                if ("7".equals(seventh)) {
                    skintreatmentpress.setVisibility(View.VISIBLE);
                }else if("17".equals(seventh)){
                    skintreatmentpress.setVisibility(View.INVISIBLE);
                }
                if ("8".equals(eighth)) {
                    tanningpress.setVisibility(View.VISIBLE);
                }else if("18".equals(eighth)){
                    tanningpress.setVisibility(View.INVISIBLE);
                }
                if ("9".equals(ninth)) {
                    ayurvedicpress.setVisibility(View.VISIBLE);
                }else if("19".equals(ninth)){
                    ayurvedicpress.setVisibility(View.INVISIBLE);
                }

            }else
            {
                Toast.makeText(fillForm5.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
            }

//            StudentListView.setVisibility(View.VISIBLE);

//            ListAdapterClass adapter = new ListAdapterClass(studentList, context);

//            StudentListView.setAdapter(adapter);
        }
    }

    public void GetUpdatefillform5(View v){
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
        if (sixth == null)
        {
            sixth = "16";
        }
        if (seventh == null)
        {
            seventh = "17";
        }
        if (eighth == null)
        {
            eighth = "18";
        }
        if (ninth == null)
        {
            ninth = "19";
        }

        InsertData(IDHolder,first,second,third,fourth,fifth,sixth,seventh,eighth,ninth);

        Intent intent = new Intent(fillForm5.this, fillForm5a.class);
        intent.putExtra("email",TempEmailStore);
        intent.putExtra("idholder",IDHolder);
        intent.putExtra("id", id1);
        startActivity(intent);
        finish();
    }

    public void InsertData(final String id,final String first,final String second,final String third,final String fourth,final String fifth,final String sixth,final String seventh,final String eighth,final String ninth) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String NameHolder = id ;
                String NameHolder1 = first ;
                String NameHolder2 = second ;
                String NameHolder3 = third ;
                String NameHolder4 = fourth ;
                String NameHolder5 = fifth ;
                String NameHolder6 = sixth ;
                String NameHolder7 = seventh ;
                String NameHolder8 = eighth ;
                String NameHolder9 = ninth ;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("salon_id", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("spec_id", NameHolder1));
                nameValuePairs.add(new BasicNameValuePair("spec_id1", NameHolder2));
                nameValuePairs.add(new BasicNameValuePair("spec_id2", NameHolder3));
                nameValuePairs.add(new BasicNameValuePair("spec_id3", NameHolder4));
                nameValuePairs.add(new BasicNameValuePair("spec_id4", NameHolder5));
                nameValuePairs.add(new BasicNameValuePair("spec_id5", NameHolder6));
                nameValuePairs.add(new BasicNameValuePair("spec_id6", NameHolder7));
                nameValuePairs.add(new BasicNameValuePair("spec_id7", NameHolder8));
                nameValuePairs.add(new BasicNameValuePair("spec_id8", NameHolder9));



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

                Toast.makeText(fillForm5.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(id);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(fillForm5.this, fillForm4.class);
        this.overridePendingTransition(R.anim.animation_leave,
                R.anim.animation_enter);
        intent.putExtra("id", id2);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);
        finish();

    }
}