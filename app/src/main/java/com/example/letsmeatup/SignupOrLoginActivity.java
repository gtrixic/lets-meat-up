package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SignupOrLoginActivity extends AppCompatActivity {
    private ImageButton createButton;
    private Button loginButton;
    private static final String TAG = "Let's Meat Up";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_or_login);
        Intent recData = getIntent();
        createButton = findViewById(R.id.createButton);
        loginButton = findViewById(R.id.loginButton);
    }
    public void onStart(){
        super.onStart();
    }
    private void CreateAccount(){
        Intent create = new Intent(SignupOrLoginActivity.this,CreateUserOrBusinessActivity.class);
        startActivity(create);
    }
    private void LoginAccount(){
        Intent login = new Intent(SignupOrLoginActivity.this,LoginActivity.class);
        startActivity(login);
    }
    public void OnClickButton(View v){
        switch(v.getId()){
            case R.id.loginButton:
                Log.v(TAG, "User chose to log in.");
                LoginAccount();
                break;
            case R.id.createButton:
                Log.v(TAG, "User chose to create an account.");
                CreateAccount();
                break;
        }

    }
}
