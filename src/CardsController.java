import java.util.ArrayList;

public class CardsController {
    private final ArrayList<DragonCard> cards = new ArrayList<>();

    public ArrayList<DragonCard> getCards(){
        ArrayList<DragonCard> availableCards = new ArrayList<>();

        for (DragonCard card: cards) {
            if (card.isAvailable()){
                availableCards.add(card);
            }
        }

        return availableCards;
    }

    public DragonCard pickCard() {
        return cards.get(0);
    }

    public void resetCards() {
        for (DragonCard card: cards) {
            card.reset();
        }
    }
}

