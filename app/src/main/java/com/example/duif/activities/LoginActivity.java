package com.example.duif.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.example.duif.Interfaces.UrlHandler;
import com.example.duif.R;
import com.example.duif.communication.Connection;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity  implements UrlHandler{
    public static boolean isLoogedIn = false;
    private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";
    private String verifier;
    private ExecutorService executorService = Executors.newFixedThreadPool(2);
    Runnable getAccessToken;

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
                Connection.getInstance().getAccesToken(verifier);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        };



        executorService.execute(task);


        try {
            Log.d("Connection", "Connection up");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Button for starting the WebViewActivity
        Button loginButton = (Button)findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webViewIntent = new Intent(getApplicationContext(), WebViewActivity.class);
                webViewIntent.putExtra("URL", url[0] );
                startActivity(webViewIntent);
            }
        });

    }

    private String getOauthVerifier(String url) {
        String[] parts = url.split("oauth_verifier=");
        return parts[1];
    }

    @Override
    public void onLoggedIn(String url) {
        verifier = getOauthVerifier(url);
        executorService.execute(getAccessToken);
    }


}
