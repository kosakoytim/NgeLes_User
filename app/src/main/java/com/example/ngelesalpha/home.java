package com.example.ngelesalpha;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngelesalpha.adapter.RecyclerAdapterHomePopular;
import com.example.ngelesalpha.adapter.RecyclerAdapterHomeRecommended;
import com.example.ngelesalpha.adapter.RecyclerAdapterHome_Assignment;
import com.example.ngelesalpha.adapter.RecyclerAdapterHome_NearYou;
import com.example.ngelesalpha.adapter.RecyclerAdapterHome_Schedule;
import com.example.ngelesalpha.firebase.Home_popularClient_firebase;
import com.example.ngelesalpha.firebase.Home_recommendedClient_firebase;
import com.example.ngelesalpha.fragment.NavigationDrawer_fragment;
import com.example.ngelesalpha.model.Home_Assignment_model;
import com.example.ngelesalpha.model.Home_NearYou_model;
import com.example.ngelesalpha.model.Home_Schedule_model;
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

public class home extends ActionBarActivity implements View.OnClickListener {

	private FirebaseAuth firebaseAuth;
	private TextView find_course;
	private ImageView edit_profile;

	//Temporary Here
	private Button btnLogout;
	private Button btnEditProfile;

	//Setup Toolbar
	Toolbar toolbar;

	//Setup Firebase
	DatabaseReference db;
	Home_recommendedClient_firebase hrcb;
	Home_popularClient_firebase hpcb;
	RecyclerAdapterHomeRecommended adapterHomeRecommended;
	RecyclerAdapterHomePopular adapterHomePopular;
	RecyclerView rv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		//Initialize Firebase Authentication
		firebaseAuth=FirebaseAuth.getInstance();
		if(firebaseAuth.getCurrentUser()==null)
		{
			finish();
			startActivity(new Intent(this, login.class));
		}
		FirebaseUser user= firebaseAuth.getCurrentUser();

		//Setup Profile
		setUpUserProfileImage();
		setUpUserName();

		//Setup Logout
		btnLogout=(Button)findViewById(R.id.btn_logout);
		btnLogout.setOnClickListener(this);

		//Setup Find Course
		find_course=(TextView)findViewById(R.id.find_course);
		find_course.setOnClickListener(this);

		//Setup Edit Profile
		edit_profile=(ImageView)findViewById(R.id.edit_profile);
		edit_profile.setOnClickListener(this);
		//temp
		btnEditProfile=(Button)findViewById(R.id.btn_editprofile);
		btnEditProfile.setOnClickListener(this);

		setUpToolbar();

		setUpDrawer();


		//Set Home Content
		setUpRecyclerView_recommended();
		setUpRecyclerView_popular();
//		setUpRecyclerView_nearyou();
//		setUpRecyclerView_schedule();
//		setUpRecyclerView_assignment();

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
	}

	private void setUpUserName()
	{
		FirebaseUser user = firebaseAuth.getCurrentUser();
		String uid = user.getUid();
		DatabaseReference getUp_name = FirebaseDatabase.getInstance()
				.getReferenceFromUrl("https://ngeles-user.firebaseio.com/user");
		getUp_name.child(uid).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				String text=dataSnapshot.getValue(String.class);
				TextView home_up_name = (TextView)findViewById(R.id.up_name);
				home_up_name.setText(text);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
	}

	private void setUpToolbar()
	{
		toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("");
	}

//	------------------FOR NEXT UPDATE
//	private void setUpRecyclerView_nearyou()
//	{
//		RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView_home_near_you);
//		RecyclerAdapterHome_NearYou adapter=new RecyclerAdapterHome_NearYou(this, Home_NearYou_model.getData());
//		recyclerView.setAdapter(adapter);
//
//		LinearLayoutManager mLinearLayoutManagerHorizontal = new LinearLayoutManager(this);
//		mLinearLayoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
//		recyclerView.setLayoutManager(mLinearLayoutManagerHorizontal);
//
//		recyclerView.setItemAnimator(new DefaultItemAnimator());
//	}

	private void setUpRecyclerView_popular()
	{
		//---------------------------------------------
		//Initialize Recycler View
		rv=(RecyclerView)findViewById(R.id.recyclerView_home_popular);
		LinearLayoutManager mLinearLayoutManagerHorizontal = new LinearLayoutManager(this);
		mLinearLayoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
		rv.setLayoutManager(mLinearLayoutManagerHorizontal);

		//Initialize Firebase DB
		db= FirebaseDatabase.getInstance().getReferenceFromUrl("https://ngeles-user.firebaseio.com/programprofile/");
		hpcb=new Home_popularClient_firebase(db);

		//Initialize Adapter
		adapterHomePopular = new RecyclerAdapterHomePopular(this,hpcb.retrieve());
		rv.setAdapter(adapterHomePopular);
		rv.setItemAnimator(new DefaultItemAnimator());
	}

	private void setUpRecyclerView_recommended()
	{
		//---------------------------------------------
		//Initialize Recycler View
		rv=(RecyclerView)findViewById(R.id.recyclerView_home_recommended);
		LinearLayoutManager mLinearLayoutManagerHorizontal = new LinearLayoutManager(this);
		mLinearLayoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
		rv.setLayoutManager(mLinearLayoutManagerHorizontal);

		//Initialize Firebase DB
		db= FirebaseDatabase.getInstance().getReferenceFromUrl("https://ngeles-user.firebaseio.com/programprofile/");
		hrcb=new Home_recommendedClient_firebase(db);

		//Initialize Adapter
		adapterHomeRecommended = new RecyclerAdapterHomeRecommended(this,hrcb.retrieve());
		rv.setAdapter(adapterHomeRecommended);
		rv.setItemAnimator(new DefaultItemAnimator());
	}

//	------------------FOR NEXT UPDATE
//	private void setUpRecyclerView_schedule()
//	{
//		RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView_home_schedule);
//		RecyclerAdapterHome_Schedule adapter=new RecyclerAdapterHome_Schedule(this, Home_Schedule_model.getData());
//		recyclerView.setAdapter(adapter);
//
//		LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this);
//		mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
//		recyclerView.setLayoutManager(mLinearLayoutManagerVertical);
//
//		recyclerView.setItemAnimator(new DefaultItemAnimator());
//	}

//	------------------FOR NEXT UPDATE
//	private void setUpRecyclerView_assignment()
//	{
//		RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView_home_assignment);
//		RecyclerAdapterHome_Assignment adapter=new RecyclerAdapterHome_Assignment(this, Home_Assignment_model.getData());
//		recyclerView.setAdapter(adapter);
//
//		LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this);
//		mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
//		recyclerView.setLayoutManager(mLinearLayoutManagerVertical);
//
//		recyclerView.setItemAnimator(new DefaultItemAnimator());
//	}

	private void setUpDrawer()
	{
		NavigationDrawer_fragment drawer_fragment = (NavigationDrawer_fragment) getSupportFragmentManager().findFragmentById(R.id.navbar_drawer_fragment);
		DrawerLayout drawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
		drawer_fragment.setUpDrawer(R.id.navbar_drawer_fragment, drawerLayout, toolbar);

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}



	@Override
	public void onClick(View view) {
		if(view==find_course)
		{
			Intent i = new Intent(home.this,learning_category.class);
			startActivity(i);
		}
		if(view==edit_profile)
		{
			Intent i = new Intent(home.this,edit_profile.class);
			startActivity(i);
		}
		if(view==btnLogout)
		{
			firebaseAuth.signOut();

//			// We can logout from facebook by calling following method
//			PrefUtils.clearCurrentUser(home.this);
//			LoginManager.getInstance().logOut();

			finish();
			Intent i = new Intent(home.this,login.class);
			startActivity(i);
		}
		if(view==btnEditProfile)
		{
			Intent i = new Intent(home.this,edit_profile.class);
			startActivity(i);
		}

	}
}
