package insurance.model;

public class EmployersCompensation extends LiabilityInsurance {

    private String employer;

    public EmployersCompensation(String insurer, String insured, long compensation, int contribution, float risk, String liable, String employer) {
        super(insurer, insured, compensation, contribution, risk, liable);
        this.employer = employer;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String toString() {
        return super.toString() + String.format("  %-25s  ",
                "Employer's name: " + employer);
    }
}
