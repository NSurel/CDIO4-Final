package Controllers;

import Models.Player;

public class PlayerController {
    private Player[] players;
    private Player currentPlayer;

    public PlayerController(){

    }

    public void createPlayers(int size){
        players = new Player[size];
        for (int i = 0; i < size; i++) {
            players[i] = new Player("Player"+ (i + 1),i);
        }
        currentPlayer = players[0];
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
        player.setPos(value);
    }
    public void UpdateCurrentPlayer(){

    }

}
