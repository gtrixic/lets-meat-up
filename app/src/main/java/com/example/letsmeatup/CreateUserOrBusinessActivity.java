package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class CreateUserOrBusinessActivity extends AppCompatActivity {
    private ImageButton userAccount;
    private ImageButton businessAccount;
    private static final String TAG = "Let's Meat Up";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_or_business);
        Intent recData = getIntent();
        userAccount = findViewById(R.id.createuser);
        businessAccount = findViewById(R.id.createbusiness);
    }
    @Override
    public void onStart(){
        super.onStart();
    }
    private void createUser(){
        Intent user = new Intent(CreateUserOrBusinessActivity.this,UserSignUpActivity.class);
        startActivity(user);
    }
    private void createBusiness(){
        Intent business = new Intent(CreateUserOrBusinessActivity.this,AddRestaurants.class);
        startActivity(business);
    }
    public void OnClickButton1(View v){
        switch(v.getId()){
            case R.id.createuser:
                Log.v(TAG, "Creating user!");
                createUser();
                break;
            case R.id.createbusiness:
                Log.v(TAG,"Creating business!");
                createBusiness();
                break;
        }
    }
}
