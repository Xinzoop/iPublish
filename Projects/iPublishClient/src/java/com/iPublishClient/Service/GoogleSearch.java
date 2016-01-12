/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iPublishClient.Service;

import com.google.gson.Gson;
import com.iPublishClient.Entities.GoogleResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 *
 * @author zipv5_000
 */
public class GoogleSearch {
        
//    public static final String API_KEY = "AIzaSyDTdBcClXjkFUWIK3exS0hkGAqbKmHiBEA";
//    public static final String SEARCH_ENGINE_ID = "017798893646090660427:kxf8thm16si";
    public static final String API_KEY = "AIzaSyC9crRvBlXF0RNe7CezRJ8TnNr41KqUBWk";
    public static final String SEARCH_ENGINE_ID = "017798893646090660427:3urunlb82eg";
    
    public GoogleResponse Search(String query, String site, Boolean image) throws IOException{    
        return new Gson().fromJson(SearchResponse(query, site, image), GoogleResponse.class);
    }
    public String SearchResponse(String query, String site, Boolean image) throws IOException{
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{};
        String _URL = "https://www.googleapis.com/customsearch/v1?key=" + API_KEY + "&cx="
                + SEARCH_ENGINE_ID + "&num=5";
        _URL += "&q=" + URLEncoder.encode(query, "UTF-8");
        if(!site.isEmpty()){
            _URL += "&siteSearch=" + URLEncoder.encode(site, "UTF-8") + "&siteSearchFilter=i";
        }
        if(image){
            _URL += "&searchType=image&fileType=jpg&imgType=photo";
        }
        RestConnection conn = new RestConnection(_URL, pathParams, queryParams); 
        RestResponse res = conn.get();
        return res.getDataAsString();
    }
}
