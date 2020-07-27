package lets.meat.up;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MessageActivity extends AppCompatActivity {
    ImageView userProfile;
    TextView Username;
    ImageButton backButton;
    EditText chatBoxText;
    ImageButton sendMessage;
    String newChatID;
    lets.meat.up.AccountData currentUser;
    lets.meat.up.LMUDBHandler lmudbHandler = new lets.meat.up.LMUDBHandler(this);
    Intent intent;
    lets.meat.up.MessageAdapter mAdapter;
    ArrayList<lets.meat.up.Message> mChat;
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



        //get intent var
        intent = getIntent();
        //chat id may return "default"
        final String chatid = intent.getStringExtra("chatid");
        final String userid = intent.getStringExtra("userid");
        //send message on click
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
        //creating reference to users
        fireRef = FirebaseDatabase.getInstance().getReference("Users");
        fireRef.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //when data changes, update recycler view
                lets.meat.up.AccountData user = snapshot.getValue(lets.meat.up.AccountData.class);
                Username.setText(user.getUsername());
                readMessages(chatid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //goes to user profile activity
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MessageActivity.this, lets.meat.up.ChatProfileActivity.class);
                //check if user id is default, else send a default value
                next.putExtra("userid",userid);
                //if chat id = default and new chat id hasnt been created
                if(chatid.equals("default") && newChatID == null) {
                    next.putExtra("chatid", "default");
                }

                else{
                        //chat id != default
                        if (newChatID == null){
                            next.putExtra("chatid",chatid);

                        }
                        else{
                            next.putExtra("chatid",newChatID);

                        }
                    }

                startActivity(next);
            }
        });
        //only runs if chat id is binded to a proper chat id and not == to default
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
        //goes back to view chats
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageActivity.this, lets.meat.up.ViewChats.class);
                startActivity(intent);
            }
        });


    }
    //send message function
    private void sendMessage(String sender, String receiver, String message,String chatid) {
        //get reference
        fireRef = FirebaseDatabase.getInstance().getReference();
        //create message data
        Date currentTime = Calendar.getInstance().getTime();
        lets.meat.up.Message userMessage = new lets.meat.up.Message();
        userMessage.setSender(sender);
        userMessage.setReceiver(receiver);
        userMessage.setCreatedAt(currentTime.getTime());
        userMessage.SetMessage(message);
        //create chat data
        String users = sender + "," + receiver;
        lets.meat.up.Chat chat;

        //check if Chat ID created, else create it
        if (chatid.equals("default")) {
            chat = new lets.meat.up.Chat();
            chat.setUsers(users);
            //get id
            chat.setId(fireRef.child("Chats").push().getKey());
            //Create chat
            fireRef.child("Chats").child(chat.id).setValue(chat);
            chatid = chat.getId();
            newChatID = chat.getId();

        }
        //find chat,//Push Message info, and message metadata
        //get id
        userMessage.setId(fireRef.child("Chats").child(chatid).child("Messages").push().getKey());
        //set message
        fireRef.child("Chats").child(chatid).child("Messages").child(userMessage.getId()).setValue(userMessage);
        //set metadata
        fireRef.child("Chats").child(chatid).child("lastMessage").setValue(userMessage);
        readMessages(chatid);

    }

    private void readMessages(String chatid){
        Log.v("MessageActivity","Starting read message");
        //get all chats from references
        mChat = new ArrayList<>();
        //setting reference
        fireRef = FirebaseDatabase.getInstance().getReference();
        //Query messages from chatid
        if(!chatid.equals("default")) {
            Log.v("MessageActivity","Found chat!");
            //query messages
            DatabaseReference messagesQuery = fireRef.child("Chats").child(chatid).child("Messages");
            //setting recycler view vars
            LinearLayoutManager manager = new LinearLayoutManager(MessageActivity.this);
            //to scroll to most recent message
            manager.setStackFromEnd(true);
            recyclerView.setLayoutManager(manager);
            recyclerView.setHasFixedSize(true);
            //wait for data to return then run recycler view adapter setter
            lmudbHandler.readData(messagesQuery, new lets.meat.up.LMUDBHandler.OnGetDataListener() {
                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {
                    //clears m chat on success
                    mChat.clear();
                    for (DataSnapshot s : dataSnapshot.getChildren()) {
                        //adds values to mchat and sets to recycler view
                        mChat.add(s.getValue(lets.meat.up.Message.class));
                        mAdapter = new lets.meat.up.MessageAdapter(MessageActivity.this, mChat);
                        recyclerView.setAdapter(mAdapter);
                        recyclerView.smoothScrollToPosition(mChat.size());
                        //update mAdapter and recycler view
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
        Intent intent = new Intent(MessageActivity.this, lets.meat.up.ViewChats.class);
        startActivity(intent);
    }
}