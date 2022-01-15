package Models.Fields;

public class ChanceField extends Field{
    final String fieldType = "Chance";
    int pos;
    public ChanceField(String fieldName) {
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
