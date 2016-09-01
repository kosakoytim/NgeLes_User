package com.example.ngelesalpha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngelesalpha.R;
import com.example.ngelesalpha.model.HistoryClass_model;

import java.util.List;

/**
 * Created by Timothy on 7/3/2016.
 */
public class RecyclerAdapterHistoryClass extends RecyclerView.Adapter<RecyclerAdapterHistoryClass.MyViewHolder> {

    private List<HistoryClass_model> mData;
    private LayoutInflater mInflater;

    public RecyclerAdapterHistoryClass(Context context, List<HistoryClass_model> data){
        this.mData=data;
        this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.list_item_history_class,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HistoryClass_model currentObj=mData.get(position);
        holder.setData(currentObj,position);
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView startDate;
        TextView endDate;
        ImageView imgThumb;
        int position;
        HistoryClass_model current;

        public MyViewHolder(View itemView) {
            super(itemView);
            title       =(TextView)itemView.findViewById(R.id.TextViewTitle);
            startDate       =(TextView)itemView.findViewById(R.id.TextViewStartDate);
            endDate       =(TextView)itemView.findViewById(R.id.TextViewEndDate);
            imgThumb    =(ImageView)itemView.findViewById(R.id.ListImage);
        }

        public void setData(HistoryClass_model current, int position) {
            this.title.setText(current.getTitle());
            this.startDate.setText(current.getStart_date());
            this.endDate.setText(current.getEnd_date());
            this.imgThumb.setImageResource(current.getImageId());
            this.position=position;
            this.current=current;
        }
    }
}
