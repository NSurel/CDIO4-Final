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
        while (j < getBreweries().length) {
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

    public void allOwnedOfSameType() {
        int i = 0;
        while (i < getProperties().length) {
            if ((getProperties()[i].getType() == 1 || getProperties()[i].getType() == 9) && getProperties()[i].getOwner() == getProperties()[i + 1].getOwner())
                if ((getProperties()[i].getOwner() == getProperties()[i + 1].getOwner())) {
                    getProperties()[i].setBuildlevel(getProperties()[i].getBuildlevel() + 1);
                    getProperties()[i + 1].setBuildlevel(getProperties()[i + 1].getBuildlevel() + 1);
                } else if ((getProperties()[i].getType() == getProperties()[i + 1].getType()) && getProperties()[i].getType() == getProperties()[i + 2].getType()) {
                    if (getProperties()[i].getOwner() == getProperties()[i + 1].getOwner() && (getProperties()[i].getOwner() == getProperties()[i + 2].getOwner())) {


                        getProperties()[i].setBuildlevel(getProperties()[i].getBuildlevel() + 1);
                        getProperties()[i + 1].setBuildlevel(getProperties()[i + 1].getBuildlevel() + 1);
                        getProperties()[i + 2].setBuildlevel(getProperties()[i + 2].getBuildlevel() + 1);
                    }
                }
        }
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

    public void buyProperty(PlayerController playerController, int value){
        if (value < playerController.getCurrentPlayer().getBalance() && deed.getOwner() < 0 && !deed.getIsMortgaged()){
            playerController.getCurrentPlayer().setBalance(playerController.getCurrentPlayer().getBalance() - value);
            deed.setOwner(playerController.getCurrentPlayer().getPlayerID());

        }
       else if(deed.getIsMortgaged())
        {
            System.out.println("This property is mortgaged.");


        }
       else if(deed.getOwner() > 0){
            System.out.println("This property is owned by someone.");
       }
       else {
            System.out.println("You don't have enough money in your balance.");
        }
        //TODO Hvordan man tjekker om en property er ejet. Not sure if det er korrekt den måde jeg har gjort det på.
    }
    public void mortgageProperty(PlayerController playerController, Deed deed){
        playerController.getCurrentPlayer().setBalance(deed.getValue()/2);
        deed.updateIsMortgaged();
        //TODO Skal have tilføjet et array af nogle deeds, så playeren kan vælge hvilket deed.

    }
}
