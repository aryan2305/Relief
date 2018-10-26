package com.disaster.relief.relief;

import android.content.Context;
import android.support.v7.widget.RecyclerView;



import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;




/**
 * Created by aryan on 26/10/18.
 */

class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    private Context mContext ;
    private List<DisasterNews> mData ;


    public RvAdapter(Context mContext, List lst) {


        this.mContext = mContext;
        this.mData = lst;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_row,parent,false);
        // click listener here
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.dsname.setText(mData.get(position).getName());
        holder.ds_description.setText(mData.get(position).getDescription());
        holder.dsdate.setText(mData.get(position).getDate());
        holder.ds_latitude.setText(String.valueOf(mData.get(position).getLatitude()));
        holder.ds_longitude.setText(String.valueOf(mData.get(position).getLongitude()));

        // load image from the internet using Glide
        //Glide.with(mContext).load(mData.get(position).getImage_url()).apply(options).into(holder.AnimeThumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView dsname,ds_description,dsdate,ds_latitude,ds_longitude;
        ImageView DisasterThubmanil;


        public MyViewHolder(View itemView) {
            super(itemView);
            dsname = itemView.findViewById(R.id.rowname);
            ds_description = itemView.findViewById(R.id.description);
            dsdate = itemView.findViewById(R.id.date);
            ds_latitude = itemView.findViewById(R.id.latitude);
            ds_longitude = itemView.findViewById(R.id.longitude);
        }
    }
}
