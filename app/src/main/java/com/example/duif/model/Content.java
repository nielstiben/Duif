package com.example.duif.model;

import java.util.ArrayList;

/**
 * This class stores all the content of the app.
 * It's a singleton.
 */

public class Content {
    private static Content instance = null;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    private ArrayList<Tweet> userTweets = new ArrayList<>();
    private ArrayList<Tweet> specificTweets = new ArrayList<>();
    private User userProfile;
    private User specificProfile;

    private Content() {
    }

    public static Content getInstance() {
        if (instance == null) {
            instance = new Content();
        }
        return instance;
    }

    public ArrayList<Tweet> getUserTweets() {
        return userTweets;
    }

    public void setUserTweets(ArrayList<Tweet> userTweets) {
        this.userTweets = userTweets;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public User getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(User userProfile) {
        this.userProfile = userProfile;
    }

    public User getSpecificProfile() {
        return specificProfile;
    }

    public void setSpecificProfile(User specificProfile) {
        this.specificProfile = specificProfile;
    }

    public ArrayList<Tweet> getSpecificTweets() {
        return specificTweets;
    }

    public void setSpecificTweets(ArrayList<Tweet> specificTweets) {
        this.specificTweets = specificTweets;
    }
}
