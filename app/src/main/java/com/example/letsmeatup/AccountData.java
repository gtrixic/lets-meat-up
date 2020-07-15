package com.example.letsmeatup;

import android.content.SharedPreferences;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Spinner;

import java.lang.annotation.Target;
import java.sql.Date;

public class AccountData {
    //storing all the AccountData information
    private String id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String dob;
    private String sp;
    private String matchid;

    public AccountData(){}
    public AccountData(String id,String fullName, String username, String password,
                       String email, String gender, String dob,String matchid){
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.matchid = matchid;

    }
    public String getID(){return this.id;}

    public void setID(String id){this.id = id;}

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullname) {
        this.fullName = fullname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMatchid() {
        return matchid;
    }

    public void setMatchid(String matchid) {
        this.matchid = matchid;
    }

    //Method for checking if email is valid or empty
    public static boolean isValidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target)&& Patterns.EMAIL_ADDRESS.matcher(target).matches());

    }
    //Method for checking if both passwords are the same
    public boolean isPasswordMatch(String password2){
        if (this.password.equals(password2)){
            return true;
        }
        return false;
    }


}
