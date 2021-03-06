package Models.Fields;

import Models.Player;

public class BreweryField extends Field{

    Player owner;
    boolean isOwned;
    int rent0; int rent1;
    int price;
    String fieldType;
    int pos;

    public BreweryField(String fieldName, int price, int rent0, int rent1) {
        super(fieldName);
        this.price = price;
        this.rent0 = rent0;
        this.rent1 = rent1;
        fieldType = "Brewery";
    }

    public int getPrice() {
        return price;
    }
    public int getRent0(){
        return rent0;
    }

    public String getFieldType() {
        return fieldType;
    }

    @Override
    public int getPos() {
        return pos;
    }
}
