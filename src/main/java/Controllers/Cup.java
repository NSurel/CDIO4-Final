package Controllers;
import Models.Die;

public class Cup {
    private Die die1;
    private Die die2;


    public int rollCup(){
        die1.roll();
        die2.roll();
        return die1.getFaceValue()+die2.getFaceValue();
    }
}
