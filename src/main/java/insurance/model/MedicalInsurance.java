package insurance.model;

public class MedicalInsurance extends PersonalInsurance{

    private long cardID;

    public MedicalInsurance(String insurer, String insured, long compensation, int contribution, float risk, String person, int cardID) {
        super(insurer, insured, compensation, contribution, risk, person);
        this.cardID = cardID;
    }


    public long getCardID() {
        return cardID;
    }

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }


    public String toString(){
        return super.toString() + String.format("  %-25s  ",
                "Medical card №: " + cardID);
    }
}
