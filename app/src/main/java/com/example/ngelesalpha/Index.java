package com.example.ngelesalpha;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngelesalpha.adapter.RecyclerAdapterHomePopular;
import com.example.ngelesalpha.adapter.RecyclerAdapterHomeRecommended;
import com.example.ngelesalpha.firebase.Home_popularClient_firebase;
import com.example.ngelesalpha.firebase.Home_recommendedClient_firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class Index extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView find_course;
    private LinearLayout edit_profile;


    //Setup Toolbar
    Toolbar toolbar;

    //Setup Firebase
    DatabaseReference db;
    Home_recommendedClient_firebase hrcb;
    Home_popularClient_firebase hpcb;
    RecyclerAdapterHomeRecommended adapterHomeRecommended;
    RecyclerAdapterHomePopular adapterHomePopular;
    RecyclerView rv_hr,rv_hp;

    private LinearLayout spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize Firebase Authentication
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this, login.class));
        }
//        FirebaseUser user= firebaseAuth.getCurrentUser();

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    setUpUserProfileImage();
                    setUpUserName();

                } else {
                    // User is signed out

                }
            }
        };

        // [END auth_state_listener]
        //Setup Profile
        setUpUserProfileImage();
        setUpUserName();

        //Setup Find Course
        find_course=(TextView)findViewById(R.id.find_course);
        find_course.setOnClickListener(this);

        setUpToolbar();


        spinner = (LinearLayout)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);

        setUpRecyclerView_recommended2();
        setUpRecyclerView_popular2();



//		setUpRecyclerView_nearyou();
//		setUpRecyclerView_schedule();
//		setUpRecyclerView_assignment();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.index, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setUpRecyclerView_popular2()
    {
        //---------------------------------------------
        //Initialize Firebase DB
        db= FirebaseDatabase.getInstance().getReferenceFromUrl("https://ngeles-user.firebaseio.com/programprofile/POPULAR/");
        hpcb=new Home_popularClient_firebase(db);
        adapterHomePopular = new RecyclerAdapterHomePopular(this,hpcb.retrieve());

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                spinner.setVisibility(View.GONE);
                setUpRecyclerView_popular();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void setUpRecyclerView_popular()
    {
        //Initialize Recycler View
        rv_hp=(RecyclerView)findViewById(R.id.recyclerView_home_popular);
        LinearLayoutManager mLinearLayoutManagerHorizontal_2 = new LinearLayoutManager(this);
        mLinearLayoutManagerHorizontal_2.setOrientation(LinearLayoutManager.HORIZONTAL);

        //Initialize Adapter
        rv_hp.setLayoutManager(mLinearLayoutManagerHorizontal_2);
        rv_hp.setAdapter(adapterHomePopular);
        rv_hp.setItemAnimator(new DefaultItemAnimator());

    }

    public void setUpRecyclerView_recommended2()
    {
        //---------------------------------------------
        //Initialize Firebase DB
        db= FirebaseDatabase.getInstance().getReferenceFromUrl("https://ngeles-user.firebaseio.com/programprofile/RECOMMENDED/");
        hrcb=new Home_recommendedClient_firebase(db);
        adapterHomeRecommended = new RecyclerAdapterHomeRecommended(this,hrcb.retrieve());

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                spinner.setVisibility(View.GONE);
                setUpRecyclerView_recommended();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void setUpRecyclerView_recommended()
    {
        //Initialize Recycler View
        rv_hr=(RecyclerView)findViewById(R.id.recyclerView_home_recommended);
        LinearLayoutManager mLinearLayoutManagerHorizontal = new LinearLayoutManager(this);
        mLinearLayoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);

        //Initialize Adapter
        rv_hr.setLayoutManager(mLinearLayoutManagerHorizontal);
        rv_hr.setAdapter(adapterHomeRecommended);
        rv_hr.setItemAnimator(new DefaultItemAnimator());
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_my_schedule) {
//            Intent i = new Intent(this,my_schedule.class);
//            startActivity(i);
//        } else if (id == R.id.nav_my_assignment) {
//            Intent i = new Intent(this,assignment.class);
//            startActivity(i);
//        } else if (id == R.id.nav_active_class) {
//            Intent i = new Intent(this,active_class.class);
//            startActivity(i);
//        } else if (id == R.id.nav_class_history) {
//            Intent i = new Intent(this,history.class);
//            startActivity(i);
        if (id == R.id.nav_class_status) {
            Intent i = new Intent(this,waiting_confirmation.class);
            startActivity(i);
        } else if (id == R.id.nav_settings) {
            Intent i = new Intent(this,settings.class);
            startActivity(i);
        } else if(id==R.id.nav_help_and_support){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://nge-les.com/wordpress/hubungi-kami/"));
            startActivity(intent);
        } else if(id==R.id.nav_join_us){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://nge-les.com/wordpress/daftarkan-institusilayanan-anda/"));
            startActivity(intent);
        } else if(id==R.id.nav_log_out) {
            firebaseAuth.signOut();
            finish();
            Intent i = new Intent(this,login.class);
            startActivity(i);
        } else if(id==R.id.nav_edit_profile) {
            Intent i = new Intent(this,edit_profile.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setUpUserProfileImage()
    {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String uid = user.getUid();
            DatabaseReference getUp_image = FirebaseDatabase.getInstance()
                    .getReferenceFromUrl("https://ngeles-user.firebaseio.com/user");
            getUp_image.child(uid).child("up_image").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text=dataSnapshot.getValue(String.class);
                    Transformation transformation = new RoundedTransformationBuilder()
                            .borderColor(Color.BLACK)
                            .borderWidthDp(1)
                            .cornerRadiusDp(100)
                            //previously 30
                            .oval(false)
                            .build();

                    ImageView home_up_image = (ImageView) findViewById(R.id.up_image);
                    Picasso.with(getApplicationContext()).load(text).fit().centerInside().transform(transformation).into(home_up_image);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
//        }

    }

    private void setUpUserName()
    {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String uid = user.getUid();
//        if(login_by.equals("Facebook"))
//        {
//            String display_name = user.getDisplayName();
//            TextView home_up_name = (TextView)findViewById(R.id.up_name);
//            home_up_name.setText(display_name);
//            Toast.makeText(Index.this, "Logged By Facebook", Toast.LENGTH_SHORT).show();
//        }
//        else if(login_by.equals("Email"))
//        {
            DatabaseReference getUp_name = FirebaseDatabase.getInstance()
                    .getReferenceFromUrl("https://ngeles-user.firebaseio.com/user");
            getUp_name.child(uid).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text=dataSnapshot.getValue(String.class);
                    TextView home_up_name = (TextView)findViewById(R.id.up_name);
                    home_up_name.setText(text);
//                    Toast.makeText(Index.this, "Logged By Email", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
//        }
    }

    private void setUpToolbar()
    {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }

//    @Override
    public void onClick(View view) {
        if(view==find_course)
        {
            Intent i = new Intent(this,learning_category.class);
            startActivity(i);
        }
//        if(view==edit_profile)
//        {
//            Intent i = new Intent(this,edit_profile.class);
//            startActivity(i);
//        }

    }
}
