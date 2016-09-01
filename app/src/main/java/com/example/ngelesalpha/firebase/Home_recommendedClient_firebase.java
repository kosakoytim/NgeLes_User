package com.example.ngelesalpha.firebase;

import com.example.ngelesalpha.model.HomeRecommended_model;
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
public class Home_recommendedClient_firebase {

   DatabaseReference db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ngeles-user.firebaseio.com/programprofile/search");
    ArrayList<HomeRecommended_model> homeRecommended_models=new ArrayList<>();

    public Home_recommendedClient_firebase(DatabaseReference db) {
        this.db = db;
    }

    //Fetch Data
    private void fetchData(DataSnapshot dataSnapshot)
    {
        homeRecommended_models.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            HomeRecommended_model homeRecommended_model=dataSnapshot.getValue(HomeRecommended_model.class);
            homeRecommended_models.add(homeRecommended_model);
        }
    }

    //Read Data
    public ArrayList<HomeRecommended_model> retrieve(){
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
        return homeRecommended_models;
    }
}
