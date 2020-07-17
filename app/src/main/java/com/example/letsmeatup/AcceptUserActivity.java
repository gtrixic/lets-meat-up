package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AcceptUserActivity extends AppCompatActivity {
    AccountData currentUser;
    AccountData otherUser;
    ImageButton confirm;
    ImageButton delete;
    TextView username;
    ImageView profilePic;
    String pending;

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
        currentUser = dbHandler.findUser(dbHandler.getUserDetail(this, "username"));
        pending = currentUser.getPending();
        

    }


}