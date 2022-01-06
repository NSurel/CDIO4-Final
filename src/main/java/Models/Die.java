package Models;

import java.util.Random;

public class Die {
    private Random roll;
    private int faceValue;
    private int sides;

    public Die(int sides){
        faceValue = 1;
        this.sides = sides;
    }

    public void roll(){
        roll = new Random();
        faceValue = roll.nextInt(sides)+1;
    }
    public int getFaceValue(){
        return faceValue;
    }
}
