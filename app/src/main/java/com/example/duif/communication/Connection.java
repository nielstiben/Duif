package com.example.duif.communication;

import android.widget.Toast;

import com.example.duif.model.Content;
import com.example.duif.model.Tweet;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.ScribeJavaConfig;
import com.github.scribejava.core.oauth.OAuth10aService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Connection {
    private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";

    private static final String API_KEY = "0PkiewnTf18DRv4buIx9JszMT";
    private static final String API_SECRET = "K6XLrVWu18eeJC0hAsQdJHINhVr5eiRdEIXoHhYa6AEpoHAdzR";
    private static final String CALLBACK_URL = "";


    public static void connect(String... args)throws IOException, InterruptedException, ExecutionException{
        // Setup service
        final OAuth10aService service = new ServiceBuilder()
                .apiKey(API_KEY)
                .apiSecret(API_SECRET)
                //.callback(CALLBACK_URL)
                .build(TwitterApi.instance());

        // Get the request token
        final OAuth1AccessToken requestToken
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