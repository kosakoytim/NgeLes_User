//package com.example.ngelesalpha.fragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AbsListView;
//import android.widget.AdapterView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//
//import com.example.ngelesalpha.R;
//import com.example.ngelesalpha.adapter.RecyclerAdapterHomeNavigationDrawer;
//import com.example.ngelesalpha.book_course;
//import com.example.ngelesalpha.model.NavigationDrawerItem_model;
//
///**
// * Created by Timothy on 7/4/2016.
// */
//public class NavigationDrawer_fragment extends android.support.v4.app.Fragment {
//
//    ActionBarDrawerToggle mDrawerToggle;
//    DrawerLayout mDrawerLayout;
//    RecyclerView mDrawerList;
//
//
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_navigation_drawer,container,false);
//        setUpRecyclerView(view);
//        return view;
//    }
//
//    private void setUpRecyclerView(View view){
//        mDrawerList = (RecyclerView)view.findViewById(R.id.drawerList);
//        RecyclerAdapterHomeNavigationDrawer adapter=new RecyclerAdapterHomeNavigationDrawer(getActivity(), NavigationDrawerItem_model.getData());
//        mDrawerList.setAdapter(adapter);
//        mDrawerList.setLayoutManager(new LinearLayoutManager(getActivity()));
//    }
//
//    public void setUpDrawer(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar)
//    {
//
//
//        mDrawerLayout = drawerLayout;
//        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close) {
//
//            public void onDrawerOpened(View drawerView) {
//                mDrawerList.bringToFront();
//                mDrawerLayout.requestLayout();
////                super.onDrawerOpened(drawerView);
//
//            }
//
//            public void onDrawerClosed(View drawerView) {
//
//                super.onDrawerClosed(drawerView);
//            }
//
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                super.onDrawerSlide(drawerView, slideOffset);
//            }
//
//        };
//
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//
////        mDrawerList.bringToFront();
////        mDrawerLayout.requestLayout();
////        mDrawerLayout.setDrawerListener(mDrawerToggle);
////        mDrawerLayout.post(new Runnable() {
////            @Override
////            public void run() {
////                mDrawerToggle.syncState();
////            }
////        });
//    }
//
//    private class DrawerItemClickListener implements
//            ListView.OnItemClickListener {
//
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//        }
//    }
//
//
//
//}
