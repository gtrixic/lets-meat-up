package com.example.letsmeatup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class UserSignUp3Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "SignUpActivity.java";
    LMUDBHandler lmudbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up3);
        final EditText allergyEditText = findViewById(R.id.allergies_preference);
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
                if (allergyEditText.getText().toString().trim().length() > 0){
                StringBuffer allergyline = new StringBuffer();
                allergyline.append(diet.getSelectedItem().toString());
                allergyline.append(": "+allergyEditText.getText().toString());
                lmudbHandler.addAllergies(allergyline.toString(),UserSignUp3Activity.this);}

                Intent intent = new Intent(UserSignUp3Activity.this, PersonalityQuestionsActivity.class);
                startActivity(intent);
                finish();
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
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        System.exit(0);
                    }
                }).create().show();
    }
}