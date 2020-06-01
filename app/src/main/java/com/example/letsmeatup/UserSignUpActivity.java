package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class UserSignUpActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String TAG ="Let's-Meat-Up SignUpActivity";
    String[] Gender = {"M","F"};


    LMUDBHandler lmudbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        Spinner genderSpinner = findViewById(R.id.gender);
        genderSpinner.setOnItemClickListener(this);
        //Creating Adapter to contain gender string array
        //temporarily using simple layout for arrayadapter, will update
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //setting array adapter data for spinner
        genderSpinner.setAdapter(arrayAdapter);

        ImageButton nextButton = findViewById(R.id.nextArrow);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Fullname = findViewById(R.id.fullName);
                EditText Password = findViewById(R.id.password);
                EditText checkPassword = findViewById(R.id.passwordtwice);

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        
    }
}