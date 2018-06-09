package com.example.vibrantzz3.snipsearchadmin;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Vibrantzz3 on 2/28/2018.
 */

public class GalleryRecyclerViewAdapter extends RecyclerView.Adapter<GalleryRecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Gallery> gData;

    public GalleryRecyclerViewAdapter(Context mContext, List<Gallery> gData) {
        this.mContext=mContext;
        this.gData=gData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.gallery_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final String id=gData.get(position).getID();
        Picasso.get()
                .load(gData.get(position).getImage())
                .placeholder(R.drawable.logo2) // optional
                .into(holder.gimg);

        final ImagePopup imagePopup = new ImagePopup(mContext);

        imagePopup.setBackgroundColor(Color.BLACK);  // Optional
        imagePopup.setFullScreen(true); // Optional
        imagePopup.setHideCloseIcon(false);  // Optional
        imagePopup.setImageOnClickClose(true);  // Optional

        imagePopup.initiatePopup(holder.gimg.getDrawable());
        holder.gimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Initiate Popup view **/
                imagePopup.viewPopup();

            }
        });


    }

    @Override
    public int getItemCount() {
        return gData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView gimg;
        CardView cardview;
        public MyViewHolder(View itemview)
        {   super(itemview);

            gimg=(ImageView)itemview.findViewById(R.id.gallery_img);
            cardview=(CardView) itemview.findViewById(R.id.gallery_card);
        }
    }
}

