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

    public void createDeeds(FieldController fc) {
        fc.getFields();

        this.properties = new Property[22];
        this.shippings = new Shipping[4];
        this.breweries = new Brewery[2];
        for (int i = 0; i < properties.length; i++) {
            properties[i] = new Property(1,1,1,1);
        }
        for (int i = 0; i < shippings.length; i++) {
            shippings[i] = new Shipping(1,1,1);
        }
        for (int i = 0; i < breweries.length; i++) {
            breweries[i] = new Brewery(1,2,1);
        }
        int tmpprop = 0;
        int tmpship = 0;
        int tmpbrew = 0;
        for (int i = 0; i < fc.getFields().length; i++) {

            if (fc.getFields()[i].getClass().getName().equals("Models.Fields.DeedField")){
                properties[tmpprop].setPos(i);
                properties[tmpprop].setValue(fc.getFieldPrice(i));
                //properties[tmpprop].setRent();
                tmpprop++;
            }
            if (fc.getFields()[i].getClass().getName().equals("Models.Fields.FerryField")){
                shippings[tmpship].setPos(i);
                shippings[tmpship].setValue(fc.getFieldPrice(i));
                tmpship++;
            }
            if (fc.getFields()[i].getClass().getName().equals("Models.Fields.BreweryField")){
                breweries[tmpbrew].setPos(i);
                breweries[tmpbrew].setValue(fc.getFieldPrice(i));
                tmpbrew++;
            }
        }
//        int i = 0;
//        while (i < properties.length) {
//            switch (i) {
//                case 0:
//                case 1:
//                    properties[i] = new Property(50, 1, 0);
//                    shippings[i] = new Shipping(500, 4000);
//                    break;
//                case 2:
//                case 3:
//                    properties[i] = new Property(50, 1, 1);
//                    shippings[i] = new Shipping(500, 4000);
//                    break;
//                case 4:
//                    properties[i] = new Property(150, 1, 1);
//                    break;
//                case 5:
//                case 6:
//                    properties[i] = new Property(300, 1, 2);
//                    break;
//
//                case 7:
//                    properties[i] = new Property(350, 1, 2);
//                    break;
//                case 8:
//                case 9:
//                    properties[i] = new Property(1, 1, 3);
//                    break;
//                case 10:
//                    properties[i] = new Property(2, 1, 3);
//                    break;
//                case 11:
//                case 12:
//                    properties[i] = new Property(1, 1, 4);
//                    break;
//                case 13:
//                    properties[i] = new Property(2, 1, 4);
//                    break;
//                case 14:
//                case 15:
//                    properties[i] = new Property(1, 1, 5);
//                    break;
//                case 16:
//                    properties[i] = new Property(2, 1, 5);
//                    break;
//                case 17:
//                case 18:
//                    properties[i] = new Property(1, 1, 6);
//                    break;
//                case 19:
//                    properties[i] = new Property(2, 1, 6);
//                    break;
//                case 20:
//                    properties[i] = new Property(1, 1, 7);
//                    break;
//                case 21:
//                    properties[i] = new Property(2, 1, 7);
//                    break;
//
//
//            }
//            i++;
//        }

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

    public void buyProperty(PlayerController playerController){
        int i = playerController.getCurrentPlayer().getPos();
        for (Property property: properties) {
            if (property.getPos() == i) {
                if (property.getOwner() == -1 && playerController.getCurrentPlayer().getBalance() >= property.getValue()) {
                    property.setOwner(playerController.getCurrentPlayer().getPlayerID());
                    playerController.getCurrentPlayer().updateBalance(-property.getValue());
                }
            }
        }
//        int i = playerController.getCurrentPlayer().getPos();
//        if (properties[i].getValue() < playerController.getCurrentPlayer().getBalance() && properties[i].getOwner() < 0 && ! properties[i].getIsMortgaged()){
//            playerController.getCurrentPlayer().updateBalance(- properties[i].getValue());
//            properties[i].setOwner(playerController.getCurrentPlayer().getPlayerID());
//
//        }
//        else if( properties[i].getIsMortgaged())
//        {
//            System.out.println("This property is mortgaged.");
//
//
//        }
//        else if( properties[i].getOwner() >= 0){
//            System.out.println("This property is owned by someone.");
//        }
//        else {
//            System.out.println("You don't have enough money in your balance.");
//        }
    }

    public void buyBrewery(PlayerController playerController){
        int i = playerController.getCurrentPlayer().getPos();
        for (Brewery brewery : breweries){
            if (brewery.getPos() == i){
                if (brewery.getOwner()==-1 && playerController.getCurrentPlayer().getBalance() >= brewery.getValue()){
                    brewery.setOwner(playerController.getCurrentPlayer().getPlayerID());
                    playerController.getCurrentPlayer().updateBalance(-brewery.getValue());
                }
            }
        }
//        if (breweries[i].getValue() < playerController.getCurrentPlayer().getBalance() && breweries[i].getOwner() < 0 && ! breweries[i].getIsMortgaged()){
//            playerController.getCurrentPlayer().updateBalance(- breweries[i].getValue());
//            breweries[i].setOwner(playerController.getCurrentPlayer().getPlayerID());
//
//        }
//        else if( breweries[i].getIsMortgaged())
//        {
//            System.out.println("This brewery is mortgaged.");
//
//
//        }
//        else if( breweries[i].getOwner() >= 0){
//            System.out.println("This brewery is owned by someone.");
//        }
//        else {
//            System.out.println("You don't have enough money in your balance.");
//        }
    }
    public void buyShipping(PlayerController playerController){
        int i = playerController.getCurrentPlayer().getPos();
        for (Shipping shipping : shippings) {
            if (shipping.getPos() == i) {
                if (shipping.getOwner() == -1 && playerController.getCurrentPlayer().getBalance() >= shipping.getValue()) {
                    shipping.setOwner(playerController.getCurrentPlayer().getPlayerID());
                    playerController.getCurrentPlayer().updateBalance(-shipping.getValue());
                }
            }
        }
//
//        int i = playerController.getCurrentPlayer().getPos();
//        if (shippings[i].getValue() < playerController.getCurrentPlayer().getBalance() && shippings[i].getOwner() < 0 && ! shippings[i].getIsMortgaged()){
//            playerController.getCurrentPlayer().updateBalance(- shippings[i].getValue());
//            shippings[i].setOwner(playerController.getCurrentPlayer().getPlayerID());
//
//        }
//        else if( shippings[i].getIsMortgaged())
//        {
//            System.out.println("This shipping is mortgaged.");
//
//
//        }
//        else if( shippings[i].getOwner() >= 0){
//            System.out.println("This shipping is owned by someone.");
//        }
//        else {
//            System.out.println("You don't have enough money in your balance.");
//        }

    }

    public String mortgageProperty(PlayerController playerController, String deedName,FieldController fc) {
        int pos = -1;
        String tmp = "Default";
        for (int i = 0; i < fc.getFields().length; i++) {
            if (deedName.equals(fc.getFields()[i].getFieldName())) {
                pos = i;
                tmp = "found pos";
            }
        }
        for (Property property : properties){
            if (pos == property.getPos()){
                tmp = "found property";
                if (playerController.getCurrentPlayer().getPlayerID() == property.getOwner()){
                    if (property.getBuildlevel() == 0) {
                        playerController.getCurrentPlayer().updateBalance(property.getValue()/2);
                        property.updateIsMortgaged();
                        tmp = "The deed was mortgaged";
                    } else {
                        tmp = "Can't mortgage, there is a house/hotel on this deed";
                    }
                } else{
                    tmp = "You don't own that deed";
                }
            }
        }
        for (Shipping shipping : shippings){
            if (pos == shipping.getPos()){
                tmp = "found shipping";
                if (playerController.getCurrentPlayer().getPlayerID() == property.getOwner()){
                    playerController.getCurrentPlayer().updateBalance(shipping.getValue()/2);
                    shipping.updateIsMortgaged();
                    tmp = "The deed was mortgaged";
                } else{
                    tmp = "You don't own that deed";
                }
            }

        }
        for (Brewery brewery : breweries){
            if (pos == brewery.getPos()){
                tmp = "found brewery";
                if (playerController.getCurrentPlayer().getPlayerID() == brewery.getOwner()){
                    playerController.getCurrentPlayer().updateBalance(brewery.getValue()/2);
                    brewery.updateIsMortgaged();
                    tmp = "The deed was mortgaged";
                } else{
                    tmp = "You don't own that deed";
                }
            }
        }
        if (pos == -1){
            tmp = "That input was invalid";
        }
        return tmp;

    }
    public String unMortgageProperty(PlayerController playerController, String deedName,FieldController fc) {
        int pos = -1;
        String tmp = "Default";
        for (int i = 0; i < fc.getFields().length; i++) {
            if (deedName.equals(fc.getFields()[i].getFieldName())) {
                pos = i;
                tmp = "found pos";
            }
        }
        for (Property property : properties) {
            if (pos == property.getPos()) {
                tmp = "found property";
                if (playerController.getCurrentPlayer().getPlayerID() == property.getOwner()) {
                    if (property.getIsMortgaged()){
                        if (playerController.getCurrentPlayer().getBalance() > (property.getValue()/2) + property.getValue()*0.1){
                            playerController.getCurrentPlayer().updateBalance((int) (-(property.getValue()/2) + property.getValue()*0.1));
                            property.updateIsMortgaged();
                            tmp = "The deed is now un mortgaged";
                        } else{
                            tmp = "You don't have enough money to pay the mortgage";
                        }
                    } else{
                        tmp = "that deed isn't mortgaged";
                    }
                } else {
                    tmp = "You don't own that deed";
                }
            }
        }
        for (Shipping shipping : shippings) {
            if (pos == shipping.getPos()) {
                tmp = "found shipping";
                if (playerController.getCurrentPlayer().getPlayerID() == shipping.getOwner()) {
                    if (shipping.getIsMortgaged()){
                        if (playerController.getCurrentPlayer().getBalance() > shipping.getValue()/2 + shipping.getValue()*0.1){
                            playerController.getCurrentPlayer().updateBalance((int) (-(shipping.getValue()/2) + shipping.getValue()*0.1));
                            shipping.updateIsMortgaged();
                            tmp = "The deed is now un mortgaged";
                        } else{
                            tmp = "You don't have enough money to pay the mortgage";
                        }
                    } else{
                        tmp = "that deed isn't mortgaged";
                    }
                    playerController.getCurrentPlayer().updateBalance(shipping.getValue() / 2);
                    shipping.updateIsMortgaged();
                    tmp = "The deed was mortgaged";
                } else {
                    tmp = "You don't own that deed";
                }
            }
        }
        for (Brewery brewery : breweries) {
            if (pos == brewery.getPos()) {
                tmp = "found brewery";
                if (playerController.getCurrentPlayer().getPlayerID() == brewery.getOwner()) {
                    if (brewery.getIsMortgaged()){
                        if (playerController.getCurrentPlayer().getBalance() > brewery.getValue()/2 + brewery.getValue()*0.1){
                            playerController.getCurrentPlayer().updateBalance((int) (-(brewery.getValue()/2) + brewery.getValue()*0.1));
                            brewery.updateIsMortgaged();
                            tmp = "The deed is now un mortgaged";
                        } else{
                            tmp = "You don't have enough money to pay the mortgage";
                        }
                    } else{
                        tmp = "that deed isn't mortgaged";
                    }
                } else {
                    tmp = "You don't own that deed";
                }
            }
        }
        if (pos == -1) {
            tmp = "That input was invalid";
        }
        return tmp;
    }
    public void setOwnerToPos(int id, int pos,int amount, PlayerController pc){
        for (Property property : properties){
            if (property.getPos()==pos){
                property.setOwner(id);
                pc.getPlayers()[id].updateBalance(-amount);
            }
        }
        for (Brewery brewery : breweries){
            if (brewery.getPos() == pos){
                brewery.setOwner(id);
                pc.getPlayers()[id].updateBalance(-amount);
            }
        }
        for (Shipping shipping : shippings){
            if (shipping.getPos() == pos){
                shipping.setOwner(id);
                pc.getPlayers()[id].updateBalance(-amount);
            }
        }
    }

}