package com.example.ngelesalpha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ngelesalpha.adapter.PagerAdapterProgramProfile;
import com.example.ngelesalpha.firebase.SearchClient_firebase;
import com.example.ngelesalpha.fragment.ProgramProfileContact_fragment;
import com.example.ngelesalpha.fragment.ProgramProfileInformation_fragment;
import com.example.ngelesalpha.fragment.ProgramProfileTestimonial_fragment;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class program_profile extends AppCompatActivity {
//
//    CollapsingToolbarLayout pp_title;
//    TextView pp_class_days,pp_class_shift,pp_class_charge,pp_id_money,pp_charge_per_blank,pp_id_image,pp_id_color;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private ViewPager viewPager;
    private PagerAdapterProgramProfile adapter;
    private TabLayout tabLayout;
//    private String text;

    //Setup Toolbar
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_profile);

        //Get Intent
        Intent i=this.getIntent();
        //Receive Data
        String title=i.getExtras().getString("TITLE_KEY");
        String class_days=i.getExtras().getString("CLASS_DAYS_KEY");
        String class_shift=i.getExtras().getString("CLASS_SHIFT_KEY");
        String class_charge=i.getExtras().getString("CLASS_CHARGE_KEY");
        String id_money=i.getExtras().getString("ID_MONEY_KEY");
        String charge_per_blank=i.getExtras().getString("CHARGE_PER_BLANK_KEY");
        String id_image=i.getExtras().getString("ID_IMAGE_KEY");
        String id_color=i.getExtras().getString("ID_COLOR_KEY");
        String address=i.getExtras().getString("ADDRESS_KEY");
        String learning_category=i.getExtras().getString("LEARNING_CATEGORY_KEY");
        String branch=i.getExtras().getString("BRANCH_KEY");
        String study_duration=i.getExtras().getString("STUDY_DURATION_KEY");
        String study_period=i.getExtras().getString("STUDY_PERIOD_KEY");
        String payment_description=i.getExtras().getString("PAYMENT_DESCRIPTION_KEY");
        String description=i.getExtras().getString("DESCRIPTION_KEY");
        String learning_method=i.getExtras().getString("LEARNING_METHOD_KEY");
        String age_min=i.getExtras().getString("AGE_MIN_KEY");
        String age_max=i.getExtras().getString("AGE_MAX_KEY");
        String background_image=i.getExtras().getString("BACKGROUND_IMAGE_KEY");
        String id_color2=i.getExtras().getString("ID_COLOR2_KEY");
        String status_recommended=i.getExtras().getString("STATUS_RECOMMENDED_KEY");
        String address_state_name=i.getExtras().getString("ADDRESS_STATE_NAME_KEY");
        String count_registrant=i.getExtras().getString("COUNT_REGISTRANT_KEY");
        String registration_start=i.getExtras().getString("REGISTRATION_START_KEY");
        String registration_end=i.getExtras().getString("REGISTRATION_END_KEY");
        String contact_email_address=i.getExtras().getString("CONTACT_EMAIL_ADDRESS_KEY");
        String contact_facebook=i.getExtras().getString("CONTACT_FACEBOOK_KEY");
        String contact_phone=i.getExtras().getString("CONTACT_PHONE_KEY");
        String contact_web_page=i.getExtras().getString("CONTACT_WEB_PAGE_KEY");


//
//        if (Build.VERSION.SDK_INT >= 21) {
//            text="#F5F5F5";
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(Color.parseColor(text));
//            TabLayout pp_tabs_default = (TabLayout) findViewById(R.id.tabs);
//            pp_tabs_default.setBackgroundColor(Color.parseColor(text));
//            CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar_programprofile);
//            collapsingToolbar.setContentScrimColor(Color.parseColor(text));
//        }
//        ProgressDialog progressDialog;
//        progressDialog=new ProgressDialog(this);

        //Setup Toolbar
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Test");
        toolbar.inflateMenu(R.menu.backward_button_only);
        toolbar.setNavigationIcon(R.drawable.backward_icon);

        //Setup Tabs
        viewPager = (ViewPager)findViewById(R.id.vp_pp_tabs);
        tabLayout=(TabLayout)findViewById(R.id.tabs);
        prepareDataResource();
        adapter = new PagerAdapterProgramProfile(getSupportFragmentManager(),fragmentList,titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

                //Setup Data
                //---get logo
                ImageView pp_logo = (ImageView) findViewById(R.id.pp_logo);
                Picasso.with(getApplicationContext()).load(id_image).fit().centerInside().into(pp_logo);

                //---get background_image
                ImageView pp_header_bg = (ImageView) findViewById(R.id.pp_header_bg);
                Picasso.with(getApplicationContext()).load(background_image).fit().centerInside().into(pp_header_bg);

                //---get idcolor1
                if (Build.VERSION.SDK_INT >= 21) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.setStatusBarColor(Color.parseColor(id_color));
                }
                TabLayout pp_tabs = (TabLayout) findViewById(R.id.tabs);
                pp_tabs.setBackgroundColor(Color.parseColor(id_color));
                CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar_programprofile);
                collapsingToolbar.setContentScrimColor(Color.parseColor(id_color));

                //---get idcolor2
                LinearLayout pp_ll_bookbutton = (LinearLayout) findViewById(R.id.pp_ll_bookbutton);
                pp_ll_bookbutton.setBackgroundColor(Color.parseColor(id_color2));

                //---get program name/title
//                CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar_programprofile);
                collapsingToolbar.setTitle(title);

                //---get description
//                TextView pp_description=(TextView)findViewById(R.id.pp_description);
//                pp_description.setText(description);

                //---get day
//                TextView pp_class_day=(TextView)findViewById(R.id.pp_day);
//                pp_class_day.setText(class_days);

                //---get time
//                TextView pp_class_shift=(TextView)findViewById(R.id.pp_time);
//                pp_class_shift.setText(class_shift);

                //---get address
//                TextView pp_address=(TextView)findViewById(R.id.pp_address);
//                pp_address.setText(address);

                //---get payment_description
//                TextView pp_payment_description=(TextView)findViewById(R.id.pp_payment_description);
//                pp_payment_description.setText(payment_description);

                //---get charge
//                TextView pp_charge=(TextView)findViewById(R.id.pp_charge);
//                pp_charge.setText(class_charge);

                //---get charge per
//                TextView pp_chargeper=(TextView)findViewById(R.id.pp_chargeper);
//                pp_chargeper.setText(charge_per_blank);

    }

    public void gotoActivity(View v){
        switch (v.getId())
        {
            case R.id.next_to_book_course:
                Intent i = new Intent(program_profile.this,book_course.class);
                startActivity(i);
                break;
        }
    }


    private void prepareDataResource(){
        addData(new ProgramProfileInformation_fragment(), "Information");
        addData(new ProgramProfileContact_fragment(), "Contact");
//        addData(new ProgramProfileTestimonial_fragment(), "Testimonial");
    }

    private void addData(Fragment fragment,String title){
        fragmentList.add(fragment);
        titleList.add(title);
    }
}
