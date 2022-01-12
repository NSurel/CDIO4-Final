package Models.Fields;

import Models.Player;

public class FerryField extends Field{

    Player owner;
    boolean isOwned;
    int rent0; int rent1; int rent2; int rent3;
    int price;
    final String fieldType = "Ferry";

    public FerryField(String fieldName, int price, int rent0, int rent1, int rent2, int rent3) {
        super(fieldName);
        this.price = price;
        this.rent0 = rent0;
        this.rent1 = rent1;
        this.rent2 = rent2;
        this.rent3 = rent3;
    }

    public int getPrice() {
        return price;
    }
}
