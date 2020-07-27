package com.example.letsmeatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ForgetPasswordActivity extends AppCompatActivity {
    TextView enterInfo;
    ImageButton next;
    ImageButton back;
    private DatabaseReference fireRef;
    public static final String TAG = "Let's-Meat-Up";
    String FILENAME = "ForgetPasswordActivity.java";
    LMUDBHandler dbHandler = new LMUDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        next = findViewById(R.id.nextArrow);
        back = findViewById(R.id.backArrow7);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPasswordActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        //when clicking the enter/next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterInfo = findViewById(R.id.usernameEmail);
                findUser(enterInfo.getText().toString());
            }
        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(ForgetPasswordActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                }).create().show();


    }

    public void findUser(String email){
        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");
        //Query email
        Query emailQuery = fireRef.orderByChild("email").equalTo(email);
        emailQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    AccountData acc = dataSnapshot.getChildren().iterator().next().getValue(AccountData.class);
                    dbHandler.saveUser(ForgetPasswordActivity.this,acc);
                    nextPage();
                }
                else{
                    Toast.makeText(ForgetPasswordActivity.this, "Invalid Username/Email", Toast.LENGTH_SHORT).show();
                    Log.v(TAG,"No Email Found!");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.v(TAG,"Error reading from firebase");
            }
        });


    }
    public void nextPage(){ //after validating user input, pass the input to the next page of forget password
        Intent next = new Intent(ForgetPasswordActivity.this,ForgetPassword1Activity.class);
        next.putExtra("input",enterInfo.getText().toString());
        startActivity(next);
        finish();
    }
    protected void onStop(){
        super.onStop();
        finish();
    }
}
