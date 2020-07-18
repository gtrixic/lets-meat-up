package com.example.letsmeatup;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

public class EditProfile extends AppCompatActivity {
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "EditProfile.java";

    DatabaseReference databaseReference;
    StorageReference storageReference;
    SharedPreferences sharedPreferences;

    EditText etUsername;
    EditText etName;
    EditText etEmail;
    Spinner etGender;
    EditText etDob;
    EditText etAllergies;
    Button confirm;
    Boolean allInputFilled = false;
    String[] gender = {"M", "F"};
    String genderSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        etUsername = findViewById(R.id.editUsername);
        etName = findViewById(R.id.editName);
        etEmail = findViewById(R.id.editEmail);
        etGender = findViewById(R.id.editGender); //spinner
        etDob = findViewById(R.id.editDob);
        etAllergies = findViewById(R.id.editAllergies);

        //gender spinner
        etGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderSelected = gender[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                genderSelected = null;
            }
        });
        //creating adapter to contain gender string array
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.spinner_layout, gender);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        //setting array adapter data for spinner
        etGender.setAdapter(arrayAdapter);

        /*EditText[] editedInfo = {etUsername, etName, etEmail, etGender, etDob, etAllergies};
        for (EditText line : editedInfo){
            if (line.getText().toString() == null){
                    allInputFilled = false;
                    break;
            }
            else

        }*/

        //confirm button
        confirm = findViewById(R.id.confirmButton);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmChanges();
            }
        });
    }

    private void confirmChanges(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Confirm changes?");
        builder.setCancelable(false);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG, "User declined");
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG, "User accepts");
                returnToUserProfile();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void returnToUserProfile(){
        Intent intent = new Intent(EditProfile.this, UserProfile.class);
        startActivity(intent);
    }
}
