package Controllers;
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
                    fc.getFields()[i].getClass().getName().equals("Models.Fields.BreweryField"))
            gui.getFields()[i].setSubText("pris: "+ fc.getFields()[i].getRent());
        }
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
    public void createPlayers(int amount, int startMoney, PlayerController pc){
        players = new GUI_Player[amount];
        for (int i = 0; i < amount; i++) {
            players[i] = new GUI_Player(pc.getPlayers()[i].getName(),startMoney,getCars()[i]);
            gui.addPlayer(players[i]);
            players[i].getCar().setPosition(gui.getFields()[0]);
        }
    }
    public String getUserSelection(String msg, String option1, String option2, String option3){
        String tmp;
        tmp = gui.getUserSelection(msg, option1, option2, option3);
        return tmp;
    }
    public void updateCarPos(PlayerController pc){
        players[pc.getCurrentPlayer().getPlayerID()].getCar().setPosition(gui.getFields()[pc.getCurrentPlayer().getPos()]);
    }
    public void updateGuiPlayerBal(PlayerController pc){
        players[pc.getCurrentPlayer().getPlayerID()].setBalance(pc.getCurrentPlayer().getBalance());
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
    public boolean yesOrNo(String msg){
        return gui.getUserLeftButtonPressed(msg, "yes", "no");
    }
    public void setOwner(PlayerController pc, FieldController fc){
        ownables = (GUI_Ownable) gui.getFields()[pc.getCurrentPlayer().getPos()];
        ownables.setBorder(players[pc.getCurrentPlayer().getPlayerID()].getCar().getPrimaryColor());
        ownables.setOwnerName(players[pc.getCurrentPlayer().getPlayerID()].getName());
    }
    public void setlevel(DeedController dc){
        //Need to find the buildlevel and location of the property to be able to place the house/hotel on the gui
    }

}
