package com.example.letsmeatup;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class auAdapter extends RecyclerView.Adapter<auViewHolder> {
    private Context ctx;
    private ArrayList<AccountData> userRequest;
    AccountData currentUser;
    String confirm;
    private OnItemClickListener onItemClickListener;

    DatabaseReference fireRef = FirebaseDatabase.getInstance().getReference().child("Users");


    public void setOnItemClickListener(OnItemClickListener onItem) {
        this.onItemClickListener = onItem;
    }

    public interface OnItemClickListener{
        void ItemClick(int position);
    }

    public auAdapter(Context ctx, ArrayList<AccountData> users, AccountData current)
    {
        this.ctx = ctx;
        this.userRequest = users;
        this.currentUser = current;
    }

    public auViewHolder onCreateViewHolder (ViewGroup parent, int ViewType)
    {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_view_user_requests, parent, false);
        return new auViewHolder(item, onItemClickListener);
    }

    public void onBindViewHolder (final auViewHolder holder, final int position)
    {
        final AccountData secondUser = userRequest.get(position);
        holder.username.setText(secondUser.getUsername());
        if(secondUser.getPfp().equals("default")){
            holder.profilePic.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(ctx).load(secondUser.getPfp()).into(holder.profilePic);
        }
        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                List<String> currentUserConfirmed;
                List<String> secondUserConfirmed;

                if (currentUser.getconfirmeduserlist() == null){

                    currentUserConfirmed = new ArrayList<>();
                }
                else{
                    currentUserConfirmed = Arrays.asList(currentUser.getconfirmeduserlist().split(","));
                    currentUserConfirmed = new ArrayList<>(currentUserConfirmed);

                }
                if (secondUser.getconfirmeduserlist() == null){

                    secondUserConfirmed = new ArrayList<>();
                }
                else{
                    secondUserConfirmed = Arrays.asList(secondUser.getconfirmeduserlist().split(","));
                    secondUserConfirmed = new ArrayList<>(secondUserConfirmed);
                }

                currentUserConfirmed.add(secondUser.getID());
                secondUserConfirmed.add(currentUser.getID());
                //Convert list to string
                String currentUserList;
                String secondUserList;
                if(currentUserConfirmed.size() > 1) {
                    currentUserList = TextUtils.join(",", currentUserConfirmed);
                }
                else{
                    currentUserList = currentUserConfirmed.get(0);
                }
                if(secondUserConfirmed.size() > 1) {
                    secondUserList = TextUtils.join(",", secondUserConfirmed);
                }
                else{
                    secondUserList = secondUserConfirmed.get(0);
                }

                for(String i: currentUserConfirmed){
                }
                for(String i: secondUserConfirmed){
                }

                fireRef.child(currentUser.getID()).child("confirmeduserlist").setValue(currentUserList);
                fireRef.child(secondUser.getID()).child("confirmeduserlist").setValue(secondUserList);
                deletePending(position, secondUser);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                deletePending(position, secondUser);
            }
        });

    }

    public int getItemCount()
    {
        return userRequest.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void deletePending(final int position, AccountData user) {
        List<String> currentUserPending;
        userRequest.remove(position);
        notifyDataSetChanged();
        String pending = currentUser.getpendinguserlist();
        if(pending.contains(",")){
            currentUserPending = new LinkedList<String>(Arrays.asList(pending.split(",")));
        }
        else{
            currentUserPending = new ArrayList<>();
        }
        currentUserPending.remove(user.getID());
        //Convert to string
        String currentUserList = TextUtils.join(",",currentUserPending);
        fireRef.child(currentUser.getID()).child("pendinguserlist").setValue(currentUserList);
    }

}
