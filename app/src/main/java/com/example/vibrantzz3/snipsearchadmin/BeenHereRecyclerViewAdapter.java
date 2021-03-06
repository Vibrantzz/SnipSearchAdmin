package com.example.vibrantzz3.snipsearchadmin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Vibrantzz3 on 3/3/2018.
 */

public class BeenHereRecyclerViewAdapter extends RecyclerView.Adapter<BeenHereRecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private List <BeenHere> hData;

    public BeenHereRecyclerViewAdapter(Context mContext, List<BeenHere> hData) {
        this.mContext=mContext;
        this.hData=hData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.beenhere_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.hname.setText(hData.get(position).getName());
        final String id=hData.get(position).getID();
        holder.hlocation.setText(hData.get(position).getLocation());

        holder.hrating.setText(hData.get(position).getRating());
        Picasso.get()
                .load(hData.get(position).getThumbnail())
                .into(holder.himage);
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,SalonProfile.class);
                intent.putExtra("idholder",id);
                mContext.startActivity(intent);

            }
        });




    }

    @Override
    public int getItemCount() {
        return hData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hname,hlocation,hrating,hrcount;
        ImageView himage;
        CardView cardview;

        public ViewHolder(View itemview)
        {   super(itemview);
            hname=(TextView)itemview.findViewById(R.id.sname);
            hlocation=(TextView)itemview.findViewById(R.id.saddress);
            hrating=(TextView)itemview.findViewById(R.id.txtrate);
            himage=(ImageView)itemview.findViewById(R.id.simg);
            cardview=(CardView) itemview.findViewById(R.id.bookmark_card);
        }
    }

}
