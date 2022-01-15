package Models.Fields;

public class Jail extends Field{
    final String fieldType = "Jail";
    int pos;
    public Jail(String fieldName) {
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
