package com.disaster.relief.relief;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class seeRvAdapter extends RecyclerView.Adapter<seeRvAdapter.MyViewHolder2> {
    private Context mContext ;
    private List<Details> mData ;


    public seeRvAdapter(Context mContext, List lst) {


        this.mContext = mContext;
        this.mData = lst;


    }

    @Override
    public MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.details_row,parent,false);
        // click listener here
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder2 holder, final int position) {

        holder.ds_name.setText("Name: "+mData.get(position).getName());

            holder.ds_gender.setText("Gender: "+mData.get(position).getGender());

        holder.dsage.setText("Age: "+mData.get(position).getAge());
        holder.ds_contact1.setText("Contact Details : \n"+String.valueOf(mData.get(position).getContact1()));
        holder.ds_contact2.setText(String.valueOf(mData.get(position).getContact2()));
        holder.ds_address1.setText("Last Seen: "+String.valueOf(mData.get(position).getAddress1()));
        holder.ds_address2.setText("Residence "+String.valueOf(mData.get(position).getAddress2()));

        // load image from the internet using Glide
        //Glide.with(mContext).load(mData.get(position).getImage_url()).apply(options).into(holder.AnimeThumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder {

        TextView ds_name,ds_gender,dsage,ds_contact1,ds_contact2,ds_address1,ds_address2;



        public MyViewHolder2(View itemView) {
            super(itemView);
            ds_name = itemView.findViewById(R.id.username);
            ds_gender = itemView.findViewById(R.id.gender);
            dsage = itemView.findViewById(R.id.age);
            ds_contact1 = itemView.findViewById(R.id.contact1);
            ds_contact2 = itemView.findViewById(R.id.contact2);
            ds_address1 = itemView.findViewById(R.id.address1);
            ds_address2 = itemView.findViewById(R.id.address2);

        }
    }

}
