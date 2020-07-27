package com.example.letsmeatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.FirebaseError;
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
    ImageButton confirm;
    ImageButton delete;
    TextView username;
    ImageView profilePic;
    TextView noUsers;
    TextView Desc;
    ImageView LINE;
    String pending;
    String[] ids;
    ImageButton back;
    ArrayList<AccountData> pendingUsers;

    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "AcceptUserActivity.java";
    LMUDBHandler dbHandler = new LMUDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_request);
        confirm = findViewById(R.id.confirmButton);
        delete = findViewById(R.id.deleteButton);
        username = findViewById(R.id.username);
        profilePic = findViewById(R.id.profilePic);
        noUsers = findViewById(R.id.textView4);
        Desc = findViewById(R.id.textView5);
        LINE = findViewById(R.id.line);
        database = FirebaseDatabase.getInstance();
        fireRef = database.getReference();
        back = findViewById(R.id.backArrow);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcceptUserActivity.this,mainPageActivity.class);
                startActivity(intent);
                startActivity(intent);
            }
        });

        currentUser = dbHandler.returnUser(this);
        Log.v(TAG, "acc details: " + currentUser.getFullName() + currentUser.getID());
        pending = currentUser.getpendinguserlist();
        Log.v(TAG, "pending string: " + pending);
        recyclerView =findViewById(R.id.AURecyclerView);
        //check if got less than 2 requests
        //populate pending users list
        pendingUsers = new ArrayList<>();
        if (!pending.equals("")) {
            noUsers.setVisibility(View.GONE);
            Desc.setVisibility(View.GONE);
            if (pending.contains(",")) {
                ids = pending.split(",");
            } else {
                ids = new String[1];
                ids[0] = pending;
            }
            Log.v(TAG, "Pending ID list: " + ids.length);
            for (int i = 0; i < ids.length; i++) {
                String userID = ids[i];
                Query idQuery = fireRef.child("Users").orderByChild("id").equalTo(userID);
                readData(idQuery, new OnGetDataListener() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {

                        AccountData acc = dataSnapshot.getChildren().iterator().next().getValue(AccountData.class);
                        Log.v(TAG, acc.getUsername());
                        pendingUsers.add(acc);
                        Log.v(TAG, "Username: " + acc.getUsername());
                        adapter = new auAdapter(AcceptUserActivity.this, pendingUsers, currentUser);
                        recyclerView.setAdapter(adapter);
                        LinearLayoutManager manager =new LinearLayoutManager(AcceptUserActivity.this);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());

                        adapter.setOnItemClickListener(new auAdapter.OnItemClickListener() {
                            @Override
                            public void ItemClick(int position) {
                                viewUserProfile(position);
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
        else{
            recyclerView.setVisibility(View.GONE);
        }

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

    public void viewUserProfile(final int position)
    {
        Intent viewUser = new Intent(AcceptUserActivity.this, UserRequestProfileActivity.class);
        AccountData move = pendingUsers.get(position);
        Log.v(TAG, "Sending Info: " + move.getID());
        viewUser.putExtra("id", move.getID());
        startActivity(viewUser);
    }

    public interface OnGetDataListener {
        //this is for callbacks
        void onSuccess(DataSnapshot dataSnapshot);
        void onStart();
        void onFailure();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AcceptUserActivity.this, mainPageActivity.class);
        startActivity(intent);
    }
}