package com.example.ngelesalpha;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sign_up extends ActionBarActivity implements View.OnClickListener {

    private EditText signup_et_name,signup_et_email,signup_et_phone_number,signup_et_password,signup_et_confirm_password;
    private TextInputLayout signup_layout_name,signup_layout_phone,signup_layout_email,signup_layout_password,signup_layout_confirm_password;
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
            Intent i = new Intent(sign_up.this,Index.class);
            startActivity(i);
        }

        //Loading Sign Up Process
        progressDialog=new ProgressDialog(this);

        //Setup Sign Up
        signup_layout_name=(TextInputLayout)findViewById(R.id.signup_layout_name);
        signup_layout_email=(TextInputLayout)findViewById(R.id.signup_layout_email);
        signup_layout_phone=(TextInputLayout)findViewById(R.id.signup_layout_phone);
        signup_layout_password=(TextInputLayout)findViewById(R.id.signup_layout_password);
        signup_layout_confirm_password=(TextInputLayout)findViewById(R.id.signup_layout_confirm_password);

        signup_et_name = (EditText) findViewById(R.id.signup_et_name);
        signup_et_email = (EditText) findViewById(R.id.signup_et_email);
        signup_et_phone_number = (EditText) findViewById(R.id.signup_et_phone_number);
        signup_et_password = (EditText) findViewById(R.id.signup_et_password);
        signup_et_confirm_password = (EditText) findViewById(R.id.signup_et_confirm_password);
        sign_up = (TextView)findViewById(R.id.sign_up);
        sign_up.setOnClickListener(this);
    }

    private void sign_up_execute()
    {
        String name=signup_et_name.getText().toString().trim();
        String email=signup_et_email.getText().toString().trim();
        String phone_number=signup_et_phone_number.getText().toString().trim();
        String password=signup_et_password.getText().toString().trim();
        String confirm_password=signup_et_confirm_password.getText().toString().trim();


        if(validate_name()&&validate_email()&&validate_phone()&&validate_password()) {

            progressDialog.setMessage("Signing Up..");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //                    mAuthListener = new FirebaseAuth.AuthStateListener() {
                                //                        @Override
                                //                        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                firebaseAuth = FirebaseAuth.getInstance();
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
                                Intent i = new Intent(sign_up.this, Index.class);
                                startActivity(i);
                            }
                        }
                    });
        }
    }


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

    private boolean validate_name()
    {
        if(signup_et_name.getText().toString().isEmpty())
        {
            signup_layout_name.setError("Nama harus diisi");
            return false;
        }
        else
        {
            signup_layout_name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validate_phone()
    {
        boolean check=false;
        if(signup_et_phone_number.getText().toString().matches("[a-zA-Z]+"))
        {
            signup_layout_phone.setError("Nomor telepon harus berupa angka");
            check=false;
        }
        else if(signup_et_phone_number.length()==0)
        {
            signup_layout_phone.setError("Nomor telepon harus diisi");
            check=false;
        }
        else if(signup_et_phone_number.length() < 6 || signup_et_phone_number.length() > 13 && signup_et_phone_number.length()>0)
        {
            signup_layout_phone.setError("Nomor telepon tidak valid");
            check=false;
        }
        else
        {
            signup_layout_phone.setErrorEnabled(false);
            check=true;
        }
        return check;
    }

    private boolean validate_email()
    {
        if(validate_email_pattern(signup_et_email.getText().toString()))
        {
            signup_layout_email.setErrorEnabled(false);
            return true;
        }
        else
        {
            signup_layout_email.setError("Alamat email tidak valid");
            return false;
        }
    }

    public static boolean validate_email_pattern(final String mailAddress)
    {
        Pattern pattern;
        Matcher matcher;

        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(mailAddress);
        return matcher.matches();
    }

    private boolean validate_password()
    {
        boolean pstatus = false;
        if (signup_et_password.getText().toString()!= null && signup_et_confirm_password.getText().toString()!= null)
        {
            if ((signup_et_password.getText().toString()).equals(signup_et_confirm_password.getText().toString()))
            {
                pstatus = true;
            }
            else
            {
                signup_layout_confirm_password.setError("Password tidak sama");
                pstatus = false;
            }
        }
        else if(signup_et_password.getText().toString()== null)
        {
            signup_layout_password.setError("Password harus diisi");
            pstatus = false;
        }
        else if(signup_et_confirm_password.getText().toString()== null)
        {
            signup_layout_password.setError("Konfirmasi password harus diisi");
            pstatus = false;
        }
        return pstatus;
    }
}
