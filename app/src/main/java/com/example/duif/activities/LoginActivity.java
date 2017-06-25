package com.example.duif.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.duif.Interfaces.UrlHandler;
import com.example.duif.R;
import com.example.duif.communication.Connection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is responsible for Login. It contains a webview wich redirects you to the twitter login page.
 */
public class LoginActivity extends AppCompatActivity implements UrlHandler {
    public static boolean isLogedIn = false;
    Runnable getAccessToken;
    private String verifier;
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        WebViewActivity.addListener(this);

        final String[] url = new String[1];


        Runnable task = new Runnable() {

            @Override
            public void run() {
                url[0] = Connection.getInstance().getRequestUrl();
            }
        };

        getAccessToken = new Runnable() {
            @Override
            public void run() {
                Connection.getInstance().getAccessToken(verifier);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        };


        executorService.execute(task);


        // Button for starting the WebViewActivity
        Button loginButton = (Button) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webViewIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                webViewIntent.putExtra("URL", url[0]);
                startActivity(webViewIntent);
            }
        });

    }

    /**
     * Splits the url and looks for the verifier.
     *
     * @param url The callback url.
     * @return OAUth verifier.
     */
    private String getOauthVerifier(String url) {
        String[] parts = url.split("oauth_verifier=");
        return parts[1];
    }

    /**
     * Callback method. Gets called if the user has logged in.
     *
     * @param url the verification url from twitter.
     */
    @Override
    public void onLoggedIn(String url) {
        verifier = getOauthVerifier(url);
        executorService.execute(getAccessToken);
    }
}
