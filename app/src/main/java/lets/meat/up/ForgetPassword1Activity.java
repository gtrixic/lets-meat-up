package lets.meat.up;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ForgetPassword1Activity extends AppCompatActivity {
    public static final String TAG = "Let's-Meat-Up";
    String FILENAME = "ForgetPassword1Activity.java";
    EditText enterOnce;
    EditText enterTwice;
    ImageButton goNext;
    ImageButton back;
    lets.meat.up.LMUDBHandler dbHandler = new lets.meat.up.LMUDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password1);
        Intent recData = getIntent(); //get the user input from 1st forget password page
        final String input = recData.getStringExtra("input");
        goNext = findViewById(R.id.goNext);
        back = findViewById(R.id.backArrow6);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPassword1Activity.this, lets.meat.up.ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        //after entering password twice and clicking next
        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enterOnce = findViewById(R.id.enterPassword);
                enterTwice = findViewById(R.id.reenterPassword);
                if(checkBlank(enterOnce.getText().toString(),enterTwice.getText().toString())) {
                    if (checkSimilarity(enterOnce.getText().toString(), enterTwice.getText().toString())) { //if the 2 passwords are identical
                        //update password here
                        Intent intent = nextPage();
                        dbHandler.updatePassword(input, enterOnce.getText().toString(), ForgetPassword1Activity.this,intent);
                    } else {
                        Toast.makeText(ForgetPassword1Activity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(ForgetPassword1Activity.this, "Not all inputs have been filled.", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        System.exit(0);
                    }
                }).create().show();
    }
    public boolean checkSimilarity(String once, String twice){ //to check if the 2 password inputs are the same
        if(once.equals(twice)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkBlank(String once, String twice){
        if(once == null || twice == null){
            return false;
        }
        return true;
    }
    public Intent nextPage(){ //from this page to the successful password change page
        Intent next = new Intent(ForgetPassword1Activity.this, lets.meat.up.ForgetPassword2Activity.class);
        next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        return next;
    }
}