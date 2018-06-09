package com.example.vibrantzz3.snipsearchadmin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AppointmentDetaileView extends AppCompatActivity {

    String HttpURL = "http://test.epoqueapparels.com/CRUD/FilterAppointmentData.php";
    HttpParse httpParse = new HttpParse();
    ProgressDialog pDialog;
    String NameHolder;
    String FinalJSonObject ;
    TextView username;
    String requestValue;
    ImageView logo;
    String ParseResult ;
    HashMap<String,String> ResultHash = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_detaile_view);

        Intent intent = getIntent();
        requestValue = intent.getExtras().getString("idholder");


        username = (TextView) findViewById(R.id.username);
        logo = (ImageView) findViewById(R.id.logo);

        HttpWebCall(requestValue);
    }

    //Method to show current record Current Selected Record
    public void HttpWebCall(final String PreviousListViewClickedItem){

        class HttpWebCallFunction extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(AppointmentDetaileView.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;

                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponse(AppointmentDetaileView.this).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("idholder",params[0]);

                ParseResult = httpParse.postRequest(ResultHash, HttpURL);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }

    // Parsing Complete JSON Object.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        public GetHttpResponse(Context context)
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
            try
            {
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
                            /*NumberHolder = jsonObject.getString("email").toString() ;
                            ClassHolder = jsonObject.getString("password").toString() ;
                            IsActive = jsonObject.getString("isactive").toString() ;*/


                        }
                    }
                    catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
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

            // Setting Student Name, Phone Number, Class into TextView after done all process .
            username.setText(NameHolder);
            /*PHONE_NUMBER.setText(NumberHolder);
            CLASS.setText(ClassHolder);
            ISACTIVE.setText(IsActive);*/

            /*if (tmpStr10.equals(IsActive)){
                ActiveUser.setVisibility(View.VISIBLE);
                DeleteButton.setVisibility(View.INVISIBLE);
                ISSTATUS.setText("(Deactivated)");
                return;
            }else{
                ISSTATUS.setText("(Activated)");
                DeleteButton.setVisibility(View.VISIBLE);
            }*/
        }
    }

}
