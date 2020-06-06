package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PersonalityQuestionsActivity extends AppCompatActivity {
    public static final String TAG = "Let's Meat Up";
    String FILENAME = "PersonalityQuestionsActivity.java";
    ArrayList<ArrayList<String>> qna = new ArrayList<>(); //parent list
    //child lists
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
        setContentView(R.layout.activity_personality_questions);

        recyclerView = findViewById(R.id.recyclerView);
        question = findViewById(R.id.question);
        answer1 = findViewById(R.id.button1);
        answer2 = findViewById(R.id.button2);

        // placing data into arraylists
        set1.add("Are you introverted or extroverted?");
        set1.add("Introverted"); //= ans1
        set1.add("Extroverted"); //= ans2

        set2.add("Are you typically organised?");
        set2.add("Tidy");
        set2.add("Messy");

        set3.add("Do you like indoors or outdoors?");
        set3.add("Indoors");
        set3.add("Outdoors");

        set4.add("Are you stubborn or open-minded");
        set4.add("Stubborn");
        set4.add("Open-minded");

        set5.add("Are you a big eater?");
        set5.add("Yes");
        set5.add("No");

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

    public String userId(){
        ArrayList<String> userAnswers = new ArrayList<>();
        ArrayList<String> id = new ArrayList<>();
        int i;
        int j;

        /*
        //bool
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "First answer selected!");
                //press = true;
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "First answer selected!");
            }
        });*/

        //2 for loops
        //access set through qna     //if (qna[i][j].equals(answer1.gettext().tosstring()
        //qna.get(i).get(1)

        for (i = 0; i < qna.size(); i++){
            for (j = 0; j < set1.size(); j++){
                //if (press1 == true) add 1
                if (answer1.getText().toString().equals(qna.get(i).get(1))){
                    userAnswers.add("1");
                }
            }
        }

        /*for (i = 0; i < set1.size(); i++){
            if (answer1.getText().toString().equals(set1.get(1))){
                userAnswers.add("1");
            }
            else if (answer2.getText().toString().equals(set1 .get(1))){
                userAnswers.add("2");
            }
        }*/

        /*if (answer1.getText().toString().equals(set1.get(1))){
            userAnswers.add(answer1.getText().toString());
        }*/
    }
}