package Models.Fields;

public class BreweryField extends Field{
    int price;
    public BreweryField(String fieldName, int price) {
        super(fieldName);
        this.price = price;
    }
}
