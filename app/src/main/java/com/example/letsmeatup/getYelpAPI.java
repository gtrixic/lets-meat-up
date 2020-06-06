package com.example.letsmeatup;

import org.apache.commons.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class getYelpAPI{
    private static final String API_HOST = "api.yelp.com";
    private static final String YELP_SEARCHPATH = "/v3/businesses/search";
    private static final String clientID = "i6TGYF0mi-FePzzS7cnqBg";
    private static final String APIKEY = "w0hqahBgHMnJVITtl7KmT78HaYTrC9FZrfEu153HfurXH9HI_p2kWxn31_ml-JoP1Rx3Th9qlSPiM968MlOCFEuyOgDhtrFFFbCWV2BVkufDSr27N_InYw63P0HbXnYx";

    public String getYelpRestaurants(){
        HttpURLConnection connection = null;
        try{

            URL url = new URL(API_HOST+YELP_SEARCHPATH);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization","Bearer "+APIKEY);
            connection.setRequestProperty("term","delis");
            connection.setRequestProperty("location","Singapore");
            connection.setRequestProperty("categories","Food");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
