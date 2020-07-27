package lets.meat.up;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    lets.meat.up.auAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference fireRef;
    lets.meat.up.AccountData currentUser;
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
    ArrayList<lets.meat.up.AccountData> pendingUsers;

    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "AcceptUserActivity.java";
    lets.meat.up.LMUDBHandler dbHandler = new lets.meat.up.LMUDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting vars
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

        //back button code
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcceptUserActivity.this, lets.meat.up.mainPageActivity.class);
                startActivity(intent);
            }
        });

        currentUser = dbHandler.returnUser(this);
        pending = currentUser.getpendinguserlist();
        recyclerView =findViewById(R.id.AURecyclerView);
        //check if got less than 2 requests
        //populate pending users list
        pendingUsers = new ArrayList<>();
        //as pending list is a string, if the list is empty it will return as a ""
        if (!pending.equals("")) {
            //remove "No users found" text
            noUsers.setVisibility(View.GONE);
            Desc.setVisibility(View.GONE);
            //If the , is found, there is more than 1 user in list
            if (pending.contains(",")) {
                //split the list
                ids = pending.split(",");
            } else {
                //only 1 user in pending list
                ids = new String[1];
                ids[0] = pending;
            }
            for (int i = 0; i < ids.length; i++) {
                //get each user id and query the information
                String userID = ids[i];
                Query idQuery = fireRef.child("Users").orderByChild("id").equalTo(userID);
                //wait for read data to finish
                readData(idQuery, new OnGetDataListener() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        //cast info to acc
                        lets.meat.up.AccountData acc = dataSnapshot.getChildren().iterator().next().getValue(lets.meat.up.AccountData.class);
                        pendingUsers.add(acc);
                        //create adapter for each entry
                        adapter = new lets.meat.up.auAdapter(AcceptUserActivity.this, pendingUsers, currentUser);
                        recyclerView.setAdapter(adapter);
                        LinearLayoutManager manager =new LinearLayoutManager(AcceptUserActivity.this);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());

                        adapter.setOnItemClickListener(new lets.meat.up.auAdapter.OnItemClickListener() {
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
        //remove recyclerview if no user is found
        else{
            recyclerView.setVisibility(View.GONE);
        }

    }
    //read data waits for the information to be retrieved from the query in order to run the code afterwards
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

    //creates an intent to go from recyclerview to user profile
    public void viewUserProfile(final int position)
    {
        Intent viewUser = new Intent(AcceptUserActivity.this, lets.meat.up.UserRequestProfileActivity.class);
        lets.meat.up.AccountData move = pendingUsers.get(position);
        Log.v(TAG, "Sending Info: " + move.getID());
        viewUser.putExtra("id", move.getID());
        startActivity(viewUser);
    }

    //interface for read data
    public interface OnGetDataListener {
        //this is for callbacks
        void onSuccess(DataSnapshot dataSnapshot);
        void onStart();
        void onFailure();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AcceptUserActivity.this, lets.meat.up.mainPageActivity.class);
        startActivity(intent);
    }
}