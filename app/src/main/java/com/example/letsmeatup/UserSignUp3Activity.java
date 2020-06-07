package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class UserSignUp3Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "SignUpActivity.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up3);

        final Spinner diet = findViewById(R.id.diet);
        ArrayAdapter dAdapter = ArrayAdapter.createFromResource(this, R.array.diet_array, R.layout.spinner_layout);
        dAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        diet.setAdapter(dAdapter);
        diet.setOnItemSelectedListener(this);

        Button createProfile;

        createProfile = findViewById(R.id.createProfile);
        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // brings the user to the personality questions page
                Intent intent = new Intent(UserSignUp3Activity.this, PersonalityQuestionsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.v(TAG, parent.getSelectedItem().toString() + "selected.");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}