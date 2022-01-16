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
        while (!endTurn) {
            gui.updateGuiPlayerBal(playerController);
            gui.updateCarPos(playerController);
            gui.updateOwners(playerController, deedController);
            switch (gui.selectAction(canEndTurn, playerController)) {
                case "Rul":
                    roll();
                    break;
                case "Upgrader skøde":
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
                case "Slut tur":
                    endTurn = true;
                    break;
                case "Betal for frihed":
                    if (1000 >= playerController.getCurrentPlayer().getNetWorth()) {
                        playerController.getCurrentPlayer().isBroke();
                    } else {
                        playerController.getCurrentPlayer().updateBalance(-1000);
                        playerController.getCurrentPlayer().setIsJailed(false);
                    }
                    break;
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
                case "Brug kort":
                    playerController.getCurrentPlayer().updateGetOutOfJailCard();
                    playerController.getCurrentPlayer().setIsJailed(false);
                    break;
            }
        }

        playerController.updateCurrentPlayer();
        deedController.allOwnedOfSameType(fieldController);
    }

    public static void roll() {
        int roll = cup.rollCup();
        if (cup.getDie1Value() == cup.getDie2Value()) {
            extraTurns++;
        } else {
            canEndTurn = true;
        }
        if (extraTurns >= 3) {
            playerController.getCurrentPlayer().setPos(10);
            endTurn = true;
        } else {
            //gui.rollMsg("Roll the dies");
            playerController.getCurrentPlayer().updatePos(roll);
            gui.updateCarPos(playerController);
            gui.showDice(cup);
            doFieldAction();
        }
    }

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
                if (playerController.getCurrentPlayer().getPos() == 4) {
                    if (playerController.getCurrentPlayer().getBalance() <= 4000) {
                        playerController.getCurrentPlayer().setBalance((int) (playerController.getCurrentPlayer().getBalance() * 0.9));
                    } else {
                        if (gui.yesOrNo("Vil du betale 10% af din pengebeholdning i stedet for 4000 kr.")) {
                            playerController.getCurrentPlayer().setBalance((int) (playerController.getCurrentPlayer().getBalance() * 0.9));
                        } else {
                            playerController.getCurrentPlayer().updateBalance(-4000);
                        }
                    }
                } else if (playerController.getCurrentPlayer().getPos() == 38) {
                    playerController.updatePlayerBal(-2000, playerController.getCurrentPlayer());
                }
                break;
            case "Chance":
                gui.showChanceCard(chanceDeck.draw(playerController));
                if (playerController.getCurrentPlayer().getPos() == 10) {
                    endTurn = true;
                }
                break;
            default:
                break;
        }
    }

    public static void upgradeProperty() {
        String nameofproptobeupgraded = gui.getPlayernameOrPropertyName("skødet");
        for (int i = 0; i < fieldController.getFields().length; i++) {
            if (nameofproptobeupgraded.equals(fieldController.getFields()[i].getFieldName())) {
                DeedField tempDeedField = (DeedField) fieldController.getFields()[i];
                for (int j = 0; j < deedController.getProperties().length; j++) {
                    if (i == deedController.getProperties()[j].getPos()) {
                        if (deedController.getProperties()[j].getOwner() == playerController.getCurrentPlayer().getPlayerID()) {
                            if (gui.yesOrNo("Vil du købe et hus på denne grund for " + tempDeedField.getHousePrice())) {

                                switch (deedController.getProperties()[j].getBuildlevel()) {
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
        String propertyToBeDowngraded = gui.getPlayernameOrPropertyName("skødet");
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
                                        deedController.getProperties()[j].setBuildlevel(4);
                                        playerController.getCurrentPlayer().updateBalance(tempDeedField.getHousePrice()/2);
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
        gui.msg("Dette skøde er nu på auktion");
        String name = gui.getPlayernameOrPropertyName("den købende spiller");
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
