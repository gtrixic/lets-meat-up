package lets.meat.up;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<lets.meat.up.MessageViewHolder> {
    private Context ctx;
    private lets.meat.up.LMUDBHandler dbhandler;
    //values to detect if message is to be on the left or right
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    private ArrayList<lets.meat.up.Message> mChat;

    public MessageAdapter(Context ctx,ArrayList<lets.meat.up.Message> mChat){
        //params
        this.ctx = ctx;
        dbhandler = new lets.meat.up.LMUDBHandler(ctx);
        this.mChat = mChat;
    }

    @NonNull
    @Override
    public lets.meat.up.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //receive view types and set them either left or right , or center if system message
        View view;
        Log.v("MessageAdapter","Creating view!");

        if(viewType == MSG_TYPE_RIGHT){
            //inflate message sent layout
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent,parent,false);
            return new lets.meat.up.MessageViewHolder(view,ctx);
        }
        else if(viewType == MSG_TYPE_LEFT){
            //inflate message receive layout
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received,parent,false);
            return new lets.meat.up.MessageViewHolder(view,ctx);
        }
        else if(viewType == -1){
            //inflate system message layout
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_system,parent,false);
            return new lets.meat.up.MessageViewHolder(view,ctx);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull lets.meat.up.MessageViewHolder holder, int position) {
        //runs the holder's bind function
        lets.meat.up.Message userMessage = mChat.get(position);
        holder.bind(userMessage);
        }


    @Override
    public int getItemCount() {
        return mChat.size();
    }

    @Override
    public int getItemViewType(int position) {
        //checking if the message's sender is the current user
        lets.meat.up.Message message = mChat.get(position);
        if(message.getSender().equals(dbhandler.getUserDetail(ctx,"id"))){
            return MSG_TYPE_RIGHT;

        }
        else if(message.getSender().equals("System")){
            return -1;
        }
        else{

            return MSG_TYPE_LEFT;
        }
    }

}
