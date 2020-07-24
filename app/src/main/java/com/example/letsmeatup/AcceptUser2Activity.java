package com.example.letsmeatup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AcceptUser2Activity extends AppCompatActivity {
    ImageButton backArrow;

    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "AcceptUser2Activity.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_user2);
        backArrow = findViewById(R.id.backArrow4);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton();
            }
        });
    }

    public void backButton(){
        Intent viewRequests = new Intent(AcceptUser2Activity.this, mainPageActivity.class);
        Log.v(TAG, FILENAME+": User pressed the back button.");
        startActivity(viewRequests);
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
                        Intent logout = new Intent(AcceptUser2Activity.this,LoginActivity.class);
                        startActivity(logout);
                    }
                }).create().show();
    }
}