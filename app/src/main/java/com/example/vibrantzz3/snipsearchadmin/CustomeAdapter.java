package com.example.vibrantzz3.snipsearchadmin;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CustomeAdapter extends PagerAdapter{
    private int[] imgs = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.bg, R.drawable.book};
    private LayoutInflater inflater;
    private Context ctx;

    public CustomeAdapter(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view ==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.content_salonprofile,container,false);
        ImageView img = (ImageView)v.findViewById(R.id.salonimage);
        //TextView tv = (TextView)v.findViewById(R.id.textView);
        img.setImageResource(imgs[position]);
        //tv.setText("Image: " +position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }
}
