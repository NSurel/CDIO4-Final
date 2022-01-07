package Models.Deeds;

import Models.Player;

public class Property extends Deed{

    public Property(int rent, int value, int type){
        super( rent,value,type);
    }
    @Override
    public void payRent(Player player) {

    }
}
