package com.example.ngelesalpha;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class login extends ActionBarActivity implements View.OnClickListener {

    private EditText et_email,et_password;
    private TextView log_in,belum_sign_up;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

//    private LoginButton loginButton;

//    private CallbackManager callbackManager;
//    private TextView btnLogin;
//    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

//        //Initialize Facebook Login
//        if(PrefUtils.getCurrentUser(login.this) != null){
//
//            Intent homeIntent = new Intent(login.this, home.class);
//
//            startActivity(homeIntent);
//
//            finish();
//        }

        //Initialize Firebase Authentication
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            Intent i = new Intent(login.this,Index.class);
            startActivity(i);
        }

        //Loading Login Process
        progressDialog=new ProgressDialog(this);

        //Set up login
        et_email = (EditText) findViewById(R.id.login_email);
        et_password = (EditText) findViewById(R.id.login_password);
        log_in = (TextView)findViewById(R.id.log_in);
        log_in.setOnClickListener(this);

        //Set up page signup
        belum_sign_up = (TextView)findViewById(R.id.sign_up);
        belum_sign_up.setOnClickListener(this);
    }



    private void log_in(){
        String email=et_email.getText().toString().trim();
        String password=et_password.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Masukkan Email anda",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Masukkan Password anda", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging In..");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            Intent i = new Intent(login.this,Index.class);
                            startActivity(i);
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view==log_in){
            log_in();
        }
        if(view==belum_sign_up){
            finish();
            Intent i = new Intent(login.this,sign_up.class);
            startActivity(i);
        }
    }
}
