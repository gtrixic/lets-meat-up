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

public class SignupOrLoginActivity extends AppCompatActivity {
    public ImageButton createButton;
    public Button loginButton;
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "SignupOrLoginActivity.java";
    private LMUDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_or_login);
        createButton = findViewById(R.id.createButton);
        loginButton = findViewById(R.id.loginButton);
        db = new LMUDBHandler(this,null,null,1);
        Log.v(TAG,Boolean.toString(db.checkLoginstatus(this)));
        if(db.checkLoginstatus(this)){
            Log.v(TAG,Boolean.toString(db.getLogin(this)));

            if(db.getLogin(this)){
                Intent intent = new Intent(SignupOrLoginActivity.this, mainPageActivity.class);
                startActivity(intent);
            }
        }
    }
    public void onStart(){

        super.onStart();

    }
    private void CreateAccount(){ //from this page to UserSignUpActivity page
        Intent create = new Intent(SignupOrLoginActivity.this,UserSignUpActivity.class);
        startActivity(create);
    }
    private void LoginAccount(){ //from this page to LoginActivity page
        Intent login = new Intent(SignupOrLoginActivity.this,LoginActivity.class);
        startActivity(login);
    }
    public void OnClickButton(View v){
        switch(v.getId()){
            case R.id.loginButton: //if user chooses to login to their account
                Log.v(TAG, "User chose to log in.");
                LoginAccount();
                break;
            case R.id.createButton: //if user chooses to create an account
                Log.v(TAG, "User chose to create an account.");
                CreateAccount();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        SignupOrLoginActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}
