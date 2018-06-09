package com.example.vibrantzz3.snipsearchadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView img, touser;
    String IDHolder;
    TextView uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Edit Profile");
        toolbar.setNavigationIcon(R.drawable.backarrow3);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        Intent intent = getIntent();
        IDHolder = intent.getExtras().getString("idholder");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    public void GoToBusinessProfile(View view){

        Intent intent = new Intent(EditProfile.this , editForm1.class);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);

    }

    public void GoToAddress(View view){

        Intent intent = new Intent(EditProfile.this , editForm2.class);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);

    }

    public void GoToDropLocation(View view){

        Intent intent = new Intent(EditProfile.this , editForm3.class);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);

    }

    public void GoToGender(View view){

        Intent intent = new Intent(EditProfile.this , editForm3a.class);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);

    }

    public void GoToBusinessCategories(View view){

        Intent intent = new Intent(EditProfile.this , editForm4.class);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);

    }

    public void GoToService(View view){

        Intent intent = new Intent(EditProfile.this , editForm5.class);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);

    }

    public void GoToFacilites(View view){

        Intent intent = new Intent(EditProfile.this , editForm5a.class);
        intent.putExtra("idholder", IDHolder);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_appointment) {
            /*Intent intent = new Intent(Settings.this , ViewReviewActivity.class);

            startActivity(intent);*/

        } else if (id == R.id.nav_offers) {

        }else if (id == R.id.nav_review) {

        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(EditProfile.this , EditProfile.class);
            startActivity(intent);

        } else if (id == R.id.nav_signout) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
