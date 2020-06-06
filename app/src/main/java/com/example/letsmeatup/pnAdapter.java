package com.example.letsmeatup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pnAdapter extends RecyclerView.Adapter<pnViewHolder> {
    ArrayList<ArrayList<String>> qna;


    public pnAdapter(ArrayList<ArrayList<String>> input)
    {
        qna = input;
    }

    public pnViewHolder onCreateViewHolder (ViewGroup parent, int ViewType)
    {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent,false);
        return new pnViewHolder(item);
    }

    public void onBindViewHolder (pnViewHolder holder, int position)
    {
        ArrayList<String> a = qna.get(position);
        String s = a.get(0);
        String ss = a.get(1);
        String sss = a.get(2);
        holder.txt.setText(s);
        holder.ans1.setText(ss);
        holder.ans2.setText(sss);
    }
    public int getItemCount()
    {
        return qna.size();
    }


}
