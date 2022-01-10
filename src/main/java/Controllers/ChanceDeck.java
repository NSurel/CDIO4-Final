package Controllers;
import Models.ChanceCards.*;

import java.io.*;
import java.nio.file.Files;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class ChanceDeck {

    public ChanceCard[] deck;
    public int numberOfCards = 0;
    int currentCardIndex = 0;
    private File file = new File("ChanceCards.txt");
    private BufferedReader r;
    private String line;



    //Creates the chance card for the chance deck.
    public void populateDeck() throws IOException{
        String pathName = "ChanceCards.txt";
        Path path = Paths.get(pathName);
        numberOfCards = (int)Files.lines(path).count();
        String line;

        r = new BufferedReader(new FileReader(pathName));
        r.readLine();

        deck = new ChanceCard[numberOfCards-1];
        while((line = r.readLine()) !=null){
            //Checks if a line begins with #. If it does that line is null.
            // Have not added functionality that decreases the deck size.
            if(line.indexOf("#")==0)
                continue;
            String[] info = line.split(";;");
            String desc = info[0];
            String type = info[1];
            String act = info[2];

            deck[currentCardIndex] = new ChanceCard(desc, type, act);
            currentCardIndex++;
        }
    }

    //Method for shuffling the deck randomly.
    private void shuffle(){

        deck = new ChanceCard[46];

        Random random = new Random();

        for (int i = 0; i < deck.length ; i++) {
            int randomIndexSwap = random.nextInt(deck.length);
            ChanceCard temp = deck[randomIndexSwap];
            deck[randomIndexSwap] = deck[i];
            deck[i] = temp;
        }
    }

    /*Method for drawing cards from the chance deck. If all 46 cards are drawn, the deck is reshuflled.
    public void draw(){

        if (numberOfCardsDrawn >= deck.length){
            shuffle();
            numberOfCardsDrawn = 0;
        }
        numberOfCardsDrawn++;

        Implement how to display card in GUI and do the action of the card.
    }

     */
}


