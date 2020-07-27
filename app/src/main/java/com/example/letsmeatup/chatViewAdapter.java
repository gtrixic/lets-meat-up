package com.example.letsmeatup;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
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

import com.google.firebase.database.Query;
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
    private HashMap<AccountData,Chat>chathash;
    private DatabaseReference fireRef;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void ItemClick(int position);
    }

    public chatViewAdapter(Context ctx,ArrayList<AccountData>acceptedUsers,HashMap<AccountData,Chat>chathash){
        this.ctx = ctx;
        this.acceptedUsers = acceptedUsers;
        this.chathash = chathash;
    }

    public chatViewHolder onCreateViewHolder(ViewGroup parent,int ViewType){
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_chat_item,parent,false);
        return new chatViewHolder(item,onItemClickListener);
    }


    public void onBindViewHolder(final chatViewHolder holder, final int position) {
        AccountData acc = acceptedUsers.get(position);
        holder.Username.setText(acc.getUsername());
        if (acc.getPfp().equals("default")) {
            Log.v("ChatViewAdapter","Setting default image" );
            holder.ProfilePicture.setImageResource(R.mipmap.ic_launcher);
        } else {
            Log.v("ChatViewAdapter","Setting profile picture" );
            Glide.with(ctx).load(acc.getPfp()).into(holder.ProfilePicture);
        }
        //add last message and last time
        if (chathash.get(acc) != null) {
            //get last message
            int count = 0;
            String lastmessage = chathash.get(acc).lastMessage.getMessage();
            StringBuilder tempMessage = new StringBuilder();
            for (int i = 0; i < lastmessage.length();i++){
                if(count < 20) {
                    count++;
                    tempMessage.append(lastmessage.charAt(i));
                }
                else{break;}
            }
            if (count >= 20){
                tempMessage.append("...");
                holder.lastMessage.setText(tempMessage);
            }
            else{
                holder.lastMessage.setText(chathash.get(acc).lastMessage.getMessage());

            }
            //get last time
            holder.lastTime.setText(DateUtils.formatDateTime(ctx,chathash.get(acc).getLastMessage().getCreatedAt(),DateUtils.FORMAT_SHOW_TIME));

        }

    }

    @Override
    public int getItemCount() {
        return acceptedUsers.size();
    }
}



