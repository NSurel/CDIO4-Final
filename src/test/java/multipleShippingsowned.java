import Controllers.DeedController;

import Controllers.PlayerController;

import java.io.IOException;

public class multipleShippingsowned {
    public static void main(String[] args) throws IOException {

        DeedController deedController = new DeedController();
        deedController.createDeeds();
        PlayerController playerController = new PlayerController();
        playerController.createPlayers(2);



        System.out.println("Owner of 1, 2, 3, 4:" + deedController.getShippings()[0].getOwner() +", " + deedController.getShippings()[1].getOwner());
        System.out.println("rent: " + deedController.getShippings()[0].getRent());
        deedController.getShippings()[0].setOwner(playerController.getPlayers()[0].getPlayerID());
        deedController.multipleShipping(playerController);
        System.out.println("Owner of 1, 2, 3, 4:" + deedController.getShippings()[0].getOwner() +", " + deedController.getShippings()[1].getOwner() + ", " + deedController.getShippings()[2].getOwner() + ", " +deedController.getShippings()[3].getOwner() );
        System.out.println("rent: " + deedController.getShippings()[1].getRent());
        deedController.getShippings()[1].setOwner(playerController.getPlayers()[0].getPlayerID());
        deedController.multipleShipping(playerController);
        System.out.println("Owner of 1, 2, 3, 4:" + deedController.getShippings()[0].getOwner() +", " + deedController.getShippings()[1].getOwner() + ", " + deedController.getShippings()[2].getOwner() + ", " +deedController.getShippings()[3].getOwner() );
        System.out.println("rent: " + deedController.getShippings()[2].getRent() );
        deedController.getShippings()[2].setOwner(playerController.getPlayers()[0].getPlayerID());
        deedController.multipleShipping(playerController);
        System.out.println("Owner of 1, 2, 3, 4:" + deedController.getShippings()[0].getOwner() +", " + deedController.getShippings()[1].getOwner() + ", " + deedController.getShippings()[2].getOwner() + ", " +deedController.getShippings()[3].getOwner() );
        System.out.println("rent: " + deedController.getShippings()[3].getRent());
        deedController.getShippings()[3].setOwner(playerController.getPlayers()[0].getPlayerID());
        deedController.multipleShipping(playerController);
        System.out.println("Owner of 1, 2, 3, 4:" + deedController.getShippings()[0].getOwner() +", " + deedController.getShippings()[1].getOwner() + ", " + deedController.getShippings()[2].getOwner() + ", " +deedController.getShippings()[3].getOwner() );
        System.out.println("rent: " + deedController.getShippings()[0].getRent());


    }
}
