package com.example.letsmeatup;

public class RestaurantData {
    private String Name;
    private String Address;
    private String Password;
    private String Email;
    private String PfpLink;

    public RestaurantData(){}
    public RestaurantData(String n, String a, String p, String e, String pf){
        this.Name = n;
        this.Address = a;
        this.Password = p;
        this.Email = e;
        this.PfpLink = pf;
    }
    public String getRestaurantName(){
        return this.Name;
    }
    public void setRestaurantName(String n){
        this.Name = n;
    }
    public String getAddress(){
        return this.Address;
    }
    public void setAddress(String a){
        this.Address = a;
    }
    public String getPassword(){
        return this.Password;
    }
    public void setPassword(String p){
        this.Password = p;
    }
    public String getEmail(){
        return this.Email;
    }
    public void setEmail(String e){
        this.Email = e;
    }
    public String getPfpLink(){
        return this.PfpLink;
    }
    public void setPfpLink(String p){
        this.PfpLink = p;
    }

    public boolean checkPassword(String p){
        if (this.Password.equals(p)) {
            return true;
        }
        return false;
    }
}
