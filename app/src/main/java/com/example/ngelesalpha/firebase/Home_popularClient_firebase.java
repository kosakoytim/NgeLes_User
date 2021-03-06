package com.example.ngelesalpha.firebase;

import com.example.ngelesalpha.model.HomePopular_model;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Timothy on 8/21/2016.
 */
public class Home_popularClient_firebase {

   DatabaseReference db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ngeles-user.firebaseio.com/programprofile/search");
    ArrayList<HomePopular_model> homePopular_models=new ArrayList<>();

    public Home_popularClient_firebase(DatabaseReference db) {
        this.db = db;
    }

    //Fetch Data
    private void fetchData(DataSnapshot dataSnapshot)
    {
        homePopular_models.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            HomePopular_model homePopular_model=ds.getValue(HomePopular_model.class);
            homePopular_models.add(homePopular_model);
        }
    }

    //Read Data
    public ArrayList<HomePopular_model> retrieve(){
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
        return homePopular_models;
    }
}
