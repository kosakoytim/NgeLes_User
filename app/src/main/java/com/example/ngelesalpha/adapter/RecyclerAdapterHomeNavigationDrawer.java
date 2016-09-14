package com.example.ngelesalpha.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngelesalpha.R;
import com.example.ngelesalpha.learning_method;
import com.example.ngelesalpha.model.NavigationDrawerItem_model;

import java.util.Collections;
import java.util.List;

/**
 * Created by Timothy on 7/4/2016.
 */
public class RecyclerAdapterHomeNavigationDrawer extends RecyclerView.Adapter<RecyclerAdapterHomeNavigationDrawer.MyViewHolder>
{
    private List<NavigationDrawerItem_model> mData;
//            = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public RecyclerAdapterHomeNavigationDrawer(Context context, List<NavigationDrawerItem_model> data){
        this.context=context;
        this.mData=data;
        inflater=LayoutInflater.from(context);
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View view = inflater.inflate(R.layout.list_item_navigation_drawer,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(final MyViewHolder holder,int position)
    {
        NavigationDrawerItem_model currentObj = mData.get(position);
        holder.setData(currentObj,position);
//        holder.UserProfileImage.setImageResource(current.getNavbar_user_profile_image());
//        holder.UserFirstName.setText(current.getNavbar_user_first_name());
//        holder.ItemIcon.setImageResource(current.getNavbar_list_item_icon());
//        holder.ItemTitle.setText(current.getNavbar_list_item_title());
        holder.itemView.setOnClickListener((v)-> {
                switch (position)
                {
                    case 1:
                        Intent a = new Intent(v.getContext(),learning_method.class);
                        context.startActivity(a);
                        break;
                    case 2:
                        Intent b = new Intent(v.getContext(),learning_method.class);
                        context.startActivity(b);
                        break;
                    case 3:
                        Intent c = new Intent(v.getContext(),learning_method.class);
                        context.startActivity(c);
                        break;
                    case 4:
                        Intent d = new Intent(v.getContext(),learning_method.class);
                        context.startActivity(d);
                        break;
                }
        });
    }


    public int getItemCount()
    {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView UserProfileImage;
        TextView UserFirstName;
        ImageView ItemIcon;
        TextView ItemTitle;
        int position;
        NavigationDrawerItem_model current;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            UserProfileImage = (ImageView)itemView.findViewById(R.id.up_image);
            UserFirstName = (TextView)itemView.findViewById(R.id.up_name);
            ItemIcon = (ImageView)itemView.findViewById(R.id.navbar_list_item_icon);
            ItemTitle = (TextView)itemView.findViewById(R.id.navbar_list_item_title);
        }

        public void setData(NavigationDrawerItem_model current, int position)
        {
//            this.UserProfileImage.setImageResource(current.getNavbar_user_profile_image());
//            this.UserFirstName.setText(current.getNavbar_user_first_name());
            this.ItemIcon.setImageResource(current.getNavbar_list_item_icon());
            this.ItemTitle.setText(current.getNavbar_list_item_title());
            this.position=position;
            this.current=current;
        }
    }


}
