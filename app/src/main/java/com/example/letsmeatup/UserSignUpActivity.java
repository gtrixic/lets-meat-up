package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class UserSignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String TAG ="Let's-Meat-Up SignUpActivity";
    String[] Gender = {"M","F"};
    String GenderSelected;
    Boolean allInputFilled = true;


    LMUDBHandler lmudbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        Spinner genderSpinner = findViewById(R.id.gender);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GenderSelected = Gender[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                GenderSelected = null;
            }
        });
        //Creating Adapter to contain gender string array
        //temporarily using simple layout for arrayadapter, will update
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Gender);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //setting array adapter data for spinner
        genderSpinner.setAdapter(arrayAdapter);

        //spinner for gender preference
        final Spinner genderPref = findViewById(R.id.genderpreference);
        ArrayAdapter gpAdapter = ArrayAdapter.createFromResource(this, R.array.gender_pref_array, R.layout.spinner_layout);
        gpAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        genderPref.setAdapter(gpAdapter);
        genderPref.setOnItemSelectedListener(this);


        ImageButton nextButton = findViewById(R.id.nextArrow);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Instantiate edit texts
                EditText FullName = findViewById(R.id.fullName);
                EditText Username = findViewById(R.id.username);
                EditText Password = findViewById(R.id.Rpassword);
                EditText checkPassword = findViewById(R.id.passwordtwice);
                EditText Email = findViewById(R.id.email);
                EditText Date = findViewById(R.id.DOB);
                //put edit texts into an array to loop through to check if any values return null
                EditText[] Info = {FullName,Username,Password,checkPassword,Email};
                for(EditText line : Info){
                    if (line.getText().toString() == null){
                        allInputFilled = false;
                        break;
                    }
                }
                if (allInputFilled == true && GenderSelected != null){
                    //check if user is already in database
                    AccountData dbAccountData = new AccountData();
                    AccountData dbAccountDataUsername = lmudbHandler.findUser(Username.getText().toString());
                    AccountData dbAccountDataEmail = lmudbHandler.findEmail(Email.getText().toString());

                    if (dbAccountDataUsername == null && dbAccountDataEmail == null){
                        dbAccountData.setFullName(FullName.getText().toString());
                        dbAccountData.setUsername(Username.getText().toString());
                        dbAccountData.setPassword(Password.getText().toString());
                        dbAccountData.setEmail(Email.getText().toString());
                        dbAccountData.setGender(GenderSelected);
                        dbAccountData.setDob(Date.getText().toString());
                        dbAccountData.setSp(genderPref.getSelectedItem().toString());
                        if(dbAccountData.isPasswordMatch(checkPassword.getText().toString())) {
                            lmudbHandler.addUser(dbAccountData);
                            Toast.makeText(UserSignUpActivity.this, "User created!", Toast.LENGTH_SHORT).show();
                            Log.v(TAG, "User Created :" + Username.getText().toString());
                            lmudbHandler.saveUsername(UserSignUpActivity.this,Username.getText().toString());
                            Intent intent = new Intent(UserSignUpActivity.this,UserSignUp2Activity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(UserSignUpActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (dbAccountDataUsername != null){
                        Toast.makeText(UserSignUpActivity.this, "Username taken!", Toast.LENGTH_SHORT).show();
                        Log.v(TAG,"Username: "+Username.getText().toString());
                    }
                    else if(dbAccountDataEmail != null){
                        Toast.makeText(UserSignUpActivity.this, "User is already registered in the database!", Toast.LENGTH_SHORT).show();
                        Log.v(TAG,"Email: "+ Email.getText().toString());
                    }



                }
                else if(allInputFilled == false || GenderSelected == null){
                    Toast.makeText(UserSignUpActivity.this,"Not all inputs have been filled in.",Toast.LENGTH_SHORT).show();
                    //reset after trying to post to database
                    allInputFilled = true;
                }



            }
        });

        ImageButton BackButton = findViewById(R.id.backArrow);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserSignUpActivity.this,CreateUserOrBusinessActivity.class);
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