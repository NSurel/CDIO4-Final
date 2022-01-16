import Controllers.*;
import Models.Fields.DeedField;

import java.io.IOException;

public class Matador {
    static FieldController fieldController;

    static {
        try {
            fieldController = new FieldController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static GuiController gui = new GuiController();
    static PlayerController playerController = new PlayerController();
    static ChanceDeck chanceDeck;
    static DeedController deedController = new DeedController();

    static {
        try {
            chanceDeck = new ChanceDeck();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Cup cup = new Cup(6);
    static int playerCount;
    static boolean canEndTurn;
    static boolean endTurn;
    static int extraTurns;

    // Main starts the game and set up players
    public static void main(String[] args) throws IOException {
        deedController.createDeeds(fieldController);
        System.out.println(deedController.getProperties()[13].getType());
        gui.fixAllPrices(fieldController);
        playerCount = gui.getPlayerAmount();
        playerController.createPlayers(playerCount, gui);
        gui.createCars(playerCount);
        gui.createPlayers(playerController.getPlayers().length, playerController);
        while (gameOngoing(playerController)) {
            turn();
        }
    }
    public static void turn() {
        canEndTurn = false;
        endTurn = false;
        extraTurns = 0;
        gui.msg("Det er nu " + playerController.getCurrentPlayer().getName() + "'s tur");
        //loop that enables the player to make multiple actions each turn, ending when player selects to end turn.
        // The player can only end turn after they have rolled, and can only roll once unless rolled a pair
        while (!endTurn) {
            gui.updateGuiPlayerBal(playerController);
            gui.updateCarPos(playerController);
            gui.updateOwners(playerController, deedController);
            playerController.getCurrentPlayer().updateNetWorth(deedController.getNetValues(playerController));
            switch (gui.selectAction(canEndTurn, playerController)) {
                case "Rul":
                    roll();
                    break;
                case "Opgrader skøde":
                    upgradeProperty();
                    break;
                case "Byt skøde":
                    tradeDeed();
                    break;
                case "Sælg hus":
                    sellHouse();
                    break;
                case "Pantsæt skøde":
                    mortgage();
                    break;
                case "Afpansæt skøde":
                    unMortgage();
                    break;
                //only an option after roll
                case "Slut tur":
                    endTurn = true;
                    break;
                    //only an option in Jail
                case "Betal for frihed":
                    if (1000 >= playerController.getCurrentPlayer().getNetWorth()) {
                        playerController.getCurrentPlayer().isBroke();
                    } else {
                        playerController.getCurrentPlayer().updateBalance(-1000);
                        playerController.getCurrentPlayer().setIsJailed(false);
                    }
                    break;

                //only an option in Jail
                case "Rul for frihed":
                    cup.rollCup();
                    gui.showDice(cup);
                    if (cup.getDie1Value() == cup.getDie2Value()) {
                        playerController.getCurrentPlayer().setIsJailed(false);
                        playerController.getCurrentPlayer().updatePos(cup.getDie1Value() + cup.getDie2Value());
                        doFieldAction();
                    } else {
                        endTurn = true;
                    }
                    break;
                //only an option in Jail
                case "Brug kort":
                    playerController.getCurrentPlayer().updateGetOutOfJailCard(false);
                    playerController.getCurrentPlayer().setIsJailed(false);
                    break;
            }
        }

        playerController.updateCurrentPlayer();
        deedController.allOwnedOfSameType(fieldController);
    }

    //roll using cup, and moves the player then do field action.
    public static void roll() {
        int roll = cup.rollCup();
        if (cup.getDie1Value() == cup.getDie2Value()) {
            extraTurns++;
        } else {
            canEndTurn = true;
        }
        //Player is jailed if they roll pairs 3 times in a row
        if (extraTurns >= 3) {
            playerController.getCurrentPlayer().setPos(10);
            endTurn = true;
        } else {
            playerController.getCurrentPlayer().updatePos(roll);
            gui.updateCarPos(playerController);
            gui.showDice(cup);
            doFieldAction();
        }
    }

    //Does the action after land on a field, depended on which type of field landed on
    public static void doFieldAction() {
        String fieldType = fieldController.GetCurrentFiledType(playerController);
        switch (fieldType) {
            case "Brewery":
            case "Deed":
            case "Ferry":
                if (!deedController.isDeedOwned(playerController.getCurrentPlayer().getPos(),fieldType)){
                    if(gui.yesOrNo("Vil du købe dette skøde?")){
                        buyDeed(fieldType);
                    } else{
                        auction();
                    }
                }
                else {
                    deedController.payRent(playerController, fieldType);
                }
                break;
            case "GoToJail":
                gui.msg("Du er blevet sat i fængsel");
                playerController.setPlayerPos(10, playerController.getCurrentPlayer());
                endTurn = true;
                break;
            case "Tax":
                //At pos 4 player get a choice to either 4000kr or 10% of balance
                if (playerController.getCurrentPlayer().getPos() == 4) {
                    // balance is lower than 4000kr, player is forced to pay 10%
                    if (playerController.getCurrentPlayer().getBalance() <= 4000) {
                        playerController.getCurrentPlayer().setBalance((int) (playerController.getCurrentPlayer().getBalance() * 0.9));
                    } else {
                        if (gui.yesOrNo("Vil du betale 10% af din pengebeholdning i stedet for 4000 kr.")) {
                            playerController.getCurrentPlayer().setBalance((int) (playerController.getCurrentPlayer().getBalance() * 0.9));
                        } else {
                            playerController.getCurrentPlayer().updateBalance(-4000);
                        }
                    }
                    //At pos 38 just pay 2000kr
                } else if (playerController.getCurrentPlayer().getPos() == 38) {
                    playerController.updatePlayerBal(-2000, playerController.getCurrentPlayer());
                }
                break;
            case "Chance":
                gui.showChanceCard(chanceDeck.draw(playerController,deedController));
                //if chance card puts player in jail, and turn.
                if (playerController.getCurrentPlayer().getPos() == 10) {
                    endTurn = true;
                }
                break;
            default:
                break;
        }
    }

    public static void upgradeProperty() {
        // the bane of my existence

        String nameofproptobeupgraded = gui.getPlayernameOrPropertyName("property");
        for (int i = 0; i < fieldController.getFields().length; i++) {
            if (nameofproptobeupgraded.equals(fieldController.getFields()[i].getFieldName())) {
                DeedField tempDeedField = (DeedField) fieldController.getFields()[i];
                for (int j = 0; j < deedController.getProperties().length; j++) {
                    if (i == deedController.getProperties()[j].getPos()) {
                        if (deedController.getProperties()[j].getOwner() == playerController.getCurrentPlayer().getPlayerID()) {
                            if (gui.yesOrNo("Vil du købe et hus på denne grund for " + tempDeedField.getHousePrice())) {

                                switch (deedController.getProperties()[j].getBuildlevel()) {
                                    // not really relevant case but it gives a message what they need to do
                                    case 0:
                                        gui.msg("Du skal eje alle grunde af same farve for at kunne bygge/sælge");
                                        break;
                                    case 1:
                                        deedController.getProperties()[j].setBuildlevel(2);
                                        playerController.getCurrentPlayer().updateBalance(-tempDeedField.getHousePrice());
                                        deedController.getProperties()[j].setRent(tempDeedField.getRent2());
                                        gui.setLevel(i, deedController.getProperties()[j].getBuildlevel()-1);
                                        break;
                                    case 2:
                                        deedController.getProperties()[j].setBuildlevel(3);
                                        playerController.getCurrentPlayer().updateBalance(-tempDeedField.getHousePrice());
                                        deedController.getProperties()[j].setRent(tempDeedField.getRent3());
                                        gui.setLevel(i, deedController.getProperties()[j].getBuildlevel()-1);
                                        break;
                                    case 3:
                                        //something wrong with buyin the fourth house makes it to a hotel maybe fixed?
                                        deedController.getProperties()[j].setBuildlevel(4);
                                        playerController.getCurrentPlayer().updateBalance(-tempDeedField.getHousePrice());
                                        deedController.getProperties()[j].setRent(tempDeedField.getRent4());
                                        gui.setLevel(i, deedController.getProperties()[j].getBuildlevel()-1);
                                        break;
                                    case 4:
                                        deedController.getProperties()[j].setBuildlevel(5);
                                        playerController.getCurrentPlayer().updateBalance(-tempDeedField.getHousePrice());
                                        deedController.getProperties()[j].setRent(tempDeedField.getRent5());
                                        gui.setLevel(i, deedController.getProperties()[j].getBuildlevel()-1);
                                        break;
                                    case 5:
                                        gui.msg("Der kan ikke bygges mere på denne grund");
                                        break;
                                }
                            }
                        } else {
                            gui.msg("Du ejer ikke denne grund");
                        }
                        // would have been nice to make a message if the player typed in wrong property
                    }


                }
            }
        }

    }

    public static void buyDeed(String deedType) {

        switch (deedType) {
            case "Brewery":
                deedController.buyBrewery(playerController);
                break;
            case "Deed":
                deedController.buyProperty(playerController);
                break;
            case "Ferry":
                deedController.buyShipping(playerController);
                break;
        }
    }

    public static void sellHouse() {

        String propertyToBeDowngraded = gui.getPlayernameOrPropertyName("property");
        for (int i = 0; i < fieldController.getFields().length; i++) {
            if (propertyToBeDowngraded.equals(fieldController.getFields()[i].getFieldName())) {
                DeedField tempDeedField = (DeedField) fieldController.getFields()[i];
                for (int j = 0; j < deedController.getProperties().length; j++) {
                    if (i == deedController.getProperties()[j].getPos()) {
                        if (deedController.getProperties()[j].getOwner() == playerController.getCurrentPlayer().getPlayerID()) {
                            if (gui.yesOrNo("Vil du sælge dette hus for " + tempDeedField.getHousePrice()/2)) {

                                switch (deedController.getProperties()[j].getBuildlevel()) {
                                    case 0:
                                        gui.msg("Du skal eje alte felter af same farve for at kunne bygge/sælge");
                                        break;
                                    case 1:
                                        gui.msg("Der er ingen huse på feltet");
                                        break;
                                    case 2:
                                        deedController.getProperties()[j].setBuildlevel(1);
                                        playerController.getCurrentPlayer().updateBalance(tempDeedField.getHousePrice()/2);
                                        deedController.getProperties()[j].setRent(tempDeedField.getRent1());
                                        gui.setLevel(i, deedController.getProperties()[j].getBuildlevel()-1);
                                        break;
                                    case 3:
                                        deedController.getProperties()[j].setBuildlevel(2);
                                        playerController.getCurrentPlayer().updateBalance(tempDeedField.getHousePrice()/2);
                                        deedController.getProperties()[j].setRent(tempDeedField.getRent2());
                                        gui.setLevel(i, deedController.getProperties()[j].getBuildlevel()-1);
                                        break;
                                    case 4:
                                        deedController.getProperties()[j].setBuildlevel(3);
                                        playerController.getCurrentPlayer().updateBalance(tempDeedField.getHousePrice()/2);
                                        deedController.getProperties()[j].setRent(tempDeedField.getRent3());
                                        gui.setLevel(i, deedController.getProperties()[j].getBuildlevel()-1);
                                        break;
                                    case 5:
                                        deedController.getProperties()[j].setBuildlevel(1);
                                        playerController.getCurrentPlayer().updateBalance( (5*tempDeedField.getHousePrice())/2);
                                        deedController.getProperties()[j].setRent(tempDeedField.getRent4());
                                        gui.setLevel(i, deedController.getProperties()[j].getBuildlevel()-1);
                                        break;
                                }
                            }
                        } else {
                            gui.msg("Du ejer ikke denne ejendom");
                        }
                    }


                }
            }
        }
}
    public static void mortgage(){
         String deedName = gui.getPlayernameOrPropertyName("skødet");
         gui.msg(deedController.mortgageProperty(playerController, deedName, fieldController));

    }
    public static void unMortgage(){
        String deedName = gui.getPlayernameOrPropertyName("skødet");
        gui.msg(deedController.unMortgageProperty(playerController, deedName, fieldController));
    }

    public static void tradeDeed() {

    }

    public static void auction() {
        int pos = playerController.getCurrentPlayer().getPos();
        gui.msg("Dette skøde er nu på aktion");
        String name = gui.getPlayernameOrPropertyName("købende spiller");
        int id = playerController.getPlayerIdFromName(name);
        int amount = gui.getInt("Mængde at betale");
        if (id == -1) {
            gui.msg("Dette er ikke en spiller");
        } else {
            deedController.setOwnerToPos(id, pos, amount, playerController);
        }
    }

    public static boolean gameOngoing(PlayerController playerController) {
        boolean gaming = true;
        for (int i = 1; i <= playerController.getPlayers().length; i++) {
            if (playerController.getPlayers()[i - 1].getBalance() <= 0) {
                gaming = false;
            }
        }
        return gaming;
    }
}
