package com.example.letsmeatup;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class getYelpAPI extends AsyncTask<HashMap<String,String>,Void,ArrayList<RestaurantData>> {
    private String TAG = "YELP-API";
    private Context context;
    private String term;
    private static final String API_HOST = "https://api.yelp.com/v3/businesses/search?";
    private static final String APIKEY = "w0hqahBgHMnJVITtl7KmT78HaYTrC9FZrfEu153HfurXH9HI_p2kWxn31_ml-JoP1Rx3Th9qlSPiM968MlOCFEuyOgDhtrFFFbCWV2BVkufDSr27N_InYw63P0HbXnYx";
    public getYelpAPI(Context context,String term){this.context = context; this.term = term;}

    @Override
    protected ArrayList<RestaurantData> doInBackground(HashMap<String,String>... params) {
        HttpURLConnection connection;
        URL url = null;
        try {
            url = new URL(API_HOST+getPostDataString(params[0]));
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization","Bearer "+APIKEY);

            int responsecode = connection.getResponseCode();
            Log.v(TAG,"Response Code:"+responsecode);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer result = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                result.append(inputLine);
            }
            reader.close();
            Log.v(TAG,result.toString());
            for(RestaurantData restaurantData:convertToRestaurantData(result.toString())){
                String name = restaurantData.getRestaurantName();
                Log.v(TAG,name);
            }
            return convertToRestaurantData(result.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    ;
    /*@Override
    protected ArrayList<RestaurantData> doInBackground(HashMap<String,String>... params) throws IOException, JSONException {
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

     */

    private ArrayList<RestaurantData> convertToRestaurantData(String result) throws JSONException {
        ArrayList<RestaurantData> rDataList = new ArrayList<>();
        JSONObject JsonResults = new JSONObject(result);
        JSONArray Business = JsonResults.getJSONArray("businesses");
        Log.v(TAG,Business.toString());
        //get name,display_address,category,imageurl,autogenerate password and email
        for(int i = 0; i < Business.length(); i++){
            RestaurantData rData = new RestaurantData();

            rData.setRestaurantName(Business.getJSONObject(i).get("name").toString());
            Log.v(TAG,rData.getRestaurantName());
            JSONObject Location = (JSONObject) Business.getJSONObject(i).get("location");

            JSONArray displayLocation = Location.getJSONArray("display_address");
            StringBuilder finallocation = new StringBuilder();
            for(int index = 0; index < displayLocation.length(); index ++){
                finallocation.append(displayLocation.getJSONObject(index).toString());
            }
            Log.v(TAG,finallocation.toString());
            rData.setAddress(finallocation.toString());
            rData.setPfpLink(Business.getJSONObject(i).get("image_url").toString());
            Log.v(TAG,rData.getPfpLink());
            rData.setPassword("password");
            rData.setEmail(Business.getJSONObject(i).get("name").toString()+"@email.com");
            rData.setCategory(Business.getJSONObject(i).getJSONArray("categories").getJSONObject(0).getJSONObject("title").toString());
            Log.v(TAG,rData.getCategory());
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

        @Override
    protected void onPostExecute(ArrayList<RestaurantData> rData){
        LMUDBHandler handler = new LMUDBHandler(context,null,null,1);
            try {
                handler.addRestaurants(rData);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }



}

