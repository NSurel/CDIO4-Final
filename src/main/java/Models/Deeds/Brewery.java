package Models.Deeds;

import Controllers.PlayerController;
import Models.Player;

public class Brewery extends Deed{
    public Brewery(int rent, int value, int type){
        super( rent,value,type);
    }
    @Override
    public void updateBuildLevel(){

    }
    @Override
    public void payRent(PlayerController playerController) {

    }
}
