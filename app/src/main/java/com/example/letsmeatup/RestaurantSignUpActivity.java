package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RestaurantSignUpActivity extends AppCompatActivity {
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "RestaurantSignUpActivity.java";
    Boolean allInputsFilled = true;
    LMUDBHandler lmudbHandler = new LMUDBHandler(this,null,null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sign_up);

        ImageButton nextButton = findViewById(R.id.nextArrow);
        ImageButton backButton = findViewById(R.id.backArrow);

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
                if(allInputsFilled == true){
                    //check if account is already registered
                    RestaurantData rData = new RestaurantData();
                    RestaurantData rDataCheck = lmudbHandler.findRestaurant(Email.getText().toString());
                    if (rDataCheck == null){
                        rData.setRestaurantName(Rname.getText().toString());
                        rData.setAddress(Address.getText().toString());
                        rData.setPassword(Password.getText().toString());
                        rData.setEmail(Email.getText().toString());
                        rData.setPfpLink(null);
                        //check if password matches
                        if(rData.checkPassword(checkPassword.getText().toString())){
                            lmudbHandler.addRestaurant(rData);
                            Toast.makeText(RestaurantSignUpActivity.this,"Restaurant created!",Toast.LENGTH_SHORT).show();
                            Log.v(TAG,FILENAME+"Restaurant Account Created: "+rData.getRestaurantName());
                            Intent intent = new Intent(RestaurantSignUpActivity.this,RestaurantSignUp2Activity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(RestaurantSignUpActivity.this,"Passwords do not match!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RestaurantSignUpActivity.this,"Account already exists under this email!",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(RestaurantSignUpActivity.this,"Not all fields have been filled in!",Toast.LENGTH_SHORT).show();
                    //reset allinputsfilled
                    allInputsFilled = true;
                }

            }



        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantSignUpActivity.this,CreateUserOrBusinessActivity.class);
                startActivity(intent);
            }
        });
    }
}