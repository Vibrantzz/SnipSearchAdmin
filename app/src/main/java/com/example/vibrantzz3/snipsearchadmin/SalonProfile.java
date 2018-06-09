package com.example.vibrantzz3.snipsearchadmin;


import android.content.Context;
import android.content.Intent;
import com.squareup.picasso.Picasso;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
//import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;

/*import com.example.vibrantzz3.snipsearch.ImageMatrixTouchHandler;*/

/*
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.squareup.picasso.Picasso;
*/

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;*/

public class SalonProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView s_name,s_addr,s_address,s_timings,soffers,s_payment,s_rating,s_contact,saddr,view,fave,bmtxt;
    private ImageView simg,checkac,checkwifi,checkladies,checkpark,checkkids,svisited,offers,sbook,sbooked,sviewr,sunv,sunbm,sunfav,sfave, scalled, snotcalled;
    private Toolbar toolbar;
    String HttpURL = "http://test.epoqueapparels.com/CRUD/getSalonHomeDetail.php";
    private CardView menucard;
    HttpParse httpParse = new HttpParse();
    String ParseResult ;
    HashMap<String,String> ResultHash = new HashMap<>();
    String FinalJSonObject ;
    public static final String DEFAULT_IMAGES_FOLDER = "default_images";
    public static final String PICKED_IMAGES = "picked_images";
    private static final BitmapFactory.Options BITMAP_FACTORY_OPTIONS;
    static {
        BITMAP_FACTORY_OPTIONS = new BitmapFactory.Options();
        BITMAP_FACTORY_OPTIONS.inPreferredConfig = Bitmap.Config.RGB_565;
    }
    private ViewPager viewPager;
    public static final String TAG = SalonProfile.class.getSimpleName();
    private ImageViewPagerAdapter imageViewPagerAdapter;
    private ArrayList<Uri> pickedImageUris;
    NestedScrollView myView;
    ScrollView scroll;
    TextView scrollUp,viewreviews,viewservice,viewphotos;
    Spinner sp1;
    ImageView backPress, rate;
    CollapsingToolbarLayout c;
    private int success;
    //    FloatingActionMenu fam;
    Bundle bundle;
    ImageView img, touser, logo;
    TextView username, City, Address, SalonName,ratingBar;
    String TempCity, TempAddress, NameHolder, ImageHolder, TempEmailStore, IDHolder, RatingBarHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon_profile);


        SalonName=(TextView) findViewById(R.id.salonName);
        ratingBar = (TextView) findViewById(R.id.ratetext);
        backPress = (ImageView) findViewById(R.id.backarrow);
        logo = (ImageView) findViewById(R.id.logo);
        City = (TextView) findViewById(R.id.city);
        Address = (TextView) findViewById(R.id.salonAddr);
        simg=(ImageView) findViewById(R.id.salonimage);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        menucard=(CardView)findViewById(R.id.card_menu);
        svisited=(ImageView) findViewById(R.id.svisited);
        sfave=(ImageView) findViewById(R.id.sfave);
        offers=(ImageView) findViewById(R.id.offers);
        scalled=(ImageView) findViewById(R.id.scall);
        myView = (NestedScrollView)findViewById(R.id.scrollview1);
        c = (CollapsingToolbarLayout)findViewById(R.id.collapstoolbar);
        viewservice=(TextView)findViewById(R.id.viewservice);
        viewphotos=(TextView)findViewById(R.id.viewphotos);


        Intent intent = getIntent();
        TempEmailStore = intent.getExtras().getString("email");
        IDHolder = intent.getExtras().getString("idholder");

        backPress.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBackPressed();
            }});

        HttpWebCall(IDHolder);


        final Toolbar tool = (Toolbar)findViewById(R.id.toolbar);
        /*c = (CollapsingToolbarLayout)findViewById(R.id.collapstoolbar);*/
        AppBarLayout appbar = (AppBarLayout)findViewById(R.id.app_bar_layout);
        tool.setTitle("");
        setSupportActionBar(toolbar);
        c.setTitleEnabled(true);

        tool.setNavigationIcon(R.drawable.backarrow3);// your drawable
        tool.setTitleTextColor(Color.rgb(0, 0, 0));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, tool, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        View headview=navigationView.getHeaderView(0);
        img=headview.findViewById(R.id.imageView);
        username=headview.findViewById(R.id.uname);
        touser=headview.findViewById(R.id.imgto);
        /*username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SalonProfile.this , User.class);

                startActivity(intent);

                //InsertLocation(UName, GetCityName);
            }
        });*/
        //Intent intent = getIntent();
        // id = intent.getExtras().getString("id");

        bundle = new Bundle();
        bundle.putString("idholder",IDHolder);

        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            boolean isVisible = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    c.setTitle(NameHolder);
//                    c.setTitle();
                    tool.setVisibility(View.VISIBLE);
                    //c.setCollapsedTitleTextColor(Color.rgb(0, 0, 0));
                    isVisible = true;
                } else if(isVisible) {
                    c.setTitle("");
                    tool.setVisibility(View.INVISIBLE);
                    isVisible = false;
                }
            }
        });


        /*Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        profilepic = intent.getExtras().getString("Thumbnail") ;
        uid = intent.getExtras().getString("user") ;*/

        /*bundle = new Bundle();
        bundle.putString("id",id);*/

        /*Picasso.with(this)
                .load(profilepic)
                .placeholder(R.drawable.logo2) // optional
                .into(simg);*/

       /* SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        uid = pref.getString("userid", null);
        uname = pref.getString("    name", null);*/



        MenuFragment mf =new MenuFragment();
        android.support.v4.app.FragmentManager mfmanager=getSupportFragmentManager();
        mf.setArguments(bundle);
        mfmanager.beginTransaction()
                .replace(R.id.serviceframe,mf,mf.getTag())
                .commit();

        GalleryFragment gf =new GalleryFragment();
        android.support.v4.app.FragmentManager gmanager=getSupportFragmentManager();
        gf.setArguments(bundle);
        gmanager.beginTransaction()
                .replace(R.id.photoframe,gf,gf.getTag())
                .commit();


        scalled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SalonProfile.this , ViewAppointments.class);
                intent.putExtra("idholder", IDHolder);
                startActivity(intent);

            }});


        sfave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SalonProfile.this , ViewFavouritesActivity.class);
                intent.putExtra("idholder", IDHolder);
                startActivity(intent);

            }});

        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SalonProfile.this , AddOffer.class);
                intent.putExtra("idholder", IDHolder);
                startActivity(intent);

            }});


        svisited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SalonProfile.this , ViewBeenHereActivity.class);
                intent.putExtra("idholder", IDHolder);
                startActivity(intent);

            }});

        viewservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SalonProfile.this , SalonMenuActivity.class);
                intent.putExtra("idholder",IDHolder);

                startActivity(intent);

            }});

        viewphotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SalonProfile.this , SalonGallery.class);
                intent.putExtra("idholder",IDHolder);

                startActivity(intent);

            }});

//        new WelcomeAsyncTask().execute();
        List<Drawable> drawables = new ArrayList<>();
        addDefaultImages(drawables);

        imageViewPagerAdapter = new ImageViewPagerAdapter(drawables);
        viewPager = findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(imageViewPagerAdapter);

        if(savedInstanceState != null) {
            pickedImageUris = savedInstanceState.getParcelableArrayList(PICKED_IMAGES);
        }

        if(pickedImageUris != null) {
            for(Uri uri: pickedImageUris) {
                try {
                    addDrawableByUri(uri);
                } catch (FileNotFoundException e) {
                    Log.e(TAG, "File not found", e);
                }
            }
        } else {
            pickedImageUris = new ArrayList<>();
        }
    }

    //Method to show current record Current Selected Record
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
                new GetHttpResponse(SalonProfile.this).execute();

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

    // Parsing Complete JSON Object.
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
                            NameHolder = jsonObject.getString("name").toString() ;
                            TempCity = jsonObject.getString("city").toString() ;
                            TempAddress = jsonObject.getString("address").toString() ;
                            IDHolder = jsonObject.getString("id").toString() ;
                            RatingBarHolder = jsonObject.getString("rating").toString() ;
                            ImageHolder = jsonObject.getString("profilepic").toString() ;

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

            // Setting Student Name, Phone Number, Class into TextView after done all process .
            SalonName.setText(NameHolder);
            username.setText(NameHolder);
            City.setText(TempCity);
            /*unamee.setText(NameHolder);*/
            /*int image;
            image = Integer.parseInt(ImageHolder);*/
            //Uri uri = Uri.parse(ImageHolder);

            if(!"null".equals(ImageHolder)) {
                Picasso
                        .get()
                        .load(ImageHolder)
                        .into(logo);

                Picasso
                        .get()
                        .load(ImageHolder)
                        .into(img);
            }


            Address.setText(TempAddress);
            if(RatingBarHolder == null || RatingBarHolder == "null"){
                RatingBarHolder = "0";
            }
            Float rating = Float.parseFloat(RatingBarHolder);
            ratingBar.setText(RatingBarHolder);

        }
    }

    private void addDrawableByUri(Uri uri) throws FileNotFoundException {
        InputStream is = getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, BITMAP_FACTORY_OPTIONS);
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);

        // Add drawable to end of list
        imageViewPagerAdapter.drawables.add(drawable);
        imageViewPagerAdapter.notifyDataSetChanged();
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(R.id.add_photo == id) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.add_photo)), PICK_IMAGE);
        } else if(R.id.clear == id) {
            List<Drawable> drawables = imageViewPagerAdapter.drawables;
            drawables.clear();
            addDefaultImages(drawables);
            imageViewPagerAdapter.notifyDataSetChanged();
            pickedImageUris.clear();
        } else if(R.id.info == id) {
            DialogFragment infoDialogFragment = new InfoDialogFragment();
            infoDialogFragment.show(getFragmentManager(), "info");
        }
        return super.onOptionsItemSelected(item);
    }*/

    private void addDefaultImages(List<Drawable> drawables) {
        // Note: Images are stored as assets instead of as resources
        // This because content should be in its raw format as opposed to UI elements
        // and to have more control over the decoding of image files

        AssetManager assets = getAssets();
        Resources resources = getResources();
        try {
            List<String> images = Arrays.asList(assets.list(DEFAULT_IMAGES_FOLDER));
            Collections.sort(images);
            for(String image: images) {
                InputStream is = null;
                try {
                    is = assets.open(DEFAULT_IMAGES_FOLDER + "/" + image);
                    Bitmap bitmap = BitmapFactory.decodeStream(is, null, BITMAP_FACTORY_OPTIONS);
                    //drawables.add(new BitmapDrawable(resources, bitmap));
                } finally {
                    if(is != null) {
                        try {
                            is.close();
                        } catch(IOException ignored) {
                        }
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static class ImageViewPagerAdapter extends PagerAdapter {

        private List<Drawable> drawables;

        public ImageViewPagerAdapter(List<Drawable> drawables) {
            this.drawables = drawables;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = container.getContext();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.content_salonprofile, null);
            container.addView(view);

            ImageView imageView = view.findViewById(R.id.salonimage);
            imageView.setImageDrawable(drawables.get(position));

            /*ImageMatrixTouchHandler imageMatrixTouchHandler = new ImageMatrixTouchHandler(context);
            imageView.setOnTouchListener(imageMatrixTouchHandler);*/

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;

            ImageView imageView = view.findViewById(R.id.image);
            imageView.setImageResource(0);

            container.removeView(view);
        }

        @Override
        public int getCount() {
            return drawables.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition (Object object) {
            return POSITION_NONE;
        }
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
            Intent intent = new Intent(SalonProfile.this , ViewAppointments.class);
            intent.putExtra("idholder", IDHolder);
            startActivity(intent);

        } else if (id == R.id.nav_offers) {
            /*Intent intent = new Intent(SalonProfile.this , ViewOffers.class);
            intent.putExtra("id", IDHolder);
            startActivity(intent);*/

        }else if (id == R.id.nav_review) {
            Intent intent = new Intent(SalonProfile.this , ViewReviewActivity.class);
            intent.putExtra("idholder", IDHolder);
            startActivity(intent);

        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(SalonProfile.this , Settings.class);
            intent.putExtra("idholder", IDHolder);
            startActivity(intent);

        } else if (id == R.id.nav_signout) {
            SharedPreferences preferences = getSharedPreferences("loginData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(SalonProfile.this , login.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /*Intent intent = new Intent(SalonProfile.this , Settings.class);
    startActivity(intent);*/
}



