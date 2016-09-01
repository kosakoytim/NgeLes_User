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
import com.example.ngelesalpha.learning_method;
import com.example.ngelesalpha.model.Schedule_model;
import com.example.ngelesalpha.model.Search_model;
import com.example.ngelesalpha.program_profile;

import java.util.List;

/**
 * Created by Timothy on 7/3/2016.
 */
public class RecyclerAdapterSchedule extends RecyclerView.Adapter<RecyclerAdapterSchedule.MyViewHolder> {

    private List<Schedule_model> mData;
    private LayoutInflater mInflater;
    private Context context;

    public RecyclerAdapterSchedule(Context context, List<Schedule_model> data){
        this.context=context;
        this.mData=data;
        this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.list_item_schedule,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Schedule_model currentObj=mData.get(position);
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
        TextView classday;
        TextView classdate;
        TextView classroom;
        TextView classtime;
        int position;
        Schedule_model current;

        public MyViewHolder(View itemView) {
            super(itemView);
            classname       =(TextView)itemView.findViewById(R.id.textView_classname);
            classday       =(TextView)itemView.findViewById(R.id.textView_classday);
            classdate     =(TextView)itemView.findViewById(R.id.textView_classdate);
            classroom     =(TextView)itemView.findViewById(R.id.textView_classroom);
            classtime    =(TextView)itemView.findViewById(R.id.textView_classtime);
        }

        public void setData(Schedule_model current, int position) {
            this.classname.setText(current.getText_classname_sch());
            this.classday.setText(current.getText_classday_sch());
            this.classdate.setText(current.getText_classdate_sch());
            this.classroom.setText(current.getText_classroom_sch());
            this.classtime.setText(current.getText_classtime_sch());
            this.position=position;
            this.current=current;
        }
    }
}
