package insurance.model;

public class HomeInsurance extends PropertyInsurance {

    private String adress;

    public HomeInsurance(String insurer, String insured, long compensation, int contribution, float risk, String propertyType, String adress) {
        super(insurer, insured, compensation, contribution, risk, propertyType);
        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String toString() {
        return super.toString() + String.format("  %-25s  ",
                "Adress: " + adress);
    }
}
