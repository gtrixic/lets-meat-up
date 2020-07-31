package lets.meat.up;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class auAdapter extends RecyclerView.Adapter<lets.meat.up.auViewHolder> {
    private Context ctx;
    private ArrayList<lets.meat.up.AccountData> userRequest;
    lets.meat.up.AccountData currentUser;
    private OnItemClickListener onItemClickListener;

    DatabaseReference fireRef = FirebaseDatabase.getInstance().getReference().child("Users");


    public void setOnItemClickListener(OnItemClickListener onItem) {
        this.onItemClickListener = onItem;
    }

    public interface OnItemClickListener{
        void ItemClick(int position);
    }

    public auAdapter(Context ctx, ArrayList<lets.meat.up.AccountData> users, lets.meat.up.AccountData current)
    {
        this.ctx = ctx;
        this.userRequest = users;
        this.currentUser = current;
    }

    public lets.meat.up.auViewHolder onCreateViewHolder (ViewGroup parent, int ViewType)
    {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_view_user_requests, parent, false);
        return new lets.meat.up.auViewHolder(item, onItemClickListener);
    }

    public void onBindViewHolder (final lets.meat.up.auViewHolder holder, final int position)
    {
        // create pending user account
        final lets.meat.up.AccountData secondUser = userRequest.get(position);
        holder.username.setText(secondUser.getUsername());
        // if user does not have a profile picture
        if(secondUser.getPfp().equals("default")){
            holder.profilePic.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(ctx).load(secondUser.getPfp()).into(holder.profilePic);
        }
        // on click listener for confirm of pending user
        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                // create list for confirm users
                List<String> currentUserConfirmed;
                List<String> secondUserConfirmed;

                // if confirm user list is empty
                if (currentUser.getconfirmeduserlist().equals("")){
                    currentUserConfirmed = new ArrayList<>();
                }
                else{
                    // split into arraylist
                    currentUserConfirmed = Arrays.asList(currentUser.getconfirmeduserlist().split(","));
                    currentUserConfirmed = new ArrayList<>(currentUserConfirmed);

                }
                // if other user confirm user list is empty
                if (secondUser.getconfirmeduserlist().equals("")){

                    secondUserConfirmed = new ArrayList<>();
                }
                else{
                    // split into arraylist
                    secondUserConfirmed = Arrays.asList(secondUser.getconfirmeduserlist().split(","));
                    secondUserConfirmed = new ArrayList<>(secondUserConfirmed);
                }

                // add each other's id
                currentUserConfirmed.add(secondUser.getID());
                secondUserConfirmed.add(currentUser.getID());
                //Convert list to string
                String currentUserList;
                String secondUserList;
                // if list got more than 1
                if(currentUserConfirmed.size() > 1) {
                    String delimiter = ",";
                    String result = "", prefix = "";
                    for (String s: currentUserConfirmed) {
                        result += prefix + s;
                        prefix = delimiter;
                    }
                    currentUserList = result;
                }
                else{
                    currentUserList = currentUserConfirmed.get(0);
                }
                // if list got more than 1
                if(secondUserConfirmed.size() > 1) {
                    String delimiter = ",";
                    String result = "", prefix = "";
                    for (String s: secondUserConfirmed) {
                        result += prefix + s;
                        prefix = delimiter;
                    }
                    secondUserList = result;
                }
                else{
                    secondUserList = secondUserConfirmed.get(0);
                }

                // push to database
                fireRef.child(currentUser.getID()).child("confirmeduserlist").setValue(currentUserList);
                fireRef.child(secondUser.getID()).child("confirmeduserlist").setValue(secondUserList);
                deletePending(position, secondUser);
                lets.meat.up.LMUDBHandler lmudbHandler = new lets.meat.up.LMUDBHandler(ctx);
                currentUser.setconfirmeduserlist(currentUserList);
                lmudbHandler.saveUser(ctx,currentUser);
            }
        });
        // decline request, remove from pending list
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
    private void deletePending(final int position, lets.meat.up.AccountData user) {
        List<String> currentUserPending;
        userRequest.remove(position);
        notifyDataSetChanged();
        String pending = currentUser.getpendinguserlist();
        if(pending.contains(",")){
            currentUserPending = new ArrayList(Arrays.asList(pending.split(",")));
        }
        else{
            currentUserPending = new ArrayList<>();
            currentUserPending.add(pending);
        }
        currentUserPending.remove(user.getID());
        //Convert to string
        String currentUserList = String.join(",",currentUserPending);
        fireRef.child(currentUser.getID()).child("pendinguserlist").setValue(currentUserList);
        lets.meat.up.LMUDBHandler lmudbHandler = new lets.meat.up.LMUDBHandler(ctx);
        currentUser.setpendinguserlist(currentUserList);
        lmudbHandler.saveUser(ctx,currentUser);

    }

}
