package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPasswordActivity extends AppCompatActivity {
    TextView enterInfo;
    ImageButton next;
    public static final String TAG = "Let's-Meat-Up";
    String FILENAME = "ForgetPasswordActivity.java";
    LMUDBHandler dbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        next = findViewById(R.id.nextArrow);
        //when clicking the enter/next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterInfo = findViewById(R.id.usernameEmail);
                if (validCredential(enterInfo.getText().toString())) { //valid user
                    nextPage();
                }
                else { //invalid user
                    Toast.makeText(ForgetPasswordActivity.this, "Invalid Username/Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void nextPage(){ //after validating user input, pass the input to the next page of forget password
        Intent next = new Intent(ForgetPasswordActivity.this,ForgetPassword1Activity.class);
        next.putExtra("input",enterInfo.getText().toString());
        startActivity(next);
    }
    public boolean validCredential(String input){ //check if the username/email entered exists in the database
        AccountData dbData = dbHandler.findUser(input); //if user entered username
        AccountData dbData2 = dbHandler.findEmail(input); //if user entered email
        Log.v(TAG,FILENAME+":SharedPref Info = " + input);
        if (dbData != null){
        Log.v(TAG,FILENAME+":SharedPref Info = " + dbData.getUsername());
            if(dbData.getUsername().equals(input)){
                return true; //valid
            }
            else{return false;} //invalid
        }
        if (dbData2 != null) {
            Log.v(TAG, FILENAME + ":SharedPref Info = " + dbData2.getEmail());
            if (dbData2.getEmail().equals(input)) {
                return true; //valid
            } else {
                return false; //invalid
            }
        }
        return false; //if user did not enter any information into the textbox
    }
    protected void onStop(){
        super.onStop();
        finish();
    }
}
