package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SignupOrLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_or_login);
        Intent recData = getIntent();
    }
    public void onStart(){
        super.onStart();
    }
}
