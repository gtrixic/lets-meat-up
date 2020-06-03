package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
                EditText Fullname = findViewById(R.id.fullName);
                EditText Username = findViewById(R.id.username);
                EditText Password = findViewById(R.id.password);
                EditText checkPassword = findViewById(R.id.passwordtwice);
                EditText Email = findViewById(R.id.email);
                EditText SP = findViewById(R.id.sexualpreference);
                //put edit texts into an array to loop through to check if any values return null
                EditText[] Info = {Fullname,Username,Password,checkPassword,Email,SP};
                for(EditText line : Info){
                    if (line.getText().toString() == null){
                        Toast.makeText(getApplicationContext(),"Not all fields have been answered!",Toast.LENGTH_SHORT);
                        allInputFilled = false;
                        break;
                    }
                }
                if (allInputFilled == true && GenderSelected != null){
                    //check if user is already in database
                    UserData newUser = new UserData(Fullname.getText().toString(),Username.getText().toString(),Password.getText().toString(),Email.getText().toString(),GenderSelected,null,SP.getText().toString());

                }


            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GenderSelected = Gender[position];
    }

}