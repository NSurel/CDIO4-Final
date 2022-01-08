package Controllers;

import Models.Deeds.Brewery;

import Models.Deeds.Deed;
import Models.Deeds.Property;
import Models.Deeds.Shipping;

public class DeedController {
    private Property[] properties;
    private Shipping[] shippings;
    private Brewery[] breweries;
    private Deed deed;

    public DeedController(Property[] properties, Brewery[] breweries, Shipping[] shippings, Deed deed) {
        this.properties = properties;
        this.breweries = breweries;
        this.shippings = shippings;
        this.deed = deed;
    }

    public int getNetValues(PlayerController playerController) {
        int value = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < getProperties().length) {

            if (!getProperties()[i].getIsMortgaged()) {
                if (getProperties()[i].getOwner() == playerController.getCurrentPlayer().getPlayerID()) {
                    value += ((getProperties()[i].getValue()) / 2);

                }
            }
            i++;
        }
        while (j < getBreweries().length ) {
            if (!getBreweries()[j].getIsMortgaged()) {
                if (getBreweries()[j].getOwner() == playerController.getCurrentPlayer().getPlayerID()) {
                    value += getBreweries()[j].getValue() / 2;
                }
            }
            j++;
        }
        while (k < getShippings().length) {
            if (!getShippings()[k].getIsMortgaged()) {
                if (getShippings()[k].getOwner() == playerController.getCurrentPlayer().getPlayerID()) {
                    value += getShippings()[k].getValue() / 2;
                }
            }
            k++;
        }
        return value;
    }

    public Brewery[] getBreweries() {
        return breweries;
    }

    public Property[] getProperties() {
        return properties;
    }

    public Shipping[] getShippings() {
        return shippings;
    }

    public void BuyProperty(PlayerController playerController, int value){
        if (value < playerController.getCurrentPlayer().getBalance() && deed.getOwner() == 0){
            playerController.getCurrentPlayer().setBalance(playerController.getCurrentPlayer().getBalance() - value);
            deed.setOwner(playerController.getCurrentPlayer().getPlayerID());

        }
        else
        {
            System.out.println("You don't have enough money in your balance.");


        }

    }
}
