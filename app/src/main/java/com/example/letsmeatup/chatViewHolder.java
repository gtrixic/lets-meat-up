package com.example.letsmeatup;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class chatViewHolder extends RecyclerView.ViewHolder {
    ImageView ProfilePicture;
    TextView Username;
    TextView lastMessage;
    TextView lastTime;

    public chatViewHolder(View view, final chatViewAdapter.OnItemClickListener onItemClickListener){
        super(view);
        ProfilePicture = view.findViewById(R.id.CIPfp);
        Username = view.findViewById(R.id.CIUsername);
        lastMessage = view.findViewById(R.id.CIlastmessage);
        lastTime = view.findViewById(R.id.CILastTime);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    int position = getAdapterPosition();
                    onItemClickListener.ItemClick(position);
                }
            }
        });
    }

}
