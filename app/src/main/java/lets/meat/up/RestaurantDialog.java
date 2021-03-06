package lets.meat.up;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RestaurantDialog extends Dialog implements android.view.View.OnClickListener {
    public Context c;
    public Dialog d;
    public TextView name;
    public ImageView image;
    public TextView addr;
    public TextView type;
    public Button add;
    public Button reject;
    lets.meat.up.LMUDBHandler handler;
    lets.meat.up.RestaurantData RestData;
    String restChatID;
    //values
    private String rName;
    private String rAddr;
    private String rType;
    private String rUrl;
    //custom get random restaurant dialog
    public RestaurantDialog(Context a, lets.meat.up.RestaurantData rData, String chatID ){
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
        setContentView(R.layout.restaurant_dialog);
        reject = findViewById(R.id.rejectRestButton);
        reject.setOnClickListener(this);
        add = findViewById(R.id.addRestButton);
        add.setOnClickListener(this);
        name = findViewById(R.id.restName);
        image = findViewById(R.id.restPfp);
        addr = findViewById(R.id.restAddress);
        type = findViewById(R.id.restType);
        name.setText(rName);
        addr.setText(rAddr);
        type.setText(rType);
        Glide.with(c).load(rUrl).placeholder(R.mipmap.ic_launcher).dontAnimate().into(image);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addRestButton:
                handler.addRestaurant(RestData,restChatID);
                dismiss();
                break;
            case R.id.rejectRestButton:
                dismiss();
                break;
            default:
                break;
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
}
