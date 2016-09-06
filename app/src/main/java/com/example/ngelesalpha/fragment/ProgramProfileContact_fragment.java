package com.example.ngelesalpha.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ngelesalpha.R;

/**
 * Created by Timothy on 7/15/2016.
 */
public class ProgramProfileContact_fragment extends Fragment {
//    public  ProgramProfileContact_fragment()
//    {
//
//    }
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_ppcontact,container,false);
//        View fragmentHandle = inflater.inflate(R.layout.fragment_ppcontact, container, false);
        Bundle args = getArguments();
        String contact_email_address = args.getString("CONTACT_EMAIL_ADDRESS_KEY");
        String contact_facebook = args.getString("CONTACT_FACEBOOK_KEY");
        String contact_phone = args.getString("CONTACT_PHONE_KEY");
        String contact_web_page = args.getString("CONTACT_WEB_PAGE_KEY");

        //---get contact email address
        TextView pp_contact_email_address=(TextView)view.findViewById(R.id.pp_contact_email_address);
        pp_contact_email_address.setText(contact_email_address);

        //---get contact facebook
        TextView pp_contact_facebook=(TextView)view.findViewById(R.id.pp_contact_facebook);
        pp_contact_facebook.setText(contact_facebook);

        //---get contact phone
        TextView pp_contact_phone=(TextView)view.findViewById(R.id.pp_contact_phone);
        pp_contact_phone.setText(contact_phone);

        //---get contact web page
        TextView pp_contact_web_page=(TextView)view.findViewById(R.id.pp_contact_web_page);
        pp_contact_web_page.setText(contact_web_page);

        return view;

    }
//    public void onViewCreated(View view,Bundle savedInstanceState)
//    {
//
//    }



}
