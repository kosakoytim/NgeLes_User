package com.example.ngelesalpha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ngelesalpha.adapter.PagerAdapterProgramProfile;
import com.example.ngelesalpha.firebase.SearchClient_firebase;
import com.example.ngelesalpha.fragment.ProgramProfileContact_fragment;
import com.example.ngelesalpha.fragment.ProgramProfileInformation_fragment;
import com.example.ngelesalpha.fragment.ProgramProfileTestimonial_fragment;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

//public class program_profile extends AppCompatActivity implements OnMapReadyCallback {
public class program_profile extends AppCompatActivity {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private ViewPager viewPager;
    private PagerAdapterProgramProfile adapter;
    private TabLayout tabLayout;

    //Setup Toolbar Variable
    Toolbar toolbar;

    //Setup Loading Variable
    private LinearLayout spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_profile);

        //Get Bundle
        Bundle bundle = getIntent().getExtras();
        //Receive Data
        String stringdata = bundle.getString("KEY_NAME");
        String title = bundle.getString("TITLE_KEY");
        String class_days = bundle.getString("CLASS_DAYS_KEY");
        String class_shift = bundle.getString("CLASS_SHIFT_KEY");
        String class_charge = bundle.getString("CLASS_CHARGE_KEY");
        String id_money = bundle.getString("ID_MONEY_KEY");
        String charge_per_blank = bundle.getString("CHARGE_PER_BLANK_KEY");
        String id_image = bundle.getString("ID_IMAGE_KEY");
        String id_color = bundle.getString("ID_COLOR_KEY");
        String address = bundle.getString("ADDRESS_KEY");
        String learning_category = bundle.getString("LEARNING_CATEGORY_KEY");
        String branch = bundle.getString("BRANCH_KEY");
        String study_duration = bundle.getString("STUDY_DURATION_KEY");
        String study_period = bundle.getString("STUDY_PERIOD_KEY");
        String payment_description = bundle.getString("PAYMENT_DESCRIPTION_KEY");
        String description = bundle.getString("DESCRIPTION_KEY");
        String learning_method = bundle.getString("LEARNING_METHOD_KEY");
        String age_min = bundle.getString("AGE_MIN_KEY");
        String age_max = bundle.getString("AGE_MAX_KEY");
        String background_image = bundle.getString("BACKGROUND_IMAGE_KEY");
        String id_color2 = bundle.getString("ID_COLOR2_KEY");
        String status_recommended = bundle.getString("STATUS_RECOMMENDED_KEY");
        String address_state_name = bundle.getString("ADDRESS_STATE_NAME_KEY");
        String count_registrant = bundle.getString("COUNT_REGISTRANT_KEY");
        String registration_start = bundle.getString("REGISTRATION_START_KEY");
        String registration_end = bundle.getString("REGISTRATION_END_KEY");
        String contact_email_address = bundle.getString("CONTACT_EMAIL_ADDRESS_KEY");
        String contact_facebook = bundle.getString("CONTACT_FACEBOOK_KEY");
        String contact_phone = bundle.getString("CONTACT_PHONE_KEY");
        String contact_web_page = bundle.getString("CONTACT_WEB_PAGE_KEY");

        //Prepare Data Resource
        Bundle bundle_pp_information = new Bundle();
        bundle_pp_information.putString("DESCRIPTION_KEY", description);
        bundle_pp_information.putString("CLASS_DAYS_KEY", class_days);
        bundle_pp_information.putString("CLASS_SHIFT_KEY", class_shift);
        bundle_pp_information.putString("ADDRESS_KEY", address);
        bundle_pp_information.putString("PAYMENT_DESCRIPTION_KEY", payment_description);
        bundle_pp_information.putString("CLASS_CHARGE_KEY", class_charge);
        bundle_pp_information.putString("CHARGE_PER_BLANK_KEY", charge_per_blank);
        bundle_pp_information.putString("ID_MONEY_KEY", id_money);
        ProgramProfileInformation_fragment fragment_pp_information = new ProgramProfileInformation_fragment();
        fragment_pp_information.setArguments(bundle_pp_information);

        addData(fragment_pp_information, "Information");

        Bundle bundle_pp_contact = new Bundle();
        bundle_pp_contact.putString("CONTACT_EMAIL_ADDRESS_KEY", contact_email_address);
        bundle_pp_contact.putString("CONTACT_FACEBOOK_KEY", contact_facebook);
        bundle_pp_contact.putString("CONTACT_PHONE_KEY", contact_phone);
        bundle_pp_contact.putString("CONTACT_WEB_PAGE_KEY", contact_web_page);
        ProgramProfileContact_fragment fragment_pp_contact = new ProgramProfileContact_fragment();
        fragment_pp_contact.setArguments(bundle_pp_contact);

        addData(fragment_pp_contact, "Contact");

        //initialize get data of other program here
        addData(fragment_pp_contact, "Others");

        //Loading Layout
        spinner = (LinearLayout)findViewById(R.id.progressBar_program_profile);
        spinner.setVisibility(View.VISIBLE);

        //Setup Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Test");
        toolbar.inflateMenu(R.menu.backward_button_only);
        toolbar.setNavigationIcon(R.drawable.backward_icon);

        //Setup Map
//        SupportMapFragment mapFragment =
//                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);


        //Setup Tabs
        viewPager = (ViewPager) findViewById(R.id.vp_pp_tabs);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        prepareDataResource();
        adapter = new PagerAdapterProgramProfile(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        LinearLayout bottomButton = (LinearLayout)findViewById(R.id.bottom_button);
        bottomButton.setVisibility(View.GONE);

        //Setup Data
        //---get logo
        ImageView pp_logo = (ImageView) findViewById(R.id.pp_logo);
        Picasso.with(getApplicationContext()).load(id_image).fit().centerInside().into(pp_logo);

        //---get background_image
        ImageView pp_header_bg = (ImageView) findViewById(R.id.pp_header_bg);
        Picasso.with(getApplicationContext()).load(background_image).fit().centerInside().into(pp_header_bg, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                spinner.setVisibility(View.GONE);
                bottomButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {
                spinner.setVisibility(View.GONE);
                bottomButton.setVisibility(View.VISIBLE);
            }
        });

        //---get idcolor1
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor(id_color));
        }
        TabLayout pp_tabs = (TabLayout) findViewById(R.id.tabs);
        pp_tabs.setBackgroundColor(Color.parseColor(id_color));
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar_programprofile);
        collapsingToolbar.setContentScrimColor(Color.parseColor(id_color));
        spinner.setBackgroundColor(Color.parseColor(id_color));

        //---get idcolor2
        LinearLayout pp_ll_bookbutton = (LinearLayout) findViewById(R.id.pp_ll_bookbutton);
        pp_ll_bookbutton.setBackgroundColor(Color.parseColor(id_color2));

        //---get program name/title
//                CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collapsingToolbar_programprofile);
        collapsingToolbar.setTitle(title);

    }

//    public void onMapReady(GoogleMap map) {
//        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
//        map.getUiSettings().setZoomGesturesEnabled(true);
//    }

    public void gotoActivity(View v) {
        switch (v.getId()) {
            case R.id.next_to_book_course:
                Intent intent= getIntent();
                String id_color2=intent.getExtras().getString("ID_COLOR2_KEY");
                Intent i = new Intent(program_profile.this, book_course.class);
                i.putExtra("ID_COLOR2_KEY",id_color2);
                startActivity(i);
                break;
        }
    }


    private void addData(Fragment fragment,String title){
        
        fragmentList.add(fragment);
        titleList.add(title);
    }
}
