package com.example.vibrantzz3.snipsearchadmin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class signUp extends AppCompatActivity {

    String ServerURL = "http://test.epoqueapparels.com/Salon/Salon_App/indexsalonmaster.php" ;
    TextView already_user;
    EditText name ,email, phonenumber, description ;
    ImageView btn_sign_up;
    String TempName, TempEmail, TempPhonenumber, TempDescription;
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
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.etname);
        email = (EditText) findViewById(R.id.etemail);
        phonenumber =(EditText) findViewById(R.id.etrnumber);
        description = (EditText) findViewById(R.id.etdescription);
        btn_sign_up = (ImageView) findViewById(R.id.btnsubmit);
        already_user = (TextView) findViewById(R.id.txtloginredirect);
        //final String token = TokenManager.getInstance(this).getDeviceToken();
        /*btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUp.this, InforPageForFreshLogin.class);
                startActivity(intent);
                finish();

            }});*/

                // -------------------validations-----------------

                //----------------------------------------------------------------------------------

                already_user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(signUp.this, login.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }

    public void GetUpdate(View view){
        //TempName = name.getText().toString();
        TempName = name.getText().toString();
        TempEmail = email.getText().toString();
        TempPhonenumber = phonenumber.getText().toString();
        TempDescription = description.getText().toString();



        InsertData(TempName, TempEmail, TempPhonenumber, TempDescription);//, TempOwner, TempContact, TempPassword, TempEmail);

    }

    public void InsertData(final String name, final String email, final String contactno, final String discription) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String NameHolder = name ;
                String NameHolder1 = email ;
                String NameHolder2 = contactno ;
                String NameHolder3 = discription ;



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("email", NameHolder1));
                nameValuePairs.add(new BasicNameValuePair("contactno", NameHolder2));
                nameValuePairs.add(new BasicNameValuePair("discription", NameHolder3));


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

                Toast.makeText(signUp.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(signUp.this, InforPageForFreshLogin.class);
                /*intent.putExtra("name",TempName);
                intent.putExtra("id", TempID);*/
                startActivity(intent);
                finish();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(name);
    }



}