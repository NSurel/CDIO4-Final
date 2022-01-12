package Controllers;

import Models.Deeds.*;


public class DeedController {
    private Property[] properties;
    private Shipping[] shippings;
    private Brewery[] breweries;
    private Property property;
    private Shipping shipping;
    private Brewery brewery;

    public DeedController() {
    }

    public void createDeeds() {
        this.properties = new Property[22];
        this.shippings = new Shipping[4];
        this.breweries = new Brewery[2];
        int i = 0;
        while (i < properties.length) {
            switch (i) {
                case 0:
                case 1:
                    properties[i] = new Property(50, 1, 0);
                    shippings[i] = new Shipping(500, 4000);
                    break;
                case 2:
                case 3:
                    properties[i] = new Property(50, 1, 1);
                    shippings[i] = new Shipping(500, 4000);
                    break;
                case 4:
                    properties[i] = new Property(150, 1, 1);
                    break;
                case 5:
                case 6:
                    properties[i] = new Property(300, 1, 2);
                    break;

                case 7:
                    properties[i] = new Property(350, 1, 2);
                    break;
                case 8:
                case 9:
                    properties[i] = new Property(1, 1, 3);
                    break;
                case 10:
                    properties[i] = new Property(2, 1, 3);
                    break;
                case 11:
                case 12:
                    properties[i] = new Property(1, 1, 4);
                    break;
                case 13:
                    properties[i] = new Property(2, 1, 4);
                    break;
                case 14:
                case 15:
                    properties[i] = new Property(1, 1, 5);
                    break;
                case 16:
                    properties[i] = new Property(2, 1, 5);
                    break;
                case 17:
                case 18:
                    properties[i] = new Property(1, 1, 6);
                    break;
                case 19:
                    properties[i] = new Property(2, 1, 6);
                    break;
                case 20:
                    properties[i] = new Property(1, 1, 7);
                    break;
                case 21:
                    properties[i] = new Property(2, 1, 7);
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

    public void multipleShipping(PlayerController playerController) {
        int amountowned = 0;
        for (int i = 0; i < shippings.length; i++) {
            if (shippings[i].getOwner() == playerController.getCurrentPlayer().getPlayerID()) {
                amountowned++;
            }
        }
        switch (amountowned) {
            case 2:
                for (int i = 0; i < shippings.length; i++) {
                    if (shippings[i].getOwner() == playerController.getCurrentPlayer().getPlayerID()){
                        shippings[i].setRent(123456789);
                    }
                }
                break;
            case 3:
                for (int i = 0; i < shippings.length; i++) {
                    if (shippings[i].getOwner() == playerController.getCurrentPlayer().getPlayerID()){
                        shippings[i].setRent(1234789);

                    }
                }
                break;
            case 4:
                for (int i = 0; i < shippings.length; i++) {

                        shippings[i].setRent(9999);

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

    public void buyProperty(PlayerController playerController, FieldController fieldController){
        int i = 0;
        if (properties[i].getValue() < playerController.getCurrentPlayer().getBalance() && properties[i].getOwner() < 0 && ! properties[i].getIsMortgaged()){
            playerController.getCurrentPlayer().updateBalance(playerController.getCurrentPlayer().getBalance() - properties[i].getValue());
            properties[i].setOwner(playerController.getCurrentPlayer().getPlayerID());

        }
        else if( properties[i].getIsMortgaged())
        {
            System.out.println("This property is mortgaged.");


        }
        else if( properties[i].getOwner() >= 0){
            System.out.println("This property is owned by someone.");
        }
        else {
            System.out.println("You don't have enough money in your balance.");
        }
    }

    public void buyBrewery(PlayerController playerController, FieldController fieldController){
        int i = 0;
        if (breweries[i].getValue() < playerController.getCurrentPlayer().getBalance() && breweries[i].getOwner() < 0 && ! breweries[i].getIsMortgaged()){
            playerController.getCurrentPlayer().updateBalance(playerController.getCurrentPlayer().getBalance() - breweries[i].getValue());
            breweries[i].setOwner(playerController.getCurrentPlayer().getPlayerID());

        }
        else if( breweries[i].getIsMortgaged())
        {
            System.out.println("This brewery is mortgaged.");


        }
        else if( breweries[i].getOwner() >= 0){
            System.out.println("This brewery is owned by someone.");
        }
        else {
            System.out.println("You don't have enough money in your balance.");
        }
    }
    public void buyShipping(PlayerController playerController, FieldController fieldController){
        int i = 0;
        if (shippings[i].getValue() < playerController.getCurrentPlayer().getBalance() && shippings[i].getOwner() < 0 && ! shippings[i].getIsMortgaged()){
            playerController.getCurrentPlayer().updateBalance(playerController.getCurrentPlayer().getBalance() - shippings[i].getValue());
            shippings[i].setOwner(playerController.getCurrentPlayer().getPlayerID());

        }
        else if( shippings[i].getIsMortgaged())
        {
            System.out.println("This shipping is mortgaged.");


        }
        else if( shippings[i].getOwner() >= 0){
            System.out.println("This shipping is owned by someone.");
        }
        else {
            System.out.println("You don't have enough money in your balance.");
        }

    }

    public void mortgageProperty(PlayerController playerController, Property property) {
        int i = 0;
        playerController.getCurrentPlayer().updateBalance(properties[i].getValue() / 2);
        property.updateIsMortgaged();
        //TODO Skal have tilføjet et array af nogle deeds, så playeren kan vælge hvilket deed.
        //TODO Svar Det kan først komme når gui controlleren er ved at være der

    }

}
