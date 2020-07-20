package com.example.letsmeatup;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class auAdapter extends RecyclerView.Adapter<auViewHolder> {
    private Context ctx;
    private ArrayList<AccountData> userRequest;
    AccountData currentUser;
    String confirm;
    private OnItemClickListener onItemClickListener;

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
        final AccountData user = userRequest.get(position);
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
                if (currentUser.getConfirmed()==null){
                    currentUser.setConfirmed(user.getID());
                }
                else{
                    confirm = currentUser.getConfirmed()+","+user.getID();
                    currentUser.setConfirmed(confirm);
                }
                if (user.getConfirmed()==null){
                    user.setConfirmed(currentUser.getID());
                }
                else{
                    confirm = user.getConfirmed()+","+currentUser.getID();
                    user.setConfirmed(confirm);
                }
                deletePending(position, user);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePending(position, user);
            }
        });


    }

    public int getItemCount()
    {
        return userRequest.size();
    }

    private void deletePending(final int position, AccountData user) {

        userRequest.remove(position);
        notifyDataSetChanged();
        String pending = currentUser.getPending();
        if (pending.contains(","+user.getID()+","))
        {
            pending.replace(user.getID(), "");
            pending.replace(",,", ",");
            currentUser.setPending(pending);
        }
        else if (pending.contains(user.getID()+","))
        {
            pending.replace(user.getID()+",", "");
            currentUser.setPending(pending);
        }
        else if(pending.contains(","+user.getID()))
        {
            pending.replace(","+user.getID(), "");
            currentUser.setPending(pending);
        }
    }

}
