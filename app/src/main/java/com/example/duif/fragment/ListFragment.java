package com.example.duif.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.duif.R;
import com.example.duif.activities.MainActivity;
import com.example.duif.controller.TweetListAdapter;
import com.example.duif.model.Content;
import com.example.duif.model.Tweet;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ArrayList<Tweet> tweets = new ArrayList<>();

        tweets.add(new Tweet("10-01-2007", "Pietje paulus zijn bericht."));
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        TweetListAdapter adapter = new TweetListAdapter(getContext(), MainActivity.tweets);

        ListView listView = (ListView) view.findViewById(R.id.listview_tweets);
        listView.setAdapter(adapter);


        return view;
    }
}
