package com.example.letsmeatup;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class EditProfile extends AppCompatActivity {
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "EditProfile.java";

    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;

    EditText etUsername;
    EditText etName;
    EditText etEmail;
    Spinner etGender;
    EditText etDob;
    EditText etAllergies;
    ImageView etPfp;
    Button confirm;
    ImageView backArrow;
    Boolean allInputFilled = false;
    String[] gender = {"M", "F"};
    String genderSelected;
    private static final int IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("users/"+mAuth.getCurrentUser().getUid()+"pfp.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(etPfp);
            }
        });

        etUsername = findViewById(R.id.editUsername);
        etName = findViewById(R.id.editName);
        etEmail = findViewById(R.id.editEmail);
        etGender = findViewById(R.id.editGender); //spinner
        etDob = findViewById(R.id.editDob);
        etAllergies = findViewById(R.id.editAllergies);
        etPfp = findViewById(R.id.editProfilePic);

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
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.spinner_layout, gender);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        //setting array adapter data for spinner
        etGender.setAdapter(arrayAdapter);

        //change pfp
        etPfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        /*changePfpIntent.setType("image/*");
                        changePfpIntent.setAction(Intent.ACTION_GET_CONTENT);*/
                startActivityForResult(openGalleryIntent, 1000);
            }
        });

        //confirm button
        confirm = findViewById(R.id.confirmButton);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText[] editedInfo = {etUsername, etName, etEmail, etDob, etAllergies};
                for (EditText line : editedInfo){
                    if (line.getText().toString() == null){
                        allInputFilled = false;
                        break;
                    }
                }
                if (allInputFilled == true && genderSelected != null){
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
                    //Query if data exists
                    Query emailQuery = databaseReference.orderByChild("email").equalTo(etEmail.getText().toString());
                    emailQuery.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                            int runtime = 0;
                            if(dataSnapshot.exists() && runtime > 0) {
                                Toast.makeText(EditProfile.this, "Email already exists!", Toast.LENGTH_SHORT).show();
                                Log.v(TAG, "Email: " + etEmail.getText().toString());
                            }
                            else {
                                AccountData accountData = new AccountData();
                                accountData.setUsername(etUsername.getText().toString());
                                accountData.setFullName(etName.getText().toString());
                                accountData.setEmail(etEmail.getText().toString());
                                accountData.setGender(genderSelected);
                                accountData.setDob(etDob.getText().toString());
                                accountData.setAllergy(etAllergies.getText().toString());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }




                //UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName();

                //confirmChanges();
                //go back to UserProfile.java
                Intent intent = new Intent(EditProfile.this, UserProfile.class);
                startActivity(intent);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                //etPfp.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri){
        final StorageReference fileRef = storageReference.child("users/"+mAuth.getCurrentUser().getUid()+"pfp.jpg"); //to allow each user to upload their own pfp
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Toast.makeText(EditProfile.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //to display pfp
                        Picasso.get().load(uri).into(etPfp);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfile.this, "Image failed to upload", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*//confirm changes popup
    private void confirmChanges(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Confirm changes?");
        builder.setCancelable(false);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG, "User declined");
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG, "User accepts");
                returnToUserProfile();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    //go back to UserProfile.java
    private void returnToUserProfile(){
        Intent intent = new Intent(EditProfile.this, UserProfile.class);
        startActivity(intent);
    }*/
}
