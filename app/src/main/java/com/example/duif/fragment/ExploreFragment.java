package com.example.duif.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.duif.R;
import com.example.duif.communication.Connection;
import com.example.duif.controller.JSONParser;
import com.example.duif.controller.TweetListAdapter;
import com.example.duif.model.Content;
import com.example.duif.model.Tweet;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExploreFragment extends Fragment {
    private ArrayList<Tweet> tweets = new ArrayList<>();
    private String searchQuerry;
    private Handler handler = new Handler();
    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private ListView resultTweetsList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_explore, container, false);

        final EditText search = (EditText)view.findViewById(R.id.et_search);




        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                TextView result = (TextView)view.findViewById(R.id.tv_result);
                resultTweetsList = (ListView)view.findViewById(R.id.lv_search_result_tweets);


                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    searchQuerry = ""+ search.getText();
                    executorService.execute(searchTweet);
                }
                return false;
            }
        });



        return view;
    }

    Runnable searchTweet = new Runnable() {
        @Override
        public void run() {
            System.out.println("Debug = " + Connection.getInstance().getSearchTweet(searchQuerry));
            tweets = JSONParser.parseTweets(Connection.getInstance().getSearchTweet(searchQuerry));
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TweetListAdapter adapter = new TweetListAdapter(getContext(), tweets);
                    resultTweetsList.setAdapter(adapter);
                }
            });
        }
    };

}
