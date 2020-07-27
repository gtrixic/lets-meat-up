package com.example.letsmeatup;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class suggestViewHolder extends RecyclerView.ViewHolder {
    TextView suggestedRest;

    public suggestViewHolder(View view,final suggestAdapter.OnItemClickListener onItemClickListener){
        super(view);
        suggestedRest = view.findViewById(R.id.suggestedName);
        //when restaurant is clicked
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
