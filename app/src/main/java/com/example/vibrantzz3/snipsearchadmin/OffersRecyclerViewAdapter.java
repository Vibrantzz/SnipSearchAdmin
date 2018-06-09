package com.example.vibrantzz3.snipsearchadmin;

import android.content.Context;
import android.media.Image;
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

public class OffersRecyclerViewAdapter extends RecyclerView.Adapter<OffersRecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private List <Offers> hData;

    public OffersRecyclerViewAdapter(Context mContext, List<Offers> hData) {
        this.mContext=mContext;
        this.hData=hData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
    holder.name.setText(hData.get(position).getName());
    /*holder.location.setText(hData.get(position).getLocation());
        Picasso.get()
                .load(hData.get(position).getThumbnail())
                .placeholder(R.drawable.logo2) // optional
                .into(holder.himage);
        holder.txt.setText(hData.get(position).getRequest()+hData.get(position).getUDate()+hData.get(position).getUTime());
*/
    }

    @Override
    public int getItemCount() {
        return hData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView active, deactive, favourite, unfavourite;
        CardView cardview;

        public ViewHolder(View itemview)
        {   super(itemview);
            name=(TextView)itemview.findViewById(R.id.txtname);
            active = (ImageView) itemview.findViewById(R.id.active);
            deactive = (ImageView)itemview.findViewById(R.id.deactive);
            favourite = (ImageView)itemview.findViewById(R.id.favourate);
            unfavourite = (ImageView)itemview.findViewById(R.id.unfavourate);





        }
    }

}
