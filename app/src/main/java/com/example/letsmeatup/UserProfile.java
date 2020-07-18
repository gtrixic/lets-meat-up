package com.example.letsmeatup;

import android.net.Uri;
import android.os.Bundle;
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

    ImageView pfp;
    TextView viewUsername;
    TextView name;
    TextView age;
    TextView gender;
    TextView dob;
    TextView allergies;
    private FirebaseAuth mAuth;
    LMUDBHandler dbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        pfp = findViewById(R.id.profilePic);
        viewUsername = findViewById(R.id.usernameView);
        name = findViewById(R.id.nameView);
        age = findViewById(R.id.ageView);
        gender = findViewById(R.id.genderView);
        dob = findViewById(R.id.dobView);
        allergies = findViewById(R.id.allergiesView);
        mAuth = FirebaseAuth.getInstance();

        //profile picture
//        storageReference = FirebaseStorage.getInstance().getReference("uploads");
//        FirebaseUser firebaseUser = mAuth.getCurrentUser();
//        databaseReference = FirebaseStorage.getInstance().getReference("Users").child(dbHandler.getUserDetail(this,));


//        viewUsername = dbHandler.getUserDetail(username);
    }
}
