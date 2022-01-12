package Models.Fields;

import Models.Player;

public class DeedField extends Field {

    Player owner;
    boolean isOwned;
    int rent0; int rent1; int rent2; int rent3; int rent4; int rent5;
    int price;
    int housePrice;
    String fieldType;

    public DeedField(String fieldName,int price, int housePrice, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5) {
        super(fieldName);
        this.price = price;
        this.housePrice = housePrice;
        this.rent0 = rent0;
        this.rent1 = rent1;
        this.rent2 = rent2;
        this.rent3 = rent3;
        this.rent4 = rent4;
        this.rent5 = rent5;
        fieldType = "Deed";
    }

    public int getRent0() {
        return rent0;
    }
    public int getRent1() {
        return rent1;
    }
    public int getRent2() {
        return rent2;
    }
    public int getRent3() {
        return rent3;
    }
    public int getRent4() {
        return rent4;
    }
    public int getRent5(){
        return rent5;
    }

    public Player getOwner() {
        return owner;
    }

    public int getPrice() {
        return price;
    }
    public int getHousePrice() {
        return housePrice;
    }

    public void setOwned(boolean owned) {
        isOwned = owned;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public void setRent(int rent) {
        rent = rent;
    }

    @Override
    public String getFieldType() {
        return fieldType;
    }
}

