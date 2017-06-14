package com.example.duif.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.duif.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        String url = getIntent().getStringExtra("URL");
        if(url != null){
            WebView webContent = (WebView)findViewById(R.id.webView);
            webContent.setWebViewClient(new WebViewClient());
            webContent.loadUrl(url);
        }
    }
}
