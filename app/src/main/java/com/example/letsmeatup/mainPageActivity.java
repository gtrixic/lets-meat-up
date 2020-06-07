package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class mainPageActivity extends AppCompatActivity {
    ImageButton pickUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        pickUser = findViewById(R.id.pickUserButton);
        //when pick user button is clicked
        pickUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickUserScreen();
            }
        });
    }
    public void pickUserScreen(){ //from this page to the pick user page
        Intent pick = new Intent(mainPageActivity.this,PickUserActivity.class);
        startActivity(pick);
    }
}