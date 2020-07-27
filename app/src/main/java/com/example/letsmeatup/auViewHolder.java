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

    public auViewHolder(View view, final auAdapter.OnItemClickListener onItemClickListener)
    {
        super(view);
        profilePic = view.findViewById(R.id.profilePic);
        username = view.findViewById(R.id.username);
        confirm = view.findViewById(R.id.confirmButton);
        delete = view.findViewById(R.id.deleteButton);

        // on click for profile
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                {
                    int position = getAdapterPosition();
                    onItemClickListener.ItemClick(position);
                }
            }
        });
    }
}
