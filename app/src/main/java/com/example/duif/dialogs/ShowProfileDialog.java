package com.example.duif.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duif.R;

/**
 * Created by raffe on 25-6-2017.
 */

public class ShowProfileDialog extends DialogFragment {
    public static ShowProfileDialog newInstance(int myIndex) {
        ShowProfileDialog yourDialogFragment = new ShowProfileDialog();

        //example of passing args
        Bundle args = new Bundle();
        args.putInt("anIntToSend", myIndex);
        yourDialogFragment.setArguments(args);

        return yourDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //read the int from args
        int myInteger = getArguments().getInt("anIntToSend");

        View view = inflater.inflate(R.layout.dialog_show_profile, null);

        //here read the different parts of your layout i.e :
        //tv = (TextView) view.findViewById(R.id.yourTextView);
        //tv.setText("some text")

        return view;
    }
}