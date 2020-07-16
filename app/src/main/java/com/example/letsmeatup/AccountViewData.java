package com.example.letsmeatup;

public class AccountViewData extends AccountData {
    //fields
    private String allergy;
    private String diet;
    //Constructor
    public AccountViewData(){}
    //get and setters
    public String getAllergy(){return allergy;}

    public void setAllergy(String allergy){this.allergy = allergy;}

    public String getDiet(){return diet;}

    public void setDiet(String diet){this.diet = diet;}
}



