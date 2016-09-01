package com.example.ngelesalpha;

import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ngelesalpha.adapter.PagerAdapterLearningMethod;

import java.util.ArrayList;
import java.util.List;

public class learning_method extends ActionBarActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learning_method);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Choose Method");
        toolbar.setSubtitle("Subtitle Here");
        toolbar.inflateMenu(R.menu.learning_method);

        //Prepare the data model
        List<learning_method_data_model> itemList = getDataList();
        //Locate the viewpager in main activity xml
        ViewPager viewPager = (ViewPager)findViewById(R.id.learning_method_viewpager);
        //Create instance of pageradapter
        PagerAdapterLearningMethod adapterLearningMethod = new PagerAdapterLearningMethod(this,itemList);
        //Binds the adapter to the viewpager
        viewPager.setAdapter(adapterLearningMethod);

    }

    public List<learning_method_data_model> getDataList(){

        List<learning_method_data_model> itemList = new ArrayList<>();
        int[] imageIds = new int[]{
                R.drawable.test_group_class,R.drawable.test_private_lesson,R.drawable.test_online_learning
        };

        String[] titles = new String[]{
                "Group","Private","Online"
        };

        String[] titles2 = new String[]{
                "Class","Lesson","Learning"
        };


        int count = imageIds.length;
        for(int i=0;i<count;i++)
        {
            itemList.add(new learning_method_data_model(imageIds[i],titles[i],titles2[i]));
        }

        return itemList;
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

    public void ok_button(View v){
        Intent i = new Intent(learning_method.this,learning_category.class);
        startActivity(i);
    }
}
