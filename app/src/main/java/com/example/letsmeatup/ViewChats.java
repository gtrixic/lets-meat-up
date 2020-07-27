package com.example.letsmeatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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
    TextView text1;
    TextView text2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //params
        setContentView(R.layout.activity_chat_1);
        lmudbHandler = new LMUDBHandler(this);
        currentUser = lmudbHandler.returnUser(this);
        recyclerView = findViewById(R.id.viewChatRecyclerView);
        backButton = findViewById(R.id.backArrow5);
        text1 = findViewById(R.id.textView4);
        text2 = findViewById(R.id.textView5);
        final ArrayList<AccountData> confirmedUsers = new ArrayList<>();
        //as confirmeduserlist either returns "" or a string, check if confirmeduserlsit is empty
        if (!currentUser.getconfirmeduserlist().equals("")){
            //start loading dialog
            final LoadingDialog loadingDialog = new LoadingDialog(this);
            loadingDialog.startLoadingDialog();
            //remove "No users found text"
            text1.setVisibility(View.GONE);
            text2.setVisibility(View.GONE);
            //get all ids in confirmed userlist by splitting
            for (String id : currentUser.getconfirmeduserlist().split(",")) {
                //query each data
                fireRef = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
                lmudbHandler.readData(fireRef, new LMUDBHandler.OnGetDataListener() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        //wait for data to return and cast into accountdata
                        confirmedUsers.add(dataSnapshot.getValue(AccountData.class));
                        //run bindhash to bind to view holders
                        bindHash(confirmedUsers);
                        //close loading dialog
                        loadingDialog.dismissDialog();
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
        else{
            //list is empty, remove recyclerview
            recyclerView.setVisibility(View.GONE);
        }
        //return to prev view
    backButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ViewChats.this,mainPageActivity.class);
            startActivity(intent);
        }
    });
    }

    //function to bind chats to accountdata, to set references to each chat
    private void bindHash(final ArrayList<AccountData>acceptedUsers) {
        //get chat reference
        fireRef = FirebaseDatabase.getInstance().getReference().child("Chats");
        lmudbHandler = new LMUDBHandler(ViewChats.this);
        //wait for data to return
        lmudbHandler.readData(fireRef, new LMUDBHandler.OnGetDataListener() {
            //get new chats arraylist
            ArrayList<Chat> chats = new ArrayList<>();
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    //cast into chat class
                    Chat chat = s.getValue(Chat.class);
                    //checks if the current user is in chat's users
                    if(chat.getUsers().contains(currentUser.getID())){
                        //adds chat into chats arraylist
                    chats.add(chat);}
                }
                final HashMap<AccountData,Chat>chathash = new HashMap<>();
                for (Chat c : chats) {
                    //binding users to chats
                    List<String> users = Arrays.asList(c.getUsers().split(","));

                    for (AccountData user : acceptedUsers) {
                        //if the other user's confirmed user is in the chat, bind the user to the chat
                        if (users.contains(user.getID())) {
                            chathash.put(user, c);
                            break;
                        }
                    }
                }
                //check if any user not in chathash
                for (AccountData user : acceptedUsers) {
                    //if user is not in list, set chat to null, which will be casted to "default"
                    if (!chathash.containsKey(user)) {
                        chathash.put(user, null);
                    }
                }

                //initialise
                cAdapter = new chatViewAdapter(ViewChats.this,acceptedUsers,chathash);
                recyclerView.setLayoutManager(new LinearLayoutManager(ViewChats.this));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(cAdapter);
                //set onclicks for each adapter
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ViewChats.this,mainPageActivity.class    );
        startActivity(intent);
    }
}
