package com.example.letsmeatup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class pnAdapter extends RecyclerView.Adapter<pnViewHolder> {
    private ArrayList<String> qna;
    private HashMap<String,Integer>ans;
    private String[] answers;
    String[] code = {"0","0","0","0","0"};



    public pnAdapter(ArrayList<String> input,HashMap<String,Integer>ans,String[]answers)
    {
        this.qna = input;
        this.ans = ans;
        this.answers = answers;
    }

    public pnViewHolder onCreateViewHolder (ViewGroup parent, int ViewType)
    {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent,false);
        return new pnViewHolder(item);
    }

    public void onBindViewHolder (final pnViewHolder holder, final int position)
    {
        String qn = qna.get(position);
        final String ans1 = answers[position+position];
        String ans2 = answers[position+position+1];
        String selectedAns = "";
        holder.txt.setText(qn);
        holder.ans1.setText(ans1);
        holder.ans2.setText(ans2);
        holder.answerdisplay.setText(selectedAns);
        holder.ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.answerdisplay.setText(holder.ans1.getText());
                code[position] = "1";

            }
        });
        holder.ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.answerdisplay.setText(holder.ans2.getText());
                code[position] = "2";
            }
        });


    }
    public int getItemCount()
    {
        return qna.size();
    }

    public String[] returnCode(){
        return code;
    }

}
