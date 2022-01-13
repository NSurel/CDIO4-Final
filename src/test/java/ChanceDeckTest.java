import org.junit.jupiter.api.Test;
import Controllers.ChanceDeck;
import Controllers.PlayerController;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

class ChanceDeckTest {

    //Tests the draw method in the chance deck class to see if it is possible to draw 10000 cards without error.
    @Test
    void testDraw() throws IOException {
        /*
        Constructs a new chance deck and player controller.
         */
        ChanceDeck testChanceDeck = new ChanceDeck();
        PlayerController playerController = new PlayerController();
        playerController.createPlayers(6);
        /*
        The for loop draws 10000 cards from the deck.
        */
        int drawnCards = 0;
        for (int i = 0; i < 10000; i++){
            testChanceDeck.draw(playerController);
            drawnCards++;
        }
        /*
        Checks if the drawnCard variable is 10000.
        Test passes if it returns true.
        */
        assertEquals(10000, drawnCards);
    }
}