package com.example.ngelesalpha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngelesalpha.R;
import com.example.ngelesalpha.model.WaitingConfirmation_model;

import java.util.List;

/**
 * Created by Timothy on 7/3/2016.
 */
public class RecyclerAdapterWaitingConfirmation extends RecyclerView.Adapter<RecyclerAdapterWaitingConfirmation.MyViewHolder> {

    private List<WaitingConfirmation_model> mData;
    private LayoutInflater mInflater;

    public RecyclerAdapterWaitingConfirmation(Context context, List<WaitingConfirmation_model> data){
        this.mData=data;
        this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.list_item_waiting_confirmation_class,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WaitingConfirmation_model currentObj=mData.get(position);
        holder.setData(currentObj,position);
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView response_1;
        TextView response_2;
        ImageView imgThumb;
        int position;
        WaitingConfirmation_model current;

        public MyViewHolder(View itemView) {
            super(itemView);
            title       =(TextView)itemView.findViewById(R.id.TextViewTitle);
            response_1       =(TextView)itemView.findViewById(R.id.TextViewResponse1);
            response_2       =(TextView)itemView.findViewById(R.id.TextViewResponse2);
            imgThumb    =(ImageView)itemView.findViewById(R.id.ListImage);
        }

        public void setData(WaitingConfirmation_model current, int position) {
            this.title.setText(current.getTitle());
            this.response_1.setText(current.getResponse_1());
            this.response_2.setText(current.getResponse_2());
            this.imgThumb.setImageResource(current.getImageId());
            this.position=position;
            this.current=current;
        }
    }
}
