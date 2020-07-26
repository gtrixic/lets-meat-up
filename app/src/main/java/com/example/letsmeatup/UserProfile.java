package com.example.letsmeatup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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

public class UserProfile extends AppCompatActivity {
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "UserProfile.java";

    DatabaseReference databaseReference;
    StorageReference storageReference;
    SharedPreferences sharedPreferences;
    ImageView pfp;
    TextView username;
    TextView name;
    TextView gender;
    TextView dob;
    TextView allergies;
    Button edit;
    Button SignOut;
    private FirebaseAuth mAuth;
    LMUDBHandler dbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_profile);
        pfp = findViewById(R.id.profilePic);
        username = findViewById(R.id.usernameView);
        name = findViewById(R.id.nameView);
        gender = findViewById(R.id.genderView);
        dob = findViewById(R.id.dobView);
        allergies = findViewById(R.id.allergiesView);
        SignOut = findViewById(R.id.SignOutButton);
        mAuth = FirebaseAuth.getInstance();

        //profile picture
        if(dbHandler.returnUser(this).getPfp().equals("default")){
            pfp.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(this).load(dbHandler.getUserDetail(this,"pfp")).dontAnimate().into(pfp);
        }
        //username
        username.setText(dbHandler.getUserDetail(this, "username"));
        //name
        name.setText(dbHandler.getUserDetail(this, "fullname"));
        //gender
        gender.setText(dbHandler.getUserDetail(this, "gender"));
        //dob
        dob.setText(dbHandler.getUserDetail(this, "dob"));
        //allergies
        allergies.setText(dbHandler.getUserDetail(this, "allergies"));

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

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler = new LMUDBHandler(UserProfile.this,null,null,1);
                dbHandler.signOut(UserProfile.this);
                Intent signout = new Intent(UserProfile.this,LoginActivity.class);
                startActivity(signout);
            }
        });

    }
}
