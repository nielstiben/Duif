package com.example.duif.communication;

import android.os.AsyncTask;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Connection extends AsyncTask<URL, Integer, Long> {
    private final static String API_KEY = "0PkiewnTf18DRv4buIx9JszMT";
    private final static String API_SECRET = "K6XLrVWu18eeJC0hAsQdJHINhVr5eiRdEIXoHhYa6AEpoHAdzR";


    public static void connect() throws IOException, InterruptedException, ExecutionException{

    }

    @Override
    protected Long doInBackground(URL... params) {

        final OAuth10aService service = new ServiceBuilder()
            .apiKey(API_KEY)
            .apiSecret(API_SECRET)
            .build(TwitterApi.instance());


        try {
            final OAuth1RequestToken requestToken = service.getRequestToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}