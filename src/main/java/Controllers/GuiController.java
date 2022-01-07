package Controllers;
import gui_fields.GUI_Field;
import gui_main.*;

public class GuiController {
    private final GUI gui;

    public GuiController(){
        gui = new GUI();
        System.out.println(gui.getFields()[1].getSubText());
    }

    public void changePrice(int pos, int value){
        gui.getFields()[pos].setSubText("pris: "+value);
    }
}
