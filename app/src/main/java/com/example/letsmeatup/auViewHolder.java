package com.example.letsmeatup;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class auViewHolder extends RecyclerView.ViewHolder {
    ImageView profilePic;
    TextView username;
    ImageButton confirm;
    ImageButton delete;

    public auViewHolder(View view)
    {
        super(view);
        profilePic = view.findViewById(R.id.profilePic);
        username = view.findViewById(R.id.username);
        confirm = view.findViewById(R.id.confirmButton);
        delete = view.findViewById(R.id.deleteButton);
    }
}
