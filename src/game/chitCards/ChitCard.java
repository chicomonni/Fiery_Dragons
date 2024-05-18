package game.chitCards;

import game.chits.Chit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class ChitCard {
    private final char[][] cardBack = getCardBack();
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

    private char[][] getCardBack() {
        InputStream inputStream = Objects.requireNonNull(getClass().getResourceAsStream("/assets/cards/back.txt"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> lines = bufferedReader.lines().toList();
        char[][] chars = new char[lines.size()][];

        for (int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);
            chars[i] = s.toCharArray();
        }
        return chars;
    }

    /**
     * Method to get the ASCII representation of this card
     *
     * @return the ASCII representation of this card
     */
    public char[][] getASCIIRep() {
        return isUncovered ? chit.getDisplayCard() : cardBack;
    }
}
