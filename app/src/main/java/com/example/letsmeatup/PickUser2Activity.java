package com.example.letsmeatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
        getSecondUser();
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAlert();
                if (secondUser.getPending()==null){
                    secondUser.setPending(firstUser.getID());
                }
                else{
                    String pend = secondUser.getPending()+","+firstUser.getID();
                    secondUser.setPending(pend);
                }
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
        //firstUser = dbHandler.getUserDetail(this,"username");
        Log.v(TAG,FILENAME+firstUser.getUsername());
        secondUser = dbHandler.findMatchingID(firstUser);
        while(secondUser==firstUser){
            secondUser = dbHandler.findMatchingID(firstUser);
        }
        Log.v(TAG,FILENAME+secondUser.getUsername());
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