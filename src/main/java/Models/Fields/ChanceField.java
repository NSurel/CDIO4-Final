package Models.Fields;

public class ChanceField extends Field{
    final String fieldType = "Chance";
    public ChanceField(String fieldName) {
        super(fieldName);
    }

    @Override
    public String getFieldType() {
        return fieldType;
    }
}
