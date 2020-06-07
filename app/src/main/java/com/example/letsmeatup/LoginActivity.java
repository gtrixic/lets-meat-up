package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText loginUser;
    EditText loginPass;
    ImageButton login;
    Button forgetPass;
    public static final String TAG = "Let's-Meat-Up";
    String FILENAME = "LoginActivity.java";
    LMUDBHandler dbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent recData = getIntent();
        login = findViewById(R.id.nextArrow);
        forgetPass = findViewById(R.id.forgetPasswordButton);
        login.setOnClickListener(new View.OnClickListener() { // when user clicks login
            @Override
            public void onClick(View v){
                loginUser =  findViewById(R.id.loginUsernameEmail);
                loginPass =  findViewById(R.id.loginPassword);
                if (validCredential(loginUser.getText().toString(), loginPass.getText().toString())) {
                    dbHandler.saveUsername(LoginActivity.this,loginUser.getText().toString());
                    if(dbHandler.findMatchID(dbHandler.getUser(LoginActivity.this).getUsername()) == null){
                        Intent intent = new Intent(LoginActivity.this,PersonalityQuestionsActivity.class);
                        startActivity(intent);
                    }
                    else {
                        mainPage();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPassPage();
            }
        });
    }
    public void mainPage(){ //method to proceed to main page
        Intent go = new Intent(LoginActivity.this,mainPageActivity.class);
        go.putExtra("ID", loginUser.getText().toString());
        startActivity(go);
    }
    public void forgetPassPage(){ //method to proceed to forget password page
        Intent forget = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
        startActivity(forget);
    }
    public boolean validCredential(String input, String password) { //method to check if login credentials are valid
        AccountData dbData = dbHandler.findUser(input);
        AccountData dbData2 = dbHandler.findEmail(input);
        Log.v(TAG, FILENAME + ":SharedPref Info = " + input + "|" + password);
        if (dbData != null) { //if user uses username to login
            Log.v(TAG, FILENAME + ":SharedPref Info = " + dbData.getUsername() + "|" + dbData.getPassword());
            if (dbData.getUsername().equals(input) && dbData.getPassword().equals(password)) {
                return true; // user exists
            } else {
                return false;  // user either has the wrong password or username or user does not exist
            }
        }
        if (dbData2 != null) { // if user uses email to login
            Log.v(TAG, FILENAME + ":SharedPref Info = " + dbData2.getEmail() + "|" + dbData2.getPassword());
            if (dbData2.getEmail().equals(input) && dbData2.getPassword().equals(password)) {
                return true;  // user exists
            } else {
                return false;  //user either has the wrong password or user or does not exist
            }
        }
        return false;
    }
    protected void onStop(){
        super.onStop();
        finish();
    }

}
