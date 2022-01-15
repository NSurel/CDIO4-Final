package Models.Fields;

public class FreeParking extends Field{
    final String fieldType = "FreeParking";
    int pos;
    public FreeParking(String fieldName) {
        super(fieldName);
    }

    @Override
    public String getFieldType() {
        return fieldType;
    }

    @Override
    public int getPos() {
        return pos;
    }
}
