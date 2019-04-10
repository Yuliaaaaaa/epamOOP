package insurance.model;


public class PropertyInsurance extends Insurance {

    private String propertyType;

    public PropertyInsurance(String insurer, String insured, long compensation, int contribution, float risk, String propertyType) {
        super(insurer, insured, compensation, contribution, risk);
        this.propertyType = propertyType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String toString(){
        return super.toString() + String.format("  %-25s  ",
                "Property type: " + propertyType);
    }
}

