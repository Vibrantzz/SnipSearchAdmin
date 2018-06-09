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

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Menu> mData;

    public MenuRecyclerViewAdapter(Context mContext, List<Menu> mData) {
        this.mContext=mContext;
        this.mData=mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.menu_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final String id=mData.get(position).getID();
        Picasso.get()
                .load(mData.get(position).getImage())
                .placeholder(R.drawable.logo2) // optional
                .into(holder.mimg);

        final ImagePopup imagePopup = new ImagePopup(mContext);

        imagePopup.setBackgroundColor(Color.BLACK);  // Optional
        imagePopup.setFullScreen(true); // Optional
        imagePopup.setHideCloseIcon(false);  // Optional
        imagePopup.setImageOnClickClose(true);  // Optional

        imagePopup.initiatePopup(holder.mimg.getDrawable());
        holder.mimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Initiate Popup view **/
                imagePopup.viewPopup();

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView mimg;
        CardView cardview;
        public MyViewHolder(View itemview)
        {   super(itemview);

            mimg=(ImageView)itemview.findViewById(R.id.menu_img);
            cardview=(CardView) itemview.findViewById(R.id.menu_card);
        }
    }
}

