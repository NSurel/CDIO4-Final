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
    int currentCardIndex = 0;
    private File file = new File("ChanceCards.txt");
    private BufferedReader r;
    private String line;

    //Populates the deck with every chance card with the right information from the readTxtInfo method.
    private void populate() throws IOException{
        String pathName = "ChanceCards.txt";
        Path path = Paths.get(pathName);
        numberOfCards = (int)Files.lines(path).count();
        String line;

        r = new BufferedReader(new FileReader(pathName));
        r.readLine();

        deck = new ChanceCard[numberOfCards];


        while((line = r.readLine()) !=null){
            String[] info = card.split(";;");

            String desc = info[0].split("::")[1];
            String type = info[1].split("::")[1];
            String act = info[2].split("::")[2];
            String amou = info[3].split("::")[3];
        }
    }

    public void populateDeck(){
        String str = readTxtInfo();
        String[] cards = str.split("££");

        for (String card : cards){
            String[] info = card.split(";;");
            String amou = info[3].split("::")[1];
            for (int i = 1; (i <= Integer.parseInt(amou)); i++) {
                numberOfCards++;
            }
        }
        deck = new ChanceCard[numberOfCards];
        
        for (String card : cards) {
            String[] info = card.split(";;");
    
            String desc = info[0].split("::")[1];
            String type = info[1].split("::")[1];
            String act = info[2].split("::")[2];
            String amou = info[3].split("::")[3];
    
            for (int i = 1; (i <= Integer.parseInt(amou)); i++) {
                deck[currentCardIndex] = new ChanceCard(desc, type, act);
                currentCardIndex++;
            }
        }
    }


    //Method used to populate the deck with the right information from the ChanceCard.txt file
    private String readTxtInfo() {
        String info = "";

        try {
            BufferedReader r = new BufferedReader(new FileReader(file));

            line = null;
            while ((line = r.readLine()) != null) {
                if(line.trim().indexOf("#") == 0)
                    continue;
                info += line.trim();
            }

            r.close();
        }
        catch (IOException a) {
            a.printStackTrace();
        }
        return info;
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


