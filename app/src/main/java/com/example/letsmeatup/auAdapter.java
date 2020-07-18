package com.example.letsmeatup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class auAdapter extends RecyclerView.Adapter<auViewHolder> {
    ArrayList<String> userRequest;

    public auAdapter(ArrayList<String> users)
    {
        this.userRequest = users;
    }

    public auViewHolder onCreateViewHolder (ViewGroup parent, int ViewType)
    {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_view_user_requests, parent, false);
        return new auViewHolder(item);
    }

    public void onBindViewHolder (final auViewHolder holder, final int position)
    {
        String user = userRequest.get(position);
        holder.username.setText(user);
        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }

    public int getItemCount()
    {
        return userRequest.size();
    }

}
