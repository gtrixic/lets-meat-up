package lets.meat.up;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;

public class chatViewAdapter extends RecyclerView.Adapter<lets.meat.up.chatViewHolder> {
    private Context ctx;
    private ArrayList<lets.meat.up.AccountData> acceptedUsers;
    private OnItemClickListener onItemClickListener;
    private HashMap<lets.meat.up.AccountData, lets.meat.up.Chat>chathash;
    private DatabaseReference fireRef;

    //onitemclicklistener for each item
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    //abstract method for item click
    public interface OnItemClickListener{
        void ItemClick(int position);
    }

    //params
    public chatViewAdapter(Context ctx, ArrayList<lets.meat.up.AccountData>acceptedUsers, HashMap<lets.meat.up.AccountData, lets.meat.up.Chat>chathash){
        this.ctx = ctx;
        this.acceptedUsers = acceptedUsers;
        this.chathash = chathash;
    }

    public lets.meat.up.chatViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_chat_item,parent,false);
        return new lets.meat.up.chatViewHolder(item,onItemClickListener);
    }


    public void onBindViewHolder(final lets.meat.up.chatViewHolder holder, final int position) {
        //for each account data, pull the data and apply to the holder components
        lets.meat.up.AccountData acc = acceptedUsers.get(position);
        holder.Username.setText(acc.getUsername());
        //get pfp will either return "default" or a link
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



