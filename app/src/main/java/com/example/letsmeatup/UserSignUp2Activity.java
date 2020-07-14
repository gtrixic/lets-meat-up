package com.example.letsmeatup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UserSignUp2Activity extends AppCompatActivity {
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "UserSignUp2Activity.java";
    private ImageButton chooseImageButton;
    //TODO:ADD PROFILE PICTURE WITH FIREBASE STORAGE SDK
    private Uri FilePath;
    private final int PICK_IMAGE_REQUEST = 71;


    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up2);
        ImageButton backButton = findViewById(R.id.backArrow);
        ImageButton nextButton = findViewById(R.id.nextArrow);
        chooseImageButton = findViewById(R.id.addProfilePic);
        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Prompts user to select an image
                chooseImage();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // when user clicks the next arrow button
                // moves from this page to next page of sign up
                Intent intent = new Intent(UserSignUp2Activity.this,UserSignUp3Activity.class);
                startActivity(intent);
            }
        });
        // alert for users that this goes to the login page
        final AlertDialog.Builder b =new AlertDialog.Builder(this);
        b.setTitle("WARNING!");
        b.setMessage("This will bring you to the login page, are you sure?");
        b.setCancelable(false);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { // brings user to login page
                Log.v(TAG,"User has selected to go to login page.");
                Intent intent = new Intent(UserSignUp2Activity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { // user remains on this page
                Log.v(TAG,"User has selected to stay on page."  );
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = b.create();
                alertDialog.show();
            }
        });





    }
    private void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null ){
            FilePath = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),FilePath);
                int height = chooseImageButton.getHeight();
                int width = chooseImageButton.getWidth();
                chooseImageButton.setImageBitmap(bitmap);
                chooseImageButton.getLayoutParams().height = height;
                chooseImageButton.getLayoutParams().width = width;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}