package com.example.duif.controller;

import com.example.duif.model.Entity;
import com.example.duif.model.Tweet;
import com.example.duif.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This class is responsible for all JSON parsing.
 */
public class JSONParser {
    /**
     * Converts a Twitter JSON-String into a list of tweets.
     *
     * @param JSONString THe JSON string that should be parsed.
     * @return a list of all tweets.
     */
    public static ArrayList<Tweet> parseTweets(String JSONString) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        try {
            // Getting JSON Array node
            JSONArray statuses = new JSONArray(JSONString);


            // Loop through all statuses
            for (int i = 0; i < statuses.length(); i++) {

                // Get the current status/tweet
                JSONObject status = statuses.getJSONObject(i);

                // Get the post date of the tweet
                String createdAt = status.getString("created_at");

                // Get the ID of the tweet
                String id = status.getString("id");

                // Get the text of the tweet
                String text = status.getString("text");

                boolean truncated = status.getBoolean("truncated");

                // Get entities of the tweet
                JSONObject entitiesObject = status.getJSONObject("entities");
                Entity[] entities = null;

                // The client used for posting the tweet
                String source = status.getString("source");

                // Get the Tweet that the current tweet replied to
                Tweet inReplyTo = null;
                //status.getInt("in_reply_to_status_id");

                // Get user of the tweet
                JSONObject userObject = status.getJSONObject("user");
                User user = parseUser(userObject.toString());

                // Get the amount of retweets and favourites
                int retweetCount = status.getInt("retweet_count");
                int favouriteCount = status.getInt("favorite_count");

                // Get a boolean whether or not the user retweeted or favourite'd the current tweet
                boolean favorited = status.getBoolean("favorited");
                boolean retweeted = status.getBoolean("retweeted");

                // Get the possibly_sensitive status of the tweet
                boolean possiblySensitive = false;
                //possiblySensitive = status.getBoolean("possibly_sensitive");

                // Get tweet language
                String language = status.getString("lang");


                // parse attributes into java object
                Tweet tweet = new Tweet(createdAt, id, text, truncated, entities, null, source, null, user, retweetCount, favouriteCount, retweeted, favorited, possiblySensitive, language);

                // adding status to the list
                tweets.add(tweet);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweets;
    }

    /**
     * Converts a Twitter JSON-String into a User.
     *
     * @param JSONString THe JSON string that should be parsed.
     * @return all user information.
     */
    public static User parseUser(String JSONString) {
        User user = new User();
        try {
            JSONObject userObj = new JSONObject(JSONString);

            user.setId(userObj.getString("id"));
            user.setIdStr(userObj.getString("id_str"));
            user.setName(userObj.getString("name"));
            user.setScreenName(userObj.getString("screen_name"));
            //user.setLocation(userObj.getString("location"));
            user.setDescription(userObj.getString("description"));
            //user.setUrl(userObj.getString("url"));

            //user.setEntities(null);
            //user.setProtected(userObj.getBoolean("protected"));

            user.setFollowersCount(userObj.getInt("followers_count"));
            user.setFriendsCount(userObj.getInt("friends_count"));
            user.setListedCount(userObj.getInt("listed_count"));


            //user.setCreatedAt(userObj.getString("created_at"));
            //user.setFavouritesCount(userObj.getInt("favourites_count"));
            //user.setUtcOffset(userObj.getInt("utc_offset"));
            //user.setTimeZone(userObj.getString("time_zone"));

            //user.setGeoEnabled(userObj.getBoolean("geo_enabled"));
            //user.setVerified(userObj.getBoolean("verified"));

            user.setStatusesCount(userObj.getInt("statuses_count"));
            //user.setLang(userObj.getString("lang"));

            //user.setStatus(null);

            //user.setContributorsEnabled(userObj.getBoolean("contributors_enabled"));
            //user.setTranslator(userObj.getBoolean("is_translator"));
            //user.setTranslatorEnabled(userObj.getBoolean("is_translation_enabled"));

            //user.setProfileBackgroundColor(userObj.getString("profile_background_color"));
            //user.setProfileBackgroundImageUrl(userObj.getString("profile_background_image_url"));
            //user.setProfileBackgroundImageUrlHTTPS(userObj.getString("profile_background_image_url_https"));
            user.setProfileImageUrl(userObj.getString("profile_image_url"));
            //user.setProfileImageUrlHTTPS(userObj.getString("profile_image_url_https"));
            user.setProfileBannerUrl(userObj.getString("profile_banner_url"));
            //user.setProfileLinkColor(userObj.getString("profile_link_color"));
            //user.setProfileSidebarBorderColor(userObj.getString("profile_sidebar_border_color"));
            //user.setProfileSidebarFillColor(userObj.getString("profile_sidebar_fill_color"));
            //user.setProfileTextColor(userObj.getString("profile_text_color"));
            //user.setProfileUseBackgroundImage(userObj.getBoolean("profile_use_background_image"));

            //user.setHasExtendedProfile(userObj.getBoolean("has_extended_profile"));
            //user.setDefaultProfile(userObj.getBoolean("default_profile"));
            //user.setDefaultProfileImage(userObj.getBoolean("default_profile_image"));

            user.setFollowing(userObj.getBoolean("following"));
            user.setFollowRequestSent(userObj.getBoolean("follow_request_sent"));

            //user.setNotifications(userObj.getBoolean("notifications"));
            //user.setTranslatorType(userObj.getString("translator_type"));


        } catch (JSONException e) {
            e.printStackTrace();

        }
        return user;
    }

    /**
     * Converts a Twitter JSON-String into a list of tweets.
     *
     * @param JSONString THe JSON string that should be parsed.
     * @return a list of tweets.
     */
    public static ArrayList<Tweet> parseTweetsForSearchQuery(String JSONString) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        try {
            // Getting JSON Array node
            JSONObject tweetObject = new JSONObject(JSONString);
            JSONArray statuses = tweetObject.getJSONArray("statuses");


            // Loop through all statuses
            for (int i = 0; i < statuses.length(); i++) {

                // Get the current status/tweet
                JSONObject status = statuses.getJSONObject(i);

                // Get the post date of the tweet
                String createdAt = status.getString("created_at");

                // Get the ID of the tweet
                String id = status.getString("id");

                // Get the text of the tweet
                String text = status.getString("text");

                boolean truncated = status.getBoolean("truncated");

                // Get entities of the tweet
                JSONObject entitiesObject = status.getJSONObject("entities");
                Entity[] entities = null;

                // The client used for posting the tweet
                String source = status.getString("source");

                // Get the Tweet that the current tweet replied to
                Tweet inReplyTo = null;
                //status.getInt("in_reply_to_status_id");

                // Get user of the tweet
                JSONObject userObject = status.getJSONObject("user");
                User user = parseUser(userObject.toString());

                // Get the amount of retweets and favourites
                int retweetCount = status.getInt("retweet_count");
                int favouriteCount = status.getInt("favorite_count");

                // Get a boolean whether or not the user retweeted or favourite'd the current tweet
                boolean favorited = status.getBoolean("favorited");
                boolean retweeted = status.getBoolean("retweeted");

                // Get the possibly_sensitive status of the tweet
                boolean possiblySensitive = false;
                //possiblySensitive = status.getBoolean("possibly_sensitive");

                // Get tweet language
                String language = status.getString("lang");


                // parse attributes into java object
                Tweet tweet = new Tweet(createdAt, id, text, truncated, entities, null, source, null, user, retweetCount, favouriteCount, retweeted, favorited, possiblySensitive, language);

                // adding status to the list
                tweets.add(tweet);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweets;
    }


}
