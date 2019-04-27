package insurance.model;


public class LiabilityInsurance extends Insurance {

    private String liablePerson;

    public LiabilityInsurance(String insurer, String insured, long compensation, int contribution, float risk, String liablePerson) {
        super(insurer, insured, compensation, contribution, risk);
        this.liablePerson = liablePerson;
    }

    public String getLiablePerson() {
        return liablePerson;
    }

    public void setLiablePerson(String liablePerson) {
        this.liablePerson = liablePerson;
    }


    public String toString() {
        return super.toString() + String.format("  %-25s  ",
                "Liable person: " + liablePerson);
    }
}
