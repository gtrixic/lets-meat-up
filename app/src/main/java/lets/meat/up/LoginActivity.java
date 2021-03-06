package lets.meat.up;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    lets.meat.up.LMUDBHandler dbHandler = new lets.meat.up.LMUDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.nextArrow);
        forgetPass = findViewById(R.id.forgetPasswordButton);
        logincheckbox = findViewById(R.id.logincheckbox);
        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPassPage();
            }
        });
        //get firebase instance
        mAuth = FirebaseAuth.getInstance();

        //login function in login button
        login.setOnClickListener(new View.OnClickListener() { // when user clicks login
            @Override
            public void onClick(View v) {
                final lets.meat.up.LoadingDialog loadingDialog = new lets.meat.up.LoadingDialog(LoginActivity.this);
                loadingDialog.startLoadingDialog();
                loginUser = findViewById(R.id.loginUsernameEmail);
                loginPass = findViewById(R.id.loginPassword);
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
                                            final List<lets.meat.up.AccountData> accountDataList = new ArrayList<>();
                                            matchIDQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    for(DataSnapshot s : dataSnapshot.getChildren()){
                                                    lets.meat.up.AccountData account = s.getValue(lets.meat.up.AccountData.class);
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
                                                        dbHandler.saveUser(LoginActivity.this,accountDataList.get(0));
                                                        Log.v(TAG,"MatchID:"+ accountDataList.get(0).getMatchid());
                                                        if (accountDataList.get(0).getMatchid().equals("0")) {
                                                            Log.v(TAG,"Personality questions not done!");
                                                            //go to personality question activity
                                                            loadingDialog.dismissDialog();
                                                            Intent intent = new Intent(LoginActivity.this, lets.meat.up.PersonalityQuestionsActivity.class);
                                                            startActivity(intent);
                                                        }
                                                        else {
                                                            //goes to main page
                                                            loadingDialog.dismissDialog();
                                                            Log.v(TAG, "Personality questions done!");
                                                            mainPage();
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                                    loadingDialog.dismissDialog();
                                                    Log.v(TAG, "loadPost:onCancelled", databaseError.toException());
                                                }
                                            });


                                        } else {
                                            loadingDialog.dismissDialog();
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
        Intent go = new Intent(LoginActivity.this, lets.meat.up.mainPageActivity.class);
        go.putExtra("ID", loginUser.getText().toString());
        startActivity(go);
    }
    public void forgetPassPage(){ //method to proceed to forget password page
        Intent forget = new Intent(LoginActivity.this, lets.meat.up.ForgetPasswordActivity.class);
        startActivity(forget);
    }


    protected void onStop(){
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
                        Intent intent = new Intent(LoginActivity.this, lets.meat.up.SignupOrLoginActivity.class);
                        startActivity(intent);
    }



}
