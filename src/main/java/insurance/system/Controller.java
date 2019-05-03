package insurance.system;

import insurance.model.*;
import insurance.system.comparators.*;

import java.util.*;

public class Controller {
    private View view;
    private Derivative allBase;
    private Derivative selected;

    public Controller(List<Insurance> obligs) {
        view = new View();
        allBase = new Derivative(obligs);
        selected = null;
    }

    public Controller() {
        view = new View();
        List<Insurance> obligs = new ArrayList<Insurance>();
        allBase = new Derivative(obligs);
        selected = null;
    }


    public long countSummaryContribution() {
        long sum = 0;
        for (int i = 0; i < allBase.getObligations().size(); i++) {
            sum += allBase.getObligations().get(i).getContribution();
        }
        return sum;
    }

    public long countSummaryCompensation() {
        long sum = 0;
        for (int i = 0; i < allBase.getObligations().size(); i++) {
            sum += allBase.getObligations().get(i).getCompensation();
        }
        return sum;
    }


    public void sortingDescByRisk() {
        Comparator<Insurance> comparator = new InsuranceRiskDescComparator();
        selected = new Derivative(allBase);
        Collections.sort(selected.getObligations(), comparator);
    }


    public Derivative selectContributions(int lowID, int topID) {
        List<Insurance> newDerivative = new ArrayList<Insurance>();
        for (int i = 0; i < allBase.getObligations().size(); i++) {
            if (allBase.getObligations().get(i).getContribution() <= topID &&
                    allBase.getObligations().get(i).getContribution() >= lowID) {
                newDerivative.add(allBase.getObligations().get(i));
            }
        }
        return new Derivative(newDerivative);
    }

    public Derivative selectCompensations(long lowID, long topID) {
        List<Insurance> newDerivative = new ArrayList<Insurance>();
        for (int i = 0; i < allBase.getObligations().size(); i++) {
            if (allBase.getObligations().get(i).getCompensation() <= topID &&
                    allBase.getObligations().get(i).getCompensation() >= lowID) {
                newDerivative.add(allBase.getObligations().get(i));
            }
        }
        return new Derivative(newDerivative);
    }

    public Derivative selectRisks(float lowID, float topID) {
        List<Insurance> newDerivative = new ArrayList<Insurance>();
        for (int i = 0; i < allBase.getObligations().size(); i++) {
            if (allBase.getObligations().get(i).getRisk() <= topID &&
                    allBase.getObligations().get(i).getRisk() >= lowID) {
                newDerivative.add(allBase.getObligations().get(i));
            }
        }
        return new Derivative(newDerivative);
    }


    public void mainMenu() {
        view.menu();
        Scanner scanner = ScannerHolder.scanner;
        int n = scanner.nextInt();
        switch (n) {
            case 1: {
                viewResults(allBase);
                mainMenu();
                break;
            }
            case 2: {
                System.out.println("Summary contribution is: " + countSummaryContribution());
                mainMenu();
                break;
            }
            case 3: {
                System.out.println("Summary compensation is: " + countSummaryCompensation());
                mainMenu();
                break;
            }
            case 4: {
                sortingDescByRisk();
                viewResults(selected);
                mainMenu();
                break;
            }
            case 5: {
                System.out.println(CommonlyUsedStrings.SEARCHING_INSURANCE_OF + "CONTRIBUTIONS");
                System.out.print(CommonlyUsedStrings.INTERVAL_START);
                if (!scanner.hasNextInt()) {
                    showErrorInInput();
                }
                int first = scanner.nextInt();
                System.out.print(CommonlyUsedStrings.INTERVAL_END);
                if (!scanner.hasNextInt()) {
                    showErrorInInput();
                }
                int last = scanner.nextInt();
                selected = selectContributions(first, last);
                Collections.sort(selected.getObligations(), new InsuranceContributionComparator());
                viewResults(selected);
                mainMenu();
                break;
            }
            case 6: {
                System.out.println(CommonlyUsedStrings.SEARCHING_INSURANCE_OF + "COMPENSATIONS");
                System.out.print(CommonlyUsedStrings.INTERVAL_START);
                if (!scanner.hasNextLong()) {
                    showErrorInInput();
                }
                long first = scanner.nextLong();
                System.out.print(CommonlyUsedStrings.INTERVAL_END);
                if (!scanner.hasNextLong()) {
                    showErrorInInput();
                }
                long last = scanner.nextLong();
                selected = selectCompensations(first, last);
                Collections.sort(selected.getObligations(), new InsuranceCompensationComparator());
                viewResults(selected);
                mainMenu();
                break;
            }
            case 7: {
                System.out.println(CommonlyUsedStrings.SEARCHING_INSURANCE_OF + "RISK LEVEL");
                System.out.print(CommonlyUsedStrings.INTERVAL_START);
                if (!scanner.hasNextFloat()) {
                    showErrorInInput();
                }
                float first = scanner.nextFloat();
                System.out.print(CommonlyUsedStrings.INTERVAL_END);
                if (!scanner.hasNextFloat()) {
                    showErrorInInput();
                }
                float last = scanner.nextFloat();
                selected = selectRisks(first, last);
                Collections.sort(selected.getObligations(), new InsuranceRiskComparator());
                viewResults(selected);
                mainMenu();
                break;
            }
            case 8: {
                System.out.println("ADDING INSURANCE\n");
                scanner.nextLine();
                System.out.println("Insurer: ");
                String insurer = scanner.nextLine();
                System.out.println("Insured: ");
                String insured = scanner.nextLine();
                System.out.println("Contribution: ");
                if (!scanner.hasNextInt()) {
                    showErrorInInput();
                }
                int contribution = scanner.nextInt();
                System.out.println("Compensation: ");
                if (!scanner.hasNextLong()) {
                    showErrorInInput();
                }
                long compensation = scanner.nextLong();
                System.out.println("Risk level: ");
                if (!scanner.hasNextFloat()) {
                    showErrorInInput();
                }
                float risk = scanner.nextFloat();
                scanner.nextLine();
                Insurance insurance;
                System.out.println("Insurance type: ");
                String type = scanner.nextLine();
                insurance = Factory.getSpecificInsurance(
                        new Insurance(insurer, insured, compensation, contribution, risk),
                        Factory.getInsuranceType(type));

                allBase = allBase.addObligation(insurance);
                mainMenu();
                break;
            }
            case 9: {
                System.out.println("DELETING INSURANCE\n");
                System.out.print("ID:\t");
                if (!scanner.hasNextInt()) {
                    showErrorInInput();
                }
                int idx = scanner.nextInt();
                if (allBase.delObligation(idx))
                    viewResults(allBase);
                else System.out.println("Insurance doesn't exist.");
                mainMenu();
                break;

            }
            case 10:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Incorrect number!");

        }
        scanner.close();
    }

    private void showErrorInInput() {
        System.out.println("Error! It's not a number!");
        mainMenu();
        return;
    }

    public void viewResults(Derivative results) {
        view.outputResults(results.getObligations());
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Derivative getAllBase() {
        return allBase;
    }

    public void setAllBase(Derivative allBase) {
        this.allBase = allBase;
    }

    public Derivative getSelected() {
        return selected;
    }

    public void setSelected(Derivative selected) {
        this.selected = selected;
    }
}
