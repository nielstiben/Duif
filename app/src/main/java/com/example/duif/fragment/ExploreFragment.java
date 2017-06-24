package com.example.duif.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.duif.R;
import com.example.duif.controller.TweetListAdapter;
import com.example.duif.model.Content;

public class ExploreFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_explore, container, false);

        EditText search = (EditText)view.findViewById(R.id.et_search);


        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                TextView result = (TextView)view.findViewById(R.id.tv_result);
                ListView resultTweetsList = (ListView)view.findViewById(R.id.lv_search_result_tweets);

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    TweetListAdapter adapter = new TweetListAdapter(getContext(), Content.getInstance().getTweets());
                    resultTweetsList.setAdapter(adapter);

                }
                return false;
            }
        });



        return view;
    }

}
