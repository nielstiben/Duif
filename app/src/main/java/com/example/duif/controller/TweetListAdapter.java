package com.example.duif.controller;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class TweetListAdapter extends ArrayAdapter<Tweet>{
    public TweetListAdapter(@NonNull Context context , ArrayList<Tweet> tweets) {
        super(context, R.layout.list_item_tweet, tweets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = convertView;
        if(convertView==null){
            v = inflater.inflate(R.layout.list_item_tweet, parent, false);
        }
        Tweet tweet = getItem(position);

        TextView name = (TextView) v.findViewById(R.id.name);
        TextView screeName = (TextView) v.findViewById(R.id.screen_name);
        TextView text = (TextView) v.findViewById(R.id.text);
        TextView date = (TextView) v.findViewById(R.id.date);
        TextView retweet = (TextView) v.findViewById(R.id.retweets);
        TextView favourites = (TextView) v.findViewById(R.id.favourites);

        ImageView icon = (ImageView) v.findViewById(R.id.icon);

//        name.setText(""+tweet.getUser().getName());
//        screeName.setText(""+tweet.getUser().getScreenName());
        text.setText(""+tweet.getText());
        date.setText(""+tweet.getCreatedAt());
//        retweet.setText(""+tweet.getRetweetCount());
//        favourites.setText("" + tweet.getFavoritedCount());
        //icon.setImageResource(tweet.getUser().getId_str())



        return v;
    }
}
