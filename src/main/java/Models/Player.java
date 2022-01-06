package Models;

public class Player {

    private String name;
    private int playerID;
    private int pos;
    private int balance;

    public Player(String name, int playerID){
        this.name = name;
        this.playerID = playerID;
        this.pos = 0;
        this.balance = 30000;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
    public int getPlayerID(){
        return playerID;
    }
    public int getPos(){
        return pos;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public int getBalance(){
        return balance;
    }
    public void updateBalance(int amount){
        balance += amount;
    }
}
