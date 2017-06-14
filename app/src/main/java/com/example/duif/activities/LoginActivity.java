package com.example.duif.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.duif.R;
import com.example.duif.communication.Connection;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {
    private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("TEST");
                String url = Connection.getInstance().getRequestUrl();
                System.out.println("TEST2");
                System.out.println(url);
                //System.out.println(getOauthToken(url));

                //// TODO: 14/06/2017 shutoveridemethodloading -> return true als goed, false als fout. En dan die url in de onderstaande method
                //Connection.getInstance().getAccesToken(getOauthToken(url));
                //8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=> 8=>
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
                startActivity(webViewIntent);
            }
        });

    }

    private String getOauthToken(String url) {
        String[] parts = url.split("(?<=oauth_verifier=)");
        return parts[1];
    }

}
