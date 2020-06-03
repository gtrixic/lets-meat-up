package com.example.letsmeatup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.service.autofill.UserData;
import android.util.Log;

public class LMUDBHandler extends SQLiteOpenHelper {
    private String TAG = "Let's Meat Up";
    private String FILENAME = "LMUDBHandler.java";

    public static String DATABASE_NAME = "LMUaccountDB.db";
    public static int DATABASE_VERSION = 1;
    public static String ACCOUNTS = "Accounts";
    public static String COLUMN_FULLNAME = "Fullname";
    public static String COLUMN_USERNAME = "Username";
    public static String COLUMN_PASSWORD = "Password";
    public static String COLUMN_EMAIL = "Email";
    public static String COLUMN_GENDER = "Gender";
    public static String COLUMN_DOB = "DOB";
    public static String COLUMN_SP = "Sexual Preference";
    public LMUDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int v){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + ACCOUNTS + "(" + COLUMN_FULLNAME +
                " TEXT," +COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_EMAIL + " TEXT," + COLUMN_GENDER + " TEXT," + COLUMN_DOB
                + " NUMERIC," + COLUMN_SP + "TEXT" + ")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ACCOUNTS);
        onCreate(db);
    }
    public void addUser(UserData userData){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, userData.getUsername());
        values.put(COLUMN_FULLNAME,userData.getFullname());
        values.put(COLUMN_PASSWORD, userData.getPassword());
        values.put(COLUMN_EMAIL,userData.getEmail());
        values.put(COLUMN_GENDER,userData.getGender());
        values.put(COLUMN_DOB,userData.getDob());
        values.put(COLUMN_SP, userData.getSp());
        //Get Database
        SQLiteDatabase db = this.getWritableDatabase();
        Log.v(TAG, FILENAME+ ": "+ values.toString());
        db.insert(ACCOUNTS, null, values);
        db.close();

    }
    public UserData findUser(String username){
        String query ="SELECT * FROM " + ACCOUNTS +" WHERE "+COLUMN_USERNAME +"=\""+username +"\"";
        SQLiteDatabase  db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(query,null);
        com.example.letsmeatup.UserData queryData = new com.example.letsmeatup.UserData();

    }

}
