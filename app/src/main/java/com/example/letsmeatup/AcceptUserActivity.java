package com.example.letsmeatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AcceptUserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    auAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference fireRef;
    AccountData currentUser;
    AccountData otherUser;
    ImageButton confirm;
    ImageButton delete;
    TextView username;
    ImageView profilePic;
    String pending;
    String[] ids;
    ArrayList<AccountData> pendingUsers;

    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "AcceptUserActivity.java";
    LMUDBHandler dbHandler = new LMUDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_request);
        confirm = findViewById(R.id.confirmButton);
        delete = findViewById(R.id.deleteButton);
        username = findViewById(R.id.username);
        profilePic = findViewById(R.id.profilePic);
        database = FirebaseDatabase.getInstance();
        fireRef = database.getReference();
        currentUser = dbHandler.findUser(dbHandler.getUserDetail(this, "username"));
        pending = currentUser.getPending();
        recyclerView =findViewById(R.id.AURecyclerView);
        //populate pending users list
        ids = pending.split(",");
        for (int i = 0; i < ids.length; i++)
        {
            String userID = ids[i];
            Query idQuery = fireRef.orderByChild("id").equalTo(userID);
            idQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    AccountData acc = dataSnapshot.getChildren().iterator().next().getValue(AccountData.class);
                    pendingUsers.add(acc);
                    Log.v(TAG, "Username: " + acc.getUsername());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.v(TAG, "loadPost:onCancelled", databaseError.toException());
                }
            });
        }
        adapter = new auAdapter(AcceptUserActivity.this,pendingUsers);
        LinearLayoutManager manager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }


}