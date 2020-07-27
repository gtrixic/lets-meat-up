package com.example.letsmeatup;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.regex.Pattern;

public class EditProfile extends AppCompatActivity {
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "EditProfile.java";
    AccountData currentUser;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseAuth mAuth;
    LMUDBHandler dbhandler;
    private final int PICK_IMAGE_REQUEST = 71;
    FirebaseStorage storage;
    private Uri FilePath;
    EditText etUsername;
    EditText etName;
    TextView etEmail;
    Spinner etGender;
    EditText etDob;
    EditText etAllergies;
    ImageButton etPfp;
    Button confirm;
    ImageView backArrow;
    Boolean allInputFilled = true;
    String[] gender = {"M", "F"};
    String genderSelected;
    private static final int IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        dbhandler = new LMUDBHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);


        etUsername = findViewById(R.id.editUsername);
        etName = findViewById(R.id.editName);
        etEmail = findViewById(R.id.editEmail);
        etGender = findViewById(R.id.editGender); //spinner
        etDob = findViewById(R.id.editDob);
        etAllergies = findViewById(R.id.editAllergies);
        etPfp = findViewById(R.id.editProfilePic);

        currentUser = dbhandler.returnUser(this);
        etUsername.setText(currentUser.getUsername());
        etName.setText(currentUser.getFullName());
        etEmail.setText(currentUser.getEmail());
        etDob.setText(currentUser.getDob());
        etAllergies.setText(currentUser.getAllergy());
        //set etPfp to pfp if available
        if (dbhandler.returnUser(this).getPfp().equals("default")) {
            etPfp.setImageResource(R.drawable.image_box);
        } else {
            Glide.with(this).load(dbhandler.returnUser(this).getPfp()).into(etPfp);
        }
        //gender spinner
        etGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderSelected = gender[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                genderSelected = null;
            }
        });
        //creating adapter to contain gender string array
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_layout, gender);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        //setting array adapter data for spinner
        etGender.setAdapter(arrayAdapter);

        //change pfp
        etPfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        //confirm button
        confirm = findViewById(R.id.confirmButton);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText[] editedInfo = {etUsername, etName, etDob, etAllergies};
                for (EditText line : editedInfo) {
                    if (line.getText().toString().length() == 0) {
                        allInputFilled = false;
                    }
                }
                if (allInputFilled && genderSelected != null) {
                    //regex for dob
                    final Pattern regex =
                            Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
                    if (regex.matcher(etDob.getText().toString()).matches()) {
                        //do upload image
                        Log.v(TAG, "Uploading image!");
                        dbhandler.uploadImage(EditProfile.this, FilePath, storageReference, null);
                        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(dbhandler.returnUser(EditProfile.this).getID());
                        final AccountData accountData = dbhandler.returnUser(EditProfile.this);
                        accountData.setUsername(etUsername.getText().toString());
                        accountData.setFullName(etName.getText().toString());
                        accountData.setEmail(etEmail.getText().toString());
                        accountData.setGender(genderSelected);
                        accountData.setDob(etDob.getText().toString());
                        accountData.setAllergy(etAllergies.getText().toString());
                        //push data to dbreference
                        Log.v(TAG, "Updating account data!");
                        dbhandler.readData(databaseReference, new LMUDBHandler.OnGetDataListener() {
                            @Override
                            public void onSuccess(DataSnapshot dataSnapshot) {
                                //save into shared pref
                                databaseReference.setValue(accountData);
                                dbhandler.saveUser(EditProfile.this, accountData);
                                //make toast
                                Toast.makeText(EditProfile.this, "Successfully updated user!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditProfile.this, UserProfile.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onFailure() {

                            }
                        });


                    } else {
                        Toast.makeText(EditProfile.this, "Invalid date!", Toast.LENGTH_SHORT).show();

                    }
                } else if (allInputFilled == false) {
                    Toast.makeText(EditProfile.this, "Not all inputs filled!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfile.this, "An error has occurred.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //back button
        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go back to UserProfile.java
                Intent intent = new Intent(EditProfile.this, UserProfile.class);
                startActivity(intent);
            }
        });
    }

    //for change pfp
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            FilePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePath);
                int height = etPfp.getHeight();
                int width = etPfp.getWidth();
                etPfp.setImageBitmap(bitmap);
                etPfp.getLayoutParams().height = height;
                etPfp.getLayoutParams().width = width;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
}


