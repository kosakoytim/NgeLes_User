package com.example.ngelesalpha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngelesalpha.R;
import com.example.ngelesalpha.model.ActiveClass_model;

import java.util.List;

/**
 * Created by Timothy on 7/3/2016.
 */
public class RecyclerAdapterActiveClass extends RecyclerView.Adapter<RecyclerAdapterActiveClass.MyViewHolder> {

    private List<ActiveClass_model> mData;
    private LayoutInflater mInflater;

    public RecyclerAdapterActiveClass(Context context, List<ActiveClass_model> data){
        this.mData=data;
        this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.list_item_active_class,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ActiveClass_model currentObj=mData.get(position);
        holder.setData(currentObj,position);
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView next_meet;
        TextView time;
        ImageView imgThumb;
        int position;
        ActiveClass_model current;

        public MyViewHolder(View itemView) {
            super(itemView);
            title       =(TextView)itemView.findViewById(R.id.TextViewTitle);
            next_meet       =(TextView)itemView.findViewById(R.id.TextViewNextMeet);
            time       =(TextView)itemView.findViewById(R.id.TextViewTime);
            imgThumb    =(ImageView)itemView.findViewById(R.id.ListImage);
        }

        public void setData(ActiveClass_model current, int position) {
            this.title.setText(current.getTitle());
            this.next_meet.setText(current.getNext_meet());
            this.time.setText(current.getTime());
            this.imgThumb.setImageResource(current.getImageId());
            this.position=position;
            this.current=current;
        }
    }
}
