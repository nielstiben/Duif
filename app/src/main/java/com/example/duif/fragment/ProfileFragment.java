package com.example.duif.fragment;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.duif.Interfaces.OnFragmentRevisited;
import com.example.duif.R;
import com.example.duif.controller.TweetListAdapter;
import com.example.duif.model.Content;
import com.example.duif.model.Tweet;
import com.example.duif.model.User;

import java.io.InputStream;
import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    private static OnFragmentRevisited onFragmentRevisited;
    private boolean isSpeceficUserProfile;
    private User user;
    private ArrayList<Tweet> tweets;

    public static void addListener(OnFragmentRevisited listener) {
        onFragmentRevisited = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isSpeceficUserProfile = getArguments().getBoolean("SPECEFIC", false);
            System.out.println(isSpeceficUserProfile);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        onFragmentRevisited.onRevisitHandler();
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        if (isSpeceficUserProfile) {
            user = Content.getInstance().getSpeceficProfile();
            tweets = Content.getInstance().getSpecificTweets();
        } else {
            user = Content.getInstance().getUserProfile();
            tweets = Content.getInstance().getUserTweets();
        }

        // Initiate all views
        TextView screenName = (TextView) view.findViewById(R.id.tv_screen_name);
        TextView name = (TextView) view.findViewById(R.id.tv_name);
        TextView description = (TextView) view.findViewById(R.id.tv_description);
        TextView statusesCount = (TextView) view.findViewById(R.id.tv_statuses_count);
        TextView friendsCount = (TextView) view.findViewById(R.id.tv_friends_count);
        TextView followersCount = (TextView) view.findViewById(R.id.tv_followers_count);
        ListView ownTweetsList = (ListView) view.findViewById(R.id.lv_own_tweets);

//         Get all data out of the Content Singleton and put it in the views
        new DownloadImageFromInternet((ImageView)
                view.findViewById(R.id.iv_profile))
                .execute(user.getProfileImageUrl());
        new DownloadImageFromInternet((ImageView)
                view.findViewById(R.id.iv_banner))
                .execute(user.getProfileBannerUrl());


        screenName.setText(user
                .getScreenName());

        name.setText(String.format("@%s", user
                .getName()));

        description.setText(user
                .getDescription());

        statusesCount.setText(
                String.format("%s Tweets", String.valueOf(
                        user
                                .getStatusesCount())));

        friendsCount.setText(
                String.format("%s Following", String.valueOf(
                        user
                                .getFriendsCount())));

        followersCount.setText(
                String.format("%s Followers", String.valueOf(
                        user
                                .getFollowersCount())));

        //JSONParser.parseTweets(PROFILETWEETS);
        TweetListAdapter adapter = new TweetListAdapter(getContext(), tweets);
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

    class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

}

