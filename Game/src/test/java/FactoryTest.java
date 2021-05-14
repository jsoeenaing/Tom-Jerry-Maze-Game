import Controller.Factory;
import GameObject.Jerry;
import GameObject.Tom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class FactoryTest {
    private Factory objectCreator; //A Factory variable

    @BeforeEach
    @DisplayName("Factory should be able to create object of type Jerry and Tom")
    @Test
    public void setUp() throws Exception {
        Factory temp;

        //Initialize the objectCreator with the initializer method in Factory
        objectCreator = Factory.ObjectBuilder(); //If this work then objectCreator should not be null
        assert objectCreator != null : "Object creator is not initialized";

        temp = objectCreator; //Store objectCreator into temp

        objectCreator = Factory.ObjectBuilder(); //Calling again to test the else statement
        //Calling ObjectBuilder again shouldn't create a new factory
        assert temp == objectCreator : "The address of temp and objectCreator are different";
    }

    @DisplayName("Should be able to create a Jerry object using the Factory")
    @RepeatedTest(5)
    public void createJerryTest(){
        Jerry jerry = objectCreator.createJerry(); //Initialize jerry using the Factory
        //Jerry's position is fixed so it has to always be at x = 0, y = 630 everytime
        assert jerry.getX() == 0 && jerry.getY() == 630 : "Jerry's position is not at (0,630)";

        jerry = objectCreator.createJerry(); //Calling again to test the else statement
        //Jerry's position shouldn't change
        assert jerry.getX() == 0 && jerry.getY() == 630 : "Jerry's position is not at (0,630)";
    }

    @DisplayName("Should be able to create a Tom object using the Factory")
    @RepeatedTest(5)
    public void createTomTest(){
        //Factory should allow a Tom object to be created in any position even if
        //our game cell is in 35 x 35 pixels

        int x, y; //variables for efficiency purposes
        x = 0;
        y = 0;
        //Standard test
        Tom cat1 = objectCreator.createTom(x,y); //Initialize Tom using the Factory
        assert cat1.getX() == x && cat1.getY() == y : "cat1 position is not at (0,0)";

        x = -1000;
        y = -2345;
        //Test for negative coordinates
        Tom cat2 = objectCreator.createTom(x,y); //Initialize Tom using the Factory
        assert cat2.getX() == x && cat2.getY() == y : "cat2 position is not at (-1000,-2345)";
    }
}