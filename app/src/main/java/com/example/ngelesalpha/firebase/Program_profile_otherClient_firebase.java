package com.example.ngelesalpha.firebase;

import android.util.Log;

import com.example.ngelesalpha.model.Search_model;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Timothy on 8/21/2016.
 */
public class Program_profile_otherClient_firebase {

    Query query_db;
    String learning_category;
//    String sort_urutkanberdasarkan;
//    String filter_kelastingkat;
    String filter_lamajambelajar;
    String filter_lamaperiodebelajar;
    String filter_hargaperbulan;
    String filter_daerahtempatbelajar;

    //Setup Sortfiltered
    String sortfiltered_lamajambelajar;
    String sortfiltered_lamaperiodebelajar;
    String sortfiltered_hargaperbulan;
    String sortfiltered_daerahtempatbelajar;

    ArrayList<Search_model> search_models=new ArrayList<>();

    public Program_profile_otherClient_firebase(Query query_db, String learning_category, String filter_lamajambelajar, String filter_lamaperiodebelajar, String filter_hargaperbulan, String filter_daerahtempatbelajar){
//  public SearchClient_firebase(DatabaseReference db, String sort_urutkanberdasarkan,String filter_kelastingkat,String filter_lamajambelajar,String filter_lamaperiodebelajar,String filter_hargaperbulan,String filter_daerahtempatbelajar) {
        this.query_db = query_db;
        this.learning_category = learning_category;
//        this.sort_urutkanberdasarkan = sort_urutkanberdasarkan;
//        this.filter_kelastingkat = filter_kelastingkat;
        this.filter_lamajambelajar = filter_lamajambelajar;
        this.filter_lamaperiodebelajar = filter_lamaperiodebelajar;
        this.filter_hargaperbulan = filter_hargaperbulan;
        this.filter_daerahtempatbelajar = filter_daerahtempatbelajar;
    }



    //Fetch Data
    private void fetchData(DataSnapshot dataSnapshot)
    {
        Log.d("class SEARCH_FIREBASE " ,"fetch data from search - "+filter_lamajambelajar+"---"+filter_lamaperiodebelajar+"---"+filter_hargaperbulan+"---"+filter_daerahtempatbelajar);
        search_models.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            String program_id = ds.getKey();
            DatabaseReference sortfilter_db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ngeles-user.firebaseio.com/programprofile/group_class/"+learning_category+"/program/"+ program_id);
//            lama_jam_belajar(sortfilter_db);

            Log.d("class SEARCH_FIREBASE " ,""+"checking match data in"+" "+program_id+"---"+sortfiltered_lamajambelajar+"---"+sortfiltered_lamaperiodebelajar+"---"+sortfiltered_hargaperbulan+"---"+sortfiltered_daerahtempatbelajar);

            //TEMPORARY
            if(filter_lamajambelajar.equals("semua"))
            {
                sortfiltered_lamajambelajar = "semua";
                Log.d("class SEARCH_FIREBASE " ,""+"Lama Jam Belajar"+"-"+sortfiltered_lamajambelajar);
            }
            else
            {
                sortfilter_db.child("status_lama_jam_belajar").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String getdata=dataSnapshot.getValue(String.class);
                        sortfiltered_lamajambelajar = getdata;
                        Log.d("class SEARCH_FIREBASE " ,""+"Lama Jam Belajar"+"-"+sortfiltered_lamajambelajar);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            if(filter_lamaperiodebelajar.equals("semua"))
            {
                sortfiltered_lamaperiodebelajar = "semua";
                Log.d("class SEARCH_FIREBASE " ,""+"Lama Periode Belajar"+"-"+sortfiltered_lamaperiodebelajar);
            }
            else
            {
                sortfilter_db.child("status_lama_periode_belajar").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String getdata=dataSnapshot.getValue(String.class);
                        sortfiltered_lamaperiodebelajar = getdata;
                        Log.d("class SEARCH_FIREBASE " ,""+"Lama Periode Belajar"+"-"+sortfiltered_lamaperiodebelajar);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            if(filter_hargaperbulan.equals("semua"))
            {
                sortfiltered_hargaperbulan = "semua";
                Log.d("class SEARCH_FIREBASE " ,""+"Harga Per Bulan"+"-"+sortfiltered_hargaperbulan);
            }
            else
            {
                sortfilter_db.child("status_harga_per_bulan").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String getdata=dataSnapshot.getValue(String.class);
                        sortfiltered_hargaperbulan = getdata;
                        Log.d("class SEARCH_FIREBASE " ,""+"Harga Per Bulan"+"-"+sortfiltered_hargaperbulan);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            if(filter_daerahtempatbelajar.equals("semua"))
            {
                sortfiltered_daerahtempatbelajar = "semua";
                Log.d("class SEARCH_FIREBASE " ,""+"Daerah Tempat Belajar"+"-"+sortfiltered_daerahtempatbelajar);
            }
            else
            {
                sortfilter_db.child("status_daerah_tempat_belajar").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String getdata=dataSnapshot.getValue(String.class);
                        sortfiltered_daerahtempatbelajar = getdata;
                        Log.d("class SEARCH_FIREBASE " ,""+"Daerah Tempat Belajar"+"-"+sortfiltered_daerahtempatbelajar);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            query_db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if((filter_lamajambelajar.equals(sortfiltered_lamajambelajar))
                            &&(filter_lamaperiodebelajar.equals(sortfiltered_lamaperiodebelajar))
                            &&(filter_hargaperbulan.equals(sortfiltered_hargaperbulan))
                            &&(filter_daerahtempatbelajar.equals(sortfiltered_daerahtempatbelajar)))
                    {
                        Search_model search_model=ds.getValue(Search_model.class);
                        search_models.add(search_model);

                        Log.d("class SEARCH_FIREBASE " ,""+"got match data in"+" "+program_id+" !");
                    }
                    else
                    {
                        Log.d("class SEARCH_FIREBASE " ,""+"unmatch data in"+" "+program_id+" !");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }

//    private void lama_jam_belajar(DatabaseReference sortfilter_db)
//    {
//        if(filter_lamajambelajar.equals("semua"))
//        {
//            sortfiltered_lamajambelajar = "semua";
//            Log.d("class SEARCH_FIREBASE " ,""+"Lama Jam Belajar"+"-"+sortfiltered_lamajambelajar);
//            lama_periode_belajar(sortfilter_db);
//        }
//        else
//        {
//            sortfilter_db.child("status_lama_jam_belajar").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    String getdata=dataSnapshot.getValue(String.class);
//                    sortfiltered_lamajambelajar = getdata;
//                    Log.d("class SEARCH_FIREBASE " ,""+"Lama Jam Belajar"+"-"+sortfiltered_lamajambelajar);
//                    lama_periode_belajar(sortfilter_db);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
//    }
//
//    private void lama_periode_belajar(DatabaseReference sortfilter_db)
//    {
//        if(filter_lamaperiodebelajar.equals("semua"))
//        {
//            sortfiltered_lamaperiodebelajar = "semua";
//            Log.d("class SEARCH_FIREBASE " ,""+"Lama Periode Belajar"+"-"+sortfiltered_lamaperiodebelajar);
//            harga_per_bulan(sortfilter_db);
//        }
//        else
//        {
//            sortfilter_db.child("status_lama_periode_belajar").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    String getdata=dataSnapshot.getValue(String.class);
//                    sortfiltered_lamaperiodebelajar = getdata;
//                    Log.d("class SEARCH_FIREBASE " ,""+"Lama Periode Belajar"+"-"+sortfiltered_lamaperiodebelajar);
//                    harga_per_bulan(sortfilter_db);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
//    }
//
//    private void harga_per_bulan(DatabaseReference sortfilter_db)
//    {
//        if(filter_hargaperbulan.equals("semua"))
//        {
//            sortfiltered_hargaperbulan = "semua";
//            Log.d("class SEARCH_FIREBASE " ,""+"Harga Per Bulan"+"-"+sortfiltered_hargaperbulan);
//            daerah_tempat_belajar(sortfilter_db);
//        }
//        else
//        {
//            sortfilter_db.child("status_harga_per_bulan").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    String getdata=dataSnapshot.getValue(String.class);
//                    sortfiltered_hargaperbulan = getdata;
//                    Log.d("class SEARCH_FIREBASE " ,""+"Harga Per Bulan"+"-"+sortfiltered_hargaperbulan);
//                    daerah_tempat_belajar(sortfilter_db);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
//    }
//
//    private void daerah_tempat_belajar(DatabaseReference sortfilter_db)
//    {
//        if(filter_daerahtempatbelajar.equals("semua"))
//        {
//            sortfiltered_daerahtempatbelajar = "semua";
//            Log.d("class SEARCH_FIREBASE " ,""+"Daerah Tempat Belajar"+"-"+sortfiltered_daerahtempatbelajar);
//        }
//        else
//        {
//            sortfilter_db.child("status_daerah_tempat_belajar").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    String getdata=dataSnapshot.getValue(String.class);
//                    sortfiltered_daerahtempatbelajar = getdata;
//                    Log.d("class SEARCH_FIREBASE " ,""+"Daerah Tempat Belajar"+"-"+sortfiltered_daerahtempatbelajar);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
//    }

    //Read Data
    public ArrayList<Search_model> retrieve(){

        query_db.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Log.d("class SEARCH_FIREBASE " ,""+"fetch data initialize");
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
