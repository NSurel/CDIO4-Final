package Controllers;

import Models.Deeds.Brewery;


import Models.Deeds.Property;
import Models.Deeds.Shipping;

public class DeedController {
    private Property[] properties;
    private Shipping[] shippings;
    private Brewery[] breweries;
    private Property property;


    public DeedController(Property[] properties, Brewery[] breweries, Shipping[] shippings, Property property) {
        this.properties = properties;
        this.breweries = breweries;
        this.shippings = shippings;
        this.property = property;
    }

    public void createDeeds(FieldController fieldController) {
        Property[] properties = new Property[22];
        Shipping[] shippings = new Shipping[4];
        Brewery[] breweries = new Brewery[2];
        int i = 0;
        while (i < properties.length) {
            switch (i) {
                case 0:
                case 1:
                    properties[i] = new Property(50, 1200, 0);
                    shippings[i] = new Shipping(500, 4000);
                    break;
                case 2:
                case 3:
                    properties[i] = new Property(50, 2000, 1);
                    shippings[i] = new Shipping(500, 4000);
                    break;
                case 4:
                    properties[i] = new Property(150, 2000, 1);
                    break;
                case 5:
                case 6:
                    properties[i] = new Property(300,3600,2);
                    break;

                case 7:
                    properties[i] = new Property(350,3600,2);
                    break;
                case 8:
                case 9:
                    properties[i] = new Property();
                    break;
                case 10:
                    properties[i] = new Property();
                    break;
                case 11:
                case 12:
                    properties[i] = new Property();
                    break;
                case 13:
                    properties[i] = new Property();
                    break;
                case 14:
                case 15:
                    properties[i] = new Property();
                    break;
                case 16:
                    properties[i] = new Property();
                    break;
                case 17:
                case 18:
                    properties[i] = new Property();
                    break;
                case 19:
                    properties[i] = new Property();
                    break;
                case 20:
                    properties[i] = new Property();
                    break;
                case 21:
                    properties[i] = new Property();
                    break;



            }
            i++;
        }

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
        while (i < this.getProperties().length) {
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

    public void multipleShipping() {
        int i = 0;
        if (getShippings()[i].getOwner() == getShippings()[i + 1].getOwner() && getShippings()[i].getOwner() != getShippings()[i + 2].getOwner() && getShippings()[i + 3].getOwner() != getShippings()[i].getOwner()) {
            getShippings()[i].setRent(1000);
            getShippings()[i + 1].setRent(1000);
            //må lige rette på talle er ikke sikre om de er rigtige
        }
       /* else if(getShippings()[i].getOwner()==1){

        }*/

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

    public Property getProperty() {
        return property;
    }

    public void buyProperty(PlayerController playerController, int value) {
        if (value < playerController.getCurrentPlayer().getBalance() && property.getOwner() < 0 && !property.getIsMortgaged()) {
            playerController.getCurrentPlayer().setBalance(playerController.getCurrentPlayer().getBalance() - value);
            property.setOwner(playerController.getCurrentPlayer().getPlayerID());

        } else if (property.getIsMortgaged()) {
            System.out.println("This property is mortgaged.");


        } else if (property.getOwner() >= 0) {
            System.out.println("This property is owned by someone.");
        } else {
            System.out.println("You don't have enough money in your balance.");
        }
        //TODO Hvordan man tjekker om en property er ejet. Not sure if det er korrekt den måde jeg har gjort det på.
    }

    public void mortgageProperty(PlayerController playerController, Property property) {
        playerController.getCurrentPlayer().setBalance(property.getValue() / 2);
        property.updateIsMortgaged();
        //TODO Skal have tilføjet et array af nogle deeds, så playeren kan vælge hvilket deed.
        //TODO Svar Det kan først komme når gui controlleren er ved at være der

    }

}
