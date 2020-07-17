package com.example.letsmeatup;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfile extends AppCompatActivity {
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "UserProfile.java";

    TextView viewUsername;
    TextView name;
    TextView age;
    TextView gender;
    TextView dob;
    TextView allergies;
    LMUDBHandler dbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        viewUsername = findViewById(R.id.usernameView);
        name = findViewById(R.id.nameView);
        age = findViewById(R.id.ageView);
        gender = findViewById(R.id.genderView);
        dob = findViewById(R.id.dobView);
        allergies = findViewById(R.id.allergiesView);

        viewUsername = dbHandler.getUserDetail(username);
    }
}
