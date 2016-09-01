package com.example.ngelesalpha;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ngelesalpha.adapter.RecycleAdapterLearningCategory;
import com.example.ngelesalpha.model.LearningCategory_model;

public class learning_category extends ActionBarActivity {

//    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_category);

//        toolbar=(Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setTitle("Pilih Pelajaran");
//		toolbar.setSubtitle("Subtitle Here");


        //compatibility by java
//		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//			toolbar.setElevation(10f);
//		}

//        toolbar.setNavigationIcon(R.drawable.backward_icon);
//		toolbar.setLogo(R.drawable.ngeles_icon);
        setUpRecyclerView();


    }

    private void setUpRecyclerView()
    {
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        RecycleAdapterLearningCategory adapter=new RecycleAdapterLearningCategory(this, LearningCategory_model.getData());
        recyclerView.setAdapter(adapter);

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(mGridLayoutManager);

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
