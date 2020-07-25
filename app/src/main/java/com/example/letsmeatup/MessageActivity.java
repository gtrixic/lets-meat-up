package com.example.letsmeatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import com.google.firebase.database.Query;
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
        sendMessage = findViewById(R.id.sendmessagebutton);
        recyclerView = findViewById(R.id.chatitemrecycler);
        MessageActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);




        intent = getIntent();
        final String chatid = intent.getStringExtra("chatid");
        final String userid = intent.getStringExtra("userid");

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = chatBoxText.getText().toString();
                if (message.trim().length() > 0) {
                    sendMessage(lmudbHandler.getUserDetail(MessageActivity.this, "id"), userid, message,chatid);
                    chatBoxText.getText().clear();
                } else {
                    Toast.makeText(MessageActivity.this, "Invalid message!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        fireRef = FirebaseDatabase.getInstance().getReference("Users");
        fireRef.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AccountData user = snapshot.getValue(AccountData.class);
                Username.setText(user.getUsername());
                readMessages(chatid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if(!chatid.equals("default")) {
            fireRef = FirebaseDatabase.getInstance().getReference().child("Chats").child(chatid);
            fireRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    readMessages(chatid);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageActivity.this,ViewChats.class);
                startActivity(intent);
            }
        });


    }

    private void sendMessage(String sender, String receiver, String message,String chatid) {
        fireRef = FirebaseDatabase.getInstance().getReference();
        Date currentTime = Calendar.getInstance().getTime();
        Message userMessage = new Message();
        userMessage.setSender(sender);
        userMessage.setReceiver(receiver);
        userMessage.setCreatedAt(currentTime.getTime());
        userMessage.SetMessage(message);
        String users = sender + "," + receiver;
        Chat chat;

        //check if Chat ID created, else create it
        if (chatid.equals("default")) {
            chat = new Chat();
            chat.setUsers(users);
            //get id
            chat.setId(fireRef.child("Chats").push().getKey());
            //Create chat
            fireRef.child("Chats").child(chat.id).setValue(chat);
            chatid = chat.getId();

        }
        //find chat,//Push Message info, and message metadata
        //get id
        userMessage.setId(fireRef.child("Chats").child(chatid).child("Messages").push().getKey());
        //set message
        fireRef.child("Chats").child(chatid).child("Messages").child(userMessage.getId()).setValue(userMessage);
        //set metadata
        fireRef.child("Chats").child(chatid).child("lastMessage").setValue(userMessage);

    }

    private void readMessages(String chatid){
        Log.v("MessageActivity","Starting read message");
        mChat = new ArrayList<>();
        fireRef = FirebaseDatabase.getInstance().getReference();
        //Query messages from chatid
        if(!chatid.equals("default")) {
            Log.v("MessageActivity","Found chat!");
            DatabaseReference messagesQuery = fireRef.child("Chats").child(chatid).child("Messages");
            recyclerView.setLayoutManager(new LinearLayoutManager(MessageActivity.this));
            recyclerView.setHasFixedSize(true);
            lmudbHandler.readData(messagesQuery, new LMUDBHandler.OnGetDataListener() {
                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {
                    mChat.clear();
                    for (DataSnapshot s : dataSnapshot.getChildren()) {
                        mChat.add(s.getValue(Message.class));
                        Log.v("MessageActivity", s.getValue(Message.class).getMessage());
                        mAdapter = new MessageAdapter(MessageActivity.this, mChat);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
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
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MessageActivity.this,ViewChats.class);
        startActivity(intent);
    }
}