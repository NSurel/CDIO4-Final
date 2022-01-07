package Models.Deeds;

import Models.Player;

public class Brewery extends Deed{
    public Brewery(int rent, int value, int type){
        super( rent,value,type);
    }

    @Override
    public void payRent(Player player) {

    }
}
