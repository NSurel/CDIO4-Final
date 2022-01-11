package Controllers;

import Models.Player;

public class PlayerController {
    private Player[] players;
    private Player currentPlayer;
    private int turn = 0;

    public PlayerController(){

    }

    public void createPlayers(int size, GuiController gc){
        players = new Player[size];
        for (int i = 0; i < size; i++) {
            players[i] = new Player(gc.getUserString(),i);
        }
        currentPlayer = players[turn];
    }
    public void createPlayers(int size){
        players = new Player[size];
        for (int i = 0; i < size; i++) {
            players[i] = new Player("player" + (i+1),i);
        }
        currentPlayer = players[turn];
    }

    public Player[] getPlayers() {
        return players;
    }
    public void updatePlayerBal(int value, Player player){
        player.updateBalance(value);
    }
    public boolean isJailed(Player player){
        return player.isJailed();
    }
    public void updatePlayerPos(int value, Player player){
        player.updatePos(value);
    }
    public void setPlayerPos(int value, Player player){
        //Used to just move the player to jail
        player.setPos(value);
    }
    public void moveTo(int value, Player player){
        //Need codes to check for move over start field
        //And add 4000kr to the player balance
        player.setPos(value);
    }
    public void updateCurrentPlayer(){
        int temp = turn+1;
        turn = temp% players.length;
        currentPlayer = players[turn];
    }
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    public void updatePlayerNetWorth(int value, Player player){
        player.updateNetWorth(value);
    }
    public void setPlayerNetWorth(int value, Player player){
        player.setNetWorth(value);
    }
    public int getPlayerNetWorth(Player player){
        return player.getNetWorth();
    }
    public boolean isBroke(int value, Player player){
        if (value>player.getNetWorth()){
            player.isBroke();
        }
        return player.getIsBroke();
    }
    public void stealFromAll(int value){
        for(Player player : players){
            player.updateBalance(-value);
            currentPlayer.updateBalance(value);
        }
    }
}
