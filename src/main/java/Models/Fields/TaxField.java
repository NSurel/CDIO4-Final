package Models.Fields;

public class TaxField extends Field{
    int taxAmount;
    String taxType;
    final String fieldType = "Tax";
    int pos;

    public TaxField(String fieldName, int taxAmount, String taxType) {
        super(fieldName);
        this.taxAmount = taxAmount;
        this.taxType = taxType;
    }

    public int getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(int taxAmount) {
        this.taxAmount = taxAmount;
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
