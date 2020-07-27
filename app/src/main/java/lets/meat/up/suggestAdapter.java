package lets.meat.up;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class suggestAdapter extends RecyclerView.Adapter<suggestViewHolder>{
    private OnItemClickListener onItemClickListener;
    private ArrayList<RestaurantData> restList;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void ItemClick(int position);
    }

    public suggestAdapter(ArrayList<RestaurantData>restList) {
        this.restList = restList;
    }

    @NonNull
    @Override
    public suggestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_restaurant_item,parent,false);
        return new suggestViewHolder(item,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(final suggestViewHolder holder, int position) {
        RestaurantData rData = restList.get(position);
        holder.suggestedRest.setText(rData.getRestaurantName());
    }

    @Override
    public int getItemCount() {
        return restList.size();
    }
}
