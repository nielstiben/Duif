package com.example.duif.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.duif.Interfaces.UrlHandler;
import com.example.duif.R;

import java.sql.SQLOutput;

public class WebViewActivity extends AppCompatActivity {
    private WebView webContent;
    private static UrlHandler listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        final String url = getIntent().getStringExtra("URL");
        System.out.println("DIRECT URL = " + url);
        if (url != null) {
            webContent = (WebView) findViewById(R.id.webView);
            webContent.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    webContent.loadUrl(url);
                    System.out.println("Loads url correct");
                    super.onPageStarted(view, url, favicon);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    System.out.println("URL CHANGED");
                    //listener.onLoggedIn(webContent.getUrl()); //TODO Later on check if it's really the logged in url.
                    return super.shouldOverrideUrlLoading(view, request);
                }
            });
        }

    }

    public static void addListener(UrlHandler urlHandler){
        listener = urlHandler;
    }

}
