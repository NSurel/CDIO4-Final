package Models.Deeds;



public abstract class Deed {
    int rent0;
    int rent1;
    int rent2;
    int rent3;
    int rent4;
    int rent5;
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








    public int getRent() {
        switch (this.buildlevel){
            case 0:
                return rent0;
            case 1:
                return rent1;
            case 2:
                return rent2;
            case 3:
                return rent3;
            case 4:
                return rent4;
            case 5:
                return rent5;
            default:
                return rent0;
        }
    }

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
    public void setRent0(int rent) {
        this.rent0 = rent;
    }
    public void setRent1(int rent) {
        this.rent1 = rent;
    }
    public void setRent2(int rent) {
        this.rent2 = rent;
    }
    public void setRent3(int rent) {
        this.rent3 = rent;
    }
    public void setRent4(int rent) {
        this.rent4 = rent;
    }
    public void setRent5(int rent) {
        this.rent5 = rent;
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
