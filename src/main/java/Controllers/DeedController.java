package Controllers;

import Models.Deeds.Brewery;
import Models.Deeds.Deed;
import Models.Deeds.Property;
import Models.Deeds.Shipping;

public class DeedController {
    private Property[] properties;
    private Shipping[] shippings;
    private Brewery[] breweries;
    public DeedController(Property[] properties, Brewery[] breweries, Shipping[] shippings){
        this.properties = properties;
    }

   /* public void getNetValues(PlayerController.getCurrentPlayer.getPlayerId){
        int i = 0;
        while(getProperties().length-1 < getProperties().length){
            if (getProperties()[i].getOwner() == )
        }
    }*/
    public Brewery[] getBreweries() {
        return breweries;
    }
    public Property[] getProperties() {
        return properties;
    }
    public Shipping[] getShippings() {
        return shippings;
    }
}
