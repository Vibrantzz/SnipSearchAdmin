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

import com.squareup.picasso.Picasso;

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

public class fillForm1 extends AppCompatActivity implements View.OnClickListener {

    String HttpURL = "http://test.epoqueapparels.com/CRUD/getSalonHome.php";
    String ServerURL = "http://test.epoqueapparels.com/Salon/Salon_App/indexsalonmaster.php" ;
    String HttpURLForHours = "http://test.epoqueapparels.com/CRUD/getSalonAdminEditFillForm1.php";
    String first, ImageHolder;
    int image;
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
    private static final String UPLOAD_URL = "http://test.epoqueapparels.com/Salon/Salon_App/salon_logoUpload.php";
    EditText ed1, ed2, businessname;
    String IDHolder;
    String NameHolder;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fillform1);

        //textView = (TextView) findViewById(R.id.text);
        businessname =(EditText) findViewById(R.id.input_email);
        uedit = (ImageView) findViewById(R.id.editlogo);
        go = (ImageView) findViewById(R.id.go);
        logo = (ImageView) findViewById(R.id.logo);
        upload = (ImageView) findViewById(R.id.uploadlogo);
        tvPath = (TextView)findViewById(R.id.path);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);

        SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        emailStored = pref.getString("email", null);
        enteredPassword = pref.getString("password", null);

        /*emailStored = getIntent().getStringExtra("email");
        IDHolder = getIntent().getStringExtra("id");*/

        NameHolder = "1";

        Intent intent = getIntent();
        IDHolder = intent.getExtras().getString("idholder");
        id = intent.getExtras().getString("id");

        if(id.equals("1")){
            /*this.overridePendingTransition(R.anim.animation_left,
                    R.anim.animation_right);*/
        }else if(id.equals("0")) {
            this.overridePendingTransition(R.anim.anim_slide_in_left,
                    R.anim.anim_slide_out_left);

        }

        ed1.setText(IDHolder);
        ed2.setText(NameHolder);

        uedit.setOnClickListener(this);
        upload.setOnClickListener(this);

       /* Bundle bundle = new Bundle();
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
                new GetHttpResponseForSR(fillForm1.this).execute();

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

                                // Storing Student Name, Phone Number, Class into Variables.
                                first = jsonObject.getString("name").toString() ;
                                ImageHolder = jsonObject.getString("profilepic").toString() ;


                            }



                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(fillForm1.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(fillForm1.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
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
                businessname.setText(first);


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


                    /*Picasso
                            .get()
                            .load(ImageHolder)
                            .into(img);*/

                //unamee.setText(NameHolder);

            /*//image = Integer.parseInt(ImageHolder);
            Uri uri = Uri.parse(ImageHolder);
            logo.setImageURI(uri);
            //logo.setImageResource(image);*/

            }else
            {
                Toast.makeText(fillForm1.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
            }

//            StudentListView.setVisibility(View.VISIBLE);

//            ListAdapterClass adapter = new ListAdapterClass(studentList, context);

//            StudentListView.setAdapter(adapter);
        }
    }



    public void GetUpdatefillform1(View v){
        TempBusinessName = businessname.getText().toString();

        Intent intent = new Intent(fillForm1.this, fillForm2.class);
        intent.putExtra("email",emailStored);
        intent.putExtra("idholder",IDHolder);
        intent.putExtra("id", id1);
        startActivity(intent);
        finish();

        InsertData(TempBusinessName, emailStored);
    }

    public void InsertData(final String name,final String email) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String NameHolder = name ;
                String NameHolder1 = email ;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("email", NameHolder1));
                /*nameValuePairs.add(new BasicNameValuePair("email", NameHolder1));*/


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

                Toast.makeText(fillForm1.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(name);
    }

    @Override
    public void onClick(View view) {
        if(view == uedit){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            tvPath.setVisibility(View.VISIBLE);
            upload.setVisibility(View.VISIBLE);
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), IMAGE_REQUEST_CODE);
        }else if(view == upload){
            uploadMultipart();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                tvPath.setText("Path: ". concat(getPath(filePath)));
                logo.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadMultipart() {
        String caption = ed1.getText().toString().trim();
        String caption1 = ed2.getText().toString().trim();

        //getting the actual path of the image
        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
                    .addFileToUpload(path, "photo") //Adding file
                    .addParameter("salon_id", caption)
                    .addParameter("ismenu", caption1)//Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload
        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(fillForm1.this, fillFormStartPage.class);
        intent.putExtra("id", id2);
//        intent.putExtra("idholder", IDHolder);
        this.overridePendingTransition(R.anim.animation_leave,
                R.anim.animation_enter);
        startActivity(intent);
        finish();

    }

}
