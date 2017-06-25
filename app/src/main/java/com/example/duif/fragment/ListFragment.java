package com.example.duif.fragment;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duif.R;
import com.example.duif.activities.MainActivity;
import com.example.duif.controller.JSONParser;
import com.example.duif.controller.TweetListAdapter;
import com.example.duif.dialogs.PostTweetDialog;
import com.example.duif.model.Content;
import com.example.duif.model.Tweet;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListFragment extends Fragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();

        final PostTweetDialog postTweetDialog = new PostTweetDialog();

        TweetListAdapter adapter = new TweetListAdapter(getContext(), Content.getInstance().getTweets());
        FloatingActionButton postTweet = (FloatingActionButton)view.findViewById(R.id.fab_post_tweet);

        postTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    postTweetDialog.show(fragmentTransaction, "PostTweetFragment");
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }
            }
        });

        ListView listView = (ListView) view.findViewById(R.id.listview_tweets);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                CharSequence options[] = new CharSequence[] {"Retweet", "Favourite"};

                // Open option dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select an option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0 ){
                            // Retweet
                            long id = Content.getInstance().getTweets().get(position).getId();
                        }else if(which ==1){
                            // Favourite
                            Content.getInstance().getTweets().get(position).getId();
                        }
                    }
                });

                builder.show();
                return false;
            }
        });

        return view;

    }
}

