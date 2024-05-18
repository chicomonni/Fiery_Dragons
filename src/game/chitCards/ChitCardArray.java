package game.chitCards;

import game.chits.Chit;
import game.chits.ChitFactory;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChitCardArray {
    private List<ChitCard> chitCards;

    public void createCards(String src, ChitFactory factory) throws KeyException {
        String[] splitSrc = src.split(",");
        chitCards = new ArrayList<>(splitSrc.length);

        for (int i = 0; i < splitSrc.length; i++) {
            String s = splitSrc[i];
            Chit chit = factory.getChit(s.charAt(0));
            chitCards.add(new ChitCard(chit, Integer.parseInt(s.substring(1)), i));
        }

        randomise();
    }

    public void flip(int i) {
        chitCards.get(i - 1).flip();
    }

    public void randomise() {
        Collections.shuffle(chitCards);
    }

    public List<ChitCard> getChitCards() {
        return chitCards;
    }
}
