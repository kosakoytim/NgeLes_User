package com.example.ngelesalpha;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngelesalpha.adapter.ExpendableListAdapter;
import com.example.ngelesalpha.firebase.SearchClient_firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Timothy on 9/8/2016.
 */
public class search_filtersort extends ActionBarActivity {
    //Setup Toolbar
    Toolbar toolbar;

    //Setup Search Filter and Sort
    ExpendableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    //Setup Radio Button
    private RadioGroup radioGroup0,radioGroup1,radioGroup2,radioGroup3,radioGroup4,radioGroup5;
    private RadioButton radioButton0,radioButton1,radioButton2,radioButton3,radioButton4,radioButton5;

    //Setup Passing Data SortFilter
    String sort_urutkanberdasarkan; // store the text corresponding to  the RadioButton which is clicked
    String filter_kelastingkat;
    String filter_lamajambelajar="semua";
    String filter_lamaperiodebelajar="semua";
    String filter_hargaperbulan="semua";
    String filter_daerahtempatbelajar="semua";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_filtersort);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sort and Filter");
        //compatibility by java
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            toolbar.setElevation(10f);
        }
        toolbar.setNavigationIcon(R.drawable.backward_icon);
        toolbar.inflateMenu(R.menu.main);
        // get the listview
//        LayoutInflater inputdialog = getLayoutInflater();
//        View search_filtersort = inputdialog.inflate(R.layout.search_filtersort, null);
        expListView = (ExpandableListView)findViewById(R.id.elv_search_sortfilter);

        radioGroup0 = (RadioGroup) findViewById(R.id.rg_urutkanberdasarkan);
        radioGroup1 = (RadioGroup) findViewById(R.id.rg_kelastingkat);
        radioGroup2 = (RadioGroup) findViewById(R.id.rg_lamajambelajar);
        radioGroup3 = (RadioGroup) findViewById(R.id.rg_lamaperiodebelajar);
        radioGroup4 = (RadioGroup) findViewById(R.id.rg_hargaperbulan);
        radioGroup5 = (RadioGroup) findViewById(R.id.rg_daerahtempatbelajar);

        // preparing list data
        prepareListData();

        listAdapter = new ExpendableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
//                // TODO Auto-generated method stub
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT)
//                        .show();
                return false;
            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Urutkan Berdasarkan...");
        listDataHeader.add("Kelas/Tingkat");
        listDataHeader.add("Lama Jam Belajar");
        listDataHeader.add("Lama Periode Belajar");
        listDataHeader.add("Harga Per Bulan");
        listDataHeader.add("Daerah Tempat Belajar");

        // Adding child data
        List<String> urutkanBerdasarkan = new ArrayList<String>();
        urutkanBerdasarkan.add("Test");
        List<String> kelasTingkat = new ArrayList<String>();
        kelasTingkat.add("Semua...");
        List<String> lamajamBelajar = new ArrayList<String>();
        lamajamBelajar.add("Semua...");
        List<String> lamaperiodeBelajar = new ArrayList<String>();
        lamaperiodeBelajar.add("Semua...");
        List<String> hargaperBulan = new ArrayList<String>();
        hargaperBulan.add("Semua...");
        List<String> daerahtempatBelajar = new ArrayList<String>();
        daerahtempatBelajar.add("Semua...");

        listDataChild.put(listDataHeader.get(0), urutkanBerdasarkan); // Header, Child data
        listDataChild.put(listDataHeader.get(1), kelasTingkat);
        listDataChild.put(listDataHeader.get(2), lamajamBelajar);
        listDataChild.put(listDataHeader.get(3), lamaperiodeBelajar);
        listDataChild.put(listDataHeader.get(4), hargaperBulan);
        listDataChild.put(listDataHeader.get(5), daerahtempatBelajar);
    }

    public void onRadioButtonClicked_sort_urutkanberdasarkan(View view) {
        sort_urutkanberdasarkan="null";
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rb_rekomendasi_kami:
                if (checked)
                    sort_urutkanberdasarkan = "1";
                break;
            case R.id.rb_termurah:
                if (checked)
                    sort_urutkanberdasarkan = "2";
                break;
            case R.id.rb_terdekat:
                if (checked)
                    sort_urutkanberdasarkan = "3";
                break;
            case R.id.rb_termahal:
                if (checked)
                    sort_urutkanberdasarkan = "1";
                break;
            case R.id.rb_terpopuler:
                if (checked)
                    sort_urutkanberdasarkan = "1";
                break;
        }
    }

    public void onRadioButtonClicked_filter_kelastingkat(View view) {
        filter_kelastingkat="null";
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rb_kelastingkat_semua:
                if (checked)
                    filter_kelastingkat = "button1Text";
                break;
            case R.id.rb_tk_paud:
                if (checked)
                    filter_kelastingkat = "button2Text";
                break;
            case R.id.rb_sd_kelas_i:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_sd_kelas_ii:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_sd_kelas_iii:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_sd_kelas_iv:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_sd_kelas_v:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_sd_kelas_vi:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_smp_kelas_vii:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_smp_kelas_viii:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_smp_kelas_ix:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_sma_kelas_x:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_sma_kelas_xi:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_sma_kelas_xii:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_mahasiswa:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_bekerja1725:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_bekerja2630:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_bekerja3140:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_bekerja4150:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
            case R.id.rb_bekerja51:
                if (checked)
                    filter_kelastingkat = "button3Text";
                break;
        }
    }

    public void onRadioButtonClicked_filter_lamajambelajar(View view) {
        filter_lamajambelajar="semua";
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rb_lamajambelajar_semua:
                if (checked)
                    filter_lamajambelajar = "semua";
                break;
            case R.id.rb_kurangdari30menit:
                if (checked)
                    filter_lamajambelajar = "kurang_dari_30_menit";
                break;
            case R.id.rb_1jam:
                if (checked)
                    filter_lamajambelajar = "1_jam";
                break;
            case R.id.rb_2jam:
                if (checked)
                    filter_lamajambelajar = "2_jam";
                break;
            case R.id.rb_3jam:
                if (checked)
                    filter_lamajambelajar = "3_jam";
                break;
            case R.id.rb_4jam:
                if (checked)
                    filter_lamajambelajar = "4_jam";
                break;
            case R.id.rb_lebihdari4jam:
                if (checked)
                    filter_lamajambelajar = "lebih_dari_4_jam";
                break;
        }
    }

    public void onRadioButtonClicked_filter_lamaperiodebelajar(View view) {
        filter_lamaperiodebelajar="semua";
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rb_lamaperiodebelajar_semua:
                if (checked)
                    filter_lamaperiodebelajar = "semua";
                break;
            case R.id.rb_1xpertemuan:
                if (checked)
                    filter_lamaperiodebelajar = "1x_pertemuan";
                break;
            case R.id.rb_1_4minggu:
                if (checked)
                    filter_lamaperiodebelajar = "1-4_minggu";
                break;
            case R.id.rb_1_2bulan:
                if (checked)
                    filter_lamaperiodebelajar = "1-2_bulan";
                break;
            case R.id.rb_2_4bulan:
                if (checked)
                    filter_lamaperiodebelajar = "2-4_bulan";
                break;
            case R.id.rb_4_6bulan:
                if (checked)
                    filter_lamaperiodebelajar = "4-6_bulan";
                break;
            case R.id.rb_6_12bulan:
                if (checked)
                    filter_lamaperiodebelajar = "6-12_bulan";
                break;
            case R.id.rb_1_2tahun:
                if (checked)
                    filter_lamaperiodebelajar = "1-2_tahun";
                break;
            case R.id.rb_2_3tahun:
                if (checked)
                    filter_lamaperiodebelajar = "2-3_tahun";
                break;
            case R.id.rb_lebihdari3tahun:
                if (checked)
                    filter_lamaperiodebelajar = "lebih_dari_3_tahun";
                break;
        }
    }

    public void onRadioButtonClicked_filter_hargaperbulan(View view) {
        filter_hargaperbulan="semua";
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rb_hargaperbulan_semua:
                if (checked)
                    filter_hargaperbulan = "semua";
                break;
            case R.id.rb_kurangdariidr100rb:
                if (checked)
                    filter_hargaperbulan = "kurang_dari_idr_100000";
                break;
            case R.id.rb_idr100rb:
                if (checked)
                    filter_hargaperbulan = "idr_100000_500000";
                break;
            case R.id.rb_idr500rb1jt:
                if (checked)
                    filter_hargaperbulan = "idr_500000_1000000";
                break;
            case R.id.rb_idr1jt2jt:
                if (checked)
                    filter_hargaperbulan = "idr_1000000_2000000";
                break;
            case R.id.rb_2jt3jt:
                if (checked)
                    filter_hargaperbulan = "idr_2000000_3000000";
                break;
            case R.id.rb_3jt5jt:
                if (checked)
                    filter_hargaperbulan = "idr_3000000_5000000";
                break;
            case R.id.rb_lebihdari5jt:
                if (checked)
                    filter_hargaperbulan = "lebih_dari_idr_5000000";
                break;
        }
    }

    public void onRadioButtonClicked_filter_daerahtempatbelajar(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rb_daerahtempatbelajar_semua:
                if (checked)
                    filter_daerahtempatbelajar = "semua";
                break;
            case R.id.rb_jakartabarat:
                if (checked)
                    filter_daerahtempatbelajar = "jakarta_barat";
                break;
            case R.id.rb_jakartatimur:
                if (checked)
                    filter_daerahtempatbelajar = "jakarta_timur";
                break;
            case R.id.rb_jakartautara:
                if (checked)
                    filter_daerahtempatbelajar = "jakarta_utara";
                break;
            case R.id.rb_jakartaselatan:
                if (checked)
                    filter_daerahtempatbelajar = "jakarta_selatan";
                break;
            case R.id.rb_jakartapusat:
                if (checked)
                    filter_daerahtempatbelajar = "jakarta_pusat";
                break;
            case R.id.rb_tangerang:
                if (checked)
                    filter_daerahtempatbelajar = "tangerang";
                break;
            case R.id.rb_bekasi:
                if (checked)
                    filter_daerahtempatbelajar = "bekasi";
                break;
        }
    }

    public void apply_sort_filter(View view) {
        //Initialize Firebase DB
        Intent intent= getIntent();
        String child=intent.getExtras().getString("CATEGORY_KEY");
        String pass_category_to_search=child;

        Intent i = new Intent(this, search.class);
        i.putExtra("CATEGORY_KEY", pass_category_to_search);

        String get_intent_from = "from_sort_and_filter";
        i.putExtra("GET_INTENT_FROM",get_intent_from);

        i.putExtra("SORT_URUTKANBERDASARKAN_KEY", sort_urutkanberdasarkan);
        i.putExtra("FILTER_KELASTINGKAT_KEY", filter_kelastingkat);
        i.putExtra("FILTER_LAMAJAMBELAJAR_KEY", filter_lamajambelajar);
        i.putExtra("FILTER_LAMAPERIODEBELAJAR_KEY", filter_lamaperiodebelajar);
        i.putExtra("FILTER_HARGAPERBULAN_KEY", filter_hargaperbulan);
        i.putExtra("FILTER_DAERAHTEMPATBELAJAR_KEY", filter_daerahtempatbelajar);

        finish();
        Log.d("class SEARCH_FILTER " ,""+filter_lamajambelajar+"---"+filter_lamaperiodebelajar+"---"+filter_hargaperbulan+"---"+filter_daerahtempatbelajar);
        startActivity(i);
    }


//    public void addListenerOnButton() {
//
//        TextView btnApply = (TextView) findViewById(R.id.btnApply);
//
//        btnApply.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                // get selected radio button from radioGroup
//                int selectedId0 = radioGroup0.getCheckedRadioButtonId();
//                int selectedId1 = radioGroup1.getCheckedRadioButtonId();
//                int selectedId2 = radioGroup2.getCheckedRadioButtonId();
//                int selectedId3 = radioGroup3.getCheckedRadioButtonId();
//                int selectedId4 = radioGroup4.getCheckedRadioButtonId();
//                int selectedId5 = radioGroup5.getCheckedRadioButtonId();
//
//                // find the radiobutton by returned id
//                radioButton0 = (RadioButton) findViewById(selectedId0);
//                radioButton1 = (RadioButton) findViewById(selectedId1);
//                radioButton2 = (RadioButton) findViewById(selectedId2);
//                radioButton3 = (RadioButton) findViewById(selectedId3);
//                radioButton4 = (RadioButton) findViewById(selectedId4);
//                radioButton5 = (RadioButton) findViewById(selectedId5);
//
//                Toast.makeText(search_filtersort.this,radioButton0.getText(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(search_filtersort.this,radioButton1.getText(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(search_filtersort.this,radioButton2.getText(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(search_filtersort.this,radioButton3.getText(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(search_filtersort.this,radioButton4.getText(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(search_filtersort.this,radioButton5.getText(), Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
//
//    }

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

