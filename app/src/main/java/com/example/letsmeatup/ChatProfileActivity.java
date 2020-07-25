package com.example.letsmeatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ChatProfileActivity extends AppCompatActivity {
    TextView userID;
    TextView userName;
    TextView userGender;
    TextView userAge;
    TextView userDOB;
    TextView userDiet;
    TextView userAllergy;
    RecyclerView suggestRV;
    ImageView profilePic;
    ImageButton addButton;
    suggestAdapter sAdapter;
    ArrayList<RestaurantData> restDataList;
    boolean checkSuggestions;
    LMUDBHandler lmudbHandler = new LMUDBHandler(this, null, null, 1);
    DatabaseReference fireRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chat_user);
        userID = findViewById(R.id.userID);
        userName = findViewById(R.id.userName);
        userGender = findViewById(R.id.userGender);
        userAge = findViewById(R.id.userAge);
        userDOB = findViewById(R.id.userDOB);
        userAllergy = findViewById(R.id.userAllergies);
        userDiet = findViewById(R.id.userDiet);
        suggestRV = findViewById(R.id.suggestedRV);
        profilePic = findViewById(R.id.chatPic);
        addButton = findViewById(R.id.suggestButton);
        checkSuggestions = false;
        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");
        String IuserID = getIntent().getStringExtra("userid");
        final String IchatID = getIntent().getStringExtra("chatid");
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getYelpAPI api = new getYelpAPI(ChatProfileActivity.this,IchatID);
                Random ran = new Random();
                String[] terms = new String[]{"Bento","Desserts","Nasi Lemak","Patisserie",
                        "Smokehouse","Gelato","Japanese","Korean","Chinese","Malay","Indian","Western"};
                int randomNo = ran.nextInt(terms.length);
                HashMap<String,String> params = new HashMap<>();
                params.put("term", terms[randomNo]);
                params.put("location","Singapore");
                params.put("categories","restaurants");
                params.put("limit","50");
                params.put("sort_by","rating");
                api.execute(params);
            }
        });
        fireRef.child(IuserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AccountData acc = snapshot.getValue(AccountData.class);
                userID.setText(acc.getUsername());
                userName.setText(acc.getFullName());
                String stDate = acc.getDob();
                Date date = new Date();
                Date c = Calendar.getInstance().getTime();
                // getting age from dob
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    date = format.parse(stDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long age = c.getTime() - date.getTime();
                int ageinyears = (int) (Math.floor(TimeUnit.DAYS.convert(age, TimeUnit.MILLISECONDS) / 365));
                String strAge = String.valueOf(ageinyears);
                userAge.setText(strAge);
                userGender.setText(acc.getGender());
                userDOB.setText(acc.getDob());
                if (acc.getPfp().equals("default")) {
                    Log.v("ChatViewAdapter","Setting default image" );
                    profilePic.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Log.v("ChatViewAdapter","Setting profile picture" );
                    Glide.with(ChatProfileActivity.this).load(acc.getPfp()).into(profilePic);
                }
                displaySuggestions(IchatID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void displaySuggestions(String chatID){
        restDataList = new ArrayList<>();
        fireRef = FirebaseDatabase.getInstance().getReference().child("Chats").child("Suggestions");
        suggestRV.setLayoutManager(new LinearLayoutManager(ChatProfileActivity.this));
        suggestRV.setHasFixedSize(true);

        lmudbHandler.readData(fireRef, new LMUDBHandler.OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                restDataList.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot s : dataSnapshot.getChildren()){
                        restDataList.add(s.getValue(RestaurantData.class));

                    }
                    sAdapter = new suggestAdapter(restDataList);
                    suggestRV.setAdapter(sAdapter);
                    sAdapter.setOnItemClickListener(new suggestAdapter.OnItemClickListener() {
                        @Override
                        public void ItemClick(int position) {
                            Intent intent = new Intent(ChatProfileActivity.this,ChatRestaurantActivity.class);
                            intent.putExtra("restID",restDataList.get(position).getRestaurantName());
                            startActivity(intent);
                        }
                    });
                }
            }
            @Override
            public void onStart() {
            }
            @Override
            public void onFailure() {
            }
        });
    }
}