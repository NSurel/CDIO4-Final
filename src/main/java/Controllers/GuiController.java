package Controllers;
import Models.Fields.DeedField;
import gui_fields.*;
import gui_main.*;
import java.awt.*;

public class GuiController {
    private GUI_Ownable ownables;
    private GUI_Street level;
    private final GUI gui;
    private GUI_Car[] cars;
    private GUI_Player[] players;
    private Color[] colors = {Color.red, Color.WHITE, Color.blue,Color.GREEN, Color.yellow, Color.MAGENTA };


    public GuiController(){
        gui = new GUI();
    }

    public void changePrice(int pos, int value){
        gui.getFields()[pos].setSubText("pris: "+value);
    }
    public void fixAllPrices(FieldController fc){
        for (int i = 0; i < fc.getFields().length; i++) {
            if (fc.getFields()[i].getClass().getName().equals("Models.Fields.DeedField")||
                    fc.getFields()[i].getClass().getName().equals("Models.Fields.FerryField")||
                    fc.getFields()[i].getClass().getName().equals("Models.Fields.BreweryField")) {
                    gui.getFields()[i].setSubText("Pris: " + fc.getFieldPrice(i));
            }
        }
        gui.getFields()[4].setSubText("10% eller kr. 4000");
        gui.getFields()[4].setDescription("Betal indkomstskat 10% eller kr. 4000");
        gui.getFields()[38].setSubText("kr. 2000");
        gui.getFields()[38].setDescription("Betal ekstraordinær statsskat kr. 2000");


    }
    public int getPlayerAmount (){
        return Integer.valueOf(gui.getUserSelection("How many players","3","4","5","6"));
    }
    public void createCars(int amount){
        cars = new GUI_Car[amount];
        for (int i = 0; i < amount; i++) {
            cars[i] = new GUI_Car(colors[i],Color.BLACK,GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
        }
    }
    public GUI_Car[] getCars(){
        return cars;
    }
    public void createPlayers(int amount, PlayerController pc){
        players = new GUI_Player[amount];
        for (int i = 0; i < amount; i++) {
            players[i] = new GUI_Player(pc.getPlayers()[i].getName(),pc.getPlayers()[i].getBalance(),getCars()[i]);
            gui.addPlayer(players[i]);
            players[i].getCar().setPosition(gui.getFields()[0]);
        }
    }
    public String getUserSelection(String msg, String option1, String option2, String option3){
        String tmp;
        tmp = gui.getUserSelection(msg, option1, option2, option3);
        return tmp;
    }
    public String selectAction(boolean haveRolled,PlayerController pc) {
        String tmp;
        String msg = "Choose an action";
        if (pc.getCurrentPlayer().isJailed())
            if (pc.getCurrentPlayer().getOutOfJailCard())
                tmp = gui.getUserSelection(msg,"Use card","Roll for freedom","Pay for freedom","Upgrade Property", "Sell House", "Mortgage", "Un mortgage", "Trade Deed");
            else
                tmp = gui.getUserSelection(msg,"Roll for freedom","Pay for freedom","Upgrade Property", "Sell House", "Mortgage", "Un mortgage", "Trade Deed");
        else if (haveRolled)
            tmp = gui.getUserSelection(msg, "End Turn", "Upgrade Property", "Sell House", "Mortgage", "Un mortgage", "Trade Deed");
        else
            tmp = gui.getUserSelection(msg, "Roll die", "Upgrade Property", "Sell House", "Mortgage", "Un mortgage", "Trade Deed");

        return tmp;
    }

    public void updateCarPos(PlayerController pc){
        players[pc.getCurrentPlayer().getPlayerID()].getCar().setPosition(gui.getFields()[pc.getCurrentPlayer().getPos()]);
    }
    public void updateGuiPlayerBal(PlayerController pc){
        for (int i = 0; i < pc.getPlayers().length; i++) {
            players[i].setBalance(pc.getPlayers()[i].getBalance());
        }
        //players[pc.getCurrentPlayer().getPlayerID()].setBalance(pc.getCurrentPlayer().getBalance());
    }
    public void showDice(Cup cup){
        gui.setDice(cup.getDie1Value(),1,5, cup.getDie2Value(),2,5);
    }
    public void showChanceCard(String msg){
        gui.displayChanceCard(msg);
    }
    public void rollMsg(String msg){
        gui.getUserButtonPressed(msg,"Roll");
    }
    public void msg(String msg){
        gui.showMessage(msg);
    }
    public boolean yesOrNo(String msg){
        return gui.getUserLeftButtonPressed(msg, "yes", "no");
    }
    public void updateOwners(PlayerController pc, DeedController dc){
        for(int i = 0; i < dc.getProperties().length;i++){
            ownables = (GUI_Ownable) gui.getFields()[dc.getProperties()[i].getPos()];
            for (int j = 0; j < pc.getPlayers().length;j++){
                if (pc.getPlayers()[j].getPlayerID() == dc.getProperties()[i].getOwner()){
                    ownables.setBorder(players[pc.getPlayers()[j].getPlayerID()].getCar().getPrimaryColor());
                    ownables.setOwnerName(players[pc.getPlayers()[j].getPlayerID()].getName());
                }
            }
        }
        for(int i = 0; i < dc.getShippings().length;i++){
            ownables = (GUI_Ownable) gui.getFields()[dc.getShippings()[i].getPos()];
            for (int j = 0; j < pc.getPlayers().length;j++){
                if (pc.getPlayers()[j].getPlayerID() == dc.getShippings()[i].getOwner()) {
                    ownables.setBorder(players[pc.getPlayers()[j].getPlayerID()].getCar().getPrimaryColor());
                    ownables.setOwnerName(players[pc.getPlayers()[j].getPlayerID()].getName());
                }
            }
        }
        for(int i = 0; i < dc.getBreweries().length;i++){
            ownables = (GUI_Ownable) gui.getFields()[dc.getBreweries()[i].getPos()];
            for (int j = 0; j < pc.getPlayers().length;j++){
                if (pc.getPlayers()[j].getPlayerID() == dc.getBreweries()[i].getOwner()) {
                    ownables.setBorder(players[pc.getPlayers()[j].getPlayerID()].getCar().getPrimaryColor());
                    ownables.setOwnerName(players[pc.getPlayers()[j].getPlayerID()].getName());
                }
            }
        }
    }
    public void setLevel(DeedController dc){
        //Need to find the buildlevel and location of the property to be able to place the house/hotel on the gui
    }
    public String getUserName(){
        return gui.getUserString("Type in name");
    }
    public String getPlayernameOrPropertyName(String playerOrProperty ){
        return gui.getUserString("Type in the name of the "+ playerOrProperty);
    }
    public String getOutOfJail(PlayerController pc){
        String tmp;
        if (pc.getCurrentPlayer().getOutOfJailCard()){
            tmp = gui.getUserSelection("Choose option","Roll","Pay","use card");
        } else {
            tmp = gui.getUserSelection("Choose option","Roll","Pay");
        }
        return tmp;
    }


}
