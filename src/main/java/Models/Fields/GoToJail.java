package Models.Fields;

public class GoToJail extends Field{
    final String fieldType = "GoToJail";
    public GoToJail(String fieldName) {
        super(fieldName);
    }

    @Override
    public String getFieldType() {
        return fieldType;
    }
}
