package insurance;

import insurance.model.*;
import insurance.system.Controller;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ControllerTest {
    private Controller controller;

    @Before
    public void createController() {
        List<Insurance> obligs = new LinkedList<Insurance>();


        obligs.add(new PersonalInsurance("Insurer1", "Insured1", 400000000,
                1000, 0.5f, "Person1"));
        obligs.add(new LiabilityInsurance("Insurer1", "Insured2", 8000000000l,
                5000, 0.4f, "LiablePerson1"));
        obligs.add(new PropertyInsurance("Insurer1", "Insured2", 1000000000,
                5000, 0.6f, "House"));

        controller = new Controller(obligs);
    }

    @Test
    public void shouldBeSortedDescByRisk() throws Exception {

        List<Insurance> obligs2 = new LinkedList<Insurance>();

        obligs2.add(controller.getAllBase().getObligations().get(2));
        obligs2.add(controller.getAllBase().getObligations().get(0));
        obligs2.add(controller.getAllBase().getObligations().get(1));

        Derivative der = new Derivative(obligs2);

        controller.sortingDescByRisk();


        assertEquals("derivatives not equal", der, controller.getSelected());
    }

    @Test
    public void shouldNotBeSortedAscByRisk() throws Exception {

        List<Insurance> obligs2 = new LinkedList<Insurance>();

        obligs2.add(controller.getAllBase().getObligations().get(1));
        obligs2.add(controller.getAllBase().getObligations().get(0));
        obligs2.add(controller.getAllBase().getObligations().get(2));

        Derivative der = new Derivative(obligs2);

        controller.sortingDescByRisk();


        assertNotEquals("derivatives  equal", der, controller.getSelected());
    }

    @Test
    public void selectContributions1() throws Exception {
        List<Insurance> obligs2 = new LinkedList<Insurance>();
        obligs2.add(controller.getAllBase().getObligations().get(0));

        Derivative expectedDer = new Derivative(obligs2);

        Derivative actualDer = controller.selectContributions(500, 1500);

        assertEquals("derivatives not equal", expectedDer, actualDer);
    }

    @Test
    public void selectContributions2() throws Exception {
        List<Insurance> obligs2 = new LinkedList<Insurance>();
        obligs2.add(controller.getAllBase().getObligations().get(0));
        obligs2.add(controller.getAllBase().getObligations().get(2));

        Derivative expectedDer = new Derivative(obligs2);

        Derivative actualDer = controller.selectContributions(500, 1500);


        assertNotEquals("derivatives  equal", expectedDer, actualDer);
    }

    @Test
    public void selectCompensations1() throws Exception {
        List<Insurance> obligs2 = new LinkedList<Insurance>();
        obligs2.add(controller.getAllBase().getObligations().get(0));
        obligs2.add(controller.getAllBase().getObligations().get(2));

        Derivative expectedDer = new Derivative(obligs2);

        Derivative actualDer = controller.selectCompensations(400000000, 1000000000);


        assertEquals("derivatives not equal", expectedDer, actualDer);
    }

    @Test
    public void selectCompensations2() throws Exception {
        List<Insurance> obligs2 = new LinkedList<Insurance>();
        obligs2.add(controller.getAllBase().getObligations().get(0));

        Derivative expectedDer = new Derivative(obligs2);

        Derivative actualDer = controller.selectCompensations(400000000, 1000000000);


        assertNotEquals("derivatives  equal", expectedDer, actualDer);
    }

    @Test
    public void selectRisks1() throws Exception {
        List<Insurance> obligs2 = new LinkedList<Insurance>();
        obligs2.add(controller.getAllBase().getObligations().get(0));
        obligs2.add(controller.getAllBase().getObligations().get(2));

        Derivative expectedDer = new Derivative(obligs2);

        Derivative actualDer = controller.selectRisks(0.5f, 0.6f);


        assertEquals("derivatives not equal", expectedDer, actualDer);
    }

    @Test
    public void selectRisks2() throws Exception {
        List<Insurance> obligs2 = new LinkedList<Insurance>();
        obligs2.add(controller.getAllBase().getObligations().get(1));
        obligs2.add(controller.getAllBase().getObligations().get(2));

        Derivative expectedDer = new Derivative(obligs2);

        Derivative actualDer = controller.selectRisks(0.5f, 0.6f);


        assertNotEquals("derivatives equal", expectedDer, actualDer);
    }

    @Test
    public void countSummaryCompensation1() {
        long sum = 9400000000l;
        assertEquals("sum should be true", controller.countSummaryCompensation(), sum);
    }

    @Test
    public void countSummaryCompensation2() {
        long sum = 940000000l;
        assertNotEquals("sum should be false", controller.countSummaryCompensation(), sum);
    }

    @Test
    public void countSummaryContribution1() {
        long sum = 11000;
        assertEquals("sum should be true", controller.countSummaryContribution(), sum);
    }

    @Test
    public void countSummaryContribution2() {
        long sum = 12000;
        assertNotEquals("sum should be false", controller.countSummaryContribution(), sum);
    }

    @Test
    public void shouldNotReturnNullAfterDeletingAll() {
        controller.getAllBase().delObligation(1);
        controller.getAllBase().delObligation(2);
        controller.getAllBase().delObligation(3);
        assertNotNull("is null", controller.getAllBase());
    }

    @Test
    public void shouldNotReturnNullIfNothingToSee() throws Exception {

        Derivative actualDer = controller.selectContributions(500, 600);


        assertNotNull("is null", actualDer);
    }

}
