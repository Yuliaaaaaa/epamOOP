package insurance.system;

import insurance.model.Insurance;

import java.util.Comparator;

public class InsuranceRiskComparator implements Comparator<Insurance> {

    public int compare(Insurance insurance1, Insurance insurance2) {
        if(insurance1.getRisk()>insurance2.getRisk()) return 1;
        else if (insurance1.getRisk()<insurance2.getRisk()) return -1;
        return 0;
    }
}
