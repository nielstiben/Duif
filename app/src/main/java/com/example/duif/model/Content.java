package com.example.duif.model;

import java.util.ArrayList;

/**
 * Created by wybrenoppedijk on 11/05/2017.
 */

public class Content {
    private static Content instance = null;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    private ArrayList<Tweet> userTweets = new ArrayList<>();
    private User userProfile;

    private  Content() {
    }

    public static Content getInstance(){
        if (instance == null){
            instance = new Content();
        }
        return instance;
    }

    public void setTweets(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public void setUserTweets(ArrayList<Tweet> userTweets) {
        this.userTweets = userTweets;
    }

    public ArrayList<Tweet> getUserTweets() {
        return userTweets;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public User getUserProfile() {
        return userProfile;
    }


    public void setUserProfile(User userProfile) {
        this.userProfile = userProfile;
    }
}
