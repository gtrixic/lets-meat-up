package com.example.letsmeatup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class UserSignUp2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up2);
        ImageButton backButton = findViewById(R.id.backArrow);
        ImageButton nextButton = findViewById(R.id.nextArrow);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserSignUp2Activity.this,UserSignUp3Activity.class);
                startActivity(intent);
            }
        });
        AlertDialog.Builder b =new AlertDialog.Builder(this);
        b.setTitle("WARNING!");
        b.setMessage("This will bring you to the login page, are you sure?");
        b.setCancelable(false);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(UserSignUp2Activity.this,LoginActivity.class);
                startActivity(intent);
            }
        })

    }
}