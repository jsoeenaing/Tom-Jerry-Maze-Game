package GameObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JerryTest {
    private Jerry J;

    @BeforeEach //Setting up the variables for the test
    public void setUp(){
        //Our game cell size is 35 x 35 pixels so the set location for Jerry is in multiples of 35
        J = new Jerry(140, 175);
    }

    // Tests the position
// Test for checking Jerry's position and getter functions
    @Test
    void checkposition() {
        int[] expected = {140, 175};
        assertEquals(expected[0], J.getX());
        assertEquals(expected[1], J.getY());
        System.out.println("Tests for get getX and getY passed");}

    // Testing move function along with setters
    @Test
    void checkmove() {
        J.setX(1);
        J.setY(1);
        J.move(true);
        int[] expected = {175, 210};
        assertEquals(expected[0], J.getX());
        assertEquals(expected[1], J.getY());

        J.setX(1);
        J.setY(0);
        J.move(true);
        expected[0] = 210;
        assertEquals(expected[0], J.getX());

        J.setX(0);
        J.setY(1);
        J.move(true);
        expected[1] = 245;
        assertEquals(expected[1], J.getY());

        J.setX(-1);
        J.setY(-1);
        J.move(true);
        expected[0] = 175;
        expected[1] = 210;
        assertEquals(expected[0], J.getX());
        assertEquals(expected[1], J.getY());

        J.setX(0);
        J.setY(-1);
        J.move(true);
        expected[1] = 175;
        assertEquals(expected[1], J.getY());

        J.setX(-1);
        J.setY(0);
        J.move(true);
        expected[0] = 140;
        assertEquals(expected[0], J.getX());

        System.out.println("All tests for get setX, setY and move passed");}

    // checking getDx and getDy functions
    @Test
    void checkD(){
        int[] expected = {0, 0};
        assertEquals(expected[0], J.getDx());
        assertEquals(expected[1], J.getDy());

        J.setX(1);
        J.setY(1);
        expected[0] = 1;
        expected[1] = 1;
        assertEquals(expected[0], J.getDx());
        assertEquals(expected[1], J.getDy());

        J.setX(1);
        J.setY(0);
        expected[1] = 0;
        assertEquals(expected[0], J.getDx());
        assertEquals(expected[1], J.getDy());

        J.setX(0);
        J.setY(1);
        expected[0] = 0;
        expected[1] = 1;
        assertEquals(expected[0], J.getDx());
        assertEquals(expected[1], J.getDy());

        J.setX(2);
        J.setY(4);
        assertTrue(expected[0] != J.getDx());
        System.out.println("All tests for get getDx and getDy passed");}
}
