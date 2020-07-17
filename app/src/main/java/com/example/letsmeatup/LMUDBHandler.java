package com.example.letsmeatup;

import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.service.autofill.UserData;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LMUDBHandler extends SQLiteOpenHelper {
    private String TAG = "Let's-Meat-Up";
    private String FILENAME = "LMUDBHandler.java";
    private static String PREF_NAME = "prefs";
    private DatabaseReference fireRef;

    public static String DATABASE_NAME = "LMUaccountDB.db";
    public static int DATABASE_VERSION = 8;
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
    public static String COLUMN_ALLERGIES = "Allergies";
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
                + " TEXT," + COLUMN_SP + " TEXT," + COLUMN_MATCHID +" INTEGER,"+COLUMN_ALLERGIES+" String"+")"; //creating account table
        String CREATE_RESTAURANT_TABLE = "CREATE TABLE " + RESTAURANTS + "(" +COLUMN_RESTAURANTNAME + " TEXT," + COLUMN_ADDRESS + " TEXT,"+ COLUMN_RPASSWORD + " TEXT,"+
                COLUMN_CATEGORY + " TEXT,"+
                COLUMN_RESTAURANTEMAIL +" TEXT," + COLUMN_PFP + " TEXT)"; //creating restaurant table
        db.execSQL(CREATE_ACCOUNTS_TABLE);
        db.execSQL(CREATE_RESTAURANT_TABLE);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){ //upgrading database from old to new version and
                                                                                //dropping previous tables to update to new ones
        Log.v(TAG,"Upgraded Database from version "+oldVersion+" to version "+newVersion);
        db.execSQL("DROP TABLE IF EXISTS "+ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS "+RESTAURANTS);
        onCreate(db);
    }
//    public void addUser(AccountData accountData){ //adding a new user into the user accounts table
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_FULLNAME,accountData.getFullName());
//        values.put(COLUMN_USERNAME, accountData.getUsername());
//        values.put(COLUMN_PASSWORD, accountData.getPassword());
//        values.put(COLUMN_EMAIL,accountData.getEmail());
//        values.put(COLUMN_GENDER,accountData.getGender());
//        values.put(COLUMN_DOB,accountData.getDob());
//        values.put(COLUMN_SP, accountData.getSp());
//        //Get Database
//        SQLiteDatabase db = this.getWritableDatabase();
//        Log.v(TAG, FILENAME+ ": "+ values.toString());
//        db.insert(ACCOUNTS, null, values);
//        db.close();
//
//    }
    public void addRestaurant(RestaurantData rdata){ //adding a new user into the restaurant accounts table
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
    public RestaurantData findRestaurant(String Remail){ //to find the selected restaurant account by its email
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
    AccountData findUser(String username){ //to returned the selected user by its username
        String query ="SELECT * FROM " + ACCOUNTS +" WHERE "+COLUMN_USERNAME +"=\""+username +"\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery(query,null);
        // temp account details holder
        AccountData queryData = new AccountData();
        if (cursor.moveToFirst()){
            queryData.setID((cursor.getString(0)));
            queryData.setFullName(cursor.getString(1));
            queryData.setUsername(cursor.getString(2));
            queryData.setPassword(cursor.getString(3));
            queryData.setEmail(cursor.getString(4));
            queryData.setGender(cursor.getString(5));
            queryData.setDob(cursor.getString(6));
            queryData.setMatchid(cursor.getString(8));
            cursor.close();

        }
        else{
            queryData = null;
        }
        db.close();
        return queryData;
    }

    public AccountData findEmail(String email){ //to return the selected user by the email
                String query ="SELECT * FROM " + ACCOUNTS +" WHERE "+COLUMN_EMAIL +"=\""+email +"\"";
                SQLiteDatabase  db =this.getWritableDatabase();
                Cursor cursor =db.rawQuery(query,null);
                // temp account details holder
                AccountData queryData = new AccountData();
                if (cursor.moveToFirst()){
                    queryData.setFullName(cursor.getString(1));
                    queryData.setUsername(cursor.getString(2));
                    queryData.setPassword(cursor.getString(3));
                    queryData.setEmail(cursor.getString(4));
                    queryData.setGender(cursor.getString(5));
                    queryData.setDob(cursor.getString(6));

                    cursor.close();
        }
        else{
            queryData = null;
        }
        db.close();
        return queryData;
    }
   public AccountData findMatchingID(String mID){ //return a random user with the stated match ID
        String query ="SELECT * FROM " + ACCOUNTS +" WHERE "+COLUMN_MATCHID +"=\""+mID +"\"";
            SQLiteDatabase  db =this.getWritableDatabase();
            Cursor cursor =db.rawQuery(query,null);
            // temp account details holder
            AccountData queryData = new AccountData();
            Log.v(TAG, FILENAME+mID);
            // get random number to choose paired user
            Random ran = new Random();
            Log.v(TAG,FILENAME+String.valueOf(cursor.getCount()));
            int randomMatchID = ran.nextInt(cursor.getCount());
            Log.v(TAG,String.valueOf(randomMatchID));
            if (cursor.moveToPosition(randomMatchID)){ //enter account information into queryData
                queryData.setFullName(cursor.getString(1));
                queryData.setUsername(cursor.getString(2));
                queryData.setPassword(cursor.getString(3));
                queryData.setEmail(cursor.getString(4));
                queryData.setGender(cursor.getString(5));
                queryData.setDob(cursor.getString(6));
                queryData.setMatchid(cursor.getString(7));
                cursor.close();
            }
        else{
            queryData = null;
        }

        db.close();
        return queryData;
    }

    public String findMatchID(String id){ //return the match ID of the user by the username
        String query ="SELECT * FROM " + ACCOUNTS +" WHERE "+COLUMN_USERNAME +"=\""+id +"\"";
        SQLiteDatabase  db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(query,null);
        // temp account details holder
        AccountData queryData = new AccountData();
        if (cursor.moveToFirst()) {
            queryData.setMatchid(cursor.getString(8));
            cursor.close();
        }
        else{
            Log.v(TAG,"No user found!");
            queryData = null;
        }
        db.close();

        return queryData.getMatchid();
    }

    //Used with yelp api, only for admin use
    public void addRestaurants(ArrayList<RestaurantData> rDataList) throws IOException, JSONException {
        for(RestaurantData rdata : rDataList){
            this.addRestaurant(rdata);
        }

    }

    //add match param
    public void addMatchID(String[] matchID,String id){
        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");
        //convert matchid to string
        StringBuffer matchid = new StringBuffer();
        for (int i = 0; i < matchID.length;i++){
            matchid.append(matchID[i]);
        }
        //point to current user
        fireRef.child(id).child("matchid").setValue(matchid.toString());

    }

    private static SharedPreferences getPrefs(Context context){
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }





    public void updatePassword(String email, final String password, final Context ctx){
        //change in fireauth
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(email,getUserDetail(ctx,"password"));
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Log.v(TAG,"Password updated in mAuth!");
                                        //instantiate fireref
                                        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");
                                        //get account
                                        fireRef.child(getUserDetail(ctx,"id")).child("password").setValue(password);
                                        Log.v(TAG,"Password updated in firebase db!");
                                    }
                                    else{
                                        Log.v(TAG,"Password failed to update.");
                                        Toast.makeText(ctx,"Failed to update password, try again later.",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });



    }

    public void saveUser(Context context, AccountData account){
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("username",account.getUsername());
        editor.putString("email",account.getEmail());
        editor.putString("id",account.getID());
        editor.putString("fullname",account.getFullName());
        editor.putString("password",account.getPassword());
        editor.putString("gender",account.getGender());
        editor.putString("dob",account.getDob());
        editor.putString("matchid",account.getMatchid());
        editor.putString("allergy",account.getAllergy());
        editor.putString("diet",account.getDiet());
        editor.apply();
        Log.v(TAG,"Shared Preference set for user!");
    }


    public void stayLogin(Context ctx,boolean val){
        SharedPreferences.Editor editor = getPrefs(ctx).edit();
        editor.putBoolean("login",val);
        editor.apply();
    }
    public boolean getLogin(Context ctx){
        return getPrefs(ctx).getBoolean("login",false);
    }
    public boolean checkLoginstatus(Context ctx){
        return getPrefs(ctx).contains("login");
    }
    public void signOut(Context ctx){
        SharedPreferences.Editor editor = getPrefs(ctx).edit();
        editor.clear();
        editor.apply();
    }
    public void saveEmail(Context context, String input){
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("email",input);
        editor.apply();
        Log.v(TAG,"Shared Preference set for email!");
    }


   public String getUserDetail(Context ctx,String inputype){//return the current user's AccountData
        switch(inputype){
            case "username":
                return getPrefs(ctx).getString("username","default_username");
            case "email":
                return getPrefs(ctx).getString("email","default_email");
            case "id":
                String id = getPrefs(ctx).getString("id","def");
                return id;
            case "password":
                String password = getPrefs(ctx).getString("password","def");
                return password;



        }
        return null;

    }

    public void addAllergies(String[] allergystringarray,Context ctx){
        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");

        //unpack array
        String allergyString;
        String dietString;
        if(allergystringarray[0].length() == 0){
            allergyString = "None";
        }
        else{
            allergyString = allergystringarray[0];
        }
        if(allergystringarray[1].equals("Diet")){
            dietString = "None";
        }
        else{
            dietString = allergystringarray[1];
        }
        //Post to firebase
        //Query id
        String stringid = getUserDetail(ctx,"id");
        fireRef.child(stringid).child("allergy").setValue(allergyString);
        fireRef.child(stringid).child("diet").setValue(dietString);
        //Log
        Log.v(TAG,"Allergy field added!");

    }

    public void uploadImage(final Context ctx, Uri FilePath, StorageReference storageReference){
        if(FilePath != null){
            Log.v(TAG,"Creating Progress Dialog.");
            final ProgressDialog progressDialog = new ProgressDialog(ctx);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            //get current user id
            Log.v(TAG,"Getting User ID.");
            final String stringid = getUserDetail(ctx,"id");
            //creates new reference to the id
            Log.v(TAG,"Creating storage reference.");
            final StorageReference ref = storageReference.child("User_Pictures/"+stringid);
            Log.v(TAG,"Checking if file exists");
            //check if file already exists in database
            if(ref.getDownloadUrl() != null){
                Log.v(TAG,"File exists!");
                ref.delete();
            }
            ref.putFile(FilePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(ctx,"Uploaded image!",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ctx,"Failed "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
        else {
            Toast.makeText(ctx, "No File Chosen!", Toast.LENGTH_SHORT).show();
        }
    }
    //PickUsr2Activity - to return the second user
    public AccountData findMatchingID(final AccountData firstUser){
        final boolean[] isUser = new boolean[1];
        final AccountData[] queryData = new AccountData[1];
        AccountData newAcc = new AccountData();
        newAcc = firstUser;
        isUser[0] = false;
        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");
        fireRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                while (!isUser[0]) {
                    Log.v(TAG, FILENAME + firstUser.getUsername());
                    // gets matching id from user details
                    String firstmID = firstUser.getMatchid();
                    Log.v(TAG, FILENAME + firstmID);
                    int count = (int) dataSnapshot.getChildrenCount();
                    Log.e(dataSnapshot.getKey(), count + "");
                    queryData[0] = dataSnapshot.getValue(AccountData.class);
                    Random ran = new Random();
                    int randomMatchID = ran.nextInt(count);
                    String randomID = String.valueOf(randomMatchID).format("%04d", randomMatchID);
                    String queryID = String.valueOf(queryData[0].getID()).format("$04d", queryData[0].getID());
                    if (!queryID.equals(randomID)){isUser[0] = true;}
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("The read failed: " ,error.getMessage());
            }
        });
        if (isUser[0]){newAcc = queryData[0];}
        return newAcc;
    }
}
