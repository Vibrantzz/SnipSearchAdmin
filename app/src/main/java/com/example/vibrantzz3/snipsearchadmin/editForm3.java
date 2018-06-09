package com.example.vibrantzz3.snipsearchadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class editForm3 extends AppCompatActivity {

    TextView already_user;
    EditText name, email, password;
    ImageView go;
    String id2 = "1";
    String id1 = "0";
    String id, IDHolder;
    ImageView btn_sign_up;
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
        setContentView(R.layout.editform3);

        Intent intent = getIntent();
        IDHolder = intent.getExtras().getString("idholder");


        go = (ImageView) findViewById(R.id.go);
        //final String token = TokenManager.getInstance(this).getDeviceToken();
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editForm3.this, fillForm3a.class);
                intent.putExtra("id", id1);
                startActivity(intent);
                finish();

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.backarrow5);
//        toolbar.setTitle("Position marker where your business is located");
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


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(editForm3.this, Settings.class);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);
        finish();

    }
}