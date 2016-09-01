package com.example.ngelesalpha.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ngelesalpha.R;
import com.example.ngelesalpha.click_listener.Search_itemclicklistener;
import com.example.ngelesalpha.model.Search_model;
import com.example.ngelesalpha.program_profile;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Timothy on 7/3/2016.
 */
public class RecyclerAdapterSearch extends RecyclerView.Adapter<RecyclerAdapterSearch.SearchClient_holder> {

    private
    Context c;
    ArrayList<Search_model> search_models;

    public RecyclerAdapterSearch(Context c, ArrayList<Search_model> search_models) {
        this.c = c;
        this.search_models = search_models;
    }

    @Override
    public SearchClient_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(c).inflate(R.layout.list_item_search,parent,false);
        return new SearchClient_holder(v);
    }

    @Override
    public void onBindViewHolder(SearchClient_holder holder, int position) {
        final Search_model s =search_models.get(position);
        holder.titleTxt.setText(s.getTitle());
        holder.class_daysTxt.setText(s.getClass_days());
        holder.class_shiftTxt.setText(s.getClass_shift());
        holder.class_chargeTxt.setText(s.getClass_charge());
        holder.id_moneyTxt.setText(s.getId_money());
        holder.charge_per_blankTxt.setText(s.getCharge_per_blank());
        holder.id_colorTxt.setBackgroundColor(Color.parseColor(s.getId_color()));
        Uri uri = Uri.parse(s.getImageId());
        Picasso.with(c).load(uri).fit().centerInside().placeholder(R.drawable.styleonline).into(holder.id_imageTxt);
//        holder.id_imageTxt.setImageResource();


        holder.setSearch_itemclicklistener(new Search_itemclicklistener() {
            @Override
            public void onItemClick(int pos) {
                //Open Activity
                openProgramProfileDetail(
                        //0
                        s.getTitle(),
                        //1
                        s.getClass_days(),
                        //2
                        s.getClass_shift(),
                        //3
                        s.getClass_charge(),
                        //4
                        s.getId_money(),
                        //5
                        s.getCharge_per_blank(),
                        //6
                        s.getImageId(),
                        //7
                        s.getId_color(),
                        //8
                        s.getAddress(),
                        //9
                        s.getLearning_category(),
                        //10
                        s.getBranch(),
                        //11
                        s.getStudy_duration(),
                        //12
                        s.getStudy_period(),
                        //13
                        s.getPayment_description(),
                        //14
                        s.getDescription(),
                        //15
                        s.getLearning_method(),
                        //16
                        s.getAge_min(),
                        //17
                        s.getAge_max(),
                        //18
                        s.getBackground_image(),
                        //19
                        s.getId_color2()
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return search_models.size();
    }

    private void openProgramProfileDetail(String...details)
    {
        Intent i=new Intent(c,program_profile.class);
        i.putExtra("TITLE_KEY",details[0]);
        i.putExtra("CLASS_DAYS_KEY",details[1]);
        i.putExtra("CLASS_SHIFT_KEY",details[2]);
        i.putExtra("CLASS_CHARGE_KEY",details[3]);
        i.putExtra("ID_MONEY_KEY",details[4]);
        i.putExtra("CHARGE_PER_BLANK_KEY",details[5]);
        i.putExtra("ID_IMAGE",details[6]);
        i.putExtra("ID_COLOR",details[7]);
        i.putExtra("ADDRESS",details[8]);
        i.putExtra("LEARNING_CATEGORY",details[9]);
        i.putExtra("BRANCH",details[10]);
        i.putExtra("STUDY_DURATION",details[11]);
        i.putExtra("STUDY_PERIOD",details[12]);
        i.putExtra("PAYMENT_DESCRIPTION",details[13]);
        i.putExtra("DESCRIPTION",details[14]);
        i.putExtra("LEARNING_METHOD",details[15]);
        i.putExtra("AGE_MIN",details[16]);
        i.putExtra("AGE_MAX",details[17]);
        i.putExtra("BACKGROUND_IMAGE",details[18]);
        i.putExtra("ID_COLOR2",details[19]);
        c.startActivity(i);
    }

    /**
     * Created by Timothy on 8/21/2016.
     */
    public static class SearchClient_holder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView titleTxt;
        TextView class_daysTxt;
        TextView class_shiftTxt;
        TextView class_chargeTxt;
        TextView id_moneyTxt;
        TextView charge_per_blankTxt;
        ImageView id_imageTxt;
        LinearLayout id_colorTxt;

        Search_itemclicklistener search_itemclicklistener;


        public SearchClient_holder(View itemView) {
            super(itemView);

            titleTxt = (TextView)itemView.findViewById(R.id.search_title);
            class_daysTxt = (TextView)itemView.findViewById(R.id.search_class_days);
            class_shiftTxt = (TextView)itemView.findViewById(R.id.search_class_shift);
            class_chargeTxt = (TextView)itemView.findViewById(R.id.search_class_charge);
            id_moneyTxt = (TextView)itemView.findViewById(R.id.search_id_money);
            charge_per_blankTxt = (TextView)itemView.findViewById(R.id.search_charge_per_blank);
            id_imageTxt = (ImageView)itemView.findViewById(R.id.search_image);
            id_colorTxt = (LinearLayout)itemView.findViewById(R.id.search_color);
            itemView.setOnClickListener(this);
        }

        public void setSearch_itemclicklistener(Search_itemclicklistener search_itemclicklistener)
        {
            this.search_itemclicklistener = search_itemclicklistener;
        }

        @Override
        public void onClick(View view) {
            this.search_itemclicklistener.onItemClick(this.getLayoutPosition());
        }
    }
}
