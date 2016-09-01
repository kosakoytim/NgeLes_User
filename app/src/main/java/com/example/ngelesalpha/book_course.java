package com.example.ngelesalpha;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class book_course extends ActionBarActivity {

//    Toolbar toolbar;
    private EditText bc_name,bc_phone,bc_email;
    private TextInputLayout layout_name,layout_phone,layout_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_course);

//        toolbar=(Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setTitle("Booking");
//		toolbar.setSubtitle("Subtitle Here");


        //compatibility by java
//		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//			toolbar.setElevation(10f);
//		}

//        toolbar.setNavigationIcon(R.drawable.backward_icon);
//		toolbar.setLogo(R.drawable.ngeles_icon);

        layout_name=(TextInputLayout)findViewById(R.id.layoutname);
        layout_email=(TextInputLayout)findViewById(R.id.layoutemail);
        layout_phone=(TextInputLayout)findViewById(R.id.layoutphone);

        bc_name=(EditText)findViewById(R.id.bc_name);
        bc_phone=(EditText)findViewById(R.id.bc_phone);
        bc_email=(EditText)findViewById(R.id.bc_email);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.id_color2));
        }
    }

    public void validate_book_course(View v){

        if(validate_name()&&validate_phone()&&validate_email())
        {
            Intent i = new Intent(book_course.this,payment_method.class);
            startActivity(i);
        }
    }

    private boolean validate_name()
    {
        if(bc_name.getText().toString().isEmpty())
        {
            layout_name.setError("Nama harus diisi");
            return false;
        }
        else
        {
            layout_name.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validate_phone()
    {
        boolean check=false;
                if(bc_phone.getText().toString().matches("[a-zA-Z]+"))
                {
                    layout_phone.setError("Nomor telepon harus berupa angka");
                    check=false;
                }
                else if(bc_phone.length()==0)
                {
                    layout_phone.setError("Nomor telepon harus diisi");
                    check=false;
                }
                else if(bc_phone.length() < 6 || bc_phone.length() > 13 && bc_phone.length()>0)
                {
                    layout_phone.setError("Nomor telepon tidak valid");
                    check=false;
                }
                else
                {
                    layout_phone.setErrorEnabled(false);
                    check=true;
                }
        return check;
    }

    private boolean validate_email()
    {
        if(validate_email_pattern(bc_email.getText().toString()))
        {
            layout_email.setErrorEnabled(false);
            return true;
        }
        else
        {
            layout_email.setError("Alamat email tidak valid");
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

}
