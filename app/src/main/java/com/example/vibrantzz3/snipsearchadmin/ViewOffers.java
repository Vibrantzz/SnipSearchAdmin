package com.example.vibrantzz3.snipsearchadmin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewOffers extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
TabLayout tablayout;
ViewPager viewpager;
String IDHolder;
Bundle bundle;
String id;
ImageView img, touser;
TextView uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewoffers);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Offers");
        mToolbar.setNavigationIcon(R.drawable.backarrow3);
        //setSupportActionBar(mToolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        View headview=navigationView.getHeaderView(0);
        img=headview.findViewById(R.id.imageView);
        uname=headview.findViewById(R.id.uname);
        touser=headview.findViewById(R.id.imgto);
        /*uname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ViewOffers.this , User.class);

                startActivity(intent);

                //InsertLocation(UName, GetCityName);
            }
        });*/
        //Intent intent = getIntent();
        // id = intent.getExtras().getString("id");

        /*SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        id = pref.getString("userid", null);*/

        Intent intent = getIntent();
        IDHolder = intent.getExtras().getString("id");

        Bundle bundle = new Bundle();
        bundle.putString("id",IDHolder);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ViewOffers.this , SalonProfile.class);
                intent.putExtra("id", IDHolder);
                startActivity(intent);
            }
        });
        tablayout = (TabLayout) findViewById(R.id.apptabs);
        viewpager = (ViewPager) findViewById(R.id.appviewpager);
        AppointmentViewPagerAdapter adapter = new AppointmentViewPagerAdapter(getSupportFragmentManager(),bundle);
        adapter.AddFragment(new ActiveOffers(), "Active Offers");
        adapter.AddFragment(new InactiveOffersFragment(), "Inactive Offers");
//        adapter.AddFragment(new PassedFragment(), "Passed");


        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);

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
        getMenuInflater().inflate(R.menu.home, menu);
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
            Intent intent = new Intent(ViewOffers.this , ViewAppointments.class);
            intent.putExtra("id", IDHolder);
            startActivity(intent);

        } else if (id == R.id.nav_offers) {

        }else if (id == R.id.nav_review) {
            Intent intent = new Intent(ViewOffers.this , ViewReviewActivity.class);
            intent.putExtra("id", IDHolder);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(ViewOffers.this , Settings.class);
            intent.putExtra("id", IDHolder);
            startActivity(intent);

        } else if (id == R.id.nav_signout) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

