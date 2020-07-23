package com.example.letsmeatup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class chatViewAdapter extends RecyclerView.Adapter<chatViewHolder> {
    private Context ctx;
    private ArrayList<AccountData> acceptedUsers;
    private OnItemClickListener onItemClickListener;
    private HashMap<AccountData,String>chathash;
    private DatabaseReference fireRef;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void ItemClick(int position);
    }

    public chatViewAdapter(Context ctx,ArrayList<AccountData>acceptedUsers){
        this.ctx = ctx;
        this.acceptedUsers = acceptedUsers;
        bindHash();
    }

    public chatViewHolder onCreateViewHolder(ViewGroup parent,int ViewType){
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_chat_item,parent,false);
        return new chatViewHolder(item,onItemClickListener);
    }


    public void onBindViewHolder(final chatViewHolder holder, final int position){
        AccountData acc = acceptedUsers.get(position);
        holder.Username.setText(acc.getUsername());
        if(acc.getPfp() == "default"){
            holder.ProfilePicture.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(ctx).load(acc.getPfp()).into(holder.ProfilePicture);
        }
        //TODO:add last message and last time
    }

    @Override
    public int getItemCount() {
        return acceptedUsers.size();
    }

    private void bindHash(){
        fireRef = FirebaseDatabase.getInstance().getReference().child("Chats");
        fireRef.addListenerForSingleValueEvent(new ValueEventListener() {
            ArrayList<Chat>chats = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    chats.add(s.getValue(Chat.class));
                }
                for(Chat c : chats){
                    List<String> users = Arrays.asList(c.getUsers().split(","));
                    for (AccountData user : acceptedUsers){
                        if(users.contains(user.getID())){
                            chathash.put(user,c.id);
                            break;
                        }
                    }
                }
                //check if any user not in chathash
                for(AccountData user: acceptedUsers){
                    if(!chathash.containsKey(user)){
                        chathash.put(user,"None");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
