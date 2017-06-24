package com.example.duif.communication;

import com.example.duif.activities.LoginActivity;
import com.example.duif.model.Content;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;

public class Connection {

    private static Connection instance;

    private final String API_KEY = "0PkiewnTf18DRv4buIx9JszMT";
    private final String API_SECRET = "K6XLrVWu18eeJC0hAsQdJHINhVr5eiRdEIXoHhYa6AEpoHAdzR";
    private final String CALLBACK_URL = "https://saxion.nl/connection/";
    private final OAuth10aService service;
    private OAuth1AccessToken accessToken;
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
            System.out.println("RequestToken = " + requestToken);
            accessToken = service.getAccessToken(requestToken, verifier);
            LoginActivity.isLogedIn = true;
            System.out.println(LoginActivity.isLogedIn);
            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTimeLine(){
        final OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/statuses/home_timeline.json", service);
        service.signRequest(accessToken, request);
        final Response response = request.send();
        try {
            return response.getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String getProfile(){
        final OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/statuses/user_timeline.json", service);
        service.signRequest(accessToken, request);
        final Response response = request.send();
        try {
            return response.getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUserProfileInformation(){
        final OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json", service);
        service.signRequest(accessToken, request);
        final Response response = request.send();
        try {
            return response.getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public String getUserCredentials(){
//        final OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json", service);
//        service.signRequest(accessToken, request);
//        final Response response = request.send();
//        try {
//            return response.getBody();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}