package com.example.vibrantzz3.snipsearchadmin;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import java.util.Locale;

public class AddOffer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView img;
    String ServerURL = "http://test.epoqueapparels.com/Salon/Salon_App/indexsalonmaster.php" ;
    String TempCategory, TempOffer, TempDesciption,TempTermsnCondition, TempValidity;
    private Button done;
    String IDHolder;
    TextView categories, categoryselection;
    EditText Offer, Discription, Termsncondition, Validity;
    private EditText salon_name,offer, offerPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offers_by_salon);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Offer = (EditText) findViewById(R.id.input_address);
        Discription = (EditText) findViewById(R.id.input_city);
        Termsncondition = (EditText) findViewById(R.id.input_pincode);
        Validity =(EditText) findViewById(R.id.input_state);
        categories = (TextView) findViewById(R.id.cat);
        /*offer=(EditText) findViewById(R.id.euname);
        offerPeriod = (EditText) findViewById(R.id.eucontact);*/
        done=(Button) findViewById(R.id.btn_done);
        categoryselection = (TextView) findViewById(R.id.categoryselection);

        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Discounts");
        categories.add("Short Period");
        categories.add("Continuous");
        categories.add("Added Value");
        categories.add("Happy Hours");
        categories.add("Seasonal");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        Intent in = getIntent();
//        TempName = in.getExtras().getString("name");
        IDHolder = getIntent().getStringExtra("idholder");

    }
    public void GetUpdate(View view){
        //TempCategory = categoryselection.getText().toString();
        TempOffer = Offer.getText().toString();
        TempDesciption = Discription.getText().toString();
        TempTermsnCondition = Termsncondition.getText().toString();
        TempValidity = Validity.getText().toString();

        InsertData(IDHolder,TempCategory, TempOffer, TempDesciption, TempTermsnCondition,TempValidity);//, TempOwner, TempContact, TempPassword, TempEmail);
        /*Intent intent = new Intent(EditUser.this, User.class);
        intent.putExtra("userName", updateUserName);
        intent.putExtra("userEmail", email);
        intent.putExtra("personCity", updateUserLocation);
        startActivity(intent);
        finish();*/
    }

    public void InsertData(final String salon_id,final String category, final String offer, final String discription, final String terms, final String validity) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String NameHolder = salon_id ;
                String NameHolder1 = category ;
                String NameHolder2 = offer ;
                String NameHolder3 = discription ;
                String NameHolder4 = terms ;
                String NameHolder5 = validity ;




                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("salon_id", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("category_id", NameHolder1));
                nameValuePairs.add(new BasicNameValuePair("title", NameHolder2));
                nameValuePairs.add(new BasicNameValuePair("offerdesc", NameHolder3));
                nameValuePairs.add(new BasicNameValuePair("terms", NameHolder4));
                nameValuePairs.add(new BasicNameValuePair("offer_period", NameHolder5));


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

                Toast.makeText(AddOffer.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AddOffer.this, SalonProfile.class);
                intent.putExtra("idholder", IDHolder);
                startActivity(intent);
                finish();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(salon_id);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        categoryselection.setText(item);

        /*categories.add("Discounts");
        categories.add("Short Period");
        categories.add("Continuous");
        categories.add("Added Value");
        categories.add("Happy Hours");
        categories.add("Seasonal");;*/

        if (item.equals("Discounts")) {
            TempCategory = "1";
        }
        else if (item.equals("Short Period")) {
            TempCategory = "2";
        }
        else if (item.equals("Continuous")) {
            TempCategory = "3";
        }
        else if (item.equals("Added Value")) {
            TempCategory = "4";
        }
        else if (item.equals("Happy Hours")) {
            TempCategory = "5";
        }
        else if (item.equals("Seasonal")) {
            TempCategory = "6";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
