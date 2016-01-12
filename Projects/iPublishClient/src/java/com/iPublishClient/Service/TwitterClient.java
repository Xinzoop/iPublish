/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iPublishClient.Service;

import com.google.gson.Gson;
import com.iPublishClient.Entities.TwitterResponse;
import java.net.URLEncoder;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 *
 * @author zipv5_000
 */
public class TwitterClient {
    
    public static final String CONSUMER_KEY = "HZqHaxeLwMbrfl06i4QQiA";
    public static final String CONSUMER_SECRET = "9OpeQ06vub27q52qb8IdCrU7RYSHTFjw8MuFjC20s";
    public static final String OAUTH_TOKEN = "2338343478-qahLN4WylQln8O5OxanIXBsDwKbOlIe3r6WQlBt";
    public static final String OAUTH_TOKEN_SECRET = "fbeyRcEUM1v6tzgyvFkHkrfcCWRyUK9OTV0yB0GA1dgBQ";
    
    public TwitterResponse TwitterSearch(String query, String maxId) throws Exception {
        OAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(OAUTH_TOKEN, OAUTH_TOKEN_SECRET);
        String URL = "https://api.twitter.com/1.1/search/tweets.json";
        URL += "?q=" + URLEncoder.encode(query, "UTF-8");
        URL += "&lang=en&include_entities=false&count=100";
        if(!maxId.isEmpty())
            URL += "&max_id=" + maxId;
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{};
        RestConnection conn = new RestConnection(consumer.sign(URL), pathParams, queryParams);
        RestResponse response = conn.get();
        return new Gson().fromJson(response.getDataAsString(), TwitterResponse.class);
    }
}
