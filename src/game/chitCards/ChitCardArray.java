package game.chitCards;

import game.Player;
import game.actions.FlipCardGameAction;
import game.actions.GameAction;
import game.actions.InvalidGameAction;
import game.chits.Chit;
import game.chits.ChitFactory;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChitCardArray {
    private List<ChitCard> chitCards;

    /**
     * Creates ChitCards from a comma-separated string of Chit representations.
     *
     * @param src     the comma-separated string of Chit representations
     * @param factory the ChitFactory used to create Chit instances
     * @throws KeyException if a Chit representation in the string is invalid
     */
    public void createCards(String src, ChitFactory factory) throws KeyException {
        List<String> splitSrc = Arrays.asList(src.split(","));
        chitCards = new ArrayList<>(splitSrc.size());
//        Collections.shuffle(splitSrc);

        for (int i = 0; i < splitSrc.size(); i++) {
            String s = splitSrc.get(i);
            Chit chit = factory.getChit(s.charAt(0));
            chitCards.add(new ChitCard(chit, Integer.parseInt(s.substring(1)), i));
        }
    }

    /**
     * Retrieves the ChitCard at the specified index.
     *
     * @param i the index of the ChitCard to retrieve
     * @return the ChitCard at the specified index
     */
    public ChitCard getChitCard(int i) {
        return chitCards.get(i - 1);
    }

    /**
     * Retrieves a copy of the list of ChitCards.
     *
     * @return a copy of the list of ChitCards
     */
    public List<ChitCard> getChitCards() {
        return new ArrayList<>(chitCards);
    }

    /**
     * Returns a relevant Action if it can be performed
     *
     * @param player  the Player instance who selected the card
     * @param cardNum the number on the selected card
     * @return a relevant Action or {@code null} if it cannot be performed
     */
    public GameAction getAction(Player player, int cardNum) {
        if (cardNum < 1 || cardNum >= length() + 1) {
            return new InvalidGameAction("INVALID CARD NUMBER", player);
        }

        ChitCard chitCard = getChitCard(cardNum);
        if (chitCard.isCardUncovered()) {
            return new InvalidGameAction("CARD ALREADY FLIPPED", player);
        }

        return new FlipCardGameAction(player, chitCard);
    }

    /**
     * Get number of cards
     *
     * @return number of cards
     */
    public int length() {
        return chitCards.size();
    }
}
