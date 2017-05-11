package com.example.duif.model;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

public class TweetListAdapter extends ArrayAdapter{
    public TweetListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Tweet[] tweets) {
        super(context, resource, tweets);
    }
}
