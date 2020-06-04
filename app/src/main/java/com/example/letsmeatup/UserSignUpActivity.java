package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class UserSignUpActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
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
                //Instantiate edit texts
                EditText FullName = findViewById(R.id.fullName);
                EditText Username = findViewById(R.id.username);
                EditText Password = findViewById(R.id.password);
                EditText checkPassword = findViewById(R.id.passwordtwice);
                EditText Email = findViewById(R.id.email);
                EditText Date = findViewById(R.id.DOB);
                EditText SP = findViewById(R.id.sexualpreference);
                //put edit texts into an array to loop through to check if any values return null
                EditText[] Info = {FullName,Username,Password,checkPassword,Email,SP};
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
                        dbAccountData.setSp(SP.getText().toString());
                        if(dbAccountData.isPasswordMatch(checkPassword.getText().toString())) {
                            lmudbHandler.addUser(dbAccountData);
                            Toast.makeText(UserSignUpActivity.this, "User created!", Toast.LENGTH_SHORT).show();
                            Log.v(TAG, "User Created :" + Username.getText().toString());
                            Intent intent = new Intent(UserSignUpActivity.this,UserSignUp2Activity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(UserSignUpActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (dbAccountDataUsername != null){
                        Toast.makeText(UserSignUpActivity.this, "Username taken!", Toast.LENGTH_SHORT).show();
                    }
                    else if(dbAccountDataEmail != null){
                        Toast.makeText(UserSignUpActivity.this, "User is already registered in the database!", Toast.LENGTH_SHORT).show();
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
                Intent intent= new Intent(UserSignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GenderSelected = Gender[position];
    }

}