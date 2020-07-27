package lets.meat.up;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RestaurantProfileDialog extends Dialog implements android.view.View.OnClickListener {
    public Context c;
    public Dialog d;
    public TextView name;
    public ImageView image;
    public TextView addr;
    public TextView type;
    public Button directions;
    lets.meat.up.LMUDBHandler handler;
    lets.meat.up.RestaurantData RestData;
    String restChatID;
    //values
    private String rName;
    private String rAddr;
    private String rType;
    private String rUrl;
    //custom specific restaurant profile dialog
    public RestaurantProfileDialog(Context a, lets.meat.up.RestaurantData rData, String chatID){
        super(a);
        this.c = a;
        this.RestData = rData;
        this.restChatID = chatID;
        handler = new lets.meat.up.LMUDBHandler(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.restaurant_profile_dialog);
        directions = findViewById(R.id.directionButton);
        directions.setOnClickListener(this);
        name = findViewById(R.id.rProfileName);
        image = findViewById(R.id.rProfilePicture);
        addr = findViewById(R.id.rProfileAddr);
        type = findViewById(R.id.rProfileType);
        name.setText(rName);
        addr.setText(rAddr);
        type.setText(rType);
        Glide.with(c).load(rUrl).placeholder(R.mipmap.ic_launcher).dontAnimate().into(image);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.directionButton) {
            getDirections(rAddr);
            dismiss();
        }
        dismiss();
    }
    public void setImage(String url) {
        rUrl = (url);
    }
    public void setRestName(String rSetName){
        rName = (rSetName);
    }
    public void setRestAddr(String rSetAddr){
        rAddr = (rSetAddr);
    }
    public void setRestType(String rSetType){
        rType = (rSetType);
    }
    private void getDirections(String map){
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+map);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        c.startActivity(mapIntent);
    }
}

