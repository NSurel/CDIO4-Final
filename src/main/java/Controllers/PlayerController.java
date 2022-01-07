package Controllers;

import Models.Player;

public class PlayerController {
    private Player[] players;

    public PlayerController(){

    }

    public void createPlayers(int size){
        players = new Player[size];
        for (int i = 0; i < size; i++) {
            players[i] = new Player("Player"+ (i + 1),i);
        }
    }

    public Player[] getPlayers() {
        return players;
    }
    public void updatePlayerBal(int value, Player player){
        player.updateBalance(value);
    }
}
