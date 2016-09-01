package com.example.ngelesalpha;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ngelesalpha.adapter.RecyclerAdapterSchedule;
import com.example.ngelesalpha.model.Schedule_model;

public class my_schedule extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_schedule);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
        getSupportActionBar().setTitle("Schedule");
//		toolbar.setSubtitle("Subtitle Here");


        //compatibility by java
//		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//			toolbar.setElevation(10f);
//		}

        toolbar.setNavigationIcon(R.drawable.backward_icon);
//		toolbar.setLogo(R.drawable.ngeles_icon);
        toolbar.inflateMenu(R.menu.main);

        setUpRecyclerView();
    }

    private void setUpRecyclerView()
    {
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        RecyclerAdapterSchedule adapter=new RecyclerAdapterSchedule(this, Schedule_model.getData());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this);
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
