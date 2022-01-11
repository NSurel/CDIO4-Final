package Controllers;
import Models.ChanceCards.*;

import java.io.*;
import java.nio.file.Files;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class ChanceDeck {

    private ChanceCard[] deck;
    private int numberOfCards = 0;
    private int currentCardIndex = 0;
    private int numberOfCardsDrawn;
    private File file = new File("ChanceCards.txt");
    private BufferedReader r;

    //Constructor for the chance deck.
    public ChanceDeck() throws IOException {
        numberOfCardsDrawn = 0;
        populateDeck();
        shuffle();
    }

    //Creates the chance cards for the chance deck.
    public void populateDeck() throws IOException {
        String pathName = "ChanceCards.txt";
        Path path = Paths.get(pathName);
        numberOfCards = (int) Files.lines(path).count();
        String line;

        r = new BufferedReader(new FileReader(pathName));
        r.readLine();

        deck = new ChanceCard[numberOfCards - 1];

        while ((line = r.readLine()) != null) {
            if (line.contains("#"))
                continue;
            String[] info = line.split(";;");
            String desc = info[0];
            String type = info[1];
            String act = info[2];

            deck[currentCardIndex] = new ChanceCard(desc, type, act);
            currentCardIndex++;
        }

        IOException e = new IOException("Something went wrong with ChanceCards.txt");
        throw e;
    }

    //Method for shuffling the deck randomly.
    private void shuffle () {
        Random random = new Random();

        for (int i = 0; i < deck.length; i++) {
            int randomIndexSwap = random.nextInt(deck.length);
            ChanceCard temp = deck[randomIndexSwap];
            deck[randomIndexSwap] = deck[i];
            deck[i] = temp;
        }
    }

    //Method for drawing cards from the chance deck. If all cards are drawn, the deck is reshuflled.
    public void draw(PlayerController playerController){

        if (numberOfCardsDrawn >= deck.length){
            shuffle();
            numberOfCardsDrawn = 0;
        }
        numberOfCardsDrawn++;

        ChanceCard drawnCard = deck[numberOfCardsDrawn-1];
        doAction(playerController, drawnCard);
    }

    public void doAction(PlayerController playerController, ChanceCard drawnCard){

        switch (Integer.parseInt(drawnCard.getType())){
            case 1:
                //Lose money
                break;

            case 2:
                //Receive money
                break;

            case 3:
                //Steal money
                break;

            case 4:
                //Move to...
                break;

            case 5:
                //Move x spaces
                break;

            case 6:
                //Pay x for each house and/or y for each hotel
                break;

            case 7:
                // Go to nearest shipping field
                break;

            case 8:
                //Receive "Matador legatet"
                break;

            case 9:
                //Leave jail
                break;





        }
    }


}

