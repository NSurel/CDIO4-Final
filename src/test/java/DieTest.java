import org.junit.jupiter.api.Test;
import Models.Die;

import static org.junit.jupiter.api.Assertions.*;


public class DieTest {
    /*
    Here we are testing that 6-sided die-instances are reliably rolling between 1-6
    with a test of 60000 rolls with an allowed delta of 400. So every number
    between 1-6 should be shown 10000 +- 400 amount of times with a 6-sided die.
     */
    @Test
    void rollDie() {
        /*
        Constructing a die object, an array with 6 elements and a variable x.
        */
        Die die = new Die(6);
        int[] facevalues = new int[6];
        int x;
        /*
        With a for loop and switch cases, we can continually roll the die
        until we reach the limit of 60000. With each case of the possible
        face values of 1-6, +1 is added to the corresponding element in the array.
        */
        for (int i = 0; i < 60000; i++) {
            die.roll();
            x = die.getFaceValue();
            switch (x) {
                case 1:
                    facevalues[x - 1]++;
                    break;
                case 2:
                    facevalues[x - 1]++;
                    break;
                case 3:
                    facevalues[x - 1]++;
                    break;
                case 4:
                    facevalues[x - 1]++;
                    break;
                case 5:
                    facevalues[x - 1]++;
                    break;
                case 6:
                    facevalues[x - 1]++;
                    break;
            }

        }
        /*
        Checks if the arrays elements are in the accepted range of 9600-10400.
        Test passes if it returns true.
        */
        assertTrue(facevalues[0] >= 9600 && facevalues[0] <= 10400);
        assertTrue(facevalues[1] >= 9600 && facevalues[1] <= 10400);
        assertTrue(facevalues[2] >= 9600 && facevalues[2] <= 10400);
        assertTrue(facevalues[3] >= 9600 && facevalues[3] <= 10400);
        assertTrue(facevalues[4] >= 9600 && facevalues[4] <= 10400);
        assertTrue(facevalues[5] >= 9600 && facevalues[5] <= 10400);


        System.out.println(facevalues[0] + " " + facevalues[1] + " " + facevalues[2] + " " + facevalues[3] + " " + facevalues[4] + " " + facevalues[5]);
    }
}