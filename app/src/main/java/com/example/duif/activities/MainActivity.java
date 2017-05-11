package com.example.duif.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duif.R;
import com.example.duif.model.Tweet;
import com.example.duif.view.MenuBarTile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String JSONString = getJSONStringFromFile("output.json");

        ArrayList<Tweet> tweets = parseJSON(JSONString);

        // Set menu bar tiles
        final MenuBarTile mbtHome = (MenuBarTile)findViewById(R.id.mbt_home);
        final MenuBarTile mbtSecond = (MenuBarTile)findViewById(R.id.mbt_second);
        final MenuBarTile mbtThird = (MenuBarTile)findViewById(R.id.mbt_third);
        final MenuBarTile mbtFourth = (MenuBarTile)findViewById(R.id.mbt_fourth);

        mbtHome.setIcon(R.drawable.ic_home);
        mbtHome.setIconSelected(R.drawable.ic_home_selected);
        mbtHome.setState(0);

        mbtHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtHome.setState(2);
                mbtSecond.setState(0);
                mbtThird.setState(0);
                mbtFourth.setState(0);
            }
        });

        mbtSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtHome.setState(0);
                mbtSecond.setState(2);
                mbtThird.setState(0);
                mbtFourth.setState(0);
            }
        });
        mbtThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtHome.setState(0);
                mbtSecond.setState(0);
                mbtThird.setState(2);
                mbtFourth.setState(0);
            }
        });
        mbtFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtHome.setState(0);
                mbtSecond.setState(0);
                mbtThird.setState(0);
                mbtFourth.setState(2);
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

    public ArrayList<Tweet> parseJSON(String JSONString) {
        ArrayList<Tweet> tweets = new ArrayList();

        try {
            JSONObject jsonObj = new JSONObject(JSONString);

            // Getting JSON Array node
            JSONArray statuses = jsonObj.getJSONArray("statuses");

            // Loop through all statuses
            for (int i = 0; i < statuses.length(); i++) {
                // Getting the current status/tweet
                JSONObject status = statuses.getJSONObject(i);

                String createdAt = status.getString("created_at");
                String text = status.getString("text");
                // TODO: 11-5-2017  Add more values

                // parse attributes into java object
                Tweet tweet = new Tweet(createdAt,text);
                // adding status to the list
                tweets.add(tweet);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
