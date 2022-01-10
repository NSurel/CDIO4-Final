package Models.ChanceCards;

public abstract class ChanceCard {
    private String description;
    private String type;
    private String action;
    private int amount;

    /* The ChanceCard constructor:
       Description: The description on the unique chance card.
       Type: The type of what the card does (lose, receive, steal money etc.).
       Action: How much a player loses, receives, steals, moves etc.
       Amount: How many copies of the cards that there are.
     */

    public ChanceCard(String description, String type, String action, int amount){
        this.description = description;
        this.type = type;
        this.action = action;
        this.amount = amount;
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

    // Returns the amount of the ChanceCard.
    public int getAmount() {
        return amount;
    }

    // Sets the amount of the ChanceCard.
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
