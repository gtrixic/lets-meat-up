package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ForgetPassword1Activity extends AppCompatActivity {
    public static final String TAG = "Let's-Meat-Up";
    String FILENAME = "ForgetPassword1Activity.java";
    EditText enterOnce;
    EditText enterTwice;
    ImageButton goNext;
    LMUDBHandler dbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password1);
        Intent recData = getIntent(); //get the user input from 1st forget password page
        final String input = recData.getStringExtra("input");
        goNext = findViewById(R.id.goNext);
        //after entering password twice and clicking next
        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterOnce = findViewById(R.id.enterPassword);
                enterTwice = findViewById(R.id.reenterPassword);
                if(checkSimilarity(enterOnce.getText().toString(),enterTwice.getText().toString())){ //if the 2 passwords are identical
                    dbHandler.updatePassword(input, enterOnce.getText().toString()); //password is updated in database
                    nextPage();
                }
                else{
                    Toast.makeText(ForgetPassword1Activity.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public boolean checkSimilarity(String once, String twice){ //to check if the 2 password inputs are the same
        if(once.equals(twice)){
            return true;
        }
        else{
            return false;
        }
    }
    public void nextPage(){ //from this page to the successful password change page
        Intent next = new Intent(ForgetPassword1Activity.this,ForgetPassword2Activity.class);
        startActivity(next);
    }
}