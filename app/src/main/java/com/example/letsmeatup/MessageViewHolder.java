package com.example.letsmeatup;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import okhttp3.internal.Util;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    Context ctx;
    public TextView message;
    public TextView createdAt;
    public MessageViewHolder(View view, Context ctx){
        super(view) ;
        this.ctx = ctx;
        message = view.findViewById(R.id.text_message_body);
        createdAt = view.findViewById(R.id.text_message_time);
    }
    void bind(Message userMessage){
        message.setText(userMessage.getMessage());

        //format timestamp
        createdAt.setText(DateUtils.formatDateTime(ctx,userMessage.getCreatedAt(),DateUtils.FORMAT_SHOW_TIME));
    }
}
