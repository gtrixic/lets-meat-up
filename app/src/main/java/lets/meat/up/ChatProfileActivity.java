package lets.meat.up;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ChatProfileActivity extends AppCompatActivity {
    private String TAG = "ChatProfileActivity";

    TextView userID;
    TextView userName;
    TextView userGender;
    TextView userAge;
    TextView userDOB;
    TextView userDiet;
    TextView userAllergy;
    RecyclerView suggestRV;
    ImageView profilePic;
    Button addButton;
    ImageButton back;
    lets.meat.up.suggestAdapter sAdapter;
    ArrayList<lets.meat.up.RestaurantData> restDataList;
    String newChatID;
    boolean checkSuggestions;
    String IchatID;
    lets.meat.up.LMUDBHandler lmudbHandler = new lets.meat.up.LMUDBHandler(this);
    DatabaseReference fireRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chat_user);
        userID = findViewById(R.id.userID);
        userName = findViewById(R.id.userName);
        userGender = findViewById(R.id.userGender);
        userAge = findViewById(R.id.userAge);
        userDOB = findViewById(R.id.userDOB);
        userAllergy = findViewById(R.id.userAllergies);
        userDiet = findViewById(R.id.userDiet);
        suggestRV = findViewById(R.id.suggestedRV);
        profilePic = findViewById(R.id.chatPic);
        addButton = findViewById(R.id.suggestButton);
        back = findViewById(R.id.backArrow3);
        final String IuserID = getIntent().getStringExtra("userid");
        IchatID = getIntent().getStringExtra("chatid");
        //back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatProfileActivity.this, lets.meat.up.MessageActivity.class);
                intent.putExtra("userid", IuserID);
                if (IchatID.equals("default")) {
                    intent.putExtra("chatid", newChatID);
                } else {
                    intent.putExtra("chatid", IchatID);

                }
                startActivity(intent);
            }
        });
        checkSuggestions = false;
        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");
        //add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lets.meat.up.getYelpAPI api = new lets.meat.up.getYelpAPI(ChatProfileActivity.this, IchatID);
                //get a random restaurant term
                Random ran = new Random();
                String[] terms = new String[]{"Bento", "Desserts", "Nasi Lemak", "Patisserie",
                        "Smokehouse", "Gelato", "Japanese", "Korean", "Chinese", "Malay", "Indian", "Western"};
                int randomNo = ran.nextInt(terms.length);
                HashMap<String, String> params = new HashMap<>();
                params.put("term", terms[randomNo]);
                params.put("location", "Singapore");
                params.put("categories", "restaurants");
                params.put("limit", "50");
                params.put("sort_by", "rating");
                api.execute(params);

            }
        });
        fireRef.child(IuserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //setting other user's information
                lets.meat.up.AccountData acc = snapshot.getValue(lets.meat.up.AccountData.class);
                userID.setText(acc.getUsername());
                userName.setText(acc.getFullName());
                String stDate = acc.getDob();
                Date date = new Date();
                Date c = Calendar.getInstance().getTime();
                // getting age from dob
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    date = format.parse(stDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long age = c.getTime() - date.getTime();
                int ageinyears = (int) (Math.floor(TimeUnit.DAYS.convert(age, TimeUnit.MILLISECONDS) / 365));
                String strAge = String.valueOf(ageinyears);
                userAge.setText(strAge);
                userGender.setText(acc.getGender());
                userDOB.setText(acc.getDob());
                userDiet.setText(acc.getDiet());
                userAllergy.setText(acc.getAllergy());
                if (acc.getPfp().equals("default")) {
                    //if no profile pic
                    Log.v("ChatViewAdapter", "Setting default image");
                    profilePic.setImageResource(R.mipmap.ic_launcher);
                } else {
                    //using Glide to set pfp
                    Log.v("ChatViewAdapter", "Setting profile picture");
                    Glide.with(ChatProfileActivity.this).load(acc.getPfp()).into(profilePic);
                }
                //if got chat
                if (!IchatID.equals("default")) {
                    displaySuggestions(IchatID);
                } else {
                    //create chat class and post
                    lets.meat.up.Chat chat = new lets.meat.up.Chat();
                    chat.setUsers(lmudbHandler.getUserDetail(ChatProfileActivity.this, "id") + "," + acc.getID());
                    final String key = FirebaseDatabase.getInstance().getReference().child("Chats").push().getKey();
                    chat.setId(key);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Chats");
                    ref.child(key).setValue(chat);
                    IchatID = key;
                    newChatID = key;
                    //creating system message
                    Date currentTime = Calendar.getInstance().getTime();
                    lets.meat.up.Message userMessage = new lets.meat.up.Message();
                    userMessage.setSender("System");
                    userMessage.setReceiver(acc.getID());
                    userMessage.setCreatedAt(currentTime.getTime());
                    userMessage.SetMessage("Chat created.");
                    userMessage.setId(ref.child(key).child("Messages").push().getKey());
                    //set message
                    ref.child(key).child("Messages").child(userMessage.getId()).setValue(userMessage);
                    //set metadata
                    ref.child(key).child("lastMessage").setValue(userMessage);
                    displaySuggestions(key);
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            displaySuggestions(key);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        if (!IchatID.equals("default") || newChatID != null){
            if (IchatID.equals("default")) {
                fireRef = FirebaseDatabase.getInstance().getReference().child("Chats").child(newChatID);
            } else {
                fireRef = FirebaseDatabase.getInstance().getReference().child("Chats").child(IchatID);
            }
            //prevent from making a new chat when accessing displaysuggestions
        fireRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (IchatID.equals("default")) {
                    displaySuggestions(newChatID);
                } else {
                    displaySuggestions(IchatID);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    }
    //display any restaurants already recorded in the database
    private void displaySuggestions(final String chatid){
        restDataList = new ArrayList<>();
        fireRef = FirebaseDatabase.getInstance().getReference().child("Chats").child(chatid).child("Suggestions");
        suggestRV.setLayoutManager(new LinearLayoutManager(ChatProfileActivity.this));
        suggestRV.setHasFixedSize(true);
        lmudbHandler.readData(fireRef, new lets.meat.up.LMUDBHandler.OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                //when adding restaurant, updates realtime
                restDataList.clear();
                for(final DataSnapshot s : dataSnapshot.getChildren()){
                    final lets.meat.up.RestaurantData rd = s.getValue(lets.meat.up.RestaurantData.class);
                    restDataList.add(rd);
                    sAdapter = new lets.meat.up.suggestAdapter(restDataList);
                    //display restaurant information when clicked
                    sAdapter.setOnItemClickListener(new lets.meat.up.suggestAdapter.OnItemClickListener() {
                        @Override
                        public void ItemClick(int position) {
                            lets.meat.up.RestaurantData Ritem = restDataList.get(position);
                            lets.meat.up.RestaurantProfileDialog rpDialog = new lets.meat.up.RestaurantProfileDialog(ChatProfileActivity.this,Ritem,chatid);
                            rpDialog.setRestName(Ritem.getRestaurantName());
                            rpDialog.setRestAddr(Ritem.getAddress());
                            rpDialog.setRestType(Ritem.getCategory());
                            rpDialog.setImage(Ritem.getPfpLink());
                            rpDialog.show();
                        }
                    });
                    suggestRV.setAdapter(sAdapter);
                    //update realtime
                    sAdapter.notifyDataSetChanged();
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