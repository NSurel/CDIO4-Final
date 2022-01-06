import java.util.Random;

public class Die {
    private Random roll;
    private int faceValue;
    private int sides;

    public Die(){
        faceValue = 1;
        sides = 6;
    }

    public void rollDie(){
        roll = new Random();
        faceValue = roll.nextInt(sides)+1;
    }
    public int getFaceValue(){
        return faceValue;
    }
}
