package com.example.duif.dialogs;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duif.R;
import com.example.duif.fragment.ProfileFragment;

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

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Fragment childFragment = new ProfileFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.content, childFragment).commit();
    }
}