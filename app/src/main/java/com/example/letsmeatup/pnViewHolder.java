package com.example.letsmeatup;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class pnViewHolder extends RecyclerView.ViewHolder {
    TextView txt;
    Button ans1;
    Button ans2;

    public pnViewHolder(View view)
    {
        super(view);
        txt = view.findViewById(R.id.question);
        ans1 = view.findViewById(R.id.button1);
        ans2 = view.findViewById(R.id.button2);
    }
}
