package lets.meat.up;

public class RestaurantData {
    //store RestaurantData information
    private String Name;
    private String Address;
    private String Category;
    private String PfpLink;

    public RestaurantData(){}
    public RestaurantData(String n, String a,String c, String pf){
        this.Name = n;
        this.Address = a;
        this.Category = c;
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
    public String getCategory(){return this.Category;}
    public void setCategory(String c){this.Category = c;}
    public String getPfpLink(){
        return this.PfpLink;
    }
    public void setPfpLink(String p){
        this.PfpLink = p;
    }

}
