package com.example.letsmeatup;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*Simple GET Request from YELP API to get restaurant data from yelp website, convert to restaurantdata and posting into database as an asynctask*/

public class getYelpAPI extends AsyncTask<HashMap<String,String>,Void,ArrayList<RestaurantData>> {
    private String TAG = "YELP-API";
    @SuppressLint("StaticFieldLeak")
    //specifiying static information to call the api
    private Context context;
    private String chatID;
    private static final String API_HOST = "https://api.yelp.com/v3/businesses/search?";
    private static final String APIKEY = "w0hqahBgHMnJVITtl7KmT78HaYTrC9FZrfEu153HfurXH9HI_p2kWxn31_ml-JoP1Rx3Th9qlSPiM968MlOCFEuyOgDhtrFFFbCWV2BVkufDSr27N_InYw63P0HbXnYx";
    public getYelpAPI(Context context,String chatID){this.context = context;this.chatID=chatID;}
    @SafeVarargs
    @Override
    //function is called when a category is provided eg.japanese to get info from yelp api
    protected final ArrayList<RestaurantData> doInBackground(HashMap<String, String>... params) {
        HttpURLConnection connection;
        URL url;
        try {
            //test if url works with provided parameters
            url = new URL(API_HOST+getPostDataString(params[0]));
            //open connection with url
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            //setting header for get request
            connection.setRequestProperty("Authorization","Bearer "+APIKEY);

            //returning response code from get request
            int responsecode = connection.getResponseCode();
            Log.v(TAG,"Response Code:"+responsecode);
            //read incoming info
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer result = new StringBuffer();
            //appending info to string buffer var
            while ((inputLine = reader.readLine()) != null) {
                result.append(inputLine);
                Log.v(TAG,result.toString());
            }
            //close reader
            reader.close();
            Log.v(TAG,"Get API called!");
            //return an arraylist of RestaurantData to onPostExecute Method
            return convertToRestaurantData(result.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.v(TAG,"GET API failed");
        }

        return null;
    }
    //function is called in doInBackground to convert json objects to restaurant data
    private ArrayList<RestaurantData> convertToRestaurantData(String result) throws JSONException {
        //deserializing json objects into rData and create a rDataList
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
            Log.v(TAG,displayLocation.toString());
            StringBuilder finallocation = new StringBuilder();
            for(int index = 0; index < displayLocation.length(); index ++){
                finallocation.append(displayLocation.get(index).toString());
            }
            Log.v(TAG,finallocation.toString());
            rData.setAddress(finallocation.toString());
            rData.setPfpLink(Business.getJSONObject(i).get("image_url").toString());
            Log.v(TAG,rData.getPfpLink());
            rData.setPassword("password");
            rData.setEmail(Business.getJSONObject(i).get("name").toString()+"@email.com");
            JSONArray categories = Business.getJSONObject(i).getJSONArray("categories");
            rData.setCategory(categories.getJSONObject(0).get("title").toString());
            Log.v(TAG,rData.getCategory());
            rDataList.add(rData);
        }
        return rDataList;
    }
    //function used to build url parameters in doInBackground
    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            //append lines, if the line is not the first line then append with & to link
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
    protected void onPostExecute(final ArrayList<RestaurantData> rData){
        Random ran = new Random();
        Log.v(TAG, String.valueOf(rData.size()));
        int randomVar = ran.nextInt(rData.size());
        final RestaurantData chosen = rData.get(randomVar);
        Log.v(TAG, String.valueOf(chosen.getRestaurantName()));
        Log.v(TAG, chosen.getPfpLink());
        RestaurantDialog rDialog = new RestaurantDialog(context,chosen,chatID);
        Log.v(TAG, String.valueOf(context));
        rDialog.setImage(chosen.getPfpLink());
        rDialog.setRestName(chosen.getRestaurantName());
        rDialog.setRestAddr(chosen.getAddress());
        rDialog.setRestType(chosen.getCategory());
        rDialog.show();
        }
}

