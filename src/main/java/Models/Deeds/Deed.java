package Models.Deeds;



public abstract class Deed {
    int rent;
    private int value;
    private int owner;
    int pos;

    private int buildlevel;
    private boolean ismortageged;

    public Deed(int rent0, int value,int pos){
        this.value = value;
        this.pos = pos;

        this.buildlevel=0;
        this.ismortageged=false;
        this.owner = -1;
    }








    public int getRent() {return rent;  }

    public void updateIsMortgaged(){
        ismortageged = !getIsMortgaged();
    }
    public int getOwner() {
        return owner;
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
    public void setValue(int value) {
        this.value = value;
    }
    public void setPos(int value){
        this.pos = value;
    }
    public int getPos(){
        return pos;
    }

}
