package com.example.letsmeatup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
    int pressedButton;
    int i;
    int j;
    static boolean press1;
    static boolean press2;


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

        //TODO:FIND A WAY TO MAKE THE BUTTONS BECOME SELECTED AND RETURN EITHER 1 OR 2

        /*
        answer1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pressedButton = 1;
                answer1.setPressed(true);
                return true;
            }
        });

        answer2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pressedButton = 2;
                answer2.setPressed(true);
                return true;
            }
        });

        */


        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalityQuestionsActivity.this, mainPageActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isButtonSelected(Button b)
    {
        boolean selected = false;
        if (b.isFocused())
        {
            selected = true;
        }
        return selected;
    }

    public String userId() {
        final ArrayList<String> ID = new ArrayList<>();
        press1 = false;
        press2 = false;

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (i = 0; i < qna.size(); i++) {
                    //q1
                    for (j = 0; j < set1.size(); j++) {
                        if (answer1.getText().toString().equals(qna.get(i).get(1))) {
                            press1 = true;
                            Log.v(TAG, "First answer selected!");
                            ID.add("1");
                        }
                    }
                    //q2
                    for (j = 0; j < set2.size(); j++) {
                        if (answer1.getText().toString().equals(qna.get(i).get(1))) {
                            press1 = true;
                            Log.v(TAG, "First answer selected!");
                            ID.add("1");
                        }
                    }
                    //q3
                    for (j = 0; j < set3.size(); j++) {
                        if (answer1.getText().toString().equals(qna.get(i).get(1))) {
                            press1 = true;
                            Log.v(TAG, "First answer selected!");
                            ID.add("1");
                        }
                    }
                    //q4
                    for (j = 0; j < set4.size(); j++) {
                        if (answer1.getText().toString().equals(qna.get(i).get(1))) {
                            press1 = true;
                            Log.v(TAG, "First answer selected!");
                            ID.add("1");
                        }
                    }
                    //q5
                    for (j = 0; j < set5.size(); j++) {
                        if (answer1.getText().toString().equals(qna.get(i).get(1))) {
                            press1 = true;
                            Log.v(TAG, "First answer selected!");
                            ID.add("1");
                        }
                    }
                }
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (i = 0; i < qna.size(); i++) {
                    //q1
                    for (j = 0; j < set1.size(); j++) {
                        if (answer2.getText().toString().equals(qna.get(i).get(2))) {
                            press2 = true;
                            Log.v(TAG, "First answer selected!");
                            ID.add("2");
                        }
                    }
                    //q2
                    for (j = 0; j < set2.size(); j++) {
                        if (answer2.getText().toString().equals(qna.get(i).get(2))) {
                            press2 = true;
                            Log.v(TAG, "First answer selected!");
                            ID.add("2");
                        }
                    }
                    //q3
                    for (j = 0; j < set3.size(); j++) {
                        if (answer2.getText().toString().equals(qna.get(i).get(2))) {
                            press2 = true;
                            Log.v(TAG, "First answer selected!");
                            ID.add("2");
                        }
                    }
                    //q4
                    for (j = 0; j < set4.size(); j++) {
                        if (answer2.getText().toString().equals(qna.get(i).get(2))) {
                            press2 = true;
                            Log.v(TAG, "First answer selected!");
                            ID.add("2");
                        }
                    }
                    //q5
                    for (j = 0; j < set5.size(); j++) {
                        if (answer2.getText().toString().equals(qna.get(i).get(2))) {
                            press2 = true;
                            Log.v(TAG, "First answer selected!");
                            ID.add("2");
                        }
                    }
                }
            }
        });
        //return ID;
    }
}