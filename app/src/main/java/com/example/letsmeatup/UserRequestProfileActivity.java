package com.example.letsmeatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UserRequestProfileActivity extends AppCompatActivity {
    ImageView profilePic;
    TextView username;
    TextView name;
    TextView age;
    TextView gender;
    TextView dob;
    TextView allergies;
    ImageButton backButton;
    AccountData userAcc;
    FirebaseDatabase database;
    DatabaseReference fireRef;
    Context ctx;

    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "UserRequestProfileActivity.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_other_user_profile);
        profilePic = findViewById(R.id.profilePic);
        username = findViewById(R.id.username);
        name = findViewById(R.id.nameView2);
        age = findViewById(R.id.ageView2);
        gender = findViewById(R.id.genderView2);
        dob = findViewById(R.id.dobView2);
        allergies = findViewById(R.id.allergiesView2);

        database = FirebaseDatabase.getInstance();
        fireRef = database.getReference();

        backButton = findViewById(R.id.backArrow);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton();
            }
        });

        Intent getInfo = getIntent();
        String userID = getInfo.getStringExtra("id");
        Log.v(TAG, "Info received: " + userID);
        Query idQuery = fireRef.child("Users").orderByChild("id").equalTo(userID);
        readData(idQuery, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {

                userAcc = dataSnapshot.getChildren().iterator().next().getValue(AccountData.class);
                Log.v(TAG, "Username: "+ userAcc.getUsername());
                username.setText(userAcc.getUsername());
                if(userAcc.getPfp().equals("default")){
                    profilePic.setImageResource(R.mipmap.ic_launcher);
                }
                else{
                    Glide.with(ctx).load(userAcc.getPfp()).into(profilePic);
                }
                name.setText(userAcc.getFullName());
                String stDate = userAcc.getDob();
                Date date = new Date();
                Date c = Calendar.getInstance().getTime();
                // getting age from dob
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    date = format.parse(stDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long getAge = c.getTime() - date.getTime();
                int ageInYears = (int) (Math.floor(TimeUnit.DAYS.convert(getAge, TimeUnit.MILLISECONDS) / 365));
                String strAge = String.valueOf(ageInYears);
                age.setText(strAge);
                gender.setText(userAcc.getGender());
                dob.setText(userAcc.getDob());
                allergies.setText(userAcc.getAllergy());

            }
            @Override
            public void onStart() {

            }

            @Override
            public void onFailure() {

            }
        });


    }

    public void readData(Query ref, final OnGetDataListener listener) {
        listener.onStart();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onFailure();
            }
        });

    }

    public interface OnGetDataListener {
        //this is for callbacks
        void onSuccess(DataSnapshot dataSnapshot);
        void onStart();
        void onFailure();
    }

    public void backButton(){
        Intent back = new Intent(UserRequestProfileActivity.this, AcceptUserActivity.class);
        Log.v(TAG, FILENAME+": User pressed the back button.");
        startActivity(back);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserRequestProfileActivity.this,AcceptUserActivity.class);
        startActivity(intent);
    }
}