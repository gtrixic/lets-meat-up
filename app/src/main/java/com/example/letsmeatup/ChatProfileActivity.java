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
    private String TAG = "ChatProfileActivity";

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
    ImageButton back;
    suggestAdapter sAdapter;
    ArrayList<RestaurantData> restDataList;
    String newChatID;
    boolean checkSuggestions;
    String IchatID;
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
        back = findViewById(R.id.backArrow3);
        final String IuserID = getIntent().getStringExtra("userid");
        IchatID = getIntent().getStringExtra("chatid");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("yes","IchatID : "+IchatID);
                Intent intent = new Intent(ChatProfileActivity.this,MessageActivity.class);
                intent.putExtra("userid",IuserID);
                if(IchatID.equals("default")) {
                    intent.putExtra("chatid", newChatID);
                    Log.v("yes","CASE 1");
                }

                else{
                    intent.putExtra("chatid",IchatID);
                    Log.v("yes","CASE 3");

                }
                startActivity(intent);
            }
        });
        checkSuggestions = false;
        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");
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
                if(!IchatID.equals("default")) {
                    Log.v("yes","IchatID : "+IchatID);
                    displaySuggestions(IchatID);
                }
                else{
                    //create chat class and post
                    Chat chat = new Chat();
                    chat.setUsers(lmudbHandler.getUserDetail(ChatProfileActivity.this,"id")+","+acc.getID());
                    String key = FirebaseDatabase.getInstance().getReference().child("Chats").push().getKey();
                    chat.setId(key);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Chats");
                    ref.child(key).setValue(chat);
                    IchatID = key;
                    newChatID = key;
                    //send a message
                    Date currentTime = Calendar.getInstance().getTime();
                    Message userMessage = new Message();
                    userMessage.setSender("System");
                    userMessage.setReceiver(acc.getID());
                    userMessage.setCreatedAt(currentTime.getTime());
                    userMessage.SetMessage("Chat created.");
                    userMessage.setId(ref.child(key).child("Messages").push().getKey());
                    //set message
                    ref.child(key).child("Messages").child(userMessage.getId()).setValue(userMessage);
                    //set metadata
                    ref.child(key).child("lastMessage").setValue(userMessage);
                    displaySuggestions(key);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void displaySuggestions(final String chatid){
        Log.v(TAG,"Displaying Suggestions");
        restDataList = new ArrayList<>();
        fireRef = FirebaseDatabase.getInstance().getReference().child("Chats").child(chatid).child("Suggestions");
        suggestRV.setLayoutManager(new LinearLayoutManager(ChatProfileActivity.this));
        suggestRV.setHasFixedSize(true);
        lmudbHandler.readData(fireRef, new LMUDBHandler.OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for(final DataSnapshot s : dataSnapshot.getChildren()){
                    final RestaurantData rd = s.getValue(RestaurantData.class);
                    restDataList.add(rd);
                    Log.v(TAG, String.valueOf(s.getValue(RestaurantData.class).getRestaurantName()));
                    Log.v(TAG, String.valueOf(restDataList.size()));
                    sAdapter = new suggestAdapter(restDataList);
                    sAdapter.setOnItemClickListener(new suggestAdapter.OnItemClickListener() {
                        @Override
                        public void ItemClick(int position) {
                            RestaurantData Ritem = restDataList.get(position);
                            RestaurantProfileDialog rpDialog = new RestaurantProfileDialog(ChatProfileActivity.this,Ritem,chatid);
                            rpDialog.setRestName(Ritem.getRestaurantName());
                            rpDialog.setRestAddr(Ritem.getAddress());
                            rpDialog.setRestType(Ritem.getCategory());
                            rpDialog.setImage(Ritem.getPfpLink());
                            rpDialog.show();
                        }
                    });
                    suggestRV.setAdapter(sAdapter);
                    sAdapter.notifyDataSetChanged();
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