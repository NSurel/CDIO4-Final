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
    static ChanceDeck chanceDeck = new ChanceDeck();
    static Cup cup = new Cup(6);
    static int playerCount;
    static boolean haveRolled;
    static boolean endTurn;

    public static void main(String[] args) throws IOException {
        gui.fixAllPrices(fieldController);
        playerCount = gui.getPlayerAmount();
        playerController.createPlayers(playerCount);
        gui.createCars(playerCount);
        gui.createPlayers(playerController.getPlayers().length, 30000, playerController);
        while (gameOngoing(playerController)){
            turn();
        }
    }
    public static void turn(){
        haveRolled = false;
        endTurn = false;
        gui.msg("It is now " + playerController.getCurrentPlayer().getName() +"'s turn");
        while (!endTurn){
            gui.updateGuiPlayerBal(playerController);
            gui.updateCarPos(playerController);
            switch (gui.selectAction(haveRolled)){
                case "Roll die":
                    roll();
                    break;
                case "Upgrade Property":
                    UpgradeProperty();
                    break;
                case "Buy Deed":
                    buyDeed();
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
        //gui.rollMsg("Roll the dies");
        playerController.getCurrentPlayer().updatePos(cup.rollCup());
        gui.updateCarPos(playerController);
        gui.showDice(cup);
        haveRolled = true;
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
