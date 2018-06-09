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

public class editForm5 extends AppCompatActivity {

    String ServerURL = "http://test.epoqueapparels.com/Salon/Salon_App/editindexsalonmaster.php" ;
    String HttpURLForHours = "http://test.epoqueapparels.com/CRUD/getSalonAdminEditFillForm5.php";
    TextView already_user;
    String TempFirst, TempSecond, TempThird, TempFourth,TempFifth, TempSixth, TempSeventh, TempEighth, TempNineth;
    EditText name, email, password;
    ImageView go;
    String id2 = "1";
    String id1 = "0";
    String FinalJSonObject ;
    HashMap<String,String> ResultHash = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String ParseResult;
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

        //to get in full screen
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.editform5);


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
        /*go = (ImageView) findViewById(R.id.go);
        //final String token = TokenManager.getInstance(this).getDeviceToken();
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editForm5.this, fillForm6.class);
                intent.putExtra("id", id1);
                startActivity(intent);
                finish();

            }
        });*/

        Intent intent = getIntent();
        IDHolder = intent.getExtras().getString("idholder");

        HttpWebCallForSalon(IDHolder);

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
                    TempFirst = "0";
                }
                else
                {
                    bridalpress.setVisibility(View.VISIBLE);
                    TempFirst = "1";
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
                    TempSecond ="0";
                }
                else
                {
                    mensgroompress.setVisibility(View.VISIBLE);
                    TempSecond = "2";
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
                    TempThird = "0";
                }
                else
                {
                    nailartpress.setVisibility(View.VISIBLE);
                    TempThird = "3";
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
                    TempFourth = "0";
                }
                else
                {
                    sareedrapingpress.setVisibility(View.VISIBLE);
                    TempFourth = "4";
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
                    TempFifth = "0";
                }
                else
                {
                    homeservicepress.setVisibility(View.VISIBLE);
                    TempFifth = "5";
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
                    TempSixth = "0";
                }
                else
                {
                    makeuppress.setVisibility(View.VISIBLE);
                    TempSixth = "6";
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
                    TempSeventh = "0";
                }
                else
                {
                    skintreatmentpress.setVisibility(View.VISIBLE);
                    TempSeventh = "7";
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
                    TempEighth = "0";
                }
                else
                {
                    tanningpress.setVisibility(View.VISIBLE);
                    TempEighth = "8";
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
                    TempNineth = "0";
                }
                else
                {
                    ayurvedicpress.setVisibility(View.VISIBLE);
                    TempNineth = "9";
                }
            }
        });
    }

    public void GetEditfillform5(View v){

        InsertData(IDHolder,TempFirst,TempSecond,TempThird,TempFourth,TempFifth,TempSixth,TempSeventh,TempEighth,TempNineth);

        Intent intent = new Intent(editForm5.this, Settings.class);
        intent.putExtra("idholder",IDHolder);
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

                Toast.makeText(editForm5.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(id);
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
                new GetHttpResponseForSR(editForm5.this).execute();

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

                            TempFirst = jsonObject.getString("spec_id").toString();
                            TempSecond = jsonObject1.getString("spec_id").toString();
                            TempThird = jsonObject2.getString("spec_id").toString();
                            TempFourth = jsonObject3.getString("spec_id").toString();
                            TempFifth = jsonObject4.getString("spec_id").toString();
                            TempSixth = jsonObject5.getString("spec_id").toString();
                            TempSeventh = jsonObject6.getString("spec_id").toString();
                            TempEighth = jsonObject7.getString("spec_id").toString();
                            TempNineth = jsonObject8.getString("spec_id").toString();




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
                bridalpress.setVisibility(View.VISIBLE);
            }else if("11".equals(TempFirst)){
                bridalpress.setVisibility(View.INVISIBLE);
            }
            if ("2".equals(TempSecond)) {
                mensgroompress.setVisibility(View.VISIBLE);
            }else if("12".equals(TempSecond)){
                mensgroompress.setVisibility(View.INVISIBLE);
            }
            if ("3".equals(TempThird)) {
                nailartpress.setVisibility(View.VISIBLE);
            }else if("13".equals(TempThird)){
                nailartpress.setVisibility(View.INVISIBLE);
            }
            if ("4".equals(TempFourth)) {
                sareedrapingpress.setVisibility(View.VISIBLE);
            }else if("14".equals(TempFourth)){
                sareedrapingpress.setVisibility(View.INVISIBLE);
            }
            if ("5".equals(TempFifth)) {
                homeservicepress.setVisibility(View.VISIBLE);
            }else if("15".equals(TempFifth)){
                homeservicepress.setVisibility(View.INVISIBLE);
            }
            if ("6".equals(TempSixth)) {
                makeuppress.setVisibility(View.VISIBLE);
            }else if("16".equals(TempSixth)){
                makeuppress.setVisibility(View.INVISIBLE);
            }
            if ("7".equals(TempSeventh)) {
                skintreatmentpress.setVisibility(View.VISIBLE);
            }else if("17".equals(TempSeventh)){
                skintreatmentpress.setVisibility(View.INVISIBLE);
            }
            if ("8".equals(TempEighth)) {
                tanningpress.setVisibility(View.VISIBLE);
            }else if("18".equals(TempEighth)){
                tanningpress.setVisibility(View.INVISIBLE);
            }
            if ("9".equals(TempNineth)) {
                ayurvedicpress.setVisibility(View.VISIBLE);
            }else if("19".equals(TempNineth)){
                ayurvedicpress.setVisibility(View.INVISIBLE);
            }

//            StudentListView.setVisibility(View.VISIBLE);

//            ListAdapterClass adapter = new ListAdapterClass(studentList, context);

//            StudentListView.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(editForm5.this, Settings.class);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);
        finish();

    }
}