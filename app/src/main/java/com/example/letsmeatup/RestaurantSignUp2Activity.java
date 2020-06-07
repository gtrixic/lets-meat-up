package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RestaurantSignUp2Activity extends AppCompatActivity {
    Button confirmProfile;
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "RestaurantSignUp2Activity.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sign_up2);
        confirmProfile = findViewById(R.id.createProfile);
        confirmProfile.setOnClickListener(new View.OnClickListener() { //when clicking confirm profile, to redirect to login page
            @Override
            public void onClick(View v) {
                loginPage();
            }
        });
    }
    public void loginPage(){
        Intent intent = new Intent(RestaurantSignUp2Activity.this, LoginActivity.class);
        startActivity(intent);
    }
}