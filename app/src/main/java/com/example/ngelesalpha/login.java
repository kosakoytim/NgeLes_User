package com.example.ngelesalpha;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.net.Uri;
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
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private static final String TAG = "FacebookLogin";
    private CallbackManager mCallbackManager;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login);


        //Initialize Firebase Authentication
        firebaseAuth=FirebaseAuth.getInstance();
//        if(firebaseAuth.getCurrentUser()!=null){
//            finish();
//            Intent i = new Intent(login.this,Index.class);
//            startActivity(i);
//        }

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
//                updateUI(user);

                // [END_EXCLUDE]
            }
        };
        // [END auth_state_listener]

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.button_facebook_login);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });

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

    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }
    // [END on_stop_remove_listener]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
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

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                            //Get Data Profile Name and Picture
                            firebaseAuth = FirebaseAuth.getInstance();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            DatabaseReference pp_database = FirebaseDatabase.getInstance()
                                    .getReferenceFromUrl("https://ngeles-user.firebaseio.com/user");
                            String uid = user.getUid();
                            Uri uri=user.getPhotoUrl();
                            String string_photourl;
                            string_photourl = uri.toString();
                            pp_database.child(uid).child("name").setValue(user.getDisplayName());
                            pp_database.child(uid).child("email").setValue(user.getEmail());
                            pp_database.child(uid).child("phone_number").setValue("");
                            pp_database.child(uid).child("up_image").setValue(string_photourl);

                            finish();
                            Intent i = new Intent(login.this,Index.class);
                            startActivity(i);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...

                    }
                });
    }

//    private void updateUI(FirebaseUser user) {
//        if (user != null) {
//            mStatusTextView.setText(getString(R.string.facebook_status_fmt, user.getDisplayName()));
//            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
//
//            findViewById(R.id.button_facebook_login).setVisibility(View.GONE);
//        }
//    }

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
