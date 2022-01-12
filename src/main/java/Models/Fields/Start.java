package Models.Fields;

public class Start extends Field{
    final String fieldType = "Start";
    public Start(String fieldName) {
        super(fieldName);
    }

    @Override
    public String getFieldType() {
        return fieldType;
    }
}
