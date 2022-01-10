package Models.ChanceCards;

public class ChanceCard {
    private String description;
    private String type;
    private String action;

    /* The ChanceCard constructor:
       Description: The description of the unique chance card.
       Type: The type of what the card does (lose, receive, steal money etc.).
       Action: How much a player loses, receives, steals, moves etc.
       Amount: How many copies of the cards that there are.
     */

    public ChanceCard(String description, String type, String action){
        this.description = description;
        this.type = type;
        this.action = action;
    }

    // Returns the description of the ChanceCard.
    public String getDescription() {
        return description;
    }

    // Sets the description of the ChanceCard.
    public void setDescription(String description) {
        this.description = description;
    }

    // Returns the type of the ChanceCard.
    public String getType() {
        return type;
    }

    // Sets the type of the ChanceCard.
    public void setType(String type) {
        this.type = type;
    }

    // Returns the action of the ChanceCard.
    public String getAction() {
        return action;
    }

    // Sets the action of the ChanceCard.
    public void setAction(String action) {
        this.action = action;
    }
}
