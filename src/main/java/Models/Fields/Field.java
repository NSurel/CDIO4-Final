package Models.Fields;


public abstract class Field {
    String fieldName;

    public Field(String fieldName)
    {
       this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

}
