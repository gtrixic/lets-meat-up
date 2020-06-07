package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

public class AddRestaurants extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurants);
        final EditText term = findViewById(R.id.Term);
        Button sendterm = findViewById(R.id.SendTerm);
        sendterm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //adding restaurants into the database via YelpAPI
                getYelpAPI api = new getYelpAPI(AddRestaurants.this);
                HashMap<String,String> params = new HashMap<>();
                params.put("term",term.getText().toString());
                params.put("location","Singapore");
                params.put("categories","restaurants");
                params.put("limit","50");
                params.put("sort_by","rating");
                api.execute(params);
                Toast.makeText(AddRestaurants.this,"50 Restaurants with tag: "+term.getText().toString()+" added!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}