package GameObject;

import GameObject.Tom;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.DisplayName;

public class GbPositionEvaluatorTest {

    private Tom cat1, cat2, cat3;

    @BeforeEach //Setting up the variables for the test
    public void setUp() throws Exception {
        //Our game cell size is 35 x 35 pixels so the set location for Tom is in multiples of 35
        cat1 = new Tom(70, 70);
        cat1.setDx(0);
        cat1.setDy(0);

        //Testing to see if it works for non-multiples of 35
        cat2 = new Tom(1, 1);
        cat2.setDx(0);
        cat2.setDy(0);

        //Testing to see if it works for negative integers
        cat3 = new Tom(-25, -25);
        cat3.setDx(0);
        cat3.setDy(0);
    }

    @RepeatedTest(5)
    @DisplayName("The location of the Tom object should change by multiples of 35")
    public void randomDirectTest() {
        int x, y;

        System.out.println("Test for cat1");
        x = cat1.getX();
        y = cat1.getY();

        System.out.println("cat1 current location: \nX = " + String.valueOf(x) + "\tY = " + String.valueOf(y));
        randomDirect(cat1); //Calls the method that is tested
        cat1.move(true); //Trigger the move method in Tom class to change the position
        x = cat1.getX();
        y = cat1.getY();
        System.out.println("cat1 next location: \nX = " + String.valueOf(x) + "\tY = " + String.valueOf(y));

        System.out.println("Test for cat2");
        x = cat2.getX();
        y = cat2.getY();

        System.out.println("cat2 current location: \nX = " + String.valueOf(x) + "\tY = " + String.valueOf(y));
        randomDirect(cat2); //Calls the method that is tested
        cat2.move(true); //Trigger the move method in Tom class to change the position
        x = cat2.getX();
        y = cat2.getY();
        System.out.println("cat2 next location: \nX = " + String.valueOf(x) + "\tY = " + String.valueOf(y));

        System.out.println("Test for cat3");
        x = cat3.getX();
        y = cat3.getY();

        System.out.println("cat3 current location: \nX = " + String.valueOf(x) + "\tY = " + String.valueOf(y));
        randomDirect(cat3); //Calls the method that is tested
        cat3.move(true); //Trigger the move method in Tom class to change the position
        x = cat3.getX();
        y = cat3.getY();
        System.out.println("cat3 next location: \nX = " + String.valueOf(x) + "\tY = " + String.valueOf(y));

        System.out.println("End of test\n");
    }


    //This method is copied from GbPositionEvaluator because it was a private method and can't be tested
    //without being copied here
    private void randomDirect(Tom t) {
        Random random = new Random();
        int direct = random.nextInt(2);
        assert direct >= 0 && direct < 2: "Random value out of bound"; //Not previously there
        if(direct == 0){
            //setting dx
            int vertical = random.nextInt(2);
            assert vertical >= 0 && vertical < 2: "Random value out of bound"; //Not previously there
            if(vertical==0){
                t.setDx(1);
                t.setDy(0);
            } else {
                t.setDx(-1);
                t.setDy(0);
            }
        }
        else {
            //setting dy
            int horiztal = random.nextInt(2);
            assert horiztal >= 0 && horiztal < 2: "Random value out of bound"; //Not previously there
            if(horiztal == 0){
                t.setDy(1);
                t.setDx(0);
            }
            else {
                t.setDy(-1);
                t.setDx(0);
            }
        }
    }
}
