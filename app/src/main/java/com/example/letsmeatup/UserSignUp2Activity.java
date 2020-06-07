package com.example.letsmeatup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.concurrent.TimeUnit;

public class UserSignUp2Activity extends AppCompatActivity {
    private static String TAG = "Let's-Meat-Up UserSignUp2Activity";
    //TODO:ADD PROFILE PICTURE WITH FIREBASE STORAGE SDK

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up2);
        ImageButton backButton = findViewById(R.id.backArrow);
        ImageButton nextButton = findViewById(R.id.nextArrow);
        AlertDialog.Builder b1 = new AlertDialog.Builder(UserSignUp2Activity.this);
        b1.setTitle("WIP");
        b1.setMessage("Adding profile picture is still WIP, sorry!");
        b1.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG,"User going to Sign Up Page 3");
            }
        });
        AlertDialog alert = b1.create();
        alert.show();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // when user clicks the next arrow button
                // moves from this page to next page of sign up
                Intent intent = new Intent(UserSignUp2Activity.this,UserSignUp3Activity.class);
                startActivity(intent);
            }
        });
        // alert for users that this goes to the login page
        final AlertDialog.Builder b =new AlertDialog.Builder(this);
        b.setTitle("WARNING!");
        b.setMessage("This will bring you to the login page, are you sure?");
        b.setCancelable(false);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { // brings user to login page
                Log.v(TAG,"User has selected to go to login page.");
                Intent intent = new Intent(UserSignUp2Activity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { // user remains on this page
                Log.v(TAG,"User has selected to stay on page."  );
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = b.create();
                alertDialog.show();
            }
        });
    }

}