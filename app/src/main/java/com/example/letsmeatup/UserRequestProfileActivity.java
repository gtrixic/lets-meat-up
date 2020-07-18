package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserRequestProfileActivity extends AppCompatActivity {
    ImageView profilePic;
    TextView username;
    TextView name;
    TextView age;
    TextView gender;
    TextView dob;
    TextView allergies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_other_user_profile);
        profilePic = findViewById(R.id.profilePic);
        username = findViewById(R.id.username);
        name = findViewById(R.id.nameView2);
        age = findViewById(R.id.ageView2);
        gender = findViewById(R.id.genderView2);
        dob = findViewById(R.id.dobView2);
        allergies = findViewById(R.id.allergiesView2);


    }
}