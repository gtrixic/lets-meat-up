/*
package com.example.letsmeatup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;



public class getYelpAPI{
    private static final String API_HOST = "https://api.yelp.com/v3/businesses/search?";
    private static final String APIKEY = "w0hqahBgHMnJVITtl7KmT78HaYTrC9FZrfEu153HfurXH9HI_p2kWxn31_ml-JoP1Rx3Th9qlSPiM968MlOCFEuyOgDhtrFFFbCWV2BVkufDSr27N_InYw63P0HbXnYx";

    public String getYelpRestaurants(HashMap<String,String>params) throws IOException {
        HttpURLConnection connection;
        URL url = new URL(API_HOST+getPostDataString(params));
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization","Bearer "+APIKEY);


        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer result = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            result.append(inputLine);
        }
        reader.close();
        return result.toString();


    }

    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }



}*/

