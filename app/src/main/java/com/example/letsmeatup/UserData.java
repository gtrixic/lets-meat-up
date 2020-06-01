package com.example.letsmeatup;

import android.provider.ContactsContract.CommonDataKinds.Email;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Spinner;

import java.lang.annotation.Target;
import java.sql.Date;

public class UserData {
    private String fullname;
    private String username;
    private String password;
    private Email email;
    private Spinner gender;
    private Date dob;
    private String sp;

    public UserData(){}
    public UserData(String fullname, String username, String password,
                    Email email, Spinner gender, Date dob, String sp){
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.sp = sp;

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Spinner getGender() {
        return gender;
    }

    public void setGender(Spinner gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    //Method for checking if email is valid or empty
    public static boolean isValidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target)&& Patterns.EMAIL_ADDRESS.matcher(target).matches());

    }
    //Method for checking if both passwords are the same
    public static boolean isPasswordMatch(String password, String checkpassword){
        if (password.equals(checkpassword)){
            return true;
        }
        return false;
    }
}
