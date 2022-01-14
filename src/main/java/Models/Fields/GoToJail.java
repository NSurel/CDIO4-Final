package Models.Fields;

public class GoToJail extends Field{
    final String fieldType = "GoToJail";
    int pos;
    public GoToJail(String fieldName) {
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
