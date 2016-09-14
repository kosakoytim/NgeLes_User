package com.example.ngelesalpha.adapter;

/**
 * Created by Timothy on 9/8/2016.
 */


import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ngelesalpha.R;

public class ExpendableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public ExpendableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

//        if (convertView == null) {
//            LayoutInflater infalInflater = (LayoutInflater) this._context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = infalInflater.inflate(R.layout.expendablelistview_list_item_urutkanberdasarkan, null);
//        }

        if (groupPosition==0)
        {
//            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.expendablelistview_list_item_urutkanberdasarkan, null);
//            }
//            TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
//            txtListChild.setText(childText);
        }
        else if(groupPosition==1)
        {
//            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.expendablelistview_list_item_kelastingkat, null);
//            }
//            TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
//            txtListChild.setText(childText);
        }
        else if(groupPosition==2)
        {
//            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.expendablelistview_list_item_lamajambelajar, null);
//            }
//            TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
//            txtListChild.setText(childText);
        }
        else if(groupPosition==3)
        {
//            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.expendablelistview_list_item_lamaperiodebelajar, null);
//            }
//            TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
//            txtListChild.setText(childText);
        }
        else if(groupPosition==4)
        {
//            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.expendablelistview_list_item_hargaperbulan, null);
//            }
//            TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
//            txtListChild.setText(childText);
        }
        else if(groupPosition==5)
        {
//            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.expendablelistview_list_item_daerahtempatbelajar, null);
//            }
//            TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
//            txtListChild.setText(childText);
        }

//        TextView txtListChild = (TextView) convertView
//                .findViewById(R.id.lblListItem);
//        txtListChild.setText(childText);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expendablelistview_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
