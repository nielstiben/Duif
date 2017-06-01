package com.example.duif.communication;

import android.widget.Toast;

import com.example.duif.model.Content;
import com.example.duif.model.Tweet;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.ScribeJavaConfig;
import com.github.scribejava.core.oauth.OAuth10aService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by raffe on 16-5-2017.
 */

public class Connection {
    private String token;

    public static void authorise() {

        final OAuth10aService service = new ServiceBuilder()
                .apiKey("your_api_key")
                .apiSecret("your_api_secret")
                .build(TwitterApi.instance());
    }


    protected void getToken(){

    }

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
    }
}
