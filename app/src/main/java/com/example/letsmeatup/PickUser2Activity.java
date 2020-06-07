package com.example.letsmeatup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PickUser2Activity extends AppCompatActivity {
    //TODO: Compare Personality Questions codes
    //TODO: Retrieve 2nd users information
    TextView name;
    TextView ageT;
    TextView gender;
    TextView sp;
    ImageButton request;
    ImageButton ignore;
    String firstmID;
    AccountData firstUser;
    AccountData secondUser;
    private String TAG = "Let's Meat Up";
    private String FILENAME = "PickUser2Activity.java";
    LMUDBHandler dbHandler = new LMUDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_user2);
        name = findViewById(R.id.nameText);
        ageT = findViewById(R.id.ageText);
        gender = findViewById(R.id.genderText);
        sp = findViewById(R.id.spText);
        request = findViewById(R.id.requestButton);
        ignore = findViewById(R.id.ignoreButton);
        getSecondUser();
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAlert();
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
        firstUser = dbHandler.getUser(this,"username");
        Log.v(TAG,FILENAME+firstUser.getUsername());
        // gets matching id from user details
        firstmID = firstUser.getMatchid();
        Log.v(TAG,FILENAME+firstmID);
        // use method to match the users
        secondUser = dbHandler.findMatchingID(firstmID);
        // setting user details into Textviews
        name.setText(secondUser.getFullName());
        String stdate = secondUser.getDob();
        Date date = new Date();
        Date c = Calendar.getInstance().getTime();
        // getting age from dob
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = format.parse(stdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long age = c.getTime() - date.getTime();
        int ageinyears = (int) (Math.floor(TimeUnit.DAYS.convert(age, TimeUnit.MILLISECONDS) / 365));
        String strAge = String.valueOf(ageinyears);
        ageT.setText(strAge);
        gender.setText(secondUser.getGender());
        sp.setText(secondUser.getSp());
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
}