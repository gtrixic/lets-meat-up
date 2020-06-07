package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PickUserActivity extends AppCompatActivity {
    ImageButton startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_user);
        startButton = findViewById(R.id.startButton);
        //when start button is clicked
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayUser();
            }
        });
    }
    public void displayUser(){ //from this page to pickuser2activity
        Intent next = new Intent(PickUserActivity.this,PickUser2Activity.class);
        startActivity(next);
    }
}