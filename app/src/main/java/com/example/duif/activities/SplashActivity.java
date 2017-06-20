package com.example.duif.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("com.example.duif", Context.MODE_PRIVATE);



        if(!prefs.getBoolean("isLoggedIn", true)){
            // show login screen
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else{
            // user already logged in, show tweets
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            finish();
        }


    }
}
