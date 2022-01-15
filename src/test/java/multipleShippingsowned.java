import Controllers.DeedController;

import Controllers.FieldController;
import Controllers.PlayerController;

import java.io.IOException;

public class multipleShippingsowned {
    public static void main(String[] args)  throws IOException {
        DeedController deedController = new DeedController();
        FieldController fieldController = new FieldController();
        PlayerController playerController = new PlayerController();
        playerController.createPlayers(2);


        for (int i = 0; i < deedController.getShippings().length; i++) {
            deedController.getShippings()[i].setOwner(playerController.getCurrentPlayer().getPlayerID());
            deedController.multipleShipping(playerController);
            System.out.println("owner: " + deedController.getShippings()[i].getOwner() );
            System.out.println("rent: " + deedController.getShippings()[i].getRent() +"\n");

        }


    }
}
