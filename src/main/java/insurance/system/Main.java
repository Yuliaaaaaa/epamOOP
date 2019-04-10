package insurance.system;

import insurance.model.*;
import insurance.system.Controller;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Insurance> obligs = new LinkedList<Insurance>();
        obligs.add(new EmployersCompensation("Insurer1", "Insured5", 80000000000l,
                30000, 0.8547362514f, "Liable1", "Employer1"));
        obligs.add(new EmployersCompensation("Insurer2", "Insured1", 30000000000l,
                70000, 0.754684675645f, "Liable1", "Employer2"));
        obligs.add(new AccidentInsurance("Insurer1", "Insured3", 700000000000l,
                20000, 0.89735645f, "Someone1", "Breaking leg"));
        obligs.add(new MedicalInsurance("Insurer2", "Insured10", 4000000000l,
                30000, 0.12345f, "Someone2", 1234567));
        obligs.add(new HomeInsurance("Insurer3", "Insured15", 695000000000l,
                40000, 0.653424f, "House", "Khreshchstyk street, 8"));
        obligs.add(new HomeInsurance("Insurer3", "Insured9", 565000000,
                9000, 0.8957356f, "Factory", "Kharkivska highway, 40"));
        Controller controller = new Controller(obligs);
        controller.mainMenu();
    }
}
