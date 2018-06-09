package com.example.vibrantzz3.snipsearchadmin;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

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
import java.util.UUID;

public class fillFormStartPage extends AppCompatActivity{

    String id, TempBusinessName;
    String id2 = "1";
    String id1 = "0";
    HttpParse httpParse = new HttpParse();
    String ParseResult ;
    String FinalJSonObject ;
    HashMap<String,String> ResultHash = new HashMap<>();
    TextView textView, tvPath;
    //String IDHolder, emailStored, NameHolder;
    ImageView upload, uedit, logo, go;
    String emailStored, enteredPassword;
    private static final int IMAGE_REQUEST_CODE = 3;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private Uri filePath;
    String HttpURL = "http://test.epoqueapparels.com/CRUD/getSalonHome.php";
    private static final String UPLOAD_URL = "http://test.epoqueapparels.com/Salon/Salon_App/salon_logoUpload.php";
    EditText ed1, ed2, businessname;
    String IDHolder = "2";
    String NameHolder;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fillformstart);

        //textView = (TextView) findViewById(R.id.text);

        SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        emailStored = pref.getString("email", null);
        enteredPassword = pref.getString("password", null);

        HttpWebCall(emailStored);

        Intent intent = getIntent();
        id = intent.getExtras().getString("id");

        if(id.equals("1")){
            /*this.overridePendingTransition(R.anim.animation_left,
                    R.anim.animation_right);*/
        }else if(id.equals("0")) {
            this.overridePendingTransition(R.anim.anim_slide_in_left,
                    R.anim.anim_slide_out_left);

        }

        /*Bundle bundle = new Bundle();
        bundle.putString("id",IDHolder);*/

        go = (ImageView) findViewById(R.id.go);
        //final String token = TokenManager.getInstance(this).getDeviceToken();
        /*go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fillForm1.this, fillForm2.class);
                intent.putExtra("id", id1);
                startActivity(intent);
                finish();

            }
        });*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.backarrow5);
        //toolbar.setTitle("Please fill your address details");
        //toolbar.setTitleTextColor(Color.rgb(0, 0, 0));;// your drawable
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });
    }

    public void HttpWebCall(final String PreviousListViewClickedItem){

        class HttpWebCallFunction extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                //pDialog = ProgressDialog.show(ShowSingleRecordActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                //pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;

                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponse(fillFormStartPage.this).execute();

            }


            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("email",params[0]);

                ParseResult = httpParse.postRequest(ResultHash, HttpURL);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }

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
                            /*NameHolder = jsonObject.getString("name").toString() ;
                            NumberHolder = jsonObject.getString("location").toString() ;*/
                            emailStored = jsonObject.getString("email").toString() ;
                            IDHolder = jsonObject.getString("id").toString() ;

                            /*RatingBarHolder = jsonObject.getString("rating").toString() ;
                            ImageHolder = jsonObject.getString("profilepic").toString() ;*/

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


        }
    }

    public void GetUpdatefillformstart(View v){

        Intent intent = new Intent(fillFormStartPage.this, fillForm1.class);
        intent.putExtra("email",emailStored);
        intent.putExtra("idholder",IDHolder);
        intent.putExtra("id", id1);
        startActivity(intent);
        finish();

        SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("email", emailStored);
        editor.putString("idholder", IDHolder);
        editor.commit();
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(fillFormStartPage.this, fillFormStartPage.class);
        this.overridePendingTransition(R.anim.animation_leave,
                R.anim.animation_enter);
        intent.putExtra("idholder",IDHolder);
        startActivity(intent);
        finish();

    }

}
