package Models.Deeds;
import Controllers.PlayerController;


public class Property extends Deed{

    public Property(int rent, int value, int type){
        super( rent,value,type);
    }
    @Override
    public void updateBuildLevel(){

    }
    @Override
    public void payRent(PlayerController playercontroller) {
        System.out.println("hello");

    }
}
