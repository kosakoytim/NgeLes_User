package com.example.ngelesalpha;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class sign_up extends ActionBarActivity implements View.OnClickListener {

    private EditText et_name,et_email,et_phone_number,et_password,et_confirm_password;
    private TextView sign_up;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

//    //Setup Database
//    DatabaseReference pp_database = FirebaseDatabase.getInstance()
//            .getReferenceFromUrl("https://ngeles-user.firebaseio.com/user/");

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        //Initialize Firebase Authentication and Key Database
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            Intent i = new Intent(sign_up.this,home.class);
            startActivity(i);
        }

        //Loading Sign Up Process
        progressDialog=new ProgressDialog(this);

        //Setup Sign Up
        et_name = (EditText) findViewById(R.id.signup_name);
        et_email = (EditText) findViewById(R.id.signup_email);
        et_phone_number = (EditText) findViewById(R.id.signup_phone_number);
        et_password = (EditText) findViewById(R.id.signup_password);
        et_confirm_password = (EditText) findViewById(R.id.signup_confirm_password);
        sign_up = (TextView)findViewById(R.id.sign_up);
        sign_up.setOnClickListener(this);
    }

    private void sign_up_execute()
    {
        String name=et_name.getText().toString().trim();
        String email=et_email.getText().toString().trim();
        String phone_number=et_phone_number.getText().toString().trim();
        String password=et_password.getText().toString().trim();
        String confirm_password=et_confirm_password.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Masukkan Nama anda",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Masukkan Email anda",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(phone_number)){
            Toast.makeText(this,"Masukkan Nomor Ponsel anda",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Masukkan Password anda",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(confirm_password)){
            Toast.makeText(this,"Masukkan Password anda",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Signing Up..");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
        //                    mAuthListener = new FirebaseAuth.AuthStateListener() {
        //                        @Override
        //                        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                        firebaseAuth=FirebaseAuth.getInstance();
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
        //                            if (user != null) {
                                        DatabaseReference pp_database = FirebaseDatabase.getInstance()
                                                .getReferenceFromUrl("https://ngeles-user.firebaseio.com/user");
                                        String uid = user.getUid();
                                        pp_database.child(uid).child("name").setValue(name);
                                        pp_database.child(uid).child("email").setValue(email);
                                        pp_database.child(uid).child("phone_number").setValue(phone_number);
                                        pp_database.child(uid).child("up_image").setValue("https://firebasestorage.googleapis.com/v0/b/ngeles-user.appspot.com/o/user_info%2Fpppicture.jpg?alt=media&token=d18f5735-cd25-44f2-b6b5-f9e7130ab9bf");
                                        finish();
                                        Intent i = new Intent(sign_up.this, home.class);
                                        startActivity(i);
        //                            }
        //
        //                        }
        //                    };
                        }
                    }

//                        pp_database.child(uid).child("email").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                DatabaseReference childRef = pp_database.child(uid);
//                                childRef.setValue(email);
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//                        }

                });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        firebaseAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            firebaseAuth.removeAuthStateListener(mAuthListener);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.backward_button_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view==sign_up)
        {
            sign_up_execute();
        }
    }
}
