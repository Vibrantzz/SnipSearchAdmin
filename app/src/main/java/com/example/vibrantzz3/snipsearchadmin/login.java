package com.example.vibrantzz3.snipsearchadmin;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//-----------new imports----------

public class login extends AppCompatActivity {
    final String TAG = "LoginActivity";
    TextView new_user;
    String id1= "0";
    int temp = 1;
    ImageView login;
    HttpParse httpParse = new HttpParse();
    String ParseResult ;
    String FinalJSonObject ;
    HashMap<String,String> ResultHash = new HashMap<>();
    EditText email , password;
    TextView Register;
    //---------------------------
    protected String enteredUsername, enteredPassword, oberservedActiveUser,token;
    String HttpURLForHours = "http://test.epoqueapparels.com/CRUD/getSalonAdminEditFillForm1.php";
    private final String serverUrl = "http://test.epoqueapparels.com/Salon/Salon_App/index.php";
    private static final String url_token = "http://test.epoqueapparels.com/Salon/Salon_App/salon_tokenupdate.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_TOKEN = "token";
    int success;
    String IDHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide title bar
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //to get in full screen
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //new_user =(TextView) findViewById(R.id.textView_No_acc);
        Register = (TextView) findViewById(R.id.txtsignup);
        login=(ImageView) findViewById(R.id.btnlogin);
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        //token = TokenManager.getInstance(this).getDeviceToken();

        /*email.setText(token);
        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalonLogin.this , Enquiry.class);
                startActivity(intent);
                finish();
            }
        });


*/
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signUp.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HttpWebCallForSalon(enteredUsername);

                enteredUsername = email.getText().toString();
                enteredPassword = password.getText().toString();

                //-----validations---------
                enteredUsername = email.getText().toString();
                if (TextUtils.isEmpty(enteredUsername)) {
                    email.setError("Invalid Email");
                }
                if(!isValidEmail(enteredUsername))
                {
                    email.setError("Invalid Email");
                }

                enteredPassword = password.getText().toString();
                if (!isValidPassword(enteredPassword)) {
                    password.setError("Invalid Password");
                }
                if(!isValidLogin(oberservedActiveUser)){
                    password.setError("User Terminated");
                }
                HashMap<String, String> loginData = new HashMap<>();
                loginData.put("email", enteredUsername);
                loginData.put("password", enteredPassword);
                loginData.put("isactive", oberservedActiveUser);

                // request authentication with remote server4
                AsyncDataClass asyncRequestObject = new AsyncDataClass();
                asyncRequestObject.execute(serverUrl, enteredUsername, enteredPassword, oberservedActiveUser);
                new UpdateAsyncTask().execute();
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
                new GetHttpResponseForSR(login.this).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("email",params[0]);

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

                                // Storing Student Name, Phone Number, Class into Variables.
                                IDHolder = jsonObject.getString("id").toString() ;

                            }



                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(login.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(login.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
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
//
        }
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    // validating password with active user
    private boolean isValidLogin(String isactiveuser) {
        String a = Integer.toString(temp);
        if (isactiveuser != a) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(login.this, MainActivity.class);
        /*intent.putExtra("userName", updateUserName);
        intent.putExtra("userEmail", email);
        intent.putExtra("personCity", updateUserLocation);*/
        startActivity(intent);
        finish();
    }

    //---------------------------------------------
    private class AsyncDataClass extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
            HttpConnectionParams.setSoTimeout(httpParameters, 5000);
            HttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpPost httpPost = new HttpPost(params[0]);
            String jsonResult = "";

            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
                nameValuePairs.add(new BasicNameValuePair("email", params[1]));
                nameValuePairs.add(new BasicNameValuePair("password", params[2]));
                nameValuePairs.add(new BasicNameValuePair("isactive", params[3]));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();

            } catch (ClientProtocolException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            if("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\"> <head> <title>IIS 8.5 Detailed Error - 503.0 - Service Temporarily Unavailable</title> <style type=\"text/css\"> <!-- body{margin:0;font-size:.7em;font-family:Verdana,Arial,Helvetica,sans-serif;} code{margin:0;color:#006600;font-size:1.1em;font-weight:bold;} .config_source code{font-size:.8em;color:#000000;} pre{margin:0;font-size:1.4em;word-wrap:break-word;} ul,ol{margin:10px 0 10px 5px;} ul.first,ol.first{margin-top:5px;} fieldset{padding:0 15px 10px 15px;word-break:break-all;} .summary-container fieldset{padding-bottom:5px;margin-top:4px;} legend.no-expand-all{padding:2px 15px 4px 10px;margin:0 0 0 -12px;} legend{color:#333333;;margin:4px 0 8px -12px;_margin-top:0px; font-weight:bold;font-size:1em;} a:link,a:visited{color:#007EFF;font-weight:bold;} a:hover{text-decoration:none;} h1{font-size:2.4em;margin:0;color:#FFF;} h2{font-size:1.7em;margin:0;color:#CC0000;} h3{font-size:1.4em;margin:10px 0 0 0;color:#CC0000;} h4{font-size:1.2em;margin:10px 0 5px 0; }#header{width:96%;margin:0 0 0 0;padding:6px 2% 6px 2%;font-family:\"trebuchet MS\",Verdana,sans-serif;  color:#FFF;background-color:#5C87B2; }#content{margin:0 0 0 2%;position:relative;} .summary-container,.content-container{background:#FFF;width:96%;margin-top:8px;padding:10px;position:relative;} .content-container p{margin:0 0 10px 0; }#details-left{width:35%;float:left;margin-right:2%; }#details-right{width:63%;float:left;overflow:hidden; }#server_version{width:96%;_height:1px;min-height:1px;margin:0 0 5px 0;padding:11px 2% 8px 2%;color:#FFFFFF;  background-color:#5A7FA5;border-bottom:1px solid #C1CFDD;border-top:1px solid #4A6C8E;font-weight:normal;  font-size:1em;color:#FFF;text-align:right; }#server_version p{margin:5px 0;} table{margin:4px 0 4px 0;width:100%;border:none;} td,th{vertical-align:top;padding:3px 0;text-align:left;font-weight:normal;border:none;} th{width:30%;text-align:right;padding-right:2%;font-weight:bold;} thead th{background-color:#ebebeb;width:25%; }#details-right th{width:20%;} table tr.alt td,table tr.alt th{} .highlight-code{color:#CC0000;font-weight:bold;font-style:italic;} .clear{clear:both;} .preferred{padding:0 5px 2px 5px;font-weight:normal;background:#006633;color:#FFF;font-size:.8em;} --> </style>  </head> <body> <div id=\"content\"> <div class=\"content-container\">   <h3>HTTP Error 503.0 - Service Temporarily Unavailable</h3>   <h4>503 Service Temporarily Unavailable</h4> </div> <div class=\"content-container\">  <fieldset><h4>Most likely causes:</h4>   <ul> \t<li>An invalid identity in the application pool could cause this error.</li> \t<li>The application pool is no longer running because of configuration or reaching application failure limits.</li> \t<li>The concurrent application request limit was reached.</li> </ul>  </fieldset> </div> <div class=\"content-container\">  <fieldset><h4>Things you can try:</h4>   <ul> \t<li>Check the event logs and the HTTP error logs for more information.</li> </ul>  </fieldset> </div>  <div class=\"content-container\">  <fieldset><h4>Detailed Error Information:</h4>   <div id=\"details-left\">    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">     <tr class=\"alt\"><th>Module</th><td>&nbsp;&nbsp;&nbsp;RewriteModule</td></tr>     <tr><th>Notification</th><td>&nbsp;&nbsp;&nbsp;BeginRequest</td></tr>     <tr class=\"alt\"><th>Handler</th><td>&nbsp;&nbsp;&nbsp;PHP-php</td></tr>     <tr><th>Error Code</th><td>&nbsp;&nbsp;&nbsp;0x00000000</td></tr>         </table>   </div>   <div id=\"details-right\">    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">     <tr class=\"alt\"><th>Requested URL</th><td>&nbsp;&nbsp;&nbsp;http://test.epoqueapparels.com:80/Salon_App/index.php</td></tr>     <tr><th>Physical Path</th><td>&nbsp;&nbsp;&nbsp;D:\\Inetpub\\vhosts\\epoqueapparels.com\\test.epoqueapparels.com\\Salon_App\\index.php</td></tr>     <tr class=\"alt\"><th>Logon Method</th><td>&nbsp;&nbsp;&nbsp;Not yet determined</td></tr>     <tr><th>Logon User</th><td>&nbsp;&nbsp;&nbsp;Not yet determined</td></tr>         </table>    <div class=\"clear\"></div>   </div>  </fieldset> </div>  <div class=\"content-container\">  <fieldset><h4>More Information:</h4>   This error occurs when the worker process was unable to start. This could be due to an invalid identity or configuration, or because the concurrent request limit was reached.   <p><a href=\"http://go.microsoft.com/fwlink/?LinkID=62293&amp;IIS70Error=503,0,0x00000000,9600\">View more information &raquo;</a></p>     </fieldset> </div> </div> </body> </html> ".equals(jsonResult)){
                /*Intent in = new Intent(SalonLogin.this, ExitActivity.class);
                startActivity(in);*/
                //Toast.makeText(SalonLogin.this, "Sorry! Server Is Under Maintenance", Toast.LENGTH_LONG).show();
                //finish();
                Toast.makeText(login.this, "Sorry! Server Is Under Maintenance", Toast.LENGTH_LONG).show();
                android.os.Process.killProcess(android.os.Process.myPid());



            }
            return jsonResult;
        }

        public void GetData(){

        }

        @Override

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("Resulted Value: " + result);
            if(result.equals("") || result == null || result.equals("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\"> <head> <title>IIS 8.5 Detailed Error - 503.0 - Service Temporarily Unavailable</title> <style type=\"text/css\"> <!-- body{margin:0;font-size:.7em;font-family:Verdana,Arial,Helvetica,sans-serif;} code{margin:0;color:#006600;font-size:1.1em;font-weight:bold;} .config_source code{font-size:.8em;color:#000000;} pre{margin:0;font-size:1.4em;word-wrap:break-word;} ul,ol{margin:10px 0 10px 5px;} ul.first,ol.first{margin-top:5px;} fieldset{padding:0 15px 10px 15px;word-break:break-all;} .summary-container fieldset{padding-bottom:5px;margin-top:4px;} legend.no-expand-all{padding:2px 15px 4px 10px;margin:0 0 0 -12px;} legend{color:#333333;;margin:4px 0 8px -12px;_margin-top:0px; font-weight:bold;font-size:1em;} a:link,a:visited{color:#007EFF;font-weight:bold;} a:hover{text-decoration:none;} h1{font-size:2.4em;margin:0;color:#FFF;} h2{font-size:1.7em;margin:0;color:#CC0000;} h3{font-size:1.4em;margin:10px 0 0 0;color:#CC0000;} h4{font-size:1.2em;margin:10px 0 5px 0; }#header{width:96%;margin:0 0 0 0;padding:6px 2% 6px 2%;font-family:\"trebuchet MS\",Verdana,sans-serif;  color:#FFF;background-color:#5C87B2; }#content{margin:0 0 0 2%;position:relative;} .summary-container,.content-container{background:#FFF;width:96%;margin-top:8px;padding:10px;position:relative;} .content-container p{margin:0 0 10px 0; }#details-left{width:35%;float:left;margin-right:2%; }#details-right{width:63%;float:left;overflow:hidden; }#server_version{width:96%;_height:1px;min-height:1px;margin:0 0 5px 0;padding:11px 2% 8px 2%;color:#FFFFFF;  background-color:#5A7FA5;border-bottom:1px solid #C1CFDD;border-top:1px solid #4A6C8E;font-weight:normal;  font-size:1em;color:#FFF;text-align:right; }#server_version p{margin:5px 0;} table{margin:4px 0 4px 0;width:100%;border:none;} td,th{vertical-align:top;padding:3px 0;text-align:left;font-weight:normal;border:none;} th{width:30%;text-align:right;padding-right:2%;font-weight:bold;} thead th{background-color:#ebebeb;width:25%; }#details-right th{width:20%;} table tr.alt td,table tr.alt th{} .highlight-code{color:#CC0000;font-weight:bold;font-style:italic;} .clear{clear:both;} .preferred{padding:0 5px 2px 5px;font-weight:normal;background:#006633;color:#FFF;font-size:.8em;} --> </style>  </head> <body> <div id=\"content\"> <div class=\"content-container\">   <h3>HTTP Error 503.0 - Service Temporarily Unavailable</h3>   <h4>503 Service Temporarily Unavailable</h4> </div> <div class=\"content-container\">  <fieldset><h4>Most likely causes:</h4>   <ul> \t<li>An invalid identity in the application pool could cause this error.</li> \t<li>The application pool is no longer running because of configuration or reaching application failure limits.</li> \t<li>The concurrent application request limit was reached.</li> </ul>  </fieldset> </div> <div class=\"content-container\">  <fieldset><h4>Things you can try:</h4>   <ul> \t<li>Check the event logs and the HTTP error logs for more information.</li> </ul>  </fieldset> </div>  <div class=\"content-container\">  <fieldset><h4>Detailed Error Information:</h4>   <div id=\"details-left\">    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">     <tr class=\"alt\"><th>Module</th><td>&nbsp;&nbsp;&nbsp;RewriteModule</td></tr>     <tr><th>Notification</th><td>&nbsp;&nbsp;&nbsp;BeginRequest</td></tr>     <tr class=\"alt\"><th>Handler</th><td>&nbsp;&nbsp;&nbsp;PHP-php</td></tr>     <tr><th>Error Code</th><td>&nbsp;&nbsp;&nbsp;0x00000000</td></tr>         </table>   </div>   <div id=\"details-right\">    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">     <tr class=\"alt\"><th>Requested URL</th><td>&nbsp;&nbsp;&nbsp;http://test.epoqueapparels.com:80/Salon_App/index.php</td></tr>     <tr><th>Physical Path</th><td>&nbsp;&nbsp;&nbsp;D:\\Inetpub\\vhosts\\epoqueapparels.com\\test.epoqueapparels.com\\Salon_App\\index.php</td></tr>     <tr class=\"alt\"><th>Logon Method</th><td>&nbsp;&nbsp;&nbsp;Not yet determined</td></tr>     <tr><th>Logon User</th><td>&nbsp;&nbsp;&nbsp;Not yet determined</td></tr>         </table>    <div class=\"clear\"></div>   </div>  </fieldset> </div>  <div class=\"content-container\">  <fieldset><h4>More Information:</h4>   This error occurs when the worker process was unable to start. This could be due to an invalid identity or configuration, or because the concurrent request limit was reached.   <p><a href=\"http://go.microsoft.com/fwlink/?LinkID=62293&amp;IIS70Error=503,0,0x00000000,9600\">View more information &raquo;</a></p>     </fieldset> </div> </div> </body> </html> ")){
                Toast.makeText(login.this, "Server connection failed", Toast.LENGTH_LONG).show();
                /*Intent intent = new Intent(login.this , MainActivity.class);
                startActivity(intent);
                finish();
                return;*/
            }
            int jsonResult = returnParsedJsonObject(result);

            if(jsonResult == 0){
                Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                return;
            }

            if(jsonResult == 1){

                Log.d(TAG, result);
                SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("email", enteredUsername);
                editor.putString("password", enteredPassword);
                editor.putString("idholder", IDHolder);
                editor.commit();

                Intent intent = new Intent(login.this, fillFormStartPage.class);
                intent.putExtra("id", id1);
                startActivity(intent);
                finish();

                /*
                //for calling home
                Intent intent = new Intent(login.this , Home.class);
                intent.putExtra("USERNAME",enteredUsername);
                intent.putExtra("MESSAGE","You have been successfully login");
                startActivity(intent);
                */


                Toast.makeText(login.this, "Welcome!", Toast.LENGTH_LONG).show();
                return;

            }

        }

        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            try {
                while ((rLine = br.readLine()) != null) {
                    answer.append(rLine);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return answer;
        }
    }

    private int returnParsedJsonObject(String result){
        JSONObject resultObject = null;
        int returnedResult = 0;
        try {
            resultObject = new JSONObject(result);
            returnedResult = resultObject.getInt("success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnedResult;
    }
    class UpdateAsyncTask extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
        }

        @Override
        protected String doInBackground(String... params) {
                HttpJsonParser httpJsonParser = new HttpJsonParser();
            Map<String, String> httpParams = new HashMap<>();
            //Populating request parameters
            httpParams.put(TAG_EMAIL, enteredUsername);
            httpParams.put(TAG_TOKEN, token);

            JSONObject jsonObject = httpJsonParser.makeHttpRequest(
                    url_token, "POST", httpParams);
            try {
                success = jsonObject.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success == 1) {
                        //Display success message


                    } else {

                    }
                }
            });
        }


    }}







