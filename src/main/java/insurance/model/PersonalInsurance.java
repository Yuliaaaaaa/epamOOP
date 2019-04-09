package insurance.model;


public class PersonalInsurance extends Insurance {

    private String person;

    public PersonalInsurance(String insurer, String insured, long compensation, int contribution, float risk, String person) {
        super(insurer, insured, compensation, contribution, risk);
        this.person = person;
    }


    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }


    public String toString(){
        return super.toString() + String.format("  %-25s  ",
                "Person: " + person);
    }
}

