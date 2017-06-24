package com.example.duif.activities;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.duif.R;
import com.example.duif.communication.Connection;
import com.example.duif.controller.JSONParser;
import com.example.duif.fragment.AboutFragment;
import com.example.duif.fragment.ExploreFragment;
import com.example.duif.fragment.ListFragment;
import com.example.duif.fragment.ProfileFragment;
import com.example.duif.model.Content;
import com.example.duif.model.Tweet;
import com.example.duif.view.MenuBarTile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String jsonTimeLine;
    private String jsonProfile;
    private MenuBarTile mbtHome;
    private MenuBarTile mbtProfile;
    private MenuBarTile mbtExplore;
    private MenuBarTile mbtAbout;
    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create tweetlist from example file
        String TweetsJSONString = getJSONStringFromFile("output_tweets.json");

        // create profile page from example file
        String ProfilePageJSONString = getJSONStringFromFile("output_user_profile.json");


        // Setup tweet list
        //Content.getInstance().setTweets(JSONParser.parseTweets(TweetsJSONString));

        // Setup profile page


        // show our welcome message
        showWelcomeMessage();

        // initiate the tiles in the top menu
        initiateMenu();

        if (LoginActivity.isLoogedIn) {
            executorService.execute(getJsonFromTwitter);
            System.out.println("DEBUG 2 = " + jsonTimeLine);
        }

    }

    Runnable getJsonFromTwitter = new Runnable() {
        @Override
        public void run() {
            jsonTimeLine = Connection.getInstance().getTimeLine();
            jsonProfile = Connection.getInstance().getProfile();
            proccesData();
        }
    };

    private void proccesData(){
        ArrayList<Tweet> tweets =  JSONParser.parseTweets(jsonTimeLine);
        Content.getInstance().setTweets(tweets);
        Content.getInstance().setUserProfile(JSONParser.parseUser(jsonProfile));

    }


    private String getJSONStringFromFile(String filename) {
        String JSONString = null;
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            JSONString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONString;
    }

    public void showWelcomeMessage() {
        Toast.makeText(getApplicationContext(), "Roekoe!", Toast.LENGTH_SHORT).show();
    }

    private void initiateMenu(){
        // Find menu bar tiles
        mbtHome = (MenuBarTile) findViewById(R.id.mbt_home);
        mbtProfile = (MenuBarTile) findViewById(R.id.mbt_profile);
        mbtExplore = (MenuBarTile) findViewById(R.id.mbt_explore);
        mbtAbout = (MenuBarTile) findViewById(R.id.mbt_about);

        // Set menu bar icons
        mbtHome.setIcon(R.drawable.ic_home);
        mbtProfile.setIcon(R.drawable.ic_profile);
        mbtExplore.setIcon(R.drawable.ic_explore);
        mbtAbout.setIcon(R.drawable.ic_about);

        // set unselected state to all tiles
        mbtHome.setState(0);
        mbtProfile.setState(0);
        mbtExplore.setState(0);
        mbtAbout.setState(0);


        // set menu bar tiles onclicklisteners
        mbtHome.setOnClickListener(this);
        mbtProfile.setOnClickListener(this);
        mbtExplore.setOnClickListener(this);
        mbtAbout.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        mbtHome.setState(0);
        mbtProfile.setState(0);
        mbtExplore.setState(0);
        mbtAbout.setState(0);

        Fragment fragment = new ListFragment();

        switch (v.getId()) {
            case R.id.mbt_home:
                mbtHome.setState(2);
                fragment = new ListFragment();
                break;
            case R.id.mbt_profile:
                mbtProfile.setState(2);
                fragment = new ProfileFragment();
                break;
            case R.id.mbt_explore:
                mbtExplore.setState(2);
                fragment = new ExploreFragment();
                break;
            case R.id.mbt_about:
                mbtAbout.setState(2);
                fragment = new AboutFragment();
                break;
        }
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

}

