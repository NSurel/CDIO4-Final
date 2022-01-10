import Controllers.DeedController;
import Models.Deeds.Deed;
import Controllers.PlayerController;
import Models.Deeds.Property;

public class Matador {
    public static void main(String[] args) {
        DeedController deedController = new DeedController();
        deedController.createDeeds();
        PlayerController players = new PlayerController();
        players.createPlayers(2);
        deedController.buyProperty(players, deedController.getProperties()[2].getValue(),2);
        System.out.println(deedController.getProperties()[2].getOwner());
        players.updateCurrentPlayer();
        deedController.buyProperty(players, deedController.getProperties()[2].getValue(),2);


    }
}
