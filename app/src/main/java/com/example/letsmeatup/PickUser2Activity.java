package com.example.letsmeatup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class PickUser2Activity extends AppCompatActivity {
    //TODO: Compare Personality Questions codes
    //TODO: Retrieve 2nd users information
    //TODO: pass 2nd user info to pickuser2
    TextView name;
    TextView age;
    TextView gender;
    TextView sp;
    ImageButton request;
    ImageButton ignore;
    String id;
    AccountData secondUser;
    LMUDBHandler dbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_user2);
    }
    public void getSecondUser(String pq){
        secondUser = dbHandler.findMatchID()
    }
    public void requestAlert(){
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
    public void mainPage(){
        Intent go = new Intent(PickUser2Activity.this,mainPageActivity.class);
        startActivity(go);
    }
    public void ignoreAlert(){
        AlertDialog.Builder ign = new AlertDialog.Builder(this);
        ign.setMessage("Request Sent!");
        ign.setCancelable(false);
        ign.setPositiveButton("Continue?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

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