package StickManHero;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure ;

public class MainTest {
    //    static final long serialVersionUID = 20L;
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(HeroTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("All tests passed: " + result.wasSuccessful());


    }




}
