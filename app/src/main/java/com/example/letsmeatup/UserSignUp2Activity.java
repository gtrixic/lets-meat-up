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

public class UserSignUp2Activity extends AppCompatActivity {
    private static String TAG = "Let's-Meat-Up UserSignUp2Activity";
    //TODO:ADD PROFILE PICTURE WITH FIREBASE STORAGE SDK

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up2);
        ImageButton backButton = findViewById(R.id.backArrow);
        ImageButton nextButton = findViewById(R.id.nextArrow);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(UserSignUp2Activity.this);
                b.setTitle("WIP");
                b.setMessage("Adding profile picture is still WIP, sorry!");
                b.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG,"User going to Sign Up Page 3");
                    }
                });
                AlertDialog alert = b.create();
                alert.show();
                Intent intent = new Intent(UserSignUp2Activity.this,UserSignUp3Activity.class);
                startActivity(intent);
            }
        });
        final AlertDialog.Builder b =new AlertDialog.Builder(this);
        b.setTitle("WARNING!");
        b.setMessage("This will bring you to the login page, are you sure?");
        b.setCancelable(false);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG,"User has selected to go to login page.");
                Intent intent = new Intent(UserSignUp2Activity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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