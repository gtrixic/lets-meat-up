package lets.meat.up;

//components for chat
public class Chat {
    String users;
    String id;
    lets.meat.up.Message lastMessage;

    public Chat(){}

    //getters and setters
    public String getUsers(){return this.users;}
    public void setUsers(String users){this.users = users;}
    public String getId(){return this.id;}
    public void setId(String id){this.id = id;}
    public lets.meat.up.Message getLastMessage(){return this.lastMessage;}
    public void setLastMessage(lets.meat.up.Message lastMessage){this.lastMessage= lastMessage;}
}
