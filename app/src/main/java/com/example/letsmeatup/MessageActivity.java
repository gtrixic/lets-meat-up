package com.example.letsmeatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MessageActivity extends AppCompatActivity {
    ImageView userProfile;
    TextView Username;
    ImageButton backButton;
    EditText chatBoxText;
    RecyclerView chatItemRecycler;
    ImageButton sendMessage;
    AccountData currentUser;
    LMUDBHandler lmudbHandler = new LMUDBHandler(this, null, null, 1);
    Intent intent;
    MessageAdapter mAdapter;
    ArrayList<Message> mChat;
    RecyclerView recyclerView;


    DatabaseReference fireRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_2);
        userProfile = findViewById(R.id.userprofile);
        Username = findViewById(R.id.username);
        backButton = findViewById(R.id.backbutton);
        chatBoxText = findViewById(R.id.chatboxtext);
        chatItemRecycler = findViewById(R.id.chatitemrecycler);
        sendMessage = findViewById(R.id.sendmessagebutton);
        recyclerView = findViewById(R.id.chatitemrecycler);
        mAdapter = new MessageAdapter(this, mChat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);


        intent = getIntent();
        final String userid = intent.getStringExtra("userid");

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = chatBoxText.getText().toString();
                if (message.trim().length() > 0) {
                    sendMessage(lmudbHandler.getUserDetail(MessageActivity.this, "username"), userid, message);
                } else {
                    Toast.makeText(MessageActivity.this, "Invalid message!", Toast.LENGTH_SHORT).show();
                    ;
                }
            }
        });
        fireRef = FirebaseDatabase.getInstance().getReference("Users");
        fireRef.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AccountData user = snapshot.getChildren().iterator().next().getValue(AccountData.class);
                Username.setText(user.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void sendMessage(String sender, String receiver, String message) {
        fireRef = FirebaseDatabase.getInstance().getReference();
        Date currentTime = Calendar.getInstance().getTime();
        Message userMessage = new Message();
        userMessage.setSender(sender);
        userMessage.setReceiver(receiver);
        userMessage.setCreatedAt(currentTime.getTime());
        userMessage.SetMessage(message);
        //push to Chats
        fireRef.child("Chats").push().setValue(hashMap);
    }

    private readMessages(final String id, String userid){
        mChat = new ArrayList<>();
        fireRef = FirebaseDatabase.getInstance().getReference();
    }
}