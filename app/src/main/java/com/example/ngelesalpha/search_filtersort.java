package com.example.ngelesalpha;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
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
    String filter_lamajambelajar;
    String filter_lamaperiodebelajar;
    String filter_hargaperbulan;
    String filter_daerahtempatbelajar;


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

        addListenerOnButton();
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
//        urutkanBerdasarkan.add((RadioButton)findViewById(R.id.rb_rekomendasi));
//        urutkanBerdasarkan.add((RadioButton)findViewById(R.id.rb_termurah));
//        urutkanBerdasarkan.add((RadioButton)findViewById(R.id.rb_terdekat));
//        urutkanBerdasarkan.add((RadioButton)findViewById(R.id.rb_terpopuler));
//        urutkanBerdasarkan.add((RadioButton)findViewById(R.id.rb_termahal));

        List<String> kelasTingkat = new ArrayList<String>();
        kelasTingkat.add("Semua...");
//        kelasTingkat.add("TK/PAUD");
//        kelasTingkat.add("SD Kelas I");
//        kelasTingkat.add("SD Kelas II");
//        kelasTingkat.add("SD Kelas III");
//        kelasTingkat.add("SD Kelas IV");
//        kelasTingkat.add("SD Kelas V");
//        kelasTingkat.add("SD Kelas VI");
//        kelasTingkat.add("SMP Kelas VII");
//        kelasTingkat.add("SMP Kelas VIII");
//        kelasTingkat.add("SMP Kelas IX");
//        kelasTingkat.add("SMA Kelas X");
//        kelasTingkat.add("SMA Kelas XI");
//        kelasTingkat.add("SMA Kelas XII");
//        kelasTingkat.add("Mahasiswa");
//        kelasTingkat.add("Bekerja (20++)");
//        kelasTingkat.add("Umum (Terbuka)");

        List<String> lamajamBelajar = new ArrayList<String>();
        lamajamBelajar.add("Semua...");
//        lamajamBelajar.add("Kurang dari 30 menit");
//        lamajamBelajar.add("1 Jam");
//        lamajamBelajar.add("2 Jam");
//        lamajamBelajar.add("3 Jam");
//        lamajamBelajar.add("4 Jam");
//        lamajamBelajar.add("Lebih dari 4 Jam");

        List<String> lamaperiodeBelajar = new ArrayList<String>();
        lamaperiodeBelajar.add("Semua...");
//        lamaperiodeBelajar.add("1x pertemuan");
//        lamaperiodeBelajar.add("1-4 Minggu");
//        lamaperiodeBelajar.add("1-2 Bulan");
//        lamaperiodeBelajar.add("2-3 Bulan");
//        lamaperiodeBelajar.add("3-4 Bulan");
//        lamaperiodeBelajar.add("4-5 Bulan");
//        lamaperiodeBelajar.add("5-6 Bulan");
//        lamaperiodeBelajar.add("6-7 Bulan");
//        lamaperiodeBelajar.add("7-12 Bulan");
//        lamaperiodeBelajar.add("1-2 Tahun");
//        lamaperiodeBelajar.add("2-3 Tahun");
//        lamaperiodeBelajar.add("Lebih dari 3 Tahun");

        List<String> hargaperBulan = new ArrayList<String>();
        hargaperBulan.add("Semua...");
//        hargaperBulan.add("Kurang dari IDR 100,000");
//        hargaperBulan.add("IDR 100,000-500,000");
//        hargaperBulan.add("IDR 500,000-1,000,000");
//        hargaperBulan.add("IDR 1,000,000-2,000,000");
//        hargaperBulan.add("IDR 2,000,000-3,000,000");
//        hargaperBulan.add("IDR 3,000,000-5,000,000");
//        hargaperBulan.add("Lebih dari IDR 5,000,000");

        List<String> daerahtempatBelajar = new ArrayList<String>();
        daerahtempatBelajar.add("Semua...");
//        daerahtempatBelajar.add("Jakarta Barat");
//        daerahtempatBelajar.add("Jakarta Timur");
//        daerahtempatBelajar.add("Jakarta Utara");
//        daerahtempatBelajar.add("Jakarta Selatan");
//        daerahtempatBelajar.add("Jakarta Pusat");
//        daerahtempatBelajar.add("Tangerang");
//        daerahtempatBelajar.add("Bekasi");

        listDataChild.put(listDataHeader.get(0), urutkanBerdasarkan); // Header, Child data
        listDataChild.put(listDataHeader.get(1), kelasTingkat);
        listDataChild.put(listDataHeader.get(2), lamajamBelajar);
        listDataChild.put(listDataHeader.get(3), lamaperiodeBelajar);
        listDataChild.put(listDataHeader.get(4), hargaperBulan);
        listDataChild.put(listDataHeader.get(5), daerahtempatBelajar);
    }

    public void onRadioButtonClicked_sort_urutkanberdasarkan(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_rekomendasi_kami:
                if (checked)
                    sort_urutkanberdasarkan = "button1Text";
                break;
            case R.id.rb_termurah:
                if (checked)
                    sort_urutkanberdasarkan = "button2Text";
                break;
            case R.id.rb_terdekat:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
            case R.id.rb_termahal:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
            case R.id.rb_terpopuler:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
        }
    }

    public void onRadioButtonClicked_sort_urutkanberdasarkan(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_rekomendasi_kami:
                if (checked)
                    sort_urutkanberdasarkan = "button1Text";
                break;
            case R.id.rb_termurah:
                if (checked)
                    sort_urutkanberdasarkan = "button2Text";
                break;
            case R.id.rb_terdekat:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
            case R.id.rb_termahal:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
            case R.id.rb_terpopuler:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
        }
    }

    public void onRadioButtonClicked_sort_urutkanberdasarkan(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_rekomendasi_kami:
                if (checked)
                    sort_urutkanberdasarkan = "button1Text";
                break;
            case R.id.rb_termurah:
                if (checked)
                    sort_urutkanberdasarkan = "button2Text";
                break;
            case R.id.rb_terdekat:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
            case R.id.rb_termahal:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
            case R.id.rb_terpopuler:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
        }
    }

    public void onRadioButtonClicked_sort_urutkanberdasarkan(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_rekomendasi_kami:
                if (checked)
                    sort_urutkanberdasarkan = "button1Text";
                break;
            case R.id.rb_termurah:
                if (checked)
                    sort_urutkanberdasarkan = "button2Text";
                break;
            case R.id.rb_terdekat:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
            case R.id.rb_termahal:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
            case R.id.rb_terpopuler:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
        }
    }

    public void onRadioButtonClicked_sort_urutkanberdasarkan(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_rekomendasi_kami:
                if (checked)
                    sort_urutkanberdasarkan = "button1Text";
                break;
            case R.id.rb_termurah:
                if (checked)
                    sort_urutkanberdasarkan = "button2Text";
                break;
            case R.id.rb_terdekat:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
            case R.id.rb_termahal:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
            case R.id.rb_terpopuler:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
        }
    }

    public void onRadioButtonClicked_sort_urutkanberdasarkan(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_rekomendasi_kami:
                if (checked)
                    sort_urutkanberdasarkan = "button1Text";
                break;
            case R.id.rb_termurah:
                if (checked)
                    sort_urutkanberdasarkan = "button2Text";
                break;
            case R.id.rb_terdekat:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
            case R.id.rb_termahal:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
            case R.id.rb_terpopuler:
                if (checked)
                    sort_urutkanberdasarkan = "button3Text";
                break;
        }
    }

    public void apply_sort_filter(View view) {
        Intent i = new Intent(this, search.class);
        i.putExtra("SORT_URUTKANBERDASARKAN_KEY", sort_urutkanberdasarkan);
        i.putExtra("FILTER_KELASTINGKAT_KEY", filter_kelastingkat);
        i.putExtra("FILTER_LAMAJAMBELAJAR_KEY", filter_lamajambelajar);
        i.putExtra("FILTER_LAMAPERIODEBELAJAR_KEY", filter_lamaperiodebelajar);
        i.putExtra("FILTER_HARGAPERBULAN_KEY", filter_hargaperbulan);
        i.putExtra("FILTER_DAERAHTEMPATBELAJAR_KEY", filter_daerahtempatbelajar);
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

