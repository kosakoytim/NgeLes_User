package com.example.ngelesalpha;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by Timothy on 7/1/2016.
 */
public class splash_screen extends ActionBarActivity {

    private FirebaseAuth firebaseAuth;
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    LoginResult loginResult;
//    AccessToken token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //check if we're running on android 5.0 or higher
        if (Build.VERSION.SDK_INT >= 21) {
            String text = "#02606c";
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor(text));
        }
        else //for below API 21
        {
            //implement this feature without material design

        }

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //Initialize Firebase Authentication
                firebaseAuth= FirebaseAuth.getInstance();
                if(firebaseAuth.getCurrentUser()!=null){
                    finish();
                    Intent i = new Intent(splash_screen.this,Index.class);

//                    AuthCredential fb_credential = FacebookAuthProvider.getCredential(token.getToken());
//                    if((loginResult.getAccessToken())!=null)
//                    {
//                        i.putExtra("LOGIN_BY","Facebook");
//                    }
//                    else
//                    {
//                        i.putExtra("LOGIN_BY","Email");
//                    }
                    startActivity(i);
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
                else
                {
                    finish();
                    Intent i = new Intent(splash_screen.this,login.class);
                    startActivity(i);
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
