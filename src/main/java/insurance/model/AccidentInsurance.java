package insurance.model;

public class AccidentInsurance extends PersonalInsurance {

    private String accidentType;

    public AccidentInsurance(String insurer, String insured, long compensation, int contribution, float risk, String person, String accidentType) {
        super(insurer, insured, compensation, contribution, risk, person);
        this.accidentType = accidentType;
    }

    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }


    public String toString(){
        return super.toString() + String.format("  %-25s  ",
                "Accident: " + accidentType);
    }
}
