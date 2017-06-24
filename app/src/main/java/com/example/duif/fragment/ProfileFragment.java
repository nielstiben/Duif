package com.example.duif.fragment;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.duif.R;
import com.example.duif.controller.JSONParser;
import com.example.duif.controller.TweetListAdapter;
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
        ImageView profileImage = (ImageView) view.findViewById(R.id.icon);
        TextView screenName = (TextView) view.findViewById(R.id.tv_screen_name);
        TextView name = (TextView) view.findViewById(R.id.tv_name);
        TextView description = (TextView) view.findViewById(R.id.tv_description);
        TextView statusesCount = (TextView) view.findViewById(R.id.tv_statuses_count);
        TextView friendsCount = (TextView) view.findViewById(R.id.tv_friends_count);
        TextView followersCount = (TextView) view.findViewById(R.id.tv_followers_count);
        ListView ownTweetsList = (ListView) view.findViewById(R.id.lv_own_tweets);

        // Get all data out of the Content Singleton and put it in the views
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

        friendsCount.setText(
                String.format("%s Following", String.valueOf(
                        Content.getInstance()
                                .getUserProfile()
                                .getFriendsCount())));

        followersCount.setText(
                String.format("%s Followers", String.valueOf(
                        Content.getInstance()
                                .getUserProfile()
                                .getFollowersCount())));

        //JSONParser.parseTweets(PROFILETWEETS);
        TweetListAdapter adapter = new TweetListAdapter(getContext(), Content.getInstance().getTweets());
        ownTweetsList.setAdapter(adapter);

        // Scale the listview in the scroll view
        int totalHeight = ownTweetsList.getPaddingTop() + ownTweetsList.getPaddingBottom();

        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, ownTweetsList);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            }

            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = ownTweetsList.getLayoutParams();
        params.height = totalHeight + (ownTweetsList.getDividerHeight() * (ownTweetsList.getCount() - 1));
        ownTweetsList.setLayoutParams(params);

        return view;
    }

}

