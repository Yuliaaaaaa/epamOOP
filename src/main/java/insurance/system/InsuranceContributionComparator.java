package insurance.system;

import insurance.model.Insurance;

import java.util.Comparator;

public class InsuranceContributionComparator implements Comparator<Insurance> {

    public int compare(Insurance insurance1, Insurance insurance2) {
        if(insurance1.getContribution()<insurance2.getContribution()) return -1;
        else if (insurance1.getContribution()>insurance2.getContribution()) return 1;
        return 0;
    }
}
