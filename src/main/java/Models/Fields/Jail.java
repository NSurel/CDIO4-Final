package Models.Fields;

public class Jail extends Field{
    final String fieldType = "Jail";
    public Jail(String fieldName) {
        super(fieldName);
    }

    @Override
    public String getFieldType() {
        return fieldType;
    }
}
