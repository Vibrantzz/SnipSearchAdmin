package com.example.vibrantzz3.snipsearchadmin;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class editHours extends AppCompatActivity implements View.OnClickListener {

    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView already_user;
    EditText name, email, password;
    ImageView save;
    String IDHolder = "2";
    String id2 = "1";
    String id1 = "0";
    String id;
    TextView start1,middle1, end1,start2,middle2, end2,start3,middle3, end3,start4,middle4, end4,start5,middle5, end5,start6,middle6, end6,start7,middle7, end7;
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
        setContentView(R.layout.edithours);
        save = (ImageView) findViewById(R.id.save);
        start1 = (TextView) findViewById(R.id.start1);
        middle1 = (TextView) findViewById(R.id.middle1);
        end1 = (TextView) findViewById(R.id.end1);
        start2 = (TextView) findViewById(R.id.start2);
        middle2 = (TextView) findViewById(R.id.middle2);
        end2 = (TextView) findViewById(R.id.end2);
        start3 = (TextView) findViewById(R.id.start3);
        middle3 = (TextView) findViewById(R.id.middle3);
        end3 = (TextView) findViewById(R.id.end3);
        start4 = (TextView) findViewById(R.id.start4);
        middle4 = (TextView) findViewById(R.id.middle4);
        end4 = (TextView) findViewById(R.id.end4);
        start5 = (TextView) findViewById(R.id.start5);
        middle5 = (TextView) findViewById(R.id.middle5);
        end5 = (TextView) findViewById(R.id.end5);
        start6 = (TextView) findViewById(R.id.start6);
        middle6 = (TextView) findViewById(R.id.middle6);
        end6 = (TextView) findViewById(R.id.end6);
        start7 = (TextView) findViewById(R.id.start7);
        middle7 = (TextView) findViewById(R.id.middle7);
        end7 = (TextView) findViewById(R.id.end7);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.backarrow3);
        toolbar.setTitle("Hours");
        //toolbar.setTitleTextColor(Color.rgb(0, 0, 0));;// your drawable
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editHours.this, Settings.class);
                //intent.putExtra("id", IDHolder);
                startActivity(intent);
                finish();

            }
        });

        end1.setOnClickListener(this);
        start1.setOnClickListener(this);
        SwitchCompat switchCompat1 = (SwitchCompat) findViewById(R.id.switch1);
        end2.setOnClickListener(this);
        start2.setOnClickListener(this);
        SwitchCompat switchCompat2 = (SwitchCompat) findViewById(R.id.switch2);
        end3.setOnClickListener(this);
        start3.setOnClickListener(this);
        SwitchCompat switchCompat3 = (SwitchCompat) findViewById(R.id.switch3);
        end4.setOnClickListener(this);
        start4.setOnClickListener(this);
        SwitchCompat switchCompat4 = (SwitchCompat) findViewById(R.id.switch4);
        end5.setOnClickListener(this);
        start5.setOnClickListener(this);
        SwitchCompat switchCompat5 = (SwitchCompat) findViewById(R.id.switch5);
        end6.setOnClickListener(this);
        start6.setOnClickListener(this);
        SwitchCompat switchCompat6 = (SwitchCompat) findViewById(R.id.switch6);
        end7.setOnClickListener(this);
        start7.setOnClickListener(this);
        SwitchCompat switchCompat7 = (SwitchCompat) findViewById(R.id.switch7);


        switchCompat1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                   start1.setText("InTime");
                   middle1.setText("TO");
                   end1.setText("OutTime");
                }else{
                    start1.setText("");
                    middle1.setText("Closed");
                    end1.setText("");
                }

            }
        });
        switchCompat2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    start2.setText("InTime");
                    middle2.setText("TO");
                    end2.setText("OutTime");
                }else{
                    start2.setText("");
                    middle2.setText("Closed");
                    end2.setText("");
                }

            }
        });
        switchCompat3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    start3.setText("InTime");
                    middle3.setText("TO");
                    end3.setText("OutTime");
                }else{
                    start3.setText("");
                    middle3.setText("Closed");
                    end3.setText("");
                }

            }
        });
        switchCompat4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    start4.setText("InTime");
                    middle4.setText("TO");
                    end4.setText("OutTime");
                }else{
                    start4.setText("");
                    middle4.setText("Closed");
                    end4.setText("");
                }

            }
        });
        switchCompat5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    start5.setText("InTime");
                    middle5.setText("TO");
                    end5.setText("OutTime");
                }else{
                    start5.setText("");
                    middle5.setText("Closed");
                    end5.setText("");
                }

            }
        });
        switchCompat6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    start6.setText("InTime");
                    middle6.setText("TO");
                    end6.setText("OutTime");
                }else{
                    start6.setText("");
                    middle6.setText("Closed");
                    end6.setText("");
                }

            }
        });
        switchCompat7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    start7.setText("InTime");
                    middle7.setText("TO");
                    end7.setText("OutTime");
                }else{
                    start7.setText("");
                    middle7.setText("Closed");
                    end7.setText("");
                }

            }
        });

        //check the current state before we display the screen
        if(switchCompat1.isChecked()){

        }
        else {
            start1.setText("");
            middle1.setText("Closed");
            end1.setText("");
        }
        if(switchCompat2.isChecked()){

        }
        else {
            start2.setText("");
            middle2.setText("Closed");
            end2.setText("");
        }
        if(switchCompat3.isChecked()){

        }
        else {
            start3.setText("");
            middle3.setText("Closed");
            end3.setText("");
        }
        if(switchCompat4.isChecked()){

        }
        else {
            start4.setText("");
            middle4.setText("Closed");
            end4.setText("");
        }
        if(switchCompat5.isChecked()){

        }
        else {
            start5.setText("");
            middle5.setText("Closed");
            end5.setText("");
        }
        if(switchCompat6.isChecked()){

        }
        else {
            start6.setText("");
            middle6.setText("Closed");
            end6.setText("");
        }
        if(switchCompat7.isChecked()){

        }
        else {
            start7.setText("");
            middle7.setText("Closed");
            end7.setText("");
        }
    }

    public void onClick(View v) {

        if (v == start1) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            start1.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == start2) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            start2.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == start3) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            start3.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == start4) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            start4.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == start5) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            start5.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == start6) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            start6.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == start7) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            start7.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (v == end1) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            end1.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == end2) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            end2.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == end3) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            end3.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == end4) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            end4.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == end5) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            end5.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == end6) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            end6.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == end7) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            end7.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(editHours.this, Settings.class);
        /*this.overridePendingTransition(R.anim.animation_leave,
                R.anim.animation_enter);
        intent.putExtra("id", id2);
        startActivity(intent);*/
        finish();

    }
}