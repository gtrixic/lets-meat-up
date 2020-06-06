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
    String FILENAME = "LoginActivity.java";
    LMUDBHandler dbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        next = findViewById(R.id.nextArrow);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterInfo = findViewById(R.id.usernameEmail);
                if (validCredential(enterInfo.getText().toString())) {
                    nextPage();
                }
                else {
                    Toast.makeText(ForgetPasswordActivity.this, "Invalid Username/Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void nextPage(){
        Intent next = new Intent(ForgetPasswordActivity.this,ForgetPassword1Activity.class);
        next.putExtra("input",enterInfo.getText().toString());
        startActivity(next);
    }
    public boolean validCredential(String input){
        AccountData dbData = dbHandler.findUser(input);
        AccountData dbData2 = dbHandler.findEmail(input);
        Log.v(TAG,FILENAME+":SharedPref Info = " + input);
        if (dbData != null){
        Log.v(TAG,FILENAME+":SharedPref Info = " + dbData.getUsername());
            if(dbData.getUsername().equals(input)){
                return true;
            }
            else{return false;}
        }
        if (dbData2 != null) {
            Log.v(TAG, FILENAME + ":SharedPref Info = " + dbData2.getEmail());
            if (dbData2.getEmail().equals(input)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    protected void onStop(){
        super.onStop();
        finish();
    }
}
