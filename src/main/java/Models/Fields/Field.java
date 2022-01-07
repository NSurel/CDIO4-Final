package Models.Fields;

import Models.Player;

public abstract class Field {
    String fieldName;
    int Rent;
    Player owner;
    boolean isOwend;

    public Field(String fieldName)
    {
       this.fieldName = fieldName;
    }

    public int getRent() {
        return Rent;
    }
    public Player getOwner() {
        return owner;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public void setOwend(boolean owend) {
        isOwend = owend;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public void setRent(int rent) {
        Rent = rent;
    }

}
