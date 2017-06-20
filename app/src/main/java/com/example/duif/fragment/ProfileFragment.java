package com.example.duif.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duif.R;
import com.example.duif.model.Content;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView screenName = (TextView)view.findViewById(R.id.tv_screen_name);
        screenName.setText(Content.getInstance().getUserProfile().getScreenName());
        return view;
    }

}
