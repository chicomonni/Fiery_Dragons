import java.util.ArrayList;
import dragoncards.DragonCard;

public class CardsController {
    private final ArrayList<DragonCard> cards = new ArrayList<>();

    public CardsController(ArrayList<DragonCard> cards) {
        this.cards.addAll(cards);
    }

    public CardsController() {
    }

    public int getCardCount() {
        System.out.println(cards.size());
        return cards.size();
    }

    public void addCard(DragonCard card) {
        cards.add(card);
    }

    public void shuffleCards() {
        for (int i = 0; i < cards.size(); i++) {
            int randomIndex = (int) (Math.random() * cards.size());
            DragonCard temp = cards.get(i);
            cards.set(i, cards.get(randomIndex));
            cards.set(randomIndex, temp);
        }
    }

    public ArrayList<String> getCardDisplays(){
        ArrayList<String> cardDisplays = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {

            DragonCard card = cards.get(i);
            if (card.isAvailable()) {
                cardDisplays.add(card.getBackDisplay(i+1));
            } else {
                cardDisplays.add(card.getFrontDisplay());
            }
        }
        return cardDisplays;
    }

    public DragonCard pickCard() {
        return cards.get(0);
    }

    public void resetCards() {
        for (DragonCard card: cards) {
            card.reset();
        }
    }

    public void setAllUnavailable() {
        for (DragonCard card: cards) {
            card.setUnavailable();
        }
    }
}

