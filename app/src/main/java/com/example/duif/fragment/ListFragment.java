package com.example.duif.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.duif.Interfaces.OnTweetPlacedHandler;
import com.example.duif.R;
import com.example.duif.communication.Connection;
import com.example.duif.controller.JSONParser;
import com.example.duif.controller.TweetListAdapter;
import com.example.duif.dialogs.PostTweetDialog;
import com.example.duif.dialogs.ShowProfileDialog;
import com.example.duif.model.Content;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Fragment that shows the main timeline. With on long press an option for retweet or favourite. And on short press
 * you can view the profile of that specific account.
 */
public class ListFragment extends Fragment implements OnTweetPlacedHandler{
    private ExecutorService executorService = Executors.newFixedThreadPool(3);
    private String tweetID;
    private TweetListAdapter adapter;
    Runnable makeFavourite = new Runnable() {
        @Override
        public void run() {
            Connection.getInstance().makeFavourite(tweetID);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                }
            });
        }
    };
    Runnable makeRetweet = new Runnable() {
        @Override
        public void run() {
            Connection.getInstance().makeRetweet(tweetID);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    };
    private ShowProfileDialog showProfileDialog;
    private String screenName;
    Runnable speceficDialog = new Runnable() {
        @Override
        public void run() {
            Content.getInstance().setSpecificProfile(JSONParser.parseUser(Connection.getInstance().getSpeceficUserProfile(screenName)));
            Content.getInstance().setSpecificTweets(JSONParser.parseTweets(Connection.getInstance().getUserTweets(screenName)));
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showProfileDialog.show(getFragmentManager().beginTransaction(), "DialogFragment");
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();

        final PostTweetDialog postTweetDialog = new PostTweetDialog();

        PostTweetDialog.addListener(this);
        adapter = new TweetListAdapter(getContext(), Content.getInstance().getTweets());
        FloatingActionButton postTweet = (FloatingActionButton) view.findViewById(R.id.fab_post_tweet);

        postTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    postTweetDialog.show(fragmentTransaction, "PostTweetFragment");
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
        });

        ListView listView = (ListView) view.findViewById(R.id.listview_tweets);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showProfileDialog = ShowProfileDialog.newInstance(0);
                screenName = Content.getInstance().getTweets().get(position).getUser().getScreenName();
                executorService.execute(speceficDialog);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                CharSequence options[] = new CharSequence[]{"Retweet", "Favourite"};

                // Open option dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Select an option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            // Retweet
                            tweetID = Content.getInstance().getTweets().get(position).getId();
                            executorService.execute(makeRetweet);

                        } else if (which == 1) {
                            // Favourite
                            tweetID = Content.getInstance().getTweets().get(position).getId();
                            executorService.execute(makeFavourite);
                        }
                    }
                });

                builder.show();
                return false;
            }
        });

        return view;

    }

    @Override
    public void onTweedPlaced() {
        adapter.notifyDataSetChanged();
    }
}

