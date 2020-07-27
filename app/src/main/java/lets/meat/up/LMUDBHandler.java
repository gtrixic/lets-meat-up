package lets.meat.up;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class LMUDBHandler {
    private String TAG = "Let's-Meat-Up";
    private String FILENAME = "LMUDBHandler.java";
    private static String PREF_NAME = "prefs";
    private DatabaseReference fireRef;
    private Context ctx;


    public LMUDBHandler(Context context){
        super();
        this.ctx = context;

    }



    //add match param
    public void addMatchID(String[] matchID,String id){
        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");
        //convert matchid to string
        StringBuffer matchid = new StringBuffer();
        for (int i = 0; i < matchID.length;i++){
            matchid.append(matchID[i]);
        }
        //point to current user
        fireRef.child(id).child("matchid").setValue(matchid.toString());

    }

    private static SharedPreferences getPrefs(Context context){
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
    //function called in forget password 1
    public void updatePassword(String email, final String password, final Context ctx, final Intent intent){
        //change in fireauth
        //create loading dialog
        final lets.meat.up.LoadingDialog loadingDialog = new lets.meat.up.LoadingDialog((Activity)ctx);
        loadingDialog.startLoadingDialog();
        //get current user from fireauth
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //get credentials for user
        AuthCredential credential = EmailAuthProvider.getCredential(email,getUserDetail(ctx,"password"));
        //reauth from new password
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //wait for task to be successful
                        if(task.isSuccessful()){
                            //update the user's password
                            user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //wait for task to be successful
                                    if(task.isSuccessful()){
                                        Log.v(TAG,"Password updated in mAuth!");
                                        //instantiate fireref
                                        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");
                                        //get account
                                        fireRef.child(getUserDetail(ctx,"id")).child("password").setValue(password)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        //close loading dialog
                                                        loadingDialog.dismissDialog();
                                                        //go to next activity
                                                        ctx.startActivity(intent);
                                                        Log.v(TAG,"Password updated in firebase db!");

                                                    }
                                                });
                                    }
                                    else{
                                        //close dialog
                                        loadingDialog.dismissDialog();
                                        Log.v(TAG,"Password failed to update.");
                                        Toast.makeText(ctx,"Failed to update password, try again later.",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Log.v(TAG,"Error updating password!");
                        }
                    }
                });
    }

    public void saveUser(Context context, lets.meat.up.AccountData account){
        //saving account data into shared pref
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("username",account.getUsername());
        editor.putString("email",account.getEmail());
        editor.putString("id",account.getID());
        editor.putString("fullname",account.getFullName());
        editor.putString("password",account.getPassword());
        editor.putString("gender",account.getGender());
        editor.putString("dob",account.getDob());
        editor.putString("matchid",account.getMatchid());
        editor.putString("allergy",account.getAllergy());
        editor.putString("diet",account.getDiet());
        editor.putString("pfp",account.getPfp());
        editor.putString("pending",account.getpendinguserlist());
        editor.putString("confirmed",account.getconfirmeduserlist());
        editor.apply();
        Log.v(TAG,"Shared Preference set for user!");
    }

    public lets.meat.up.AccountData returnUser(Context ctx){
        //get all data from shared pref
        String id = getPrefs(ctx).getString("id","default_username");
        String fullname = getPrefs(ctx).getString("fullname","default_name");
        String username = getPrefs(ctx).getString("username","default_username");
        String password = getPrefs(ctx).getString("password","def");
        String email = getPrefs(ctx).getString("email","email");
        String gender = getPrefs(ctx).getString("gender","gender");
        String dob = getPrefs(ctx).getString("dob","dob");
        String pfp = getPrefs(ctx).getString("pfp","default");
        String matchid = getPrefs(ctx).getString("matchid","id");
        String allergy = getPrefs(ctx).getString("allergy","allergy");
        String diet = getPrefs(ctx).getString("diet","diet");
        String pending = getPrefs(ctx).getString("pending",null);
        String confirmed = getPrefs(ctx).getString("confirmed",null);
        return new lets.meat.up.AccountData(id,fullname,username,password,email,gender,dob,pfp,matchid,allergy,diet,pending,confirmed);
    }

    public void stayLogin(Context ctx,boolean val){
        //adds value for whether user wants to stay logged in or not
        SharedPreferences.Editor editor = getPrefs(ctx).edit();
        editor.putBoolean("login",val);
        editor.apply();
    }
    public boolean getLogin(Context ctx){
        //get stay logged in value
        return getPrefs(ctx).getBoolean("login",false);
    }
    public boolean checkLoginstatus(Context ctx){
        //check if stayloggedin value is in shared pref

        return getPrefs(ctx).contains("login");
    }
    public void signOut(Context ctx){
        //clear shared pref
        SharedPreferences.Editor editor = getPrefs(ctx).edit();
        editor.clear();
        editor.apply();
    }

   public String getUserDetail(Context ctx,String inputype){//return the current user's Account information
        switch(inputype){
            case "username":
                return getPrefs(ctx).getString("username","default_username");
            case "email":
                return getPrefs(ctx).getString("email","default_email");
            case "id":
                String id = getPrefs(ctx).getString("id","def");
                return id;
            case "password":
                String password = getPrefs(ctx).getString("password","def");
                return password;
            case "name":
                String name = getPrefs(ctx).getString("fullname","def");
                return name;
            case "age":
                String age = getPrefs(ctx).getString("age", "def");
                return age;
            case "gender":
                String gender = getPrefs(ctx).getString("gender", "def");
                return gender;
            case "dob":
                String dob = getPrefs(ctx).getString("dob", "def");
                return dob;
        }
        return null;
    }

    public void addAllergies(String[] allergystringarray,Context ctx){
        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");

        //unpack array
        String allergyString;
        String dietString;
        //if allergy string is empty change to "None"
        if(allergystringarray[0].length() == 0){
            allergyString = "None";
        }
        else{
            allergyString = allergystringarray[0];
        }
        if(allergystringarray[1].equals("Diet")){
            dietString = "None";
        }
        else{
            dietString = allergystringarray[1];
        }
        //Post to firebase
        //Query id
        String stringid = getUserDetail(ctx,"id");
        fireRef.child(stringid).child("allergy").setValue(allergyString);
        fireRef.child(stringid).child("diet").setValue(dietString);
        //Log
        Log.v(TAG,"Allergy field added!");
    }

    public void uploadImage(final Context ctx, Uri FilePath, StorageReference storageReference, final Intent intent){
        //create firebase reference
        fireRef = FirebaseDatabase.getInstance().getReference().child("Users");
        //checks if URI is null
        if(FilePath != null){
            Log.v(TAG,"Creating Progress Dialog.");
            //creating progress dialog
            final ProgressDialog progressDialog = new ProgressDialog(ctx);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            //get current user id
            Log.v(TAG,"Getting User ID.");
            final String stringid = getUserDetail(ctx,"id");
            //creates new reference to the id
            Log.v(TAG,"Creating storage reference.");
            final StorageReference ref = storageReference.child("User_Pictures/"+stringid);
            Log.v(TAG,"Checking if file exists");
            //check if file already exists in database
            if(!returnUser(ctx).getPfp().equals("default")){
                ref.delete();
                Log.v("TAG","File Found! deleting...");
            }
            //put file into ref
            ref.putFile(FilePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismiss progress dialog on success
                            progressDialog.dismiss();
                            //get down url and store into firebase db
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //returns uri into pfp
                                    fireRef.child(returnUser(ctx).getID()).child("pfp").setValue(uri.toString());
                                    Toast.makeText(ctx,"Uploaded image!",Toast.LENGTH_SHORT).show();
                                    //save into shared pref
                                    SharedPreferences.Editor editor = getPrefs(ctx).edit();
                                    editor.putString("pfp",uri.toString());
                                    editor.apply();
                                    //checks if there is an intent to go to
                                    if(intent != null) {
                                        ctx.startActivity(intent);
                                    }
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ctx,"Failed "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
        else {
            if(intent!= null) {
                ctx.startActivity(intent);
            }
        }
    }
    //read data is used to wait for data to be returned, then do something
    public void readData(DatabaseReference ref, final OnGetDataListener listener) {
        listener.onStart();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onFailure();
            }
        });
    }
    public interface OnGetDataListener {
        //this is for callbacks
        void onSuccess(DataSnapshot dataSnapshot);
        void onStart();
        void onFailure();
    }
    //add restaurant function to store into chat's suggestion list
    public void addRestaurant(lets.meat.up.RestaurantData rData, String chatid){
        String key = FirebaseDatabase.getInstance().getReference().child("Chats").child(chatid).child("Suggestions").push().getKey();
        fireRef = FirebaseDatabase.getInstance().getReference().child("Chats").child(chatid).child("Suggestions").child(key);
        fireRef.setValue(rData);
    }


}
