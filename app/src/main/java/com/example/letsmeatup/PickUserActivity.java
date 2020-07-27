package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PickUserActivity extends AppCompatActivity {
    Button startButton;
    ImageButton backButton;
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "PickUserActivity.java";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_user);
        startButton = findViewById(R.id.startButton);
        backButton = findViewById(R.id.pickUserBackArrow);
        //when start button is clicked
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayUser();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backArrow();
            }
        });
    }
    public void displayUser(){ //from this page to pickuser2activity
        Intent next = new Intent(PickUserActivity.this,PickUser2Activity.class);
        startActivity(next);
    }
    public void backArrow(){ //from this page to pickuser2activity
        Intent next = new Intent(PickUserActivity.this,mainPageActivity.class);
        startActivity(next);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PickUserActivity.this, mainPageActivity.class);
        startActivity(intent);
    }
}