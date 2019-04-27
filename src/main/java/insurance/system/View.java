package insurance.system;

import insurance.model.Insurance;

import java.util.List;

public class View {

    public View() {

    }


    public void menu() {
        System.out.println("\n\nTopic: Insurance\n\nAvailable actions: \n"
                + "1. Show derivative\n"
                + "2. Calculate the total cost of contributions\n"
                + "3. Calculate total compensation\n"
                + "4. Sort derivatives by the reducing of the risk level\n"
                + "5. " + CommonlyUsedStrings.FIND_OBLIGATION_IN_RANGE_OF + "contributions\n"
                + "6. " + CommonlyUsedStrings.FIND_OBLIGATION_IN_RANGE_OF + "compensation values\n"
                + "7. " + CommonlyUsedStrings.FIND_OBLIGATION_IN_RANGE_OF + "risk levels\n"
                + "8. Add obligation\n"
                + "9. Remove obligation\n" +
                "10. Exit\n");
    }


    public void outputResults(List<Insurance> results) {
        if (results != null) {
            System.out.printf("     %-5s            %-25s    %-20s      %-10s         %-20s  %-15s                    %-30s%n",
                    "№", "Insurer", "Insured", "Contribution", "Compensation", "Risk level", "Additional information");
            System.out.println("_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
            for (int i = 0; i < results.size(); i++) {
                System.out.println(results.get(i));
            }
        } else {
            System.err.println("Your request has no results!\n\n");
        }
        results = null;
    }
}
