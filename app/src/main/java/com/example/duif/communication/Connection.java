package com.example.duif.communication;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;

public class Connection {

    private static Connection instance;

    private final String API_KEY = "0PkiewnTf18DRv4buIx9JszMT";
    private final String API_SECRET = "K6XLrVWu18eeJC0hAsQdJHINhVr5eiRdEIXoHhYa6AEpoHAdzR";
    private final String CALLBACK_URL = "https://saxion.nl/connection/";
    private final OAuth10aService service;
    private OAuth1RequestToken requestToken;

    private Connection() {
        service = new ServiceBuilder()
                .apiKey(API_KEY)
                .apiSecret(API_SECRET)
                .callback(CALLBACK_URL)
                .build(TwitterApi.instance());
    }

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public String getRequestUrl(){
        try {
            requestToken = service.getRequestToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return service.getAuthorizationUrl(requestToken);
    }


    public OAuth1AccessToken getAccesToken(String verifier){
        try {
            return service.getAccessToken(requestToken, verifier);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}