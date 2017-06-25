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
 * THis is the dialog that shows the profile of a specific user.
 */
public class ShowProfileDialog extends DialogFragment {

    public static ShowProfileDialog newInstance(int myIndex) {
        ShowProfileDialog DialogFragment = new ShowProfileDialog();

        Bundle args = new Bundle();
        args.putInt("anIntToSend", myIndex);
        DialogFragment.setArguments(args);

        return DialogFragment;
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
        Bundle bunde = new Bundle();
        bunde.putBoolean("SPECEFIC", true);
        Fragment childFragment = new ProfileFragment();
        childFragment.setArguments(bunde);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.content, childFragment).commit();
    }
}