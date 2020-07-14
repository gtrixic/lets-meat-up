package com.example.letsmeatup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class mainPageActivity extends AppCompatActivity {
    ImageButton pickUser;
    ImageButton userProfile;
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "mainPageActivity.java";
    private LMUDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        pickUser = findViewById(R.id.pickUserButton);
        userProfile = findViewById(R.id.profileButton);
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new LMUDBHandler(mainPageActivity.this,null,null,1);
                db.signOut(mainPageActivity.this);
                Intent signout = new Intent(mainPageActivity.this,LoginActivity.class);
                startActivity(signout);
            }
        });
        //when pick user button is clicked
        pickUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickUserScreen();
            }
        });
    }
    public void pickUserScreen(){ //from this page to PickUser page
        Intent pick = new Intent(mainPageActivity.this,PickUserActivity.class);
        Log.v(TAG,FILENAME+": User wishes to pick a user.");
        startActivity(pick);
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        System.exit(0);
                    }
                }).create().show();
    }
}