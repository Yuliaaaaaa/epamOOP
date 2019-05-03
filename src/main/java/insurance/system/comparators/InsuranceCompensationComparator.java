package insurance.system.comparators;

import insurance.model.Insurance;

import java.util.Comparator;

public class InsuranceCompensationComparator implements Comparator<Insurance> {

    public int compare(Insurance insurance1, Insurance insurance2) {
        if (insurance1.getCompensation() < insurance2.getCompensation()) return -1;
        else if (insurance1.getCompensation() > insurance2.getCompensation()) return 1;
        return 0;
    }
}
