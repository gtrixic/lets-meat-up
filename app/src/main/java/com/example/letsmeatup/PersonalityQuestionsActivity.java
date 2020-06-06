package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PersonalityQuestionsActivity extends AppCompatActivity {
    public static final String TAG = "Let's Meat Up";
    String FILENAME = "PersonalityQuestionsActivity.java";
    ArrayList<ArrayList<String>> qna = new ArrayList<>();
    ArrayList<String> set1 = new ArrayList<>();
    ArrayList<String> set2 = new ArrayList<>();
    ArrayList<String> set3 = new ArrayList<>();
    ArrayList<String> set4 = new ArrayList<>();
    ArrayList<String> set5 = new ArrayList<>();
    TextView question;
    Button answer1;
    Button answer2;
    Button submit;
    RecyclerView recyclerView;
    pnAdapter pnAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personality_questions);

        recyclerView = findViewById(R.id.recyclerView);
        question = findViewById(R.id.question);
        answer1 = findViewById(R.id.button1);
        answer2 = findViewById(R.id.button2);

        // placing data into arraylists
        set1.add("Are you introverted or extroverted?");
        set1.add("introverted");
        set1.add("extroverted");

        set2.add("Are you typically organised?");
        set2.add("tidy");
        set2.add("messy");

        set3.add("Do you like indoors or outdoors?");
        set3.add("indoors");
        set3.add("outdoors");

        set4.add("Are you stubborn or open-minded");
        set4.add("stubborn");
        set4.add("open-minded");

        set5.add("Are you a big eater?");
        set5.add("small");
        set5.add("big");

        qna.add(set1);
        qna.add(set2);
        qna.add(set3);
        qna.add(set4);
        qna.add(set5);


        pnAdapter = new pnAdapter(qna);
        LinearLayoutManager pnLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(pnLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pnAdapter);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalityQuestionsActivity.this, mainPageActivity.class);
                startActivity(intent);
            }
        });

    }
}