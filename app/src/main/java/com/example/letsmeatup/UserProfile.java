package com.example.letsmeatup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    TextView age;
    TextView gender;
    TextView dob;
    TextView allergies;
    Button edit;
    ImageView logout;
    private FirebaseAuth mAuth;
    private LMUDBHandler db;
    LMUDBHandler dbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        pfp = findViewById(R.id.profilePic);
        username = findViewById(R.id.usernameView);
        name = findViewById(R.id.nameView);
        age = findViewById(R.id.ageView);
        gender = findViewById(R.id.genderView);
        dob = findViewById(R.id.dobView);
        allergies = findViewById(R.id.allergiesView);
        mAuth = FirebaseAuth.getInstance();

        //profile picture
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference("Users").child(dbHandler.getUserDetail(this, "pfp"));
        //username
        username.setText(dbHandler.getUserDetail(this, "username"));
        //name
        name.setText(dbHandler.getUserDetail(this, "name"));
        //age
        age.setText(dbHandler.getUserDetail(this, "age"));
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

        //logout
        logout = findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to login page
                db = new LMUDBHandler(UserProfile.this,null,null,1);
                db.signOut(UserProfile.this);
                Intent signout = new Intent(UserProfile.this,LoginActivity.class);
                startActivity(signout);
            }
        });

    }
}
