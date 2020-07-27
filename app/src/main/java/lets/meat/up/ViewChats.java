package lets.meat.up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ViewChats extends AppCompatActivity {
    DatabaseReference fireRef;
    lets.meat.up.LMUDBHandler lmudbHandler;
    lets.meat.up.AccountData currentUser;
    RecyclerView recyclerView;
    lets.meat.up.chatViewAdapter cAdapter;
    ImageButton backButton;
    TextView text1;
    TextView text2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //params
        setContentView(R.layout.activity_chat_1);
        lmudbHandler = new lets.meat.up.LMUDBHandler(this);
        currentUser = lmudbHandler.returnUser(this);
        recyclerView = findViewById(R.id.viewChatRecyclerView);
        backButton = findViewById(R.id.backArrow5);
        text1 = findViewById(R.id.textView4);
        text2 = findViewById(R.id.textView5);
        final ArrayList<lets.meat.up.AccountData> confirmedUsers = new ArrayList<>();
        //as confirmeduserlist either returns "" or a string, check if confirmeduserlsit is empty
        if (!currentUser.getconfirmeduserlist().equals("")){
            //start loading dialog
            final lets.meat.up.LoadingDialog loadingDialog = new lets.meat.up.LoadingDialog(this);
            loadingDialog.startLoadingDialog();
            //remove "No users found text"
            text1.setVisibility(View.GONE);
            text2.setVisibility(View.GONE);
            //get all ids in confirmed userlist by splitting
            for (String id : currentUser.getconfirmeduserlist().split(",")) {
                //query each data
                fireRef = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
                lmudbHandler.readData(fireRef, new lets.meat.up.LMUDBHandler.OnGetDataListener() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        //wait for data to return and cast into accountdata
                        confirmedUsers.add(dataSnapshot.getValue(lets.meat.up.AccountData.class));
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
            Intent intent = new Intent(ViewChats.this, lets.meat.up.mainPageActivity.class);
            startActivity(intent);
        }
    });
    }

    //function to bind chats to accountdata, to set references to each chat
    private void bindHash(final ArrayList<lets.meat.up.AccountData>acceptedUsers) {
        //get chat reference
        fireRef = FirebaseDatabase.getInstance().getReference().child("Chats");
        lmudbHandler = new lets.meat.up.LMUDBHandler(ViewChats.this);
        //wait for data to return
        lmudbHandler.readData(fireRef, new lets.meat.up.LMUDBHandler.OnGetDataListener() {
            //get new chats arraylist
            ArrayList<lets.meat.up.Chat> chats = new ArrayList<>();
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    //cast into chat class
                    lets.meat.up.Chat chat = s.getValue(lets.meat.up.Chat.class);
                    //checks if the current user is in chat's users
                    if(chat.getUsers().contains(currentUser.getID())){
                        //adds chat into chats arraylist
                    chats.add(chat);}
                }
                final HashMap<lets.meat.up.AccountData, lets.meat.up.Chat>chathash = new HashMap<>();
                for (lets.meat.up.Chat c : chats) {
                    //binding users to chats
                    List<String> users = Arrays.asList(c.getUsers().split(","));

                    for (lets.meat.up.AccountData user : acceptedUsers) {
                        //if the other user's confirmed user is in the chat, bind the user to the chat
                        if (users.contains(user.getID())) {
                            chathash.put(user, c);
                            break;
                        }
                    }
                }
                //check if any user not in chathash
                for (lets.meat.up.AccountData user : acceptedUsers) {
                    //if user is not in list, set chat to null, which will be casted to "default"
                    if (!chathash.containsKey(user)) {
                        chathash.put(user, null);
                    }
                }

                //initialise
                cAdapter = new lets.meat.up.chatViewAdapter(ViewChats.this,acceptedUsers,chathash);
                recyclerView.setLayoutManager(new LinearLayoutManager(ViewChats.this));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(cAdapter);
                //set onclicks for each adapter
                cAdapter.setOnItemClickListener(new lets.meat.up.chatViewAdapter.OnItemClickListener() {
                    @Override
                    public void ItemClick(int position) {
                        Intent intent = new Intent(ViewChats.this, lets.meat.up.MessageActivity.class);

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
        Intent intent = new Intent(ViewChats.this, lets.meat.up.mainPageActivity.class    );
        startActivity(intent);
    }
}
