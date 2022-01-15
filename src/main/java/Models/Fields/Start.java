package Models.Fields;

public class Start extends Field{
    final String fieldType = "Start";
    int pos;
    public Start(String fieldName) {
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
