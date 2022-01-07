package Controllers;
import Models.ChanceCards.*;

import java.util.Arrays;
import java.util.Random;

public class ChanceDeck {

    private ChanceCard[] cards;
    private int numberOfCardsDrawn;




    //Method for shuffling the deck randomly.
    private void shuffle(){

        cards = new ChanceCard[46];

        Random random = new Random();

        for (int i = 0; i < cards.length ; i++) {
            int randomIndexSwap = random.nextInt(cards.length);
            ChanceCard temp = cards[randomIndexSwap];
            cards[randomIndexSwap] = cards[i];
            cards[i] = temp;
        }
    }

    //Method for drawing cards from the chance deck. If all 46 cards are drawn, the deck is reshuflled.
    public void draw(){

        if (numberOfCardsDrawn >= cards.length){
            shuffle();
            numberOfCardsDrawn = 0;
        }
        numberOfCardsDrawn++;

        //Implement how to display card in GUI and do the action of the card.
    }

    public void populate(){
    }


}


