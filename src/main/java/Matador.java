import Controllers.*;

import java.io.IOException;

public class Matador {
    static FieldController fieldController;

    static {
        try {
            fieldController = new FieldController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static GuiController gui = new GuiController();
    static PlayerController playerController = new PlayerController();
    static ChanceDeck chanceDeck;

    static {
        try {
            chanceDeck = new ChanceDeck();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Cup cup = new Cup(6);
    static int playerCount;
    static boolean canEndTurn;
    static boolean endTurn;
    static int extraTurns;

    public static void main(String[] args) throws IOException {
        playerCount = gui.getPlayerAmount();
        playerController.createPlayers(playerCount);
        gui.createCars(playerCount);
        gui.createPlayers(playerController.getPlayers().length, 30000, playerController);
        while (gameOngoing(playerController)){
            turn();
        }
    }
    public static void turn(){
        canEndTurn = false;
        endTurn = false;
        extraTurns = 0;
        gui.msg("It is now " + playerController.getCurrentPlayer().getName() +"'s turn");
        while (!endTurn){
            gui.updateGuiPlayerBal(playerController);
            gui.updateCarPos(playerController);
            switch (gui.selectAction(canEndTurn)){
                case "Roll die":
                    roll();
                    break;
                case "Upgrade Property":
                    UpgradeProperty();
                    break;
                case "Trade Deed":
                    tradeDeed();
                    break;
                case "Sell House":
                    sellHouse();
                    break;
                case "Mortgage":
                    Mortgage();
                    break;
                case "Un mortgage":
                    unMortgage();
                    break;
                case "End Turn":
                    endTurn = true;
                    break;
            }
        }
        playerController.updateCurrentPlayer();
    }
    public static void roll(){
        int roll = cup.rollCup();
        if (cup.getDie1Value() == cup.getDie2Value()){
            extraTurns++;
        }else {
            canEndTurn = true;
        }
        if (extraTurns >= 3){
            playerController.getCurrentPlayer().setPos(10);
            endTurn = true;
        }
        else{
            //gui.rollMsg("Roll the dies");
            playerController.getCurrentPlayer().updatePos(roll);
            gui.updateCarPos(playerController);
            gui.showDice(cup);
            fieldController.doFieldAction(playerController);
        }
    }
    public static void UpgradeProperty(){

    }
    public static void buyDeed(){

    }
    public static void sellHouse(){

    }
    public static void Mortgage(){

    }
    public static void unMortgage(){

    }
    public static void tradeDeed(){

    }
    public void auction(){

    }
    public void leaveJail(){

    }
    public static boolean gameOngoing(PlayerController playerController){
        boolean gaming = true;
        for (int i = 1; i <= playerController.getPlayers().length; i++) {
            if (playerController.getPlayers()[i-1].getBalance()<=0){
                gaming = false;
            }
        }
        return gaming;
    }
}
