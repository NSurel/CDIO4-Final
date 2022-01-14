package Models.Fields;


public abstract class Field {
    String fieldName;
    String fieldType;
    int pos;

    public Field(String fieldName)
    {
       this.fieldName = fieldName;
       fieldType = "Field";
    }

    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public abstract String getFieldType();
    public abstract int getPos();
}
