package Models.Deeds;
import Controllers.PlayerController;


public class Property extends Deed{

    public Property(int rent, int value, int type){
        super( rent,value,type);
    }

    @Override
    public int getRent() {
        return super.getRent();
    }

    @Override
    public int getValue() {
        return super.getValue();
    }
}
