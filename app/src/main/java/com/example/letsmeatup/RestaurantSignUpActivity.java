package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class RestaurantSignUpActivity extends AppCompatActivity {
    private static String TAG  = "Let's-Meat-Up RestaurantSignUp";
    Boolean allInputsFilled = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sign_up);

        ImageButton nextButton = findViewById(R.id.nextArrow);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Rname = findViewById(R.id.restaurantName);
                EditText Address = findViewById(R.id.address);
                EditText Password = findViewById(R.id.Rpassword);
                EditText checkPassword = findViewById(R.id.passwordtwice);
                EditText Email = findViewById(R.id.email);
                EditText[] info = {Rname,Address,Password,checkPassword,Email};
                for(EditText line : info){
                    if (line.getText().toString() == null){
                        allInputsFilled = false;
                        break;
                    }
                }

            }
        });
    }
}