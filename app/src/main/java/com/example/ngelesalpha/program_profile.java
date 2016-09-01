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

    CollapsingToolbarLayout pp_title;
    TextView pp_class_days,pp_class_shift,pp_class_charge,pp_id_money,pp_charge_per_blank,pp_id_image,pp_id_color;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private ViewPager viewPager;
    private PagerAdapterProgramProfile adapter;
    private TabLayout tabLayout;
    private String text;

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

    //Setup Database
//    private String pp_id="id_test";
//    DatabaseReference pp_database = FirebaseDatabase.getInstance()
//            .getReferenceFromUrl("https://ngeles-user.firebaseio.com/programprofile/");
    //Initialize Firebase DB
//    SearchClient_firebase scb;
//    DatabaseReference db= FirebaseDatabase.getInstance().getReferenceFromUrl("https://ngeles-user.firebaseio.com/programprofile/");
//    scb=new SearchClient_firebase(db);

    //Setup Toolbar
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_profile);

//        pp_title=(CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar_programprofile);
//        pp_class_days = (TextView) findViewById(R.id.pp_day);
//        pp_class_shift = (TextView) findViewById(R.id.pp_time);
//        pp_class_charge = (TextView) findViewById(R.id.pp_charge);
//        pp_id_money = (TextView) findViewById(R.id.pp_)
//        pp_charge_per_blank = (TextView)findViewById(R.id.pp_chargeper);
//        pp_id_image = (TextView) findViewById()


        //Bind Data
//        pp_title.setTitle(title);
//        pp_class_days.setText(class_days);
//        pp_class_shift.setText(class_shift);
//        pp_class_charge.setText(class_charge);
//        pp_id_money.setText(id_money);
//        pp_charge_per_blank.setText(charge_per_blank);
//        pp_id_image.setText(id_image);
//        pp_id_color.setText(id_color);


        if (Build.VERSION.SDK_INT >= 21) {
            text="#F5F5F5";
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor(text));
            TabLayout pp_tabs_default = (TabLayout) findViewById(R.id.tabs);
            pp_tabs_default.setBackgroundColor(Color.parseColor(text));
            CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar_programprofile);
            collapsingToolbar.setContentScrimColor(Color.parseColor(text));
        }
        ProgressDialog progressDialog;
        progressDialog=new ProgressDialog(this);

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

        progressDialog.setMessage("Sabar Yak..");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                //Setup Data
                getDataPP_logo();
                getDataPP_backgroundimage();
                getDataPP_idcolor1();
                getDataPP_idcolor2();
                getDataPP_programname();
                getDataPP_description();
                getDataPP_address();
                getDataPP_charge();
                getDataPP_chargeper();
                getDataPP_day();
                getDataPP_time();
                getDataPP_payment_description();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }

    public void getDataPP_logo()
    {
                ImageView pp_logo = (ImageView) findViewById(R.id.pp_logo);
                Picasso.with(getApplicationContext()).load(id_image).fit().centerInside().into(pp_logo);
    }

    public void getDataPP_backgroundimage(){
                ImageView pp_header_bg = (ImageView) findViewById(R.id.pp_header_bg);
                Picasso.with(getApplicationContext()).load(background_image).fit().centerInside().into(pp_header_bg);
    }

    public void getDataPP_idcolor1(){
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
    }

    public void getDataPP_idcolor2(){
                LinearLayout pp_ll_bookbutton = (LinearLayout) findViewById(R.id.pp_ll_bookbutton);
                pp_ll_bookbutton.setBackgroundColor(Color.parseColor(id_color2));
    }

    public void getDataPP_programname(){
                //Setup Collapsing Toolbar
                CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar_programprofile);
                collapsingToolbar.setTitle(title);
    }

    public void getDataPP_description(){
                TextView pp_description=(TextView)findViewById(R.id.pp_description);
                pp_description.setText(description);
    }

    public void getDataPP_day() {
                TextView pp_class_day=(TextView)findViewById(R.id.pp_day);
                pp_class_day.setText(class_days);
    }

    public void getDataPP_time() {
                TextView pp_class_shift=(TextView)findViewById(R.id.pp_time);
                pp_class_shift.setText(class_shift);
    }

    public void getDataPP_address() {
                TextView pp_address=(TextView)findViewById(R.id.pp_address);
                pp_address.setText(address);
    }

    public void getDataPP_payment_description() {
                TextView pp_payment_description=(TextView)findViewById(R.id.pp_payment_description);
                pp_payment_description.setText(payment_description);
    }

    public void getDataPP_charge() {
                TextView pp_charge=(TextView)findViewById(R.id.pp_charge);
                pp_charge.setText(class_charge);
    }

    public void getDataPP_chargeper() {
                TextView pp_chargeper=(TextView)findViewById(R.id.pp_chargeper);
                pp_chargeper.setText(charge_per_blank);
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
        addData(new ProgramProfileTestimonial_fragment(), "Testimonial");
    }

    private void addData(Fragment fragment,String title){
        fragmentList.add(fragment);
        titleList.add(title);
    }
}
