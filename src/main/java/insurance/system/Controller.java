package insurance.system;

import insurance.model.*;

import java.util.*;

public class Controller {
    private View view;
    private Derivative allBase;
    private Derivative selected;

    public Controller(List<Insurance> obligs) {
        allBase = new Derivative(obligs);
        selected = null;
    }

    public Controller(){
        view = new View();
        int n = 10;
        List<Insurance> obligs = new LinkedList<Insurance>();


        obligs.add(new EmployersCompensation("Insurer1", "Insured5", 80000000000l,
                30000, 0.8547362514f, "Liable1", "Employer1" ));
        obligs.add(new EmployersCompensation("Insurer2", "Insured1", 30000000000l,
                70000, 0.754684675645f,  "Liable1", "Employer2"));
        obligs.add(new AccidentInsurance("Insurer1", "Insured3", 700000000000l,
                20000, 0.89735645f, "Someone1", "Breaking leg"));
        obligs.add(new MedicalInsurance("Insurer2", "Insured10", 4000000000l,
                30000, 0.12345f, "Someone2", 1234567));
        obligs.add(new HomeInsurance("Insurer3", "Insured15", 695000000000l,
                40000, 0.653424f, "House", "Khreshchstyk street, 8"));
        obligs.add(new HomeInsurance("Insurer3", "Insured9", 565000000,
                9000, 0.8957356f, "Factory", "Kharkivska highway, 40"));


        allBase = new Derivative(obligs);
        selected = null;
    }



   public long countSummaryContribution(){
       long sum = 0;
       for(int i = 0; i < allBase.getObligations().size(); i++){
           sum += allBase.getObligations().get(i).getContribution();
       }
       return sum;
   }

   public long countSummaryCompensation(){

       long sum = 0;
       for(int i = 0; i < allBase.getObligations().size(); i++){
           sum += allBase.getObligations().get(i).getCompensation();
       }
       return sum;
   }


   public void sortingDescByRisk(){
       Comparator<Insurance> comparator = new InsuranceRiskDescComparator();
       selected = new Derivative(allBase);
       Collections.sort(selected.getObligations(), comparator);
   }


    public Derivative selectContributions(int lowID, int topID){
        List<Insurance> newDerivative = new LinkedList<Insurance>();
        for(int i = 0; i < allBase.getObligations().size(); i++){
            if(allBase.getObligations().get(i).getContribution()<= topID &&
                    allBase.getObligations().get(i).getContribution()>= lowID){
                newDerivative.add(allBase.getObligations().get(i));
            }
        }
        return new Derivative(newDerivative);
    }
    public Derivative selectCompensations(long lowID, long topID){
        List<Insurance> newDerivative = new LinkedList<Insurance>();
        for(int i = 0; i < allBase.getObligations().size(); i++){
            if(allBase.getObligations().get(i).getCompensation()<= topID &&
                    allBase.getObligations().get(i).getCompensation()>= lowID){
                newDerivative.add(allBase.getObligations().get(i));
            }
        }
        return new Derivative(newDerivative);
    }
    public Derivative selectRisks(float lowID, float topID){
        List<Insurance> newDerivative = new LinkedList<Insurance>();
        for(int i = 0; i < allBase.getObligations().size(); i++){
            if(allBase.getObligations().get(i).getRisk()<= topID &&
                    allBase.getObligations().get(i).getRisk()>= lowID){
                newDerivative.add(allBase.getObligations().get(i));
            }
        }
        return new Derivative(newDerivative);
    }



    public void mainMenu(){
        view.menu();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();



        switch (n){
            case 1:{
                viewResults(allBase);
                mainMenu();
                break;
            }
            case 2:{
                System.out.println("Суммарная стоимость взносов: " + countSummaryContribution());
                mainMenu();
                break;
            }
            case 3:{
                System.out.println("Суммарная компенсация: " + countSummaryCompensation());
                mainMenu();
                break;
            }
            case 4:{
                sortingDescByRisk();
                viewResults(selected);
                mainMenu();
                break;
            }
            case 5:{
                System.out.println("ПОИСК ОБЯЗАТЕЛЬСТВ ПО ИНТЕРВАЛУ ВЗНОСОВ");
                System.out.print("Начало интервала:\t");
                if(!scanner.hasNextInt()){
                    System.out.println("Ошибка! Это не число!");
                    mainMenu();
                    return;
                }
                int first = scanner.nextInt();
                System.out.print("Конец интервала:\t");
                if(!scanner.hasNextInt()){
                    System.out.println("Ошибка! Это не число!");
                    mainMenu();
                    return;
                }
                int last = scanner.nextInt();
                selected = selectContributions(first, last);
                Comparator comparator = new InsuranceContributionComparator();
                Collections.sort(selected.getObligations(), comparator);
                viewResults(selected);
                mainMenu();
                break;
            }
            case 6:{
                System.out.println("ПОИСК ОБЯЗАТЕЛЬСТВ ПО ИНТЕРВАЛУ КОМПЕНСАЦИЙ");
                System.out.print("Начало интервала:\t");
                if(!scanner.hasNextLong()){
                    System.out.println("Ошибка! Это не число!");
                    mainMenu();
                    return;
                }
                long first = scanner.nextLong();
                System.out.print("Конец интервала:\t");
                if(!scanner.hasNextLong()){
                    System.out.println("Ошибка! Это не число!");
                    mainMenu();
                    return;
                }
                long last = scanner.nextLong();
                selected = selectCompensations(first, last);
                Comparator comparator = new InsuranceCompensationComparator();
                Collections.sort(selected.getObligations(), comparator);
                viewResults(selected);
                mainMenu();
                break;
            }
            case 7:{
                System.out.println("ПОИСК ОБЯЗАТЕЛЬСТВ ПО ИНТЕРВАЛУ УРОВНЕЙ РИСКА");
                System.out.print("Начало интервала:\t");
                if(!scanner.hasNextFloat()){
                    System.out.println("Ошибка! Это не число!");
                    mainMenu();
                    return;
                }
                float first = scanner.nextFloat();
                System.out.print("Конец интервала:\t");
                if(!scanner.hasNextFloat()){
                    System.out.println("Ошибка! Это не число!");
                    mainMenu();
                    return;
                }
                float last = scanner.nextFloat();
                selected = selectRisks(first, last);
                Comparator comparator = new InsuranceRiskComparator();
                Collections.sort(selected.getObligations(), comparator);
                viewResults(selected);
                mainMenu();
                break;
            }
            case 8:{
                System.out.println("ДОБАВЛЕНИЕ ОБЯЗАТЕЛЬСТВА\n");
                scanner.nextLine();
                System.out.println("Страховщик: ");
                String insurer = scanner.nextLine();
                System.out.println("Страхователь: ");
                String insured = scanner.nextLine();
                System.out.println("Взнос: ");
                if(!scanner.hasNextInt()){
                    System.out.println("Ошибка! Это не число!");
                    mainMenu();
                    return;
                }
                int contribution = scanner.nextInt();
                System.out.println("Компенсация: ");
                if(!scanner.hasNextLong()){
                    System.out.println("Ошибка! Это не число!");
                    mainMenu();
                    return;
                }
                long compensation = scanner.nextLong();
                System.out.println("Степень риска: ");
                if(!scanner.hasNextFloat()){
                    System.out.println("Ошибка! Это не число!");
                    mainMenu();
                    return;
                }
                float risk = scanner.nextFloat();
                scanner.nextLine();
                Insurance insurance;
                System.out.println("Тип обязательства: ");
                String type = scanner.nextLine();
                 insurance = Factory.getSpecificInsurance(
                        new Insurance(insurer, insured, compensation,contribution, risk),
                        Factory.getInsuranceType(type));

                allBase = allBase.addObligation(insurance);
                mainMenu();
                break;
            }
            case 9:{
                System.out.println("УДАЛЕНИЕ ОБЯЗАТЕЛЬСТВА\n");
                System.out.print("ID:\t");
                if(!scanner.hasNextInt()){
                    System.out.println("Ошибка! Это не число!");
                    mainMenu();
                    return;
                }
                int idx = scanner.nextInt();
                if(allBase.delObligation(idx))
                    viewResults(allBase);
                else System.out.println("Обязательства не существует.");
                mainMenu();
                break;

            }
            case 10:
                System.out.println("Досвидания");
                break;
            default:
                System.out.println("Введён неправильный номер");

        }
        scanner.close();
    }

    public void viewResults(Derivative results){
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
