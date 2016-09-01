package com.example.ngelesalpha;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class edit_profile extends ActionBarActivity implements View.OnClickListener {

    //Setup Content
    private TextView edit_profile;
    private EditText ep_et_name;
    private EditText ep_et_email;
    private EditText ep_et_phone_number;

    //Setup Firebase
    private FirebaseAuth firebaseAuth;
    DatabaseReference pp_database = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://ngeles-user.firebaseio.com/user");

    //Setup Toolbar
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

//        toolbar=(Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setTitle(" ");
//		toolbar.setSubtitle("Subtitle Here");


        //compatibility by java
//		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//			toolbar.setElevation(10f);
//		}

        //Setup Toolbar
//        toolbar.setNavigationIcon(R.drawable.backward_icon);

        //Setup Firebase
        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        final String uid = user.getUid();
        setUpUserProfileImage();
        ep_et_name = (EditText)findViewById(R.id.ep_et_name);
        ep_et_email = (EditText)findViewById(R.id.ep_et_email);
        ep_et_phone_number = (EditText)findViewById(R.id.ep_et_phone_number);
        pp_database.child(uid).child("name").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        String text = dataSnapshot.getValue(String.class);
                        ep_et_name.setText(text);
                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        pp_database.child(uid).child("email").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        String text = dataSnapshot.getValue(String.class);
                        ep_et_email.setText(text);
                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        pp_database.child(uid).child("phone_number").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        String text = dataSnapshot.getValue(String.class);
                        ep_et_phone_number.setText(text);
                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        edit_profile = (TextView)findViewById(R.id.edit_my_profile);
        edit_profile.setOnClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }



    public void edit_my_profile(){
        String name=ep_et_name.getText().toString().trim();
        String email=ep_et_email.getText().toString().trim();
        String phone_number=ep_et_phone_number.getText().toString().trim();

        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        final String uid = user.getUid();
        pp_database.child(uid).child("name").setValue(name);
        pp_database.child(uid).child("email").setValue(email);
        pp_database.child(uid).child("phone_number").setValue(phone_number);
        Intent i = new Intent(edit_profile.this,home.class);
        startActivity(i);
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
                        .borderWidthDp(3)
                        .cornerRadiusDp(100)
                        .oval(false)
                        .build();

                ImageView ep_up_image = (ImageView) findViewById(R.id.ep_up_image);
                Picasso.with(getApplicationContext()).load(text).fit().centerInside().transform(transformation).into(ep_up_image);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view==edit_profile)
        {
            edit_my_profile();
        }
    }
}
