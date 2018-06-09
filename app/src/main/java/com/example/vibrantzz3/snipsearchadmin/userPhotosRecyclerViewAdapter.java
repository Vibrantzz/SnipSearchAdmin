package com.example.vibrantzz3.snipsearchadmin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Vibrantzz3 on 2/28/2018.
 */

public class userPhotosRecyclerViewAdapter extends RecyclerView.Adapter<userPhotosRecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<userPhotos> uData;


    public userPhotosRecyclerViewAdapter(Context mContext, List<userPhotos> uData) {
        this.mContext=mContext;
        this.uData=uData;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.uphotos_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {



    }

    @Override
    public int getItemCount() {
        return uData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        public MyViewHolder(View itemview)
        {   super(itemview);




        }
    }
}

