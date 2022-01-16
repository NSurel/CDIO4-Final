package Controllers;
import Models.Deeds.Brewery;
import Models.Fields.BreweryField;
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
    //This methods gets all the prices for the buyable fields from the fieldcontroller, and corrects all the titles in the gui
    public void fixAllPrices(FieldController fc){
        for (int i = 0; i < fc.getFields().length; i++) {
            if (fc.getFields()[i].getClass().getName().equals("Models.Fields.DeedField")||
                    fc.getFields()[i].getClass().getName().equals("Models.Fields.FerryField")||
                    fc.getFields()[i].getClass().getName().equals("Models.Fields.BreweryField")) {
                    gui.getFields()[i].setSubText("Pris: " + fc.getFieldPrice(i));
                    gui.getFields()[i].setTitle(fc.getFieldTitle(i));
                    gui.getFields()[i].setDescription(fc.getFieldTitle(i));
            }
        }
        //this is to make sure the tax fields and breweries have the right text displayed
        gui.getFields()[4].setSubText("10% eller kr. 4000");
        gui.getFields()[4].setDescription("Betal indkomstskat 10% eller kr. 4000");
        gui.getFields()[38].setSubText("kr. 2000");
        gui.getFields()[38].setDescription("Betal ekstraordinær statsskat kr. 2000");
        ownables = (GUI_Ownable) gui.getFields()[12];
        BreweryField field = (BreweryField) fc.getFields()[12];
        ownables.setRent(String.valueOf(field.getRent0()));
        field = (BreweryField) fc.getFields()[28];
        ownables.setRent(String.valueOf(field.getRent0()));


    }
    public int getPlayerAmount (){
        return Integer.valueOf(gui.getUserSelection("Hvor mange spillere?","3","4","5","6"));
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
    //this method chooses which dropdown menu to display to the player, which depends on what actions the player already has taken
    public String selectAction(boolean haveRolled,PlayerController pc) {
        String tmp;
        String msg = "Vælg en handling";
        if (pc.getCurrentPlayer().isJailed())
            if (pc.getCurrentPlayer().getOutOfJailCard())
                tmp = gui.getUserSelection(msg,"Brug kort","Rul for frihed","Betal for frihed","Opgrader skøde", "Sælg hus", "Pantsæt skøde", "Afpansæt skøde", "Byt skøde");
            else
                tmp = gui.getUserSelection(msg,"Rul for frihed","Betal for frihed","Opgrader skøde", "Sælg hus", "Pantsæt skøde", "Afpansæt skøde", "Byt skøde");
        else if (haveRolled)
            tmp = gui.getUserSelection(msg, "Slut tur", "0pgrader skøde", "Sælg hus", "Pantsæt skøde", "Afpansæt skøde", "Byt skøde");
        else
            tmp = gui.getUserSelection(msg, "Rul", "Opgrader skøde", "Sælg hus", "Pantsæt skøde", "Afpansæt skøde", "Byt skøde");

        return tmp;
    }

    public void updateCarPos(PlayerController pc){
        players[pc.getCurrentPlayer().getPlayerID()].getCar().setPosition(gui.getFields()[pc.getCurrentPlayer().getPos()]);
    }
    public void updateGuiPlayerBal(PlayerController pc){
        for (int i = 0; i < pc.getPlayers().length; i++) {
            players[i].setBalance(pc.getPlayers()[i].getBalance());
        }
    }
    public void showDice(Cup cup){
        gui.setDice(cup.getDie1Value(),1,5, cup.getDie2Value(),2,5);
    }
    public void showChanceCard(String msg){
        gui.displayChanceCard(msg);
    }
    public void msg(String msg){
        gui.showMessage(msg);
    }
    public boolean yesOrNo(String msg){
        return gui.getUserLeftButtonPressed(msg, "Ja", "Nej");
    }
    //updates all the fields to have the right owners if any, and set the rent on them
    public void updateOwners(PlayerController pc, DeedController dc){
        //this for loop updates all the the property fields
        for(int i = 0; i < dc.getProperties().length;i++){
            ownables = (GUI_Ownable) gui.getFields()[dc.getProperties()[i].getPos()];
            for (int j = 0; j < pc.getPlayers().length;j++){
                if (pc.getPlayers()[j].getPlayerID() == dc.getProperties()[i].getOwner()){
                    ownables.setBorder(players[pc.getPlayers()[j].getPlayerID()].getCar().getPrimaryColor());
                    ownables.setOwnerName(players[pc.getPlayers()[j].getPlayerID()].getName());
                    ownables.setRent(String.valueOf(dc.getProperties()[i].getRent()));
                }
            }
        }
        //This updates the shipping fields
        for(int i = 0; i < dc.getShippings().length;i++){
            ownables = (GUI_Ownable) gui.getFields()[dc.getShippings()[i].getPos()];
            for (int j = 0; j < pc.getPlayers().length;j++){
                if (pc.getPlayers()[j].getPlayerID() == dc.getShippings()[i].getOwner()) {
                    ownables.setBorder(players[pc.getPlayers()[j].getPlayerID()].getCar().getPrimaryColor());
                    ownables.setOwnerName(players[pc.getPlayers()[j].getPlayerID()].getName());
                    ownables.setRent(String.valueOf(dc.getShippings()[i].getRent()));
                }
            }
        }
        //this one updates the brewery fields
        for(int i = 0; i < dc.getBreweries().length;i++){
            ownables = (GUI_Ownable) gui.getFields()[dc.getBreweries()[i].getPos()];
            for (int j = 0; j < pc.getPlayers().length;j++){
                if (pc.getPlayers()[j].getPlayerID() == dc.getBreweries()[i].getOwner()) {
                    ownables.setBorder(players[pc.getPlayers()[j].getPlayerID()].getCar().getPrimaryColor());
                    ownables.setOwnerName(players[pc.getPlayers()[j].getPlayerID()].getName());
                    ownables.setRent(String.valueOf(dc.getBreweries()[i].getRent()));
                }
            }
        }
    }
    public void setLevel(int pos, int buildlevel){
        GUI_Field field = gui.getFields()[pos];
        GUI_Street street = (GUI_Street) field;
        if (buildlevel > 4){
            street.setHotel(true);
        }
        else{
            street.setHotel(false);
            street.setHouses(buildlevel);

        }
    }
    public String getUserName(){
        return gui.getUserString("Skriv dit navn");
    }
    public String getPlayernameOrPropertyName(String playerOrProperty ){
        return gui.getUserString("Skriv navnet ind for "+ playerOrProperty);
    }
    public int getInt(String msg){
        return gui.getUserInteger(msg);
    }


}
