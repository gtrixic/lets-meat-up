package com.example.letsmeatup;

public class RestaurantData {
    private String Name;
    private String Address;
    private String Email;
    private String PfpLink;

    public RestaurantData(){}
    public RestaurantData(String n, String a, String e, String p){
        this.Name = n;
        this.Address = a;
        this.Email = e;
        this.PfpLink = p;
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
}
