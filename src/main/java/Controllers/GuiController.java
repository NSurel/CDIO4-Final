package Controllers;
import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.*;

public class GuiController {
    private final GUI gui;
    private GUI_Car[] cars;
    private GUI_Player[] players;

    public GuiController(){
        gui = new GUI();
    }

    public void changePrice(int pos, int value){
        gui.getFields()[pos].setSubText("pris: "+value);
    }
    public void fixAllPrices(FieldController fc){
    }
    public int getPlayerAmount (){
        return Integer.valueOf(gui.getUserSelection("How many players","3","4","5","6"));
    }
    public void createCars(int amount){
        cars = new GUI_Car[amount];
        for (int i = 0; i < amount; i++) {
            cars[i] = new GUI_Car();
        }
    }
    public GUI_Car[] getCars(){
        return cars;
    }
    public void createPlayers(int amount, int startmoney){
        players = new GUI_Player[amount];
        for (int i = 0; i < amount; i++) {
            players[i] = new GUI_Player("Player"+(i+1),startmoney,getCars()[i]);
            gui.addPlayer(players[i]);
        }
    }
}
