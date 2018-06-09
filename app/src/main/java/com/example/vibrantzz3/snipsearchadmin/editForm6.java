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

public class editForm6 extends AppCompatActivity {

    TextView already_user;
    EditText name, email, password;
    ImageView go;
    String id2 = "1";
    String id1 = "0";
    String id, IDHolder ;
    ImageView bridal, bridalpress, haircut, haircutpress, predicure, predicurepress, manicure,manicurepress, facial, facialpress, footspa, footspapress, message, messagepress, nailart, nailartpress, haircolor, haircolorpress, waxing, waxingpress, threading, threadingpress, shave, shavepress;
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
        haircut = (ImageView) findViewById(R.id.haircut);
        haircutpress = (ImageView) findViewById(R.id.haircutpress);
        haircolor = (ImageView) findViewById(R.id.haircolor);
        haircolorpress = (ImageView) findViewById(R.id.haircolorpress);
        footspa = (ImageView) findViewById(R.id.footspa);
        footspapress = (ImageView) findViewById(R.id.footspapress);
        predicure = (ImageView) findViewById(R.id.predicure);
        predicurepress = (ImageView) findViewById(R.id.predicurepress);
        message = (ImageView) findViewById(R.id.message);
        messagepress = (ImageView) findViewById(R.id.messagepress);
        manicure = (ImageView) findViewById(R.id.manicure);
        manicurepress = (ImageView) findViewById(R.id.manicurepress);
        facial = (ImageView) findViewById(R.id.facial);
        facialpress = (ImageView) findViewById(R.id.facialpress);
        nailart = (ImageView) findViewById(R.id.nailart);
        nailartpress = (ImageView) findViewById(R.id.nailartpress);
        waxing = (ImageView) findViewById(R.id.waxing);
        waxingpress = (ImageView) findViewById(R.id.waxingpress);
        threading = (ImageView) findViewById(R.id.threading);
        threadingpress = (ImageView) findViewById(R.id.threadingpress);
        shave = (ImageView) findViewById(R.id.shave);
        shavepress = (ImageView) findViewById(R.id.shavepress);
        go = (ImageView) findViewById(R.id.go);
        //final String token = TokenManager.getInstance(this).getDeviceToken();
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editForm6.this, fillForm6.class);
                intent.putExtra("id", id1);
                startActivity(intent);
                finish();

            }
        });

        Intent intent = getIntent();
        IDHolder = intent.getExtras().getString("idholder");

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
                }
                else
                {
                    bridalpress.setVisibility(View.VISIBLE);
                }
            }
        });

        haircut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = haircutpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    haircutpress.setVisibility(View.GONE);
                }
                else
                {
                    haircutpress.setVisibility(View.VISIBLE);
                }
            }
        });

        predicure.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = predicurepress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    predicurepress.setVisibility(View.GONE);
                }
                else
                {
                    predicurepress.setVisibility(View.VISIBLE);
                }
            }
        });

        manicure.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = manicurepress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    manicurepress.setVisibility(View.GONE);
                }
                else
                {
                    manicurepress.setVisibility(View.VISIBLE);
                }
            }
        });

        facial.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = facialpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    facialpress.setVisibility(View.GONE);
                }
                else
                {
                    facialpress.setVisibility(View.VISIBLE);
                }
            }
        });

        footspa.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = footspapress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    footspapress.setVisibility(View.GONE);
                }
                else
                {
                    footspapress.setVisibility(View.VISIBLE);
                }
            }
        });

        message.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = messagepress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    messagepress.setVisibility(View.GONE);
                }
                else
                {
                    messagepress.setVisibility(View.VISIBLE);
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
                }
                else
                {
                    nailartpress.setVisibility(View.VISIBLE);
                }
            }
        });

        haircolor.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                int visibility = haircolorpress.getVisibility();
                if(visibility == View.VISIBLE)
                {
                    haircolorpress.setVisibility(View.GONE);
                }
                else
                {
                    haircolorpress.setVisibility(View.VISIBLE);
                }
            }
        });

        waxing.setOnClickListener(new View.OnClickListener()
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
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(editForm6.this, Settings.class);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);
        finish();

    }
}