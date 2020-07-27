package lets.meat.up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Let's-Meat-Up";
    private String FILENAME = "MainActivity.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onStart(){ // shows the app logo while moving to the signup or login page
        super.onStart();
        final Intent transition = new Intent(MainActivity.this, lets.meat.up.SignupOrLoginActivity.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Log.v(TAG,"Going to sign up or login page");
                startActivity(transition);
                finish();
            }
        }, 3000);   //3 seconds transition

    }
}
