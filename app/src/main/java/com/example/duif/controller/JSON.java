package com.example.duif.controller;

import com.example.duif.model.Content;
import com.example.duif.model.Entity;
import com.example.duif.model.Tweet;
import com.example.duif.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by wybrenoppedijk on 13/06/2017.
 */

public class JSON {
    /**
     * Converts a Twitter JSON-String into a Java Object
     *
     * @param JSONString JSON String that containing all statuses that will be converted
     */
    public static void parseTweetsJSON(String JSONString) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        try {
            JSONObject jsonObj = new JSONObject(JSONString);

            // Getting JSON Array node
            JSONArray statuses = jsonObj.getJSONArray("statuses");

            // Loop through all statuses
            for (int i = 0; i < statuses.length(); i++) {

                // Get the current status/tweet
                JSONObject status = statuses.getJSONObject(i);

                // Get the post date of the tweet
                String createdAt = status.getString("created_at");

                // Get the ID of the tweet
                int id = status.getInt("id");

                // Get the text of the tweet
                String text = status.getString("text");

                // // TODO: 20-6-2017 WTF is truncated??
                boolean truncated = status.getBoolean("truncated");

                // Get entities of the tweet
                //// TODO: 20-6-2017 Convert the entities into an Object
                JSONObject entitiesObject = status.getJSONObject("entities");
                Entity[] entities = null;

                // The client used for posting the tweet
                String source = status.getString("source");

                // Get the Tweet that the current tweet replied to
                Tweet inReplyTo = null;
                        //status.getInt("in_reply_to_status_id");

                // Get user of the tweet
                //// TODO: 20-6-2017 Convert the user into an Object
                JSONObject userObject = status.getJSONObject("user");
                User user = new User();

                // Get the amount of retweets and favourites
                int retweetCount = status.getInt("retweet_count");
                int favouriteCount = status.getInt("favorite_count");

                // Get a boolean whether or not the user retweeted or favourite'd the current tweet
                boolean favorited = status.getBoolean("favorited");
                boolean retweeted = status.getBoolean("retweeted");

                // Get the possibly_sensitive status of the tweet
                // TODO: 20-6-2017 this value is not always provided. If not provided, set this value to false;
                boolean possiblySensitive = false;
                //possiblySensitive = status.getBoolean("possibly_sensitive");

                // Get tweet language
                String language = status.getString("lang");


                // parse attributes into java object
                Tweet minimalTweet = new Tweet(createdAt, text);
                Tweet tweet = new Tweet(createdAt, id, text, truncated, entities, null, source, null, user, retweetCount, favouriteCount, retweeted, favorited, possiblySensitive, language);

                // adding status to the list
                tweets.add(tweet);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Content.getInstance().setTweets(tweets);
    }

    public static User parseProfilePageJSON(String JSONString){
        User user = new User();
        try {
            JSONObject userObj = new JSONObject(JSONString);

            user.setScreenName(userObj.getString("screen_name"));
            user.setName(userObj.getString("name"));




        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
