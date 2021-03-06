package Models;

public class Player {

    private String name;
    private int playerID;
    private int pos;
    private int balance;
    private int netWorth;
    private int timeInJail;
    private boolean isJailed;
    private boolean isBroke;
    private boolean hasGetOutOfJailCard;

    public Player(String name, int playerID){
        this.name = name;
        this.playerID = playerID;
        this.pos = 0;
        this.balance = 30000;
        this.netWorth = balance;
        this.timeInJail = 0;
        this.isJailed = false;
        this.isBroke = false;
        this.hasGetOutOfJailCard = false;
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
    public void setPos(int value){
        if (value == 10){
            isJailed = true;
        }else if (value<pos){
            updateBalance(4000);
        }
        pos = value;
    }
    public void updatePos(int value){
        pos += value;
        if (pos >= 40){
            pos = pos % 40;
            updateBalance(4000);
        }

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
    public void setIsJailed(boolean jailed) {
        isJailed = jailed;
    }
    public boolean isJailed() {
        return isJailed;
    }
    public void UpdateIsJailed(){
        isJailed =! isJailed;
    }
    public int getNetWorth(){
        return netWorth;
    }
    public void setNetWorth(int netWorth) {
        this.netWorth = netWorth;
    }
    public void updateNetWorth(int amount){
        netWorth = balance + amount;
    }
    public boolean getIsBroke(){
        return isBroke;
    }
    public void isBroke(){
        isBroke =! isBroke;
    }
    public void updateGetOutOfJailCard(boolean hasGetOutOfJailCard){
        this.hasGetOutOfJailCard = hasGetOutOfJailCard;
    }
    public boolean getOutOfJailCard(){
        return hasGetOutOfJailCard;
    }
}
