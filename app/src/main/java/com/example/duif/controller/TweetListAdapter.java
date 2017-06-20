package com.example.duif.controller;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duif.R;
import com.example.duif.model.Tweet;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class TweetListAdapter extends ArrayAdapter<Tweet> {
    public TweetListAdapter(@NonNull Context context, ArrayList<Tweet> tweets) {
        super(context, R.layout.list_item_tweet, tweets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item_tweet, parent, false);
        }
        Tweet tweet = getItem(position);

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView screenName = (TextView) view.findViewById(R.id.screen_name);
        TextView text = (TextView) view.findViewById(R.id.text);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView retweet = (TextView) view.findViewById(R.id.retweets);
        TextView favourites = (TextView) view.findViewById(R.id.favourites);

        ImageView icon = (ImageView) view.findViewById(R.id.icon);

        if (tweet.getUser().getName() != null) {
            name.setText(tweet.getUser().getName());
        }

        if (tweet.getUser().getScreenName() != null) {
        screenName.setText(tweet.getUser().getScreenName());
        }

        if (tweet.getText() != null){
            text.setText(tweet.getText());
        }

        if (tweet.getCreatedAt() != null){
            date.setText(tweet.getCreatedAt());
        }

        retweet.setText(String.valueOf(tweet.getRetweetCount()));
        favourites.setText(String.valueOf(tweet.getFavoritedCount()));
        //icon.setImageResource(tweet.getUser().getId_str())


        return view;
    }
}
