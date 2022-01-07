package Models.Deeds;

import Controllers.PlayerController;
import Models.Player;

public abstract class Deed {
    private int rent;
    private int value;
    private int owner;
    private int type;
    private int buildlevel;
    private boolean ismortageged;

    public Deed(int rent,int value, int type ){
        this.rent = rent;
        this.value= value;
        this.type = type;
        this.owner = 0;
        this.buildlevel=0;
        this.ismortageged=false;
    }


    abstract public void payRent(PlayerController playerController);

    public void buyProperty(PlayerController playercontroller){

    }
    abstract public void updateBuildLevel();





    public void updateIsMortgaged(){
        ismortageged = !getIsMortgaged();
    }
    public int getRent() {
        return rent;
    }
    public int getOwner() {
        return owner;
    }
    public int getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
    public int getBuildlevel() {
        return buildlevel;
    }
    public boolean getIsMortgaged(){ return ismortageged;}
    public void setBuildlevel(int buildlevel) {
        this.buildlevel = buildlevel;
    }
    public void setRent(int rent) {
        this.rent = rent;
    }
    public void setOwner(int owner) {
        this.owner = owner;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setValue(int value) {
        this.value = value;
    }

}
