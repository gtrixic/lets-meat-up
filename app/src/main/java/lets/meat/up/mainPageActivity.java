package lets.meat.up;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class mainPageActivity extends AppCompatActivity {
    Button pickUser;
    ImageButton userProfile;
    Button viewUsers;
    ImageButton chatButton;
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "mainPageActivity.java";
    private lets.meat.up.LMUDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setting vars
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        pickUser = findViewById(R.id.pickUserButton);
        userProfile = findViewById(R.id.profileButton);
        viewUsers = findViewById(R.id.acceptUserButton);
        chatButton = findViewById(R.id.chatButton);
        //onclicks for gg to intents
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainPageActivity.this, lets.meat.up.ViewChats.class);
                startActivity(intent);
            }
        });
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainPageActivity.this, lets.meat.up.UserProfile.class);
                startActivity(intent);
            }
        });
        //when pick user button is clicked
        pickUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickUserScreen();
            }
        });
        viewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewUserRequests();
            }
        });
    }
    public void pickUserScreen(){ //from this page to PickUser page
        Intent pick = new Intent(mainPageActivity.this, lets.meat.up.PickUserActivity.class);
        Log.v(TAG,FILENAME+": User wishes to pick a user.");
        startActivity(pick);
    }
    //goes to view requests
    public void viewUserRequests(){
        Intent viewRequests = new Intent(mainPageActivity.this, lets.meat.up.AcceptUserActivity.class);
        Log.v(TAG, FILENAME+": User wishes to view user requests.");
        startActivity(viewRequests);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        Intent logout = new Intent(mainPageActivity.this, lets.meat.up.LoginActivity.class);
                        startActivity(logout);
                    }
                }).create().show();
    }
}