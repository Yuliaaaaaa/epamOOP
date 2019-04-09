package insurance.system;

import insurance.model.*;

import java.util.Scanner;

public class Factory {

    public static Insurance getSpecificInsurance(Insurance insurance, InsuranceType type){
        Scanner scanner = new Scanner(System.in);
        switch (type){
            case LIABILITY:{
                System.out.println("Liable person:\t");
                String liable = scanner.nextLine();
                return new LiabilityInsurance(
                        insurance.getInsurer(), insurance.getInsured(),
                        insurance.getCompensation(), insurance.getContribution(),
                        insurance.getRisk(), liable);
            }
            case PERSONAL:{
                System.out.println("Person:\t");
                String person = scanner.nextLine();
                return new PersonalInsurance(
                        insurance.getInsurer(), insurance.getInsured(),
                        insurance.getCompensation(), insurance.getContribution(),
                        insurance.getRisk(), person);

            }
            case PROPERTY:{
                System.out.println("Type of property:\t");
                String propType = scanner.nextLine();
                return new PropertyInsurance(
                        insurance.getInsurer(), insurance.getInsured(),
                        insurance.getCompensation(), insurance.getContribution(),
                        insurance.getRisk(), propType);

            }
            case EMPLOYERS:{
                System.out.println("Liable person:\t");
                String liable = scanner.nextLine();
                System.out.println("Employer:\t");
                String employer = scanner.nextLine();
                return new EmployersCompensation(
                        insurance.getInsurer(), insurance.getInsured(),
                        insurance.getCompensation(), insurance.getContribution(),
                        insurance.getRisk(), liable, employer);

            }

            case ACCIDENT:{
                System.out.println("Person:\t");
                String person = scanner.nextLine();
                System.out.println("Accident type:\t");
                String accidentType = scanner.nextLine();
                return new AccidentInsurance(
                        insurance.getInsurer(), insurance.getInsured(),
                        insurance.getCompensation(), insurance.getContribution(),
                        insurance.getRisk(), person, accidentType);

            }
            case HOME:{
                System.out.println("Type of property:\t");
                String propType = scanner.nextLine();
                System.out.println("Address:\t");
                String address = scanner.nextLine();
                return new HomeInsurance(
                        insurance.getInsurer(), insurance.getInsured(),
                        insurance.getCompensation(), insurance.getContribution(),
                        insurance.getRisk(), propType, address);

            }
            case MEDICAL:{
                System.out.println("Person:\t");
                String person = scanner.nextLine();
                System.out.println("Medical card ID:\t");
                int cardId = scanner.nextInt();
                return new MedicalInsurance(
                        insurance.getInsurer(), insurance.getInsured(),
                        insurance.getCompensation(), insurance.getContribution(),
                        insurance.getRisk(), person, cardId);
            }
            case UNDEFINED:{
                return insurance;
            }
            default:{
                return insurance;
            }
        }
    }

    public static InsuranceType getInsuranceType(String string){
        try {
            return InsuranceType.valueOf(string.toUpperCase());
        }
        catch (IllegalArgumentException e){
            return InsuranceType.UNDEFINED;
        }
    }

}
