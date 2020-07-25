package com.example.letsmeatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ViewChats extends AppCompatActivity {
    DatabaseReference fireRef;
    LMUDBHandler lmudbHandler;
    AccountData currentUser;
    RecyclerView recyclerView;
    chatViewAdapter cAdapter;
    ImageButton backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_1);
        lmudbHandler = new LMUDBHandler(this,null,null,1);
        currentUser = lmudbHandler.returnUser(this);
        recyclerView = findViewById(R.id.viewChatRecyclerView);
        backButton = findViewById(R.id.backbutton);
        final ArrayList<AccountData>confirmedUsers = new ArrayList<>();
        for(String id: currentUser.getconfirmeduserlist().split(",")) {
            fireRef = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
            lmudbHandler.readData(fireRef, new LMUDBHandler.OnGetDataListener() {
                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {
                    confirmedUsers.add(dataSnapshot.getValue(AccountData.class));
                    bindHash(confirmedUsers);
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

    private void bindHash(final ArrayList<AccountData>acceptedUsers) {
        fireRef = FirebaseDatabase.getInstance().getReference().child("Chats");
        lmudbHandler = new LMUDBHandler(ViewChats.this, null, null, 1);

        lmudbHandler.readData(fireRef, new LMUDBHandler.OnGetDataListener() {
            ArrayList<Chat> chats = new ArrayList<>();
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    Chat chat = s.getValue(Chat.class);
                    if(chat.getUsers().contains(currentUser.getID())){
                    chats.add(chat);}
                }
                final HashMap<AccountData,Chat>chathash = new HashMap<>();
                for (Chat c : chats) {
                    List<String> users = Arrays.asList(c.getUsers().split(","));

                    for (AccountData user : acceptedUsers) {
                        if (users.contains(user.getID())) {
                            chathash.put(user, c);
                            break;
                        }
                    }
                }
                //check if any user not in chathash
                for (AccountData user : acceptedUsers) {
                    if (!chathash.containsKey(user)) {
                        chathash.put(user, null);
                    }
                }

                //initialise
                cAdapter = new chatViewAdapter(ViewChats.this,acceptedUsers,chathash);
                recyclerView.setLayoutManager(new LinearLayoutManager(ViewChats.this));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(cAdapter);
                cAdapter.setOnItemClickListener(new chatViewAdapter.OnItemClickListener() {
                    @Override
                    public void ItemClick(int position) {
                        Intent intent = new Intent(ViewChats.this,MessageActivity.class);

                            if(chathash.get(acceptedUsers.get(position)) == null) {
                                intent.putExtra("chatid", "default");
                            }

                        else{
                            intent.putExtra("chatid",chathash.get(acceptedUsers.get(position)).getId());
                        }

                        intent.putExtra("userid",acceptedUsers.get(position).getID());
                        startActivity(intent);
                    }
                });


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
