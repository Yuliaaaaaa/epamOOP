package insurance.model;


public class Insurance {

    private static int last_ID;

    private int id;
    private final String insurer;
    private final long compensation;
    private final String insured;
    private final int contribution;
    private float risk;

    public Insurance(String insurer, String insured, long compensation, int contribution, float risk) {
        if (last_ID == 0) last_ID = 1;
        else ++last_ID;

        id = last_ID;
        this.insurer = insurer;
        this.compensation = compensation;
        this.insured = insured;
        this.contribution = contribution;
        this.risk = risk;
    }


    public int getId() {
        return id;
    }

    public String getInsurer() {
        return insurer;
    }

    public long getCompensation() {
        return compensation;
    }

    public String getInsured() {
        return insured;
    }


    public int getContribution() {
        return contribution;
    }


    public float getRisk() {
        return risk;
    }

    public void setRisk(float risk) {
        this.risk = risk;
    }

    public String toString() {
        return String.format("||  %5d   ||  %-25s  ||  %-25s  ||  %-10d  ||  %-20d  ||  %-15f  ||",
                id, insurer, insured, contribution, compensation, risk);
    }

    public boolean equals(Object oblig) {
        if (oblig == null) return false;
        if (this == oblig) return true;
        if (!(oblig instanceof Insurance) || oblig.getClass() != this.getClass()
                || this.hashCode() != oblig.hashCode()) return false;
        if (id == ((Insurance) oblig).id) return true;
        return false;
    }

}
