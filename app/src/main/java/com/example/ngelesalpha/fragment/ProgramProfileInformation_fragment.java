package com.example.ngelesalpha.fragment;

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
public class ProgramProfileInformation_fragment extends Fragment {
//    public  ProgramProfileInformation_fragment()
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
        View view = inflater.inflate(R.layout.fragment_ppinformation,container,false);

        Bundle args = getArguments();
        String description = args.getString("DESCRIPTION_KEY");
        String class_days = args.getString("CLASS_DAYS_KEY");
        String class_shift = args.getString("CLASS_SHIFT_KEY");
        String address = args.getString("ADDRESS_KEY");
        String payment_description = args.getString("PAYMENT_DESCRIPTION_KEY");
        String class_charge = args.getString("CLASS_CHARGE_KEY");
        String charge_per_blank = args.getString("CHARGE_PER_BLANK_KEY");
        String id_money = args.getString("ID_MONEY_KEY");

        //---get description
        TextView pp_description=(TextView)view.findViewById(R.id.pp_description);
        pp_description.setText(description);

        //---get day
        TextView pp_class_day=(TextView)view.findViewById(R.id.pp_day);
        pp_class_day.setText(class_days);

        //---get time
        TextView pp_class_shift=(TextView)view.findViewById(R.id.pp_time);
        pp_class_shift.setText(class_shift);

        //---get address
        TextView pp_address=(TextView)view.findViewById(R.id.pp_address);
        pp_address.setText(address);

        //---get payment_description
        TextView pp_payment_description=(TextView)view.findViewById(R.id.pp_payment_description);
        pp_payment_description.setText(payment_description);

        //---get charge
        TextView pp_charge=(TextView)view.findViewById(R.id.pp_charge);
        pp_charge.setText(class_charge);

        //---get charge per
        TextView pp_chargeper=(TextView)view.findViewById(R.id.pp_chargeper);
        pp_chargeper.setText(charge_per_blank);

        //---get id money
        TextView pp_id_money=(TextView)view.findViewById(R.id.pp_id_money);
        pp_id_money.setText(id_money);

        return view;
    }

//    public void onViewCreated(View view,Bundle savedInstanceState)
//    {
//
//    }

}
