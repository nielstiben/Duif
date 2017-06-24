package com.example.duif.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duif.R;
import com.example.duif.activities.MainActivity;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

/**
 * Created by raffe on 24-6-2017.
 */

public class PostTweetDialog extends DialogFragment {
    private static int counter;
    private static TextView count;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_post_tweet, null);

        // Inflate and set the layout for the dialog
        count = (TextView)view.findViewById(R.id.tv_char_count);
        final EditText tweet = (EditText)view.findViewById(R.id.et_tweet);

        counter = 0;
        count.setText(String.format("%s / 160", String.valueOf(counter)));

        tweet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                counter = tweet.getText().length();
                count.setText(String.format("%s / 160", String.valueOf(counter)));
            }
        });
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(), "'" + tweet.getText().toString() + "' Posted!",Toast.LENGTH_LONG).show();
                        // Post tweet
                        String tweetText = tweet.getText().toString();
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
