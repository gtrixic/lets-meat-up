package com.example.letsmeatup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class getYelpAPI{
    private static final String API_HOST = "https://api.yelp.com/v3/businesses/search?";
    private static final String APIKEY = "w0hqahBgHMnJVITtl7KmT78HaYTrC9FZrfEu153HfurXH9HI_p2kWxn31_ml-JoP1Rx3Th9qlSPiM968MlOCFEuyOgDhtrFFFbCWV2BVkufDSr27N_InYw63P0HbXnYx";
    public getYelpAPI(){};

    public ArrayList<RestaurantData> getYelpRestaurants(HashMap<String,String>params) throws IOException, JSONException {
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
        return convertToRestaurantData(result.toString());


    }

    private static ArrayList<RestaurantData> convertToRestaurantData(String result) throws JSONException {
        ArrayList<RestaurantData> rDataList = new ArrayList<>();
        JSONObject JsonResults = new JSONObject(result);
        JSONArray Business = JsonResults.getJSONArray("businesses");
        //get name,display_address,category,imageurl,autogenerate password and email
        for(int i = 0; i < Business.length(); i++){
            RestaurantData rData = new RestaurantData();

            rData.setRestaurantName(Business.getJSONObject(i).get("name").toString());
            JSONObject Location = (JSONObject) Business.getJSONObject(i).get("location");
            JSONArray displayLocation = Location.getJSONArray("display_address");
            StringBuilder finallocation = new StringBuilder();
            for(int index = 0; index < displayLocation.length(); index ++){
                finallocation.append(displayLocation.getJSONObject(index).toString());
            }
            rData.setAddress(finallocation.toString());
            rData.setPfpLink(Business.getJSONObject(i).get("image_url").toString());
            rData.setPassword("password");
            rData.setEmail(Business.getJSONObject(i).get("name").toString()+"@email.com");
            rData.setCategory(Business.getJSONObject(i).getJSONArray("categories").getJSONObject(0).getJSONObject("title").toString());
            rDataList.add(rData);
        }
        return rDataList;
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



}

