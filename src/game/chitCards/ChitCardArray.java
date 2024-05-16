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

        for (String s : splitSrc) {
            Chit chit = factory.getChit(s.charAt(0));
            chitCards.add(new ChitCard(chit, Integer.parseInt(s.substring(1))));
        }
    }

    public void flip(int i) {
        chitCards.get(i - 1).flip();
    }

    public void randomise() {
        Collections.shuffle(chitCards);
    }
}
