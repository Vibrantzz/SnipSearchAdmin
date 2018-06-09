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

public class AppointmentRecyclerViewAdapter extends RecyclerView.Adapter<AppointmentRecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private List <Appointments> hData;

    public AppointmentRecyclerViewAdapter(Context mContext, List<Appointments> hData) {
        this.mContext=mContext;
        this.hData=hData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String request=hData.get(position).getRequest();

        holder.name.setText(hData.get(position).getName());
    /*holder.datetxt.setText(hData.get(position).getLocation());
    holder.timetxt.setText(hData.get(position).getTime());*/

        Picasso.get()
                .load(hData.get(position).getThumbnail())
                .placeholder(R.drawable.logo2) // optional
                .into(holder.himage);
        holder.date.setText(hData.get(position).getUDate());
        holder.time.setText(hData.get(position).getUTime());
        holder.request.setText(hData.get(position).getRequest());

        holder.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,AppointmentDetaileView.class);
                intent.putExtra("idholder",request);
                mContext.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return hData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, datetxt, time, timetxt, request;
        ImageView himage;
        CardView cardview;

        public ViewHolder(View itemview)
        {   super(itemview);
            name=(TextView)itemview.findViewById(R.id.txtname);
            request=(TextView)itemview.findViewById(R.id.txtrequest);
            datetxt=(TextView)itemview.findViewById(R.id.txtloc);
            date=(TextView)itemview.findViewById(R.id.txtapp);
            timetxt=(TextView)itemview.findViewById(R.id.txtloc1);
            time=(TextView)itemview.findViewById(R.id.txtapp1);
            himage=(ImageView)itemview.findViewById(R.id.simg);
            cardview=(CardView) itemview.findViewById(R.id.maincard);



        }
    }

}
