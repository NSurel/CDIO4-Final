package Controllers;
import Models.Die;

public class Cup {
    private Die die1;
    private Die die2;

    public Cup(int sides){
        die1 = new Die(sides);
        die2 = new Die(sides);
    }

    public int rollCup(){
        die1.roll();
        die2.roll();
        return die1.getFaceValue()+die2.getFaceValue();
    }
    public int getDie1Value(){
        return die1.getFaceValue();
    }
    public int getDie2Value(){
        return die2.getFaceValue();
    }
}
