package com.example.vibrantzz3.snipsearchadmin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class fillForm6 extends AppCompatActivity {

    TextView already_user;
    EditText name, email, password;
    ImageView go;
    String id2 = "1";
    String id1 = "0";
    String id, TempEmailStore, IDHolder;
    ImageView btn_sign_up;
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
        setContentView(R.layout.fillform6);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(fillForm6.this, SalonProfile.class);
                mainIntent.putExtra("email",TempEmailStore);
                mainIntent.putExtra("idholder",IDHolder);
                fillForm6.this.startActivity(mainIntent);
                fillForm6.this.finish();
            }
        }, 2000);




        /*go = (ImageView) findViewById(R.id.go);
        //final String token = TokenManager.getInstance(this).getDeviceToken();
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fillForm6.this, SalonProfile.class);
                intent.putExtra("email",TempEmailStore);
                intent.putExtra("idholder",IDHolder);
                intent.putExtra("id", id1);
                startActivity(intent);
                finish();

            }
        });*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.backarrow3);
        toolbar.setTitle("You're Done");
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
        Intent intent = new Intent(fillForm6.this, fillForm5a.class);
        this.overridePendingTransition(R.anim.animation_leave,
                R.anim.animation_enter);
        intent.putExtra("id", id2);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);
        finish();

    }
}