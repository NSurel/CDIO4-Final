package Models.Deeds;

public abstract class Deed {
    private int rent;
    private int value;
    private int owner;
    private int type;



    public int getRent() {
        return rent;
    }
    public int getOwner() {
        return owner;
    }
    public int getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
    public void setRent(int rent) {
        this.rent = rent;
    }
    public void setOwner(int owner) {
        this.owner = owner;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
