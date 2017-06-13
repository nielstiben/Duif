package com.example.duif.controller;

import com.example.duif.model.Content;
import com.example.duif.model.Tweet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by wybrenoppedijk on 13/06/2017.
 */

public class JSON {

    public static void parseJSON(String JSONString) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        try {
            JSONObject jsonObj = new JSONObject(JSONString);

            // Getting JSON Array node
            JSONArray statuses = jsonObj.getJSONArray("statuses");

            // Loop through all statuses
            for (int i = 0; i < statuses.length(); i++) {
                // Getting the current status/tweet
                JSONObject status = statuses.getJSONObject(i);

                String createdAt = status.getString("created_at");
                String text = status.getString("text");
                // TODO: 11-5-2017  Add more values

                // parse attributes into java object
                Tweet tweet = new Tweet(createdAt, text);

                // adding status to the list
                tweets.add(tweet);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Content.getInstance().setTweets(tweets);
    }}
