package com.example.ngelesalpha;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngelesalpha.adapter.RecyclerAdapterSearch;
import com.example.ngelesalpha.firebase.SearchClient_firebase;
import com.example.ngelesalpha.model.Search_model;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class search extends ActionBarActivity {

    //Setup Toolbar
    Toolbar toolbar;

    //Setup Firebase
    DatabaseReference db;
    SearchClient_firebase scb;
    RecyclerAdapterSearch adapterSearch;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Available Program");

        //compatibility by java
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
			toolbar.setElevation(10f);
		}

        toolbar.setNavigationIcon(R.drawable.backward_icon);

        toolbar.inflateMenu(R.menu.main);



//        setUpRecyclerView();
        //Initialize Recycler View
        rv=(RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this);
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(mLinearLayoutManagerVertical);

        //Initialize Firebase DB
        Intent intent= getIntent();
        String child=intent.getExtras().getString("CATEGORY_KEY");
        db= FirebaseDatabase.getInstance().getReferenceFromUrl("https://ngeles-user.firebaseio.com/programprofile/group_class/"+ child);
        scb=new SearchClient_firebase(db);

        //Initialize Adapter
        adapterSearch = new RecyclerAdapterSearch(this,scb.retrieve());
        rv.setAdapter(adapterSearch);
        rv.setItemAnimator(new DefaultItemAnimator());


        displayInputDialog();
        floatingactionbutton();
    }

    private void floatingactionbutton()
    {
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_search);
        fab.setOnClickListener((view)->
        {
            displayInputDialog();
        });
    }

//    private void setUpRecyclerView()
//    {
//        //Initialize Recycler View
//        rv=(RecyclerView)findViewById(R.id.recyclerView);
//        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this);
//        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
//        rv.setLayoutManager(mLinearLayoutManagerVertical);
//
//        //Initialize Firebase DB
//        Intent intent= getIntent();
//        String child=intent.getExtras().getString("CATEGORY_KEY");
//        db= FirebaseDatabase.getInstance().getReferenceFromUrl("https://ngeles-user.firebaseio.com/programprofile/group_class/"+ child);
//        scb=new SearchClient_firebase(db);
//
//        //Initialize Adapter
//        adapterSearch = new RecyclerAdapterSearch(this,scb.retrieve());
//        rv.setAdapter(adapterSearch);
//        rv.setItemAnimator(new DefaultItemAnimator());
//    }


    //TESTING
    private void displayInputDialog(){
//        Dialog d=new Dialog(this);
//        d.setTitle("Save To Firebase");
//        d.setContentView(R.layout.input_dialog);
//
//        titleEditTxt=(EditText) d.findViewById(R.id.titleEditText);
//        class_daysEditTxt=(EditText) d.findViewById(R.id.class_daysEditText);
//        class_shiftEditTxt=(EditText) d.findViewById(R.id.class_shiftEditText);
//        class_chargeTxt=(EditText) d.findViewById(R.id.class_chargeEditText);
//        id_moneyTxt=(EditText) d.findViewById(R.id.id_moneyEditText);
//        charge_per_blankTxt=(EditText) d.findViewById(R.id.charge_per_blankEditText);
//        EditText id_imageTxt=(EditText) d.findViewById(R.id.titleEditText);
//        id_colorTxt=(EditText) d.findViewById(R.id.id_colorEditText);
//        Button saveBtn = (Button) d.findViewById(R.id.saveBtn);

        //Save
//        saveBtn.setOnClickListener((v)-> {
            //Get Data
//            String title=titleEditTxt.getText().toString();
//            String class_days=class_daysEditTxt.getText().toString();
//            String class_shift=class_shiftEditTxt.getText().toString();
//            String class_charge=class_chargeTxt.getText().toString();
//            String id_money=id_moneyTxt.getText().toString();
//            String charge_per_blank=charge_per_blankTxt.getText().toString();
//            String id_image=id_imageTxt.getText().toString();
//            String id_color=id_colorTxt.getText().toString();

            //Set Data
//            Search_model s=new Search_model();
//            s.setTitle(title);
//            s.setClass_days(class_days);
//            s.setClass_shift(class_shift);
//            s.setClass_charge(class_charge);
//            s.setId_money(id_money);
//            s.setCharge_per_blank(charge_per_blank);
//            s.setImageId(id_image);
//            s.setId_color(id_color);

//            if(title!=null && title.length()>0)
//            {
//                if(scb.save(s))
//                {
//                    titleEditTxt.setText("");
//                    class_daysEditTxt.setText("");
//                    class_shiftEditTxt.setText("");
//                    class_chargeTxt.setText("");
//                    id_moneyTxt.setText("");
//                    charge_per_blankTxt.setText("");
//                    id_colorTxt.setText("");
//
//                    adapterSearch = new RecyclerAdapterSearch(search.this,scb.retrieve());
//                    rv.setAdapter(adapterSearch);
//                }
//            }
//            else
//            {
//                Toast.makeText(search.this, "Title must not be empty", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//        d.show();
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
}
