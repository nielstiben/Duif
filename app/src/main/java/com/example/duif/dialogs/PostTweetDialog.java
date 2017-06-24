package com.example.duif.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.duif.R;

/**
 * Created by raffe on 24-6-2017.
 */

public class PostTweetDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
// Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

// Inflate and set the layout for the dialog
// Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_post_tweet, null))
// Add action buttons
                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Post tweet
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       //
                       PostTweetDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
