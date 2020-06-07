package com.example.letsmeatup;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.service.autofill.UserData;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LMUDBHandler extends SQLiteOpenHelper {
    private String TAG = "Let's Meat Up";
    private String FILENAME = "LMUDBHandler.java";
    private static String PREF_NAME = "prefs";

    public static String DATABASE_NAME = "LMUaccountDB.db";
    public static int DATABASE_VERSION = 6;
    //User accounts table
    public static String ACCOUNTS = "UserAccounts";
    public static String COLUMN_FULLNAME = "Fullname";
    public static String COLUMN_USERNAME = "Username";
    public static String COLUMN_PASSWORD = "Password";
    public static String COLUMN_EMAIL = "Email";
    public static String COLUMN_GENDER = "Gender";
    public static String COLUMN_DOB = "DOB";
    public static String COLUMN_SP = "SP";
    public static String COLUMN_MATCHID = "MatchID";
    //Restaurant account table
    public static String RESTAURANTS = "RestaurantAccounts";
    public static String COLUMN_RESTAURANTNAME = "RName";
    public static String COLUMN_ADDRESS = "Address";
    public static String COLUMN_RPASSWORD = "Password";
    public static String COLUMN_RESTAURANTEMAIL = "Email";
    public static String COLUMN_CATEGORY = "Category";
    public static String COLUMN_PFP = "ProfilePictureLink";
    public LMUDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int v){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + ACCOUNTS + "(AccountID"+ " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FULLNAME +
                " TEXT," +COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_EMAIL + " TEXT," + COLUMN_GENDER + " TEXT," + COLUMN_DOB
                + " TEXT," + COLUMN_SP + " TEXT," + COLUMN_MATCHID +" INTEGER"+")";
        String CREATE_RESTAURANT_TABLE = "CREATE TABLE " + RESTAURANTS + "(" +COLUMN_RESTAURANTNAME + " TEXT," + COLUMN_ADDRESS + " TEXT,"+ COLUMN_RPASSWORD + " TEXT,"+
                COLUMN_CATEGORY + " TEXT,"+
                COLUMN_RESTAURANTEMAIL +" TEXT," + COLUMN_PFP + " TEXT)";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
        db.execSQL(CREATE_RESTAURANT_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS "+RESTAURANTS);
        onCreate(db);
    }
    public void addUser(AccountData accountData){
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULLNAME,accountData.getFullName());
        values.put(COLUMN_USERNAME, accountData.getUsername());
        values.put(COLUMN_PASSWORD, accountData.getPassword());
        values.put(COLUMN_EMAIL,accountData.getEmail());
        values.put(COLUMN_GENDER,accountData.getGender());
        values.put(COLUMN_DOB,accountData.getDob());
        values.put(COLUMN_SP, accountData.getSp());
        //Get Database
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v(TAG, FILENAME+ ": "+ values.toString());
        db.insert(ACCOUNTS, null, values);
        db.close();

    }
    public void addRestaurant(RestaurantData rdata){
        ContentValues values = new ContentValues();
        values.put(COLUMN_RESTAURANTNAME,rdata.getRestaurantName());
        values.put(COLUMN_ADDRESS,rdata.getAddress());
        values.put(COLUMN_PASSWORD,rdata.getPassword());
        values.put(COLUMN_RESTAURANTEMAIL,rdata.getEmail());
        values.put(COLUMN_CATEGORY,rdata.getCategory());
        values.put(COLUMN_PFP,rdata.getPfpLink());
        //Get Database
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v(TAG,FILENAME + ": "+ values.toString());
        db.insert(RESTAURANTS,null,values);
        db.close();
    }
    public RestaurantData findRestaurant(String Remail){
        String query = "SELECT * FROM "+RESTAURANTS +" WHERE " + COLUMN_RESTAURANTEMAIL + "=\""+Remail+"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        RestaurantData rData = new RestaurantData();
        if(cursor.moveToFirst()){
            rData.setRestaurantName(cursor.getString(0));
            rData.setAddress(cursor.getString(1));
            rData.setPassword(cursor.getString(2));
            rData.setEmail(cursor.getString(3));
            rData.setPfpLink(cursor.getString(4));
            cursor.close();
        }
        else{
            rData = null;
        }
        db.close();
        return rData;
    }
    public AccountData findUser(String username){
        String query ="SELECT * FROM " + ACCOUNTS +" WHERE "+COLUMN_USERNAME +"=\""+username +"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery(query,null);
        AccountData queryData = new AccountData();
        if (cursor.moveToFirst()){
            queryData.setID((cursor.getInt(0)));
            queryData.setFullName(cursor.getString(1));
            queryData.setUsername(cursor.getString(2));
            queryData.setPassword(cursor.getString(3));
            queryData.setEmail(cursor.getString(4));
            queryData.setGender(cursor.getString(5));
            queryData.setDob(cursor.getString(6));
            queryData.setSp(cursor.getString(7));
            cursor.close();

        }
        else{
            queryData = null;
        }
        db.close();
        return queryData;
    }
    public AccountData findEmail(String email){
        String query ="SELECT * FROM " + ACCOUNTS +" WHERE "+COLUMN_EMAIL +"=\""+email +"\"";
        SQLiteDatabase  db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(query,null);
        AccountData queryData = new AccountData();
        if (cursor.moveToFirst()){
            queryData.setFullName(cursor.getString(1));
            queryData.setUsername(cursor.getString(2));
            queryData.setPassword(cursor.getString(3));
            queryData.setEmail(cursor.getString(4));
            queryData.setGender(cursor.getString(5));
            queryData.setDob(cursor.getString(6));
            queryData.setSp(cursor.getString(7  ));
            cursor.close();

        }
        else{
            queryData = null;
        }
        db.close();
        return queryData;
    }
    public void updatePassword(String input, String password){
        AccountData dbData = this.findUser(input);
        AccountData dbData2 = this.findEmail(input);
        SQLiteDatabase db = this.getWritableDatabase();
        if (dbData!= null){
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_PASSWORD, password);
            db.update(ACCOUNTS, cv,COLUMN_USERNAME+"='"+input+"'",null);
            db.close();
        }
        if(dbData2!=null){
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_PASSWORD, password);
            db.update(ACCOUNTS, cv,COLUMN_EMAIL+"='"+input+"'",null);
            db.close();
        }
    }
    //Used with yelp api, only for admin use
    public void addRestaurants(ArrayList<RestaurantData> rDataList) throws IOException, JSONException {
        for(RestaurantData rdata : rDataList){
            this.addRestaurant(rdata);
        }

    }

    //add match param
    public void addMatchID(String[] matchID){
        //TODO:Add SharedPreference to store login info to retrieve username, or store username info in SharedPreference during signup.
        //find user in database
        AccountData account = this.findUser()
        //convert matchid to string
        StringBuffer matchid = new StringBuffer();
        for (int i = 0; i < matchID.length;i++){
            matchid.append(matchID[i]);
        }
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MATCHID,matchid.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(ACCOUNTS,cv,COLUMN)
    }

    private static SharedPreferences getPrefs(Context context){
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUsername(Context context, String input){
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("username",input);
    }

    public AccountData getUser(Context ctx,String input){
        String username = getPrefs(ctx).getString("username",input);
        return findUser(username);
    }

}
