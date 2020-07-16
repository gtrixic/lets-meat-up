package com.example.letsmeatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText loginUser;
    EditText loginPass;
    ImageButton login;
    Button forgetPass;
    CheckBox logincheckbox;
    DatabaseReference fireRef = FirebaseDatabase.getInstance().getReference("Users");
    public static final String TAG = "Let's-Meat-Up";
    String FILENAME = "LoginActivity.java";
    private FirebaseAuth mAuth;
    LMUDBHandler dbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.nextArrow);
        forgetPass = findViewById(R.id.forgetPasswordButton);
        logincheckbox = findViewById(R.id.logincheckbox);
        //get firebase instance
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() { // when user clicks login
            @Override
            public void onClick(View v) {
                loginUser = findViewById(R.id.loginUsernameEmail);
                loginPass = findViewById(R.id.loginPassword);
                forgetPass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        forgetPassPage();
                    }
                });
                //first check if the credentials are valid
                mAuth.signInWithEmailAndPassword(loginUser.getText().toString(), loginPass.getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.v(TAG, "signInWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();

                                            //check if there is match id in database
                                            //query current user
                                            Log.v(TAG,"Current user's email:"+user.getEmail());
                                            Query matchIDQuery = fireRef.orderByChild("email").equalTo(user.getEmail());
                                            final List<AccountViewData> accountDataList = new ArrayList<>();
                                            matchIDQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    for(DataSnapshot s : dataSnapshot.getChildren()){
                                                    AccountViewData account = s.getValue(AccountViewData.class);
                                                    Log.v(TAG,"Account Name"+account.getFullName());
                                                    accountDataList.add(account);
                                                    }


                                                    if(dataSnapshot.exists()){
                                                        //add shared preference setting to save auto login
                                                        if(logincheckbox.isChecked() == true){
                                                        dbHandler.stayLogin(LoginActivity.this, true);}
                                                        else{
                                                            dbHandler.stayLogin(LoginActivity.this,false);
                                                        }
                                                        //Save user info
                                                        dbHandler.saveViewUser(LoginActivity.this,accountDataList.get(0));
                                                        Log.v(TAG,"MatchID:"+ accountDataList.get(0).getMatchid());
                                                        if (accountDataList.get(0).getMatchid().equals("0")) {
                                                            Log.v(TAG,"Personality questions not done!");
                                                            //go to personality question activity
                                                            Intent intent = new Intent(LoginActivity.this, PersonalityQuestionsActivity.class);
                                                            startActivity(intent);
                                                        }
                                                        else {
                                                            //goes to main page
                                                            Log.v(TAG, "Personality questions done!");
                                                            mainPage();
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                                    Log.v(TAG, "loadPost:onCancelled", databaseError.toException());
                                                }
                                            });


                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                                            Toast.makeText(LoginActivity.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
            }
        });
    }





    public void mainPage(){ //method to proceed to main page
        Intent go = new Intent(LoginActivity.this,mainPageActivity.class);
        go.putExtra("ID", loginUser.getText().toString());
        startActivity(go);
    }
    public void forgetPassPage(){ //method to proceed to forget password page
        Intent forget = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
        startActivity(forget);
    }
   /* public boolean validCredentialUser(String input, String password) { //method to check if login credentials are valid
        AccountData dbData = dbHandler.findUser(input);
        AccountData dbData2 = dbHandler.findEmail(input);
        Log.v(TAG, FILENAME + ":SharedPref Info = " + input + "|" + password);
        if (dbData != null) { //if user uses username to login
            Log.v(TAG, FILENAME + ":SharedPref Info = " + dbData.getUsername() + "|" + dbData.getPassword());
            if (dbData.getUsername().equals(input) && dbData.getPassword().equals(password)) {
                return true; // user exists
            } else {
                return false;  // user either has the wrong password or username or user does not exist
            }
        }
        if (dbData2 != null) { // if user uses email to login
            Log.v(TAG, FILENAME + ":SharedPref Info = " + dbData2.getEmail() + "|" + dbData2.getPassword());
            if (dbData2.getEmail().equals(input) && dbData2.getPassword().equals(password)) {
                return true;  // user exists
            } else {
                return false;  //user either has the wrong password or user or does not exist
            }
        }
        return false;
    }*/

    protected void onStop(){
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
                        Intent intent = new Intent(LoginActivity.this,SignupOrLoginActivity.class);
                        startActivity(intent);
    }



}
