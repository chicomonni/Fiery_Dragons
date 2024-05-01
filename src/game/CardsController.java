package game;
import java.util.ArrayList;
import dragoncards.DragonCard;

/**
 * CardsController is a class that represents the controller for the cards in the game.
 */
public class CardsController {
    /**
     * The cards that are used in the game.
     */
    private final ArrayList<DragonCard> cards = new ArrayList<>();

    /**
     * The constructor for the CardsController class.
     * @param cards The cards that are used in the game.
     */
    public CardsController(ArrayList<DragonCard> cards) {
        this.cards.addAll(cards);
    }

    /**
     * The default constructor for the CardsController class.
     */
    public CardsController() {
    }

    /**
     * The getCardCount method returns the number of cards in the game.
     * @return The number of cards in the game.
     */
    public int getCardCount() {
        return cards.size();
    }

    /**
     * The addCard method adds a card to the game.
     * @param card The card to add to the game.
     */
    public void addCard(DragonCard card) {
        cards.add(card);
    }

    /**
     * The shuffleCards method shuffles the cards in the game.
     */
    public void shuffleCards() {
        for (int i = 0; i < cards.size(); i++) {
            int randomIndex = (int) (Math.random() * cards.size());
            DragonCard temp = cards.get(i);
            cards.set(i, cards.get(randomIndex));
            cards.set(randomIndex, temp);
        }
    }

    /**
     * The getCardDisplays method returns the displays of the cards in the game.
     * @return
     */
    public ArrayList<String> getCardDisplays(){
        ArrayList<String> cardDisplays = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {

            DragonCard card = cards.get(i);
            cardDisplays.add(card.getDisplay(i+1));
        }
        return cardDisplays;
    }

    /**
     * The pickCard method picks a card from the game.
     * @param index The index of the card to pick.
     * @return
     */
    public DragonCard pickCard(int index) {
        try {
            DragonCard card = cards.get(index);
            if (card.isAvailable()) {
                card.setUnavailable();
                return card;
            }
        } catch (IndexOutOfBoundsException e) {
        }
        return null;
    }

    /**
     * The resetCards method resets the cards in the game.
     */
    public void resetCards() {
        for (DragonCard card: cards) {
            card.reset();
        }
    }

    /**
     * The setAllUnavailable method sets all the cards in the game to be unavailable to be picked.
     */
    public void setAllUnavailable() {
        for (DragonCard card: cards) {
            card.setUnavailable();
        }
    }

    /**
     * The setAllButOneUnavailable method sets all the cards in the game to be unavailable to be picked except for one.
     * This is used for the purposes of Sprint 2 to illustrate change of turn
     */
    public void setAllButOneUnavailable() {
        for (DragonCard card: cards) {
            card.setUnavailable();
        }
        cards.get(0).reset();
    }

    /**
     * The checkAnyAvailable method checks if any cards are available to be picked.
     * @return Whether any cards are available to be picked.
     */
    public boolean checkAnyAvailable() {
        for (DragonCard card: cards) {
            if (card.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}

