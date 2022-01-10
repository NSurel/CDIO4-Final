package Models.Fields;

public class FerryField extends Field{
    int price;
    public FerryField(String fieldName, int price) {
        super(fieldName);
        this.price = price;
    }
}
