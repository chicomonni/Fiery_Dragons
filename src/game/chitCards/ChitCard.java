package game.chitCards;

import game.chits.Chit;

public class ChitCard {
    private final Chit chit;
    private final int Value;
    private boolean isUncovered;

    public ChitCard(Chit chit, int value) {
        this.chit = chit;
        Value = value;
    }

    public void flip() {
        isUncovered = true;
    }
}
