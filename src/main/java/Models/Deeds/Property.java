package Models.Deeds;
import Controllers.PlayerController;


public class Property extends Deed{
    private int type;
    public Property(int rent, int value, int type, int pos){
        super( rent,value,pos);
        this.type = type;
    }

    @Override
    public int getRent() {
        return super.getRent();
    }

    @Override
    public int getValue() {
        return super.getValue();
    }


    public int getType() {
        return type;
    }
}
