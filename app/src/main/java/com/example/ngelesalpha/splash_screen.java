package com.example.ngelesalpha;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Timothy on 7/1/2016.
 */
public class splash_screen extends ActionBarActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

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

        //Initialize Firebase Authentication
        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            Intent i = new Intent(splash_screen.this,home.class);
            startActivity(i);
        }
        else
        {
            Intent i = new Intent(splash_screen.this,login.class);
            startActivity(i);
        }

    }
}
