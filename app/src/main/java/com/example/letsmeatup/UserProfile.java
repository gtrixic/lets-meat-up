package com.example.letsmeatup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UserProfile extends AppCompatActivity {
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "UserProfile.java";


    ImageView pfp;
    TextView username;
    TextView name;
    TextView gender;
    TextView dob;
    TextView age;
    TextView allergies;
    Button edit;
    ImageButton back;
    ImageButton SignOut;
    LMUDBHandler dbHandler = new LMUDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_profile);
        pfp = findViewById(R.id.profilePic);
        username = findViewById(R.id.usernameView);
        name = findViewById(R.id.nameView);
        gender = findViewById(R.id.genderView);
        age = findViewById(R.id.ageView);
        dob = findViewById(R.id.dobView);
        allergies = findViewById(R.id.allergiesView);
        SignOut = findViewById(R.id.SignOutButton);
        back = findViewById(R.id.backArrow);

        //back to home page (find new matches/accept match requests/profile/chat page)
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go back to mainPageActivity.java
                Intent intent = new Intent(UserProfile.this,mainPageActivity.class);
                startActivity(intent);
            }
        });

        //profile picture
        if(dbHandler.returnUser(this).getPfp().equals("default")){
            pfp.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(this).load(dbHandler.returnUser(this).getPfp()).into(pfp);
        }
        //username
        username.setText(dbHandler.getUserDetail(this, "username"));
        //name
        name.setText(dbHandler.getUserDetail(this, "name"));
        //age
        String stDate = dbHandler.getUserDetail(this,"dob");
        Date date = new Date();
        Date c = Calendar.getInstance().getTime();
        //get age from dob
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = format.parse(stDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long agelong = c.getTime() - date.getTime();
        int ageinyears = (int) (Math.floor(TimeUnit.DAYS.convert(agelong, TimeUnit.MILLISECONDS) / 365));
        String strAge = String.valueOf(ageinyears);
        age.setText(strAge);
        //gender
        gender.setText(dbHandler.getUserDetail(this, "gender"));
        //dob
        dob.setText(dbHandler.getUserDetail(this, "dob"));
        //allergies
        allergies.setText(dbHandler.returnUser(this).getAllergy());

        //edit profile
        edit = findViewById(R.id.buttonEdit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to EditProfile.java
                Intent intent = new Intent(UserProfile.this, EditProfile.class);
                startActivity(intent);
            }
        });

        //logout of account
        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler = new LMUDBHandler(UserProfile.this);
                dbHandler.signOut(UserProfile.this);
                //go back to LoginActivity.java
                Intent signout = new Intent(UserProfile.this,LoginActivity.class);
                startActivity(signout);
            }
        });

    }
}
