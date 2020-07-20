package com.example.letsmeatup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class chatViewAdapter extends RecyclerView.Adapter<chatViewHolder> {
    private Context ctx;
    private ArrayList<AccountData> acceptedUsers;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void ItemClick(int position);
    }

    public chatViewAdapter(Context ctx,ArrayList<AccountData>acceptedUsers){
        this.ctx = ctx;
        this.acceptedUsers = acceptedUsers;
    }

    public chatViewHolder onCreateViewHolder(ViewGroup parent,int ViewType){
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_chat_item,parent,false);
        return new chatViewHolder(item,onItemClickListener);
    }

    public void onBindViewHolder(final chatViewHolder holder, final int position){

    }

    @Override
    public int getItemCount() {
        return acceptedUsers.size();
    }
}
