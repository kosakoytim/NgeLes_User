package com.example.ngelesalpha.firebase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.ngelesalpha.adapter.RecyclerAdapterSearch;
import com.example.ngelesalpha.model.Search_model;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Timothy on 8/21/2016.
 */
public class SearchClient_firebase {

   DatabaseReference db;
    Boolean saved=null;
    ArrayList<Search_model> search_models=new ArrayList<>();

    public SearchClient_firebase(DatabaseReference db) {
        this.db = db;
    }

    public Boolean save(Search_model search_model)
    {
        if(search_model==null)
        {
            saved=false;
        }
        else {
            try {
                db.child("search").push().setValue(search_model);
                saved=true;
            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }

    //Fetch Data
    private void fetchData(DataSnapshot dataSnapshot)
    {
        search_models.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            Search_model search_model=dataSnapshot.getValue(Search_model.class);
            search_models.add(search_model);
        }
    }

    //Read Data
    public ArrayList<Search_model> retrieve(){
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return search_models;
    }
}
