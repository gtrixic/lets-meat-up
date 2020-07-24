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
    private String pfp;
    private String matchid;
    private String allergy;
    private String diet;
    private String pendinguserlist;
    private String confirmeduserlist;

    public AccountData(){}

    public AccountData(String i, String f, String u, String p, String e, String g, String d, String pf, String m,String a,String di,String pe,String c){
        this.id = i;
        this.fullName = f;
        this.username = u;
        this. password = p;
        this.email = e;
        this.gender = g;
        this.dob = d;
        this.pfp = pf;
        this.matchid = m;
        this.allergy = a;
        this.diet = di;
        this.pendinguserlist = pe;
        this.confirmeduserlist = c;
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

    public String getPfp(){return this.pfp;}

    public void setPfp(String pfp){this.pfp = pfp;}

    public String getMatchid() {
        return this.matchid;
    }

    public void setMatchid(String matchid) {
        this.matchid = matchid;
    }

    public String getAllergy(){return this.allergy;}

    public void setAllergy(String allergy){this.allergy = allergy;}

    public String getDiet(){return this.diet;}

    public void setDiet(String diet){this.diet = diet;}

    public String getpendinguserlist(){return this.pendinguserlist;}

    public void setpendinguserlist(String pending){this.pendinguserlist = pending;}

    public String getconfirmeduserlist(){return this.confirmeduserlist;}

    public void setconfirmeduserlist(String confirmed){this.confirmeduserlist = confirmed;}

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
