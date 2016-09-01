package com.example.ngelesalpha.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngelesalpha.R;
import com.example.ngelesalpha.model.Home_NearYou_model;
import com.example.ngelesalpha.model.Search_model;
import com.example.ngelesalpha.program_profile;

import java.util.List;

/**
 * Created by Timothy on 7/3/2016.
 */
public class RecyclerAdapterHome_NearYou extends RecyclerView.Adapter<RecyclerAdapterHome_NearYou.MyViewHolder> {

    private List<Home_NearYou_model> mData;
    private LayoutInflater mInflater;
    private Context context;

    public RecyclerAdapterHome_NearYou(Context context, List<Home_NearYou_model> data){
        this.context=context;
        this.mData=data;
        this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.list_item_home_near_you,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Home_NearYou_model currentObj=mData.get(position);
        holder.setData(currentObj,position);

        holder.itemView.setOnClickListener((v)-> {
            Intent a = new Intent(v.getContext(),program_profile.class);
            context.startActivity(a);
        });
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView classname;
        TextView classaddress;
        ImageView imgThumb;
        int position;
        Home_NearYou_model current;

        public MyViewHolder(View itemView) {
            super(itemView);
            classname       =(TextView)itemView.findViewById(R.id.textView_classname_ny);
            classaddress       =(TextView)itemView.findViewById(R.id.textView_address_ny);
            imgThumb    =(ImageView)itemView.findViewById(R.id.ListImage);
        }

        public void setData(Home_NearYou_model current, int position) {
            this.classname.setText(current.getClassname_ny());
            this.classaddress.setText(current.getClassaddress_ny());
            this.imgThumb.setImageResource(current.getImageId());
            this.position=position;
            this.current=current;
        }
    }
}
