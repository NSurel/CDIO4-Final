package Models.Deeds;

import Models.Player;

public class Shipping extends Deed {
    public Shipping(int rent, int value, int type) {
        super(rent, value, type);
    }

    @Override
    public void payRent(Player player) {

    }
}
