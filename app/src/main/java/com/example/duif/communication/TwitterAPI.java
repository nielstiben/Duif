package com.example.duif.communication;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1RequestToken;

/**
 * Created by wybrenoppedijk on 14/06/2017.
 */

public class TwitterAPI extends DefaultApi10a {
    @Override
    public String getRequestTokenEndpoint() {
        return "https://api.twitter.com/oauth/request_token";
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://api.twitter.com/oauth/access_token";
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
        return "https://api.twitter.com/oauth/authorize?oauth_token=" + requestToken.getToken();
    }
}
