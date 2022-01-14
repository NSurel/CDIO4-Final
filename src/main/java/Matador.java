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
    static DeedController deedController = new DeedController();

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
        deedController.createDeeds(fieldController);
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
        canEndTurn = false;
        endTurn = false;
        extraTurns = 0;
        gui.msg("It is now " + playerController.getCurrentPlayer().getName() +"'s turn");
        while (!endTurn){
            gui.updateGuiPlayerBal(playerController);
            gui.updateCarPos(playerController);
            gui.updateOwners(playerController,deedController);
                switch (gui.selectAction(canEndTurn,playerController)){
                    case "Roll die":
                        roll();
                        break;
                    case "Upgrade Property":
                        upgradeProperty();
                        break;
                    case "Trade Deed":
                        tradeDeed();
                        break;
                    case "Sell House":
                        sellHouse();
                        break;
                    case "Mortgage":
                        mortgage();
                        break;
                    case "Un mortgage":
                        unMortgage();
                        break;
                    case "End Turn":
                        endTurn = true;
                        break;
                    case "Pay for freedom":
                        if (1000 >= playerController.getCurrentPlayer().getNetWorth()){
                            playerController.getCurrentPlayer().isBroke();
                        } else {
                            playerController.getCurrentPlayer().updateBalance(-1000);
                            playerController.getCurrentPlayer().setIsJailed(false);
                        }
                        break;
                    case "Roll for freedom":
                        cup.rollCup();
                        gui.showDice(cup);
                        if (cup.getDie1Value() == cup.getDie2Value()){
                            playerController.getCurrentPlayer().setIsJailed(false);
                            playerController.getCurrentPlayer().updatePos(cup.getDie1Value() + cup.getDie2Value());
                            doFieldAction();
                        }
                        else {
                            endTurn = true;
                        }
                        break;
                    case "Use card":
                        playerController.getCurrentPlayer().updateGetOutOfJailCard();
                        playerController.getCurrentPlayer().setIsJailed(false);
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
            doFieldAction();
        }
    }

    public static void doFieldAction(){
        String fieldType = fieldController.GetCurrentFiledType(playerController);
        switch (fieldType){
            case "Brewery":
            case "Deed":
            case "Ferry":
                //gui user select
                if(gui.yesOrNo("Do you want to buy this deed")){
                    buyDeed(fieldType);
                } else{
                    auction();
                }
                break;
            case "GoToJail":
                gui.msg("you have been sent to Jail");
                playerController.setPlayerPos(10,playerController.getCurrentPlayer());
                endTurn = true;
                break;
            case "Tax":
                if (playerController.getCurrentPlayer().getPos() == 4){
                    if (playerController.getCurrentPlayer().getBalance()<=4000){
                        playerController.getCurrentPlayer().setBalance((int) (playerController.getCurrentPlayer().getBalance()*0.9));
                    }else {
                        if(gui.yesOrNo("Do you want to pay 10% of your balance instead of kr. 4000?")){
                            playerController.getCurrentPlayer().setBalance((int) (playerController.getCurrentPlayer().getBalance()*0.9));
                        }else{
                            playerController.getCurrentPlayer().updateBalance(-4000);
                        }
                    }
                }
                else if (playerController.getCurrentPlayer().getPos() == 38){
                    playerController.updatePlayerBal(-2000,playerController.getCurrentPlayer());
                }
                break;
            case "Chance":
                gui.showChanceCard(chanceDeck.draw(playerController));
                if (playerController.getCurrentPlayer().getPos() == 10){
                    endTurn = true;
                }
                break;
            default:
                break;
        }
    }
    public static void upgradeProperty(){

    }
    public static void buyDeed(String deedType){

        switch (deedType){
            case "Brewery":
                deedController.buyBrewery(playerController);
                break;
            case "Deed":
                deedController.buyProperty(playerController);
                break;
            case "Ferry":
                deedController.buyShipping(playerController);
                break;
        }
    }
    public static void sellHouse(){

    }
    public static void mortgage(){

    }
    public static void unMortgage(){

    }
    public static void tradeDeed(){

    }
    public static void auction(){
        int pos = playerController.getCurrentPlayer().getPos();
        gui.msg("This deed is now up for auction");
        String name = gui.getPlayernameOrPropertyName("buying player");
        int id = playerController.getPlayerIdFromName(name);
        int amount = gui.getInt("Amount to pay");
        if (id==-1){
            gui.msg("This isn't a player");
        }else{
            deedController.setOwnerToPos(id,pos,amount,playerController);
        }

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
