package lets.meat.up;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class pnViewHolder extends RecyclerView.ViewHolder {
    TextView txt;
    Button ans1;
    Button ans2;
    TextView answerdisplay;

    public pnViewHolder(View view)
    {
        super(view);
        txt = view.findViewById(R.id.question);
        ans1 = view.findViewById(R.id.button1);
        ans2 = view.findViewById(R.id.button2);
        answerdisplay = view.findViewById(R.id.userAns);

    }
}
