package game.chitCards;

import game.chits.Chit;
import game.chits.ChitFactory;

import java.security.KeyException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChitCardArray {
    private ChitCard[] chitCards;

    public void createCards(String src, ChitFactory factory) throws KeyException {
        chitCards = new ChitCard[src.length() / 2];

        for (int i = 0; i < src.length() - 2; i += 2) {
            Chit chit = factory.getChit(src.charAt(i));
            chitCards[i / 2] = new ChitCard(chit, Character.getNumericValue(src.charAt(i + 1)));
        }
    }

    public void flip(int i) {
        chitCards[i - 1].flip();
    }

    public void randomise() {
        List<ChitCard> list = Arrays.asList(chitCards);
        Collections.shuffle(list);
        list.toArray(chitCards);
    }
}
