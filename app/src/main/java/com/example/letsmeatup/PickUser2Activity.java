package com.example.letsmeatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PickUser2Activity extends AppCompatActivity {
    TextView name;
    TextView ageT;
    TextView gender;
    TextView allergy;
    ImageButton request;
    ImageButton ignore;
    AccountData firstUser;
    AccountData secondUser;
    String secondID;
    private DatabaseReference fireRef;


    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "PickUser2Activity.java";
    LMUDBHandler dbHandler = new LMUDBHandler(this, null, null, 1);
    final LoadingDialog loadingDialog = new LoadingDialog(PickUser2Activity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_user2);
        name = findViewById(R.id.nameText);
        ageT = findViewById(R.id.ageText);
        gender = findViewById(R.id.genderText);
        allergy = findViewById(R.id.allergyText);
        request = findViewById(R.id.requestButton);
        ignore = findViewById(R.id.ignoreButton);
        loading();
        getSecondUser();
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAlert();
                if (secondUser.getpendinguserlist().equals("")){
                    secondUser.setpendinguserlist(firstUser.getID());
                    Log.v(TAG, "pending list: " + secondUser.getpendinguserlist());
                }
                else{
                    String pend = secondUser.getpendinguserlist()+","+firstUser.getID();
                    secondUser.setpendinguserlist(pend);
                    Log.v(TAG, "pending list: " + secondUser.getpendinguserlist());
                }
                fireRef.child(secondUser.getID()).child("pendinguserlist").setValue(secondUser.getpendinguserlist());
            }
        });
        ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ignoreAlert();
            }
        });
    }

    public void getSecondUser() {
        // gets user details for the current user
        firstUser = dbHandler.returnUser(this);
        Log.v(TAG,FILENAME+": "+firstUser.getUsername());
        final AccountData[] queryData = {new AccountData()};
        final ArrayList<AccountData> accList = new ArrayList<>();
        final boolean[] isUser = new boolean[1];
        isUser[0] = false;
        Log.v(TAG, "Finding matching ID...");
        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");
        fireRef.orderByChild("matchid").equalTo(firstUser.getMatchid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.v(TAG, "Finding matching ID...");
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    accList.add(s.getValue(AccountData.class));
                }
                while (!isUser[0]) {
                    int count = accList.size();
                    Log.v(TAG, String.valueOf(count));
                    Random ran = new Random();
                    int randomMatchID = ran.nextInt(count);
                    Log.v(TAG, String.valueOf(randomMatchID));
                    queryData[0] = accList.get(randomMatchID);
                    Log.v(TAG, queryData[0].getUsername());
                    Boolean queryPending = null;
                    Boolean queryConfirmed = null;
                    boolean queryAppear = true;

                    if(queryData[0].getpendinguserlist()!= null){
                        queryPending = queryData[0].getpendinguserlist().contains(firstUser.getID());
                    }
                    if(queryData[0].getconfirmeduserlist()!= null){
                        queryConfirmed = queryData[0].getconfirmeduserlist().contains(firstUser.getID());
                    }
                    if(queryPending!=null){
                        queryAppear = !queryPending;
                    }
                    if(queryConfirmed!=null){
                        queryAppear= !queryConfirmed;
                    }
                    if (!queryData[0].getID().equals(firstUser.getID())) {
                        if(queryAppear) {
                            Log.v(TAG, "Ended!");
                            isUser[0] = true;
                            secondUser = queryData[0];
                            Log.v(TAG, FILENAME + secondUser.getUsername());
                            // setting user details into Textviews
                            name.setText(secondUser.getFullName());
                            String stDate = secondUser.getDob();
                            Date date = new Date();
                            Date c = Calendar.getInstance().getTime();
                            // getting age from dob
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                date = format.parse(stDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            long age = c.getTime() - date.getTime();
                            int ageinyears = (int) (Math.floor(TimeUnit.DAYS.convert(age, TimeUnit.MILLISECONDS) / 365));
                            String strAge = String.valueOf(ageinyears);
                            ageT.setText(strAge);
                            gender.setText(secondUser.getGender());
                            allergy.setText(secondUser.getAllergy());
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("The read failed: ", error.getMessage());
            }
        });
    }

    public void requestAlert() { // tells user that request to pair is sent
        AlertDialog.Builder req = new AlertDialog.Builder(this);
        req.setMessage("Request Sent!");
        req.setCancelable(false);
        req.setPositiveButton("Back To Home Screen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainPage();
            }
        });
        AlertDialog alert = req.create();
        alert.show();
    }
    public void noAvailableAlert() { // tells user that request to pair is sent
        AlertDialog.Builder req = new AlertDialog.Builder(this);
        req.setMessage("No user with matching ID!");
        req.setCancelable(false);
        req.setPositiveButton("Back To Home Screen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainPage();
            }
        });
        AlertDialog alert = req.create();
        alert.show();
    }
    public void mainPage() { //from this page to mainpageactivity
        Intent go = new Intent(PickUser2Activity.this, mainPageActivity.class);
        startActivity(go);
    }

    public void ignoreAlert() { //the alert that comes out when user clicks ignore
        AlertDialog.Builder ign = new AlertDialog.Builder(this);
        ign.setMessage("Are you sure?");
        ign.setCancelable(false);
        ign.setPositiveButton("Continue?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                quickloading();
                getSecondUser();
            }
        });
        ign.setNegativeButton("Back To Home Screen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainPage();
            }
        });
        AlertDialog alert = ign.create();
        alert.show();
    }
    public void loading(){
        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
            }
        },3000);
    }
    public void quickloading(){
        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
            }
        },1000);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PickUser2Activity.this, mainPageActivity.class);
        startActivity(intent);
    }
}