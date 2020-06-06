package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ForgetPassword2Activity extends AppCompatActivity {
    ImageButton goNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password2);
        goNext = findViewById(R.id.goNextButton);
        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage();
            }
        });
    }
    public void nextPage(){
        Intent next = new Intent(ForgetPassword2Activity.this,LoginActivity.class);
        startActivity(next);
    }
}
