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
import com.example.ngelesalpha.assignment;
import com.example.ngelesalpha.model.Home_Assignment_model;

import java.util.List;

/**
 * Created by Timothy on 7/3/2016.
 */
public class RecyclerAdapterHome_Assignment extends RecyclerView.Adapter<RecyclerAdapterHome_Assignment.MyViewHolder> {

    private List<Home_Assignment_model> mData;
    private LayoutInflater mInflater;
    private Context context;

    public RecyclerAdapterHome_Assignment(Context context, List<Home_Assignment_model> data){
        this.context=context;
        this.mData=data;
        this.mInflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.list_item_home_assignment,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Home_Assignment_model currentObj=mData.get(position);
        holder.setData(currentObj,position);

        holder.itemView.setOnClickListener((v)-> {
            Intent a = new Intent(v.getContext(),assignment.class);
            context.startActivity(a);
        });
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView classname;
        TextView assignmenttitle;
        TextView assignmentdeadline;
        ImageView imgThumb;
        ImageView checkbox;
        int position;
        Home_Assignment_model current;

        public MyViewHolder(View itemView) {
            super(itemView);
            classname       =(TextView)itemView.findViewById(R.id.textView_classname_as);
            assignmenttitle       =(TextView)itemView.findViewById(R.id.textView_assignment_title);
            assignmentdeadline       =(TextView)itemView.findViewById(R.id.textView_assignment_deadline);
            imgThumb    =(ImageView)itemView.findViewById(R.id.ListImage);
            checkbox =(ImageView)itemView.findViewById(R.id.checkbox_as);
        }

        public void setData(Home_Assignment_model current, int position) {
            this.classname.setText(current.getClassname_as());
            this.assignmenttitle.setText(current.getClassassignmenttitle_as());
            this.assignmentdeadline.setText(current.getClassassignmentdeadline_as());
            this.imgThumb.setImageResource(current.getImageId());
            this.checkbox.setImageResource(current.getCheckbox_as());
            this.position=position;
            this.current=current;
        }
    }
}
