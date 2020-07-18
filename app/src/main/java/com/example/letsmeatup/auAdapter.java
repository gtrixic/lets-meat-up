package com.example.letsmeatup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class auAdapter extends RecyclerView.Adapter<auViewHolder> {
    private Context ctx;
    private ArrayList<AccountData> userRequest;

    public auAdapter(Context ctx, ArrayList<AccountData> users)
    {
        this.ctx = ctx;
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
        AccountData user = userRequest.get(position);
        holder.username.setText(user.getUsername());
        if(user.getPfp().equals("default")){
            holder.profilePic.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(ctx).load(user.getPfp()).into(holder.profilePic);
        }
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
