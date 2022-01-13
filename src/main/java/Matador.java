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
    static DeedController deedController = new DeedController();
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
    static boolean haveRolled;
    static boolean endTurn;

    public static void main(String[] args) throws IOException {
        gui.fixAllPrices(fieldController);
        playerCount = gui.getPlayerAmount();
        playerController.createPlayers(playerCount, gui);
        gui.createCars(playerCount);
        gui.createPlayers(playerController.getPlayers().length, playerController);
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
        //gui.rollMsg("Roll the dies");
        playerController.getCurrentPlayer().updatePos(cup.rollCup());
        gui.updateCarPos(playerController);
        gui.showDice(cup);
        fieldController.doFieldAction(playerController);
        boolean answer = gui.yesOrNo("Do you want to buy this property?");
        if (answer){

        }
        haveRolled = true;
    }
    public static void UpgradeProperty(){
        String propertyName = gui.getPlayernameOrPropertyName("property");


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
        String otherPlayerName = gui.getPlayernameOrPropertyName("player");
        if (isOtherPlayerName(otherPlayerName)){
            playerController.getPlayer(otherPlayerName);

        } else {
            gui.msg("That player doesn't exist");
        }

    }
    public void auction(){
        String buyingPlayerName = gui.getPlayernameOrPropertyName("player");
        if(isOtherPlayerName(buyingPlayerName)){
            playerController.getPlayer(buyingPlayerName).updateBalance(-fieldController.getFieldPrice(playerController.getCurrentPlayer().getPos()));
            // Need to find the proper way to get the deed for the field the current player landed on
            //deedController.getProperties()[playerController.getCurrentPlayer().getPos()].setOwner(playerController.getPlayer(buyingPlayerName).getPlayerID());
        } else{
            gui.msg("That player doesn't exist");
        }


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
    public static boolean isOtherPlayerName(String name){
        boolean otherPlayerName = false;
        for (int i = 0; i < playerController.getPlayers().length; i++) {

            if (playerController.getPlayers()[i].getName().equals(name)){
                otherPlayerName = true;
            }
        }{
        }
        return otherPlayerName;
    }
}
