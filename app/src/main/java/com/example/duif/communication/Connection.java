package com.example.duif.communication;

import android.os.AsyncTask;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;

public class Connection {
    final static String API_KEY = "0PkiewnTf18DRv4buIx9JszMT";
    final static String API_SECRET = "K6XLrVWu18eeJC0hAsQdJHINhVr5eiRdEIXoHhYa6AEpoHAdzR";


    public class GetRequestToken extends AsyncTask<Object, Object, Void> {
        @Override
        protected Void doInBackground(Object... params) {
           final OAuth1RequestToken requestToken;
            final OAuth10aService service = new ServiceBuilder()
                    .apiKey(API_KEY)
                    .apiSecret(API_SECRET)
                    .build(TwitterApi.instance());
            try {
                 requestToken = service.getRequestToken();
                System.out.println("DEBUG: " + requestToken);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class GetAccestoken extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
//            final OAuth1AccessToken accessToken = service.getAccessToken(requestToken, "verifier you got from the user/callback");
//
//            final OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json", service);
//            service.signRequest(accessToken, request); // the access token from step 4
//            final Response response = request.send();
//            System.out.println(response.getBody());
            return null;
        }
    }
}