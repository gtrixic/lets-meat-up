package com.example.letsmeatup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonalityQuestionsActivity extends AppCompatActivity {
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "PersonalityQuestionsActivity.java";
    ArrayList<String> qns = new ArrayList<>();
    String[] answerarray = {"introverted","extroverted","tidy","messy","indoors","outdoors","stubborn","open-minded","small","big"};
    HashMap<String, Integer> answers = new HashMap<>();
    TextView question;
    TextView answer;
    Button answer1;
    Button answer2;
    Button submit;
    RecyclerView recyclerView;
    pnAdapter pnAdapter;
    boolean allInputsFilled = true;
    LMUDBHandler dbHandler = new LMUDBHandler(this,null,null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_questions);

        recyclerView = findViewById(R.id.recyclerView);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.userAns);
        answer1 = findViewById(R.id.button1);
        answer2 = findViewById(R.id.button2);

        // placing data into arraylists
        //Convert questions to arraylist and put answers in hashmap
        qns.add("Are you introverted or extroverted?");
        qns.add("Are you typically organised?");
        qns.add("Do you like indoors or outdoors?");
        qns.add("Are you stubborn or open-minded");
        qns.add("Are you a big eater?");
        for(int i = 0; i < answerarray.length;i++){
            if(i == 0 || i % 2 == 0){
                answers.put(answerarray[i],1);
            }
            else{
                answers.put(answerarray[i],2);
            }
        }


        pnAdapter = new pnAdapter(qns,answers,answerarray);
        LinearLayoutManager pnLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(pnLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pnAdapter);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(String codel : pnAdapter.returnCode()){
                    if (codel == "0"){
                        Toast.makeText(PersonalityQuestionsActivity.this, "Not all inputs have been filled!", Toast.LENGTH_SHORT).show();
                        allInputsFilled = false;
                        break;
                    }
                }
                if(allInputsFilled){
                    //add match id to user's account
                    //get user id
                    String userid = dbHandler.getUserDetail(PersonalityQuestionsActivity.this,"id");
                    dbHandler.addMatchID(pnAdapter.returnCode(),userid);
                    Intent intent = new Intent(PersonalityQuestionsActivity.this, LoginActivity.class);
                    startActivity(intent);}
                else{
                    allInputsFilled = true;
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        System.exit(0);
                    }
                }).create().show();
    }
}