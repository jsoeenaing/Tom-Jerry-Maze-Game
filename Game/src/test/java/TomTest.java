package GameObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TomTest {
    private Tom T;

    @BeforeEach //Setting up the variables for the test
    public void setUp(){
        //Our game cell size is 35 x 35 pixels so the set location for Tom is in multiples of 35
        T = new Tom(140, 175);
    }

    // Tests the position
// Test for checking Tom's position and getter functions
    @Test
    void checkposition() {
        int[] expected = {140, 175};
        assertEquals(expected[0], T.getX());
        assertEquals(expected[1], T.getY());
        System.out.println("Tests for get getX and getY passed");}

    // Testing move function along with setters
    @Test
    void checkmove() {
        T.setDx(1);
        T.setDy(1);
        T.move(true);
        int[] expected = {175, 210};
        assertEquals(expected[0], T.getX());
        assertEquals(expected[1], T.getY());

        T.setDx(1);
        T.setDy(0);
        T.move(true);
        expected[0] = 210;
        assertEquals(expected[0], T.getX());

        T.setDx(0);
        T.setDy(1);
        T.move(true);
        expected[1] = 245;
        assertEquals(expected[1], T.getY());

        T.setDx(-1);
        T.setDy(-1);
        T.move(true);
        expected[0] = 175;
        expected[1] = 210;
        assertEquals(expected[0], T.getX());
        assertEquals(expected[1], T.getY());

        T.setDx(0);
        T.setDy(-1);
        T.move(true);
        expected[1] = 175;
        assertEquals(expected[1], T.getY());

        T.setDx(-1);
        T.setDy(0);
        T.move(true);
        expected[0] = 140;
        assertEquals(expected[0], T.getX());

        System.out.println("All tests for get setDx, setDy and move passed");}

    // checking getDx and getDy functions
    @Test
    void checkD(){
        int[] expected = {0, 0};
        assertEquals(expected[0], T.getDx());
        assertEquals(expected[1], T.getDy());

        T.setDx(1);
        T.setDy(1);
        expected[0] = 1;
        expected[1] = 1;
        assertEquals(expected[0], T.getDx());
        assertEquals(expected[1], T.getDy());

        T.setDx(1);
        T.setDy(0);
        expected[1] = 0;
        assertEquals(expected[0], T.getDx());
        assertEquals(expected[1], T.getDy());

        T.setDx(0);
        T.setDy(1);
        expected[0] = 0;
        expected[1] = 1;
        assertEquals(expected[0], T.getDx());
        assertEquals(expected[1], T.getDy());

        T.setDx(2);
        T.setDy(4);
        assertTrue(expected[0] != T.getDx());
        System.out.println("All tests for get getDx and getDy passed");}
}
