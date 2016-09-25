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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ngelesalpha.adapter.ExpendableListAdapter;
import com.example.ngelesalpha.adapter.RecyclerAdapterSearch;
import com.example.ngelesalpha.firebase.SearchClient_firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class search extends ActionBarActivity {

    //Setup Toolbar
    Toolbar toolbar;

    //Setup Firebase
    DatabaseReference db;
    SearchClient_firebase scb;
    RecyclerAdapterSearch adapterSearch;
    RecyclerView rv;

    //Setup Loading Bar
    private LinearLayout spinner;

    Query query_db;

    //Setup Get from Sort Filter
//    String sort_urutkanberdasarkan="recommended";
//    String filter_kelastingkat="semua";


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

        spinner = (LinearLayout) findViewById(R.id.progressBar_search);
        spinner.setVisibility(View.VISIBLE);

        setUpRecyclerView2();

        floatingactionbutton();
    }

    public void setUpRecyclerView2()
    {
        //Setup filter
        String filter_lamajambelajar="semua";
        String filter_lamaperiodebelajar="semua";
        String filter_hargaperbulan="semua";
        String filter_daerahtempatbelajar="semua";

        Log.d("class SEARCH " ,""+filter_lamajambelajar+"---"+filter_lamaperiodebelajar+"---"+filter_hargaperbulan+"---"+filter_daerahtempatbelajar);

        //Get Intent Data
        Intent intent= getIntent();
        String learning_category=intent.getExtras().getString("CATEGORY_KEY");
        String get_intent_from=intent.getExtras().getString("GET_INTENT_FROM");
        db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ngeles-user.firebaseio.com/programprofile/group_class/"+ learning_category);
        query_db=db;

        //Intent from Sort and filter
//        sort_urutkanberdasarkan= intent.getExtras().getString("SORT_URUTKANBERDASARKAN_KEY");
//        filter_kelastingkat= intent.getExtras().getString("FILTER_KELASTINGKAT_KEY");

        if(get_intent_from.equals("from_sort_and_filter"))
        {
            Log.d("class SEARCH " ,""+get_intent_from);
            filter_lamajambelajar= intent.getExtras().getString("FILTER_LAMAJAMBELAJAR_KEY");
            filter_lamaperiodebelajar= intent.getExtras().getString("FILTER_LAMAPERIODEBELAJAR_KEY");
            filter_hargaperbulan= intent.getExtras().getString("FILTER_HARGAPERBULAN_KEY");
            filter_daerahtempatbelajar= intent.getExtras().getString("FILTER_DAERAHTEMPATBELAJAR_KEY");
            Log.d("class SEARCH " ,"get intent success --- "+filter_lamajambelajar+"---"+filter_lamaperiodebelajar+"---"+filter_hargaperbulan+"---"+filter_daerahtempatbelajar);
        }

        scb=new SearchClient_firebase(query_db,learning_category,filter_lamajambelajar,filter_lamaperiodebelajar,filter_hargaperbulan,filter_daerahtempatbelajar);

        adapterSearch = new RecyclerAdapterSearch(this,scb.retrieve());

        query_db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                spinner.setVisibility(View.GONE);
                setUpRecyclerView();
                Log.d("class SEARCH " ,""+"finished loading");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void setUpRecyclerView()
    {
        Log.d("class SEARCH " ,""+"set up recycler view");
        //Initialize Recycler View
        rv=(RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this);
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);

        //Initialize Adapter
        rv.setLayoutManager(mLinearLayoutManagerVertical);
        rv.setAdapter(adapterSearch);
        rv.setItemAnimator(new DefaultItemAnimator());
    }



    private void floatingactionbutton()
    {
        //---------------------------------------------
        //Initialize Firebase DB
        Intent intent= getIntent();
        String child=intent.getExtras().getString("CATEGORY_KEY");

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab_search);
        fab.setOnClickListener((view)->
        {
            String pass_category_to_sortfilter=child;
            Intent i = new Intent(this,search_filtersort.class);
            i.putExtra("CATEGORY_KEY", pass_category_to_sortfilter);
            startActivity(i);
        });
    }

    //TESTING
    private void displayInputDialog(){
        Dialog d=new Dialog(this);
        d.setTitle("Search");
        d.setContentView(R.layout.search_filtersort);
        d.show();
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
