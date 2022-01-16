package Models.Fields;

import Models.Player;

public class FerryField extends Field{

    Player owner;
    boolean isOwned;
    int rent0; int rent1; int rent2; int rent3;
    int price;
    String fieldType;
    int pos;

    public FerryField(String fieldName, int price, int rent0, int rent1, int rent2, int rent3) {
        super(fieldName);
        this.price = price;
        this.rent0 = rent0;
        this.rent1 = rent1;
        this.rent2 = rent2;
        this.rent3 = rent3;
        fieldType = "Ferry";
    }

    public int getPrice() {
        return price;
    }
    public int getRent0(){
        return rent0;
    }

    @Override
    public String getFieldType() {
        return fieldType;
    }

    @Override
    public int getPos() {
        return pos;
    }
}
