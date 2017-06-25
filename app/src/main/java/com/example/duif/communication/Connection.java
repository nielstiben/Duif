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

    /**
     * Consctor of the Connection class. It gives al the
     * neccessary values to the ServiceBuilder.
     */

    private Connection() {
        service = new ServiceBuilder()
                .apiKey(API_KEY)
                .apiSecret(API_SECRET)
                .callback(CALLBACK_URL)
                .build(TwitterApi.instance());
    }

    /**
     * This class is a singleton. Method to get the singleton.
     * @return The singleton of the class.
     */

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    /**
     * It first asks the request token.
     * After it, it changes it for an authorization url.
     * @return the authorization url.
     */
    public String getRequestUrl(){
        try {
            requestToken = service.getRequestToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return service.getAuthorizationUrl(requestToken);
    }

    /**
     * Methot to obtain the access token. And the actual "login".
     * @param verifier the part from the callback url, with the verfier.
     * @return The access token.
     */
    public OAuth1AccessToken getAccessToken(String verifier){
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

    /**
     * Helper method to get the timeline of the current logged in user.
     * @return Timeline of current logged in user in JSON format.
     */
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

    /**
     * Helper method to get all your own tweets.
     * @return All the tweets of an user in JSON format.
     */

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

    /**
     * Helper method to get all the profile information.
     * @return Profile information in JSON format.
     */
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

    /**|
     * Helper method to search for a specefic tweet.
     * @param question the search term.
     * @return All tweets that match the parameter, in JSON format.
     */
    public String getSearchTweet(String question){
        final OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/search/tweets.json?q=" + question + "&count=10", service);
        service.signRequest(accessToken, request);
        final Response response = request.send();
        try {
            return response.getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Helper method to show a specefic user profile.
     * @param screenName The screenname of the user.
     * @return The profile information of a specific user, in JSON format.
     */
    public String getSpeceficUserProfile(String screenName) {
        final OAuthRequest request = new OAuthRequest(Verb.POST, "https://api.twitter.com/1.1/users/show.json?screen_name=" + screenName , service);
        service.signRequest(accessToken, request);
        final Response response = request.send();
        try {
            return response.getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Helper method to place a tweet.
     * @param tweet Text of the tweet.
     * @return All the information of the tweet. We dont use it yet, in JSON format.
     */
    public String placeTweet(String tweet) {
        final OAuthRequest request = new OAuthRequest(Verb.POST, "https://api.twitter.com/1.1/statuses/update.json?status=" + tweet , service);
        service.signRequest(accessToken, request);
        final Response response = request.send();
        try {
            return response.getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Helper method to retweet a tweet.
     * @param tweetID The ID of the tweet that should be retweeted.
     * @return All the information of the tweet, in JSON format.
     */

    public String makeRetweet(String tweetID) {
        final OAuthRequest request = new OAuthRequest(Verb.POST, "https://api.twitter.com/1.1/statuses/retweet/" + tweetID + ".json", service);
        service.signRequest(accessToken, request);
        final Response response = request.send();
        try {
            return response.getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Helper method to favourite a tweet.
     * @param tweetID The ID of the tweet that should be retweeted.
     * @return All the information of the tweet, in JSON foramt.
     */

    public String makeFavourite(String tweetID) {
        final OAuthRequest request = new OAuthRequest(Verb.POST, "https://api.twitter.com/1.1/favorites/create.json?id=" + tweetID, service);
        service.signRequest(accessToken, request);
        final Response response = request.send();
        try {
            return response.getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}