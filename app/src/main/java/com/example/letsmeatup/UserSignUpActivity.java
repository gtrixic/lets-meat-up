package com.example.letsmeatup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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

import java.util.regex.Pattern;

public class UserSignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG ="Let's-Meat-Up";
    private String FILENAME = "UserSignUpActivity.java";
    private FirebaseAuth mAuth;
    private DatabaseReference fireRef;
    String[] Gender = {"M","F"};
    String GenderSelected;
    Boolean allInputFilled = true;


    LMUDBHandler lmudbHandler = new LMUDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        Spinner genderSpinner = findViewById(R.id.gender);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GenderSelected = Gender[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                GenderSelected = null;
            }
        });
        //Creating Adapter to contain gender string array
        //temporarily using simple layout for arrayadapter, will update
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.spinner_layout,Gender);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        //setting array adapter data for spinner
        genderSpinner.setAdapter(arrayAdapter);




        ImageButton nextButton = findViewById(R.id.nextArrow);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Instantiate edit texts
                final EditText FullName = findViewById(R.id.fullName);
                final EditText Username = findViewById(R.id.username);
                final EditText Password = findViewById(R.id.Rpassword);
                final EditText checkPassword = findViewById(R.id.passwordtwice);
                final EditText Email = findViewById(R.id.email);
                final EditText Date = findViewById(R.id.DOB);
                //put edit texts into an array to loop through to check if any values return null
                EditText[] Info = {FullName,Username,Password,checkPassword,Email,Date};
                for(EditText line : Info){
                    if (line.getText().toString().length() == 0){
                        allInputFilled = false;
                        break;
                    }
                }
                if (allInputFilled && GenderSelected != null) {
                    //validate if date is correct
                    final Pattern regex =
                            Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
                    if (regex.matcher(Date.getText().toString()).matches()){
                        //check if user is already in database
                        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");
                    //Query if data exists
                    Query emailQuery = fireRef.orderByChild("email").equalTo(Email.getText().toString());
                    emailQuery.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //prevent the toast from showing if the data gets updated
                            int runtime = 0;
                            if (dataSnapshot.exists() && runtime > 0) {
                                Toast.makeText(UserSignUpActivity.this, "User is already registered in the database!", Toast.LENGTH_SHORT).show();
                                Log.v(TAG, "Email: " + Email.getText().toString());
                            } else {

                                final AccountData dbAccountData = new AccountData();
                                dbAccountData.setFullName(FullName.getText().toString());
                                dbAccountData.setUsername(Username.getText().toString());
                                dbAccountData.setPassword(Password.getText().toString());
                                dbAccountData.setEmail(Email.getText().toString());
                                dbAccountData.setGender(GenderSelected);
                                dbAccountData.setDob(Date.getText().toString());
                                dbAccountData.setMatchid("0");
                                dbAccountData.setPfp("default");
                                dbAccountData.setconfirmeduserlist("");
                                dbAccountData.setpendinguserlist("");
                                if (dbAccountData.isPasswordMatch(checkPassword.getText().toString())) {
                                    Log.v(TAG, "Passwords Match!");
                                    if (dbAccountData.isValidEmail(dbAccountData.getEmail())) {
                                        Log.v(TAG, "Valid Email!");
                                        //Create account in mAuth
                                        mAuth = FirebaseAuth.getInstance();
                                        mAuth.createUserWithEmailAndPassword(Email.getText().toString(), Password.getText().toString())
                                                .addOnCompleteListener(UserSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.v(TAG, "createUserWithEmail:success");
                                                            Log.v(TAG, "mAuth creation complete!");
                                                            //Generate ID
                                                            //count number of entries in db
                                                            fireRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                    //get total num
                                                                    int ID = (int) dataSnapshot.getChildrenCount();
                                                                    //convert to ID
                                                                    String stringID = String.valueOf(ID).format("%04d", ID);
                                                                    dbAccountData.setID(stringID);
                                                                    fireRef.child(stringID).setValue(dbAccountData);

                                                                    Toast.makeText(UserSignUpActivity.this, "User created!", Toast.LENGTH_SHORT).show();
                                                                    Log.v(TAG, "User Created :" + Username.getText().toString());
                                                                    //save email to continue sign up
                                                                    lmudbHandler.saveUser(UserSignUpActivity.this, dbAccountData);
                                                                    Intent intent = new Intent(UserSignUpActivity.this, UserSignUp2Activity.class);
                                                                    startActivity(intent);
                                                                    finish();
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                                                    Log.v(TAG, "loadPost:onCancelled", databaseError.toException());
                                                                }
                                                            });
                                                        }
                                                    }
                                                });
                                    } else {
                                        Log.v(TAG, "Email invalid!");
                                        Toast.makeText(UserSignUpActivity.this, "Email is invalid!", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(UserSignUpActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Log.v(TAG, "loadPost:onCancelled", databaseError.toException());
                        }
                    });
                }
                    else{
                        Toast.makeText(UserSignUpActivity.this,"Date is invalid!",Toast.LENGTH_SHORT).show();
                    }
                }
                else if(allInputFilled == false || GenderSelected == null){
                    Toast.makeText(UserSignUpActivity.this,"Not all inputs have been filled in.",Toast.LENGTH_SHORT).show();
                    //reset after trying to post to database
                    allInputFilled = true;
                }
            }
        });

        ImageButton BackButton = findViewById(R.id.backArrow);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserSignUpActivity.this,SignupOrLoginActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.v(TAG, parent.getSelectedItem().toString() + "selected.");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}