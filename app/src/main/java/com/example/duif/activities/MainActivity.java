package com.example.duif.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.duif.Interfaces.OnFragmentRevisited;
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

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Core of our application. Is respponsible for getting the content from twitter.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnFragmentRevisited {

    Fragment fragment = new ListFragment();

    private String jsonTimeLine;
    private String jsonProfile;
    private String jsonProfileCredentials;
    /**
     * This Thread is responsible for getting the timeline and the profile of the user from twitter.
     */
    Runnable getJsonFromTwitter = new Runnable() {
        @Override
        public void run() {
            try {
                jsonTimeLine = Connection.getInstance().getTimeLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonProfile = Connection.getInstance().getUserTweets();
            jsonProfileCredentials = Connection.getInstance().getUserProfileInformation();
            proccesData();
        }
    };
    private MenuBarTile mbtHome;
    private MenuBarTile mbtProfile;
    private MenuBarTile mbtExplore;
    private MenuBarTile mbtAbout;
    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProfileFragment.addListener(this);

        if (LoginActivity.isLogedIn) {
            executorService.execute(getJsonFromTwitter);
        }

        // show our welcome message
        showWelcomeMessage();

        // initiate the tiles in the top menu
        initiateMenu();


    }

    /**
     * THis method put the data in to the content singleton.
     */
    private void proccesData() {
        ArrayList<Tweet> tweets = JSONParser.parseTweets(jsonTimeLine);
        Content.getInstance().setTweets(tweets);
        Content.getInstance().setUserTweets(JSONParser.parseTweets(jsonProfile));
        Content.getInstance().setUserProfile(JSONParser.parseUser(jsonProfileCredentials));

    }

    /**
     * Welcome message!
     */
    public void showWelcomeMessage() {
        Toast.makeText(getApplicationContext(), "Roekoe!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Set the layout for the menubar.
     */
    private void initiateMenu() {
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

    /**
     * Reloads the data if you revist the page. THis is not working correct right now.
     */
    @Override
    public void onRevisitHandler() {
        executorService.execute(getJsonFromTwitter);
    }
}

