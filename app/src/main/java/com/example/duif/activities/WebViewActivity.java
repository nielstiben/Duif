package com.example.duif.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.duif.Interfaces.UrlHandler;
import com.example.duif.R;

/**
 * This is the webview that loads the twitter page.
 */
public class WebViewActivity extends AppCompatActivity {
    private static UrlHandler listener;
    private WebView webContent;

    /**
     * Add the class to the interface.
     *
     * @param urlHandler Class that implements the interface.
     */
    public static void addListener(UrlHandler urlHandler) {
        listener = urlHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        final String url = getIntent().getStringExtra("URL");
        System.out.println("DIRECT URL = " + url);
        if (url != null) {
            webContent = (WebView) findViewById(R.id.webView);
            webContent.loadUrl(url);
            webContent.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    System.out.println("URL CHANGED " + webContent.getUrl());
                    view.loadUrl(request.getUrl().toString());

                    if (webContent.getUrl().startsWith("https://saxion.nl/connection/?oauth_token=")) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        listener.onLoggedIn(webContent.getUrl());
                        startActivity(intent);

                    }
                    return true;
                }
                //for older phones.

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    System.out.println("URL CHANGED " + webContent.getUrl());
                    view.loadUrl(url);

                    if (webContent.getUrl().startsWith("https://saxion.nl/connection/?oauth_token=")) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        listener.onLoggedIn(webContent.getUrl()); //TODO Later on check if it's really the logged in url.
                        startActivity(intent);

                    }
                    return true;
                }
            });

        }

    }

}
