package com.example.duif.fragment;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duif.R;
import com.example.duif.model.Content;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        // Initiate all views
        ImageView profileImage = (ImageView) view.findViewById(R.id.iv_profile_image);
        TextView screenName = (TextView) view.findViewById(R.id.tv_screen_name);
        TextView name = (TextView) view.findViewById(R.id.tv_name);
        TextView description = (TextView) view.findViewById(R.id.tv_description);
        TextView statusesCount = (TextView) view.findViewById(R.id.tv_statuses_count);
        TextView friendsCount = (TextView) view.findViewById(R.id.tv_friends_count);
        TextView followingCount = (TextView) view.findViewById(R.id.tv_following_count);


        screenName.setText(Content.getInstance()
                .getUserProfile()
                .getScreenName());

        name.setText(String.format("@%s", Content.getInstance()
                .getUserProfile()
                .getName()));

        description.setText(Content.getInstance()
                .getUserProfile()
                .getDescription());

        statusesCount.setText(
                String.format("%s Tweets", String.valueOf(
                        Content.getInstance()
                                .getUserProfile()
                                .getStatusesCount())));


        return view;
    }

}

