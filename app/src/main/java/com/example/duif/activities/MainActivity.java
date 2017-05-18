package com.example.duif.activities;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duif.R;
import com.example.duif.communication.Connection;
import com.example.duif.fragment.AboutFragment;
import com.example.duif.fragment.ExploreFragment;
import com.example.duif.fragment.ListFragment;
import com.example.duif.fragment.ProfileFragment;
import com.example.duif.model.Content;
import com.example.duif.model.Tweet;
import com.example.duif.view.MenuBarTile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    public static ArrayList<Tweet> tweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String JSONString = getJSONStringFromFile("output.json");

        Connection.parseJSON(JSONString);
        showWelcomeMessage(); 

        // Set menu bar tiles
        final MenuBarTile mbtHome = (MenuBarTile) findViewById(R.id.mbt_home);
        final MenuBarTile mbtProfile = (MenuBarTile) findViewById(R.id.mbt_profile);
        final MenuBarTile mbtExplore = (MenuBarTile) findViewById(R.id.mbt_explore);
        final MenuBarTile mbtAbout = (MenuBarTile) findViewById(R.id.mbt_about);

        // Set menu bar tiles style;
        // Home tile
        mbtHome.setIcon(R.drawable.ic_home);
        mbtHome.setState(0);

        // Profile tile
        mbtProfile.setIcon(R.drawable.ic_profile);
        mbtProfile.setState(0);

        // Explore tile
        mbtExplore.setIcon(R.drawable.ic_explore);
        mbtExplore.setState(0);

        // About tile
        mbtAbout.setIcon(R.drawable.ic_about);
        mbtAbout.setState(0);





        // Menu bar tiles onclicklisteners
        mbtHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtHome.setState(2);
                mbtProfile.setState(0);
                mbtExplore.setState(0);
                mbtAbout.setState(0);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new ListFragment())
                        .commit();
            }
        });

        mbtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtHome.setState(0);
                mbtProfile.setState(2);
                mbtExplore.setState(0);
                mbtAbout.setState(0);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new ProfileFragment())
                        .commit();
            }
        });
        mbtExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtHome.setState(0);
                mbtProfile.setState(0);
                mbtExplore.setState(2);
                mbtAbout.setState(0);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new ExploreFragment())
                        .commit();
            }
        });
        mbtAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtHome.setState(0);
                mbtProfile.setState(0);
                mbtExplore.setState(0);
                mbtAbout.setState(2);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new AboutFragment())
                        .commit();
            }
        });

        // Set TweetListFragment



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

    public void showWelcomeMessage(){
        Toast.makeText(getApplicationContext(), "Roekoe!", Toast.LENGTH_SHORT).show();
    }

    private void OnClick(View view)
}
