package game.chitCards;

import game.chits.Chit;
import game.tiles.GameTile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class ChitCard {
    private final char[][] back;
    private final char[][] front;
    private final Chit chit;
    private final int value;
    //TODO: remove test
    private boolean isUncovered = true;

    public ChitCard(Chit chit, int value, int index) {
        this.chit = chit;
        this.value = value;
        this.back = getBack();
        this.front = chit.getDisplayCard();

        addNumber(back, index + 1);
        addNumber(front, value);
    }

    public void flip() {
        isUncovered = true;
    }

    public void reset() {
        isUncovered = false;
    }

    private char[][] getBack() {
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
        return isUncovered ? clone2D(front) : clone2D(back);
    }

    /**
     * Method to add desired number on the ASCII representation of this card
     */
    private void addNumber(char[][] card, int number) {
        String topNum = String.format("%-2s", number);
        String bottomNum = String.format("%2s", number);

        card[2][2] = topNum.charAt(0);
        card[2][3] = topNum.charAt(1);

        card[13][7] = bottomNum.charAt(0);
        card[13][8] = bottomNum.charAt(1);
    }

    public boolean isCardUncovered() {
        return isUncovered;
    }

    public boolean isMatch(GameTile tile) {
        return chit.validate(tile.getChit());
    }

    public int getValue() {
        return chit.modifyValue(value);
    }

    private char[][] clone2D(char[][] array) {
        char[][] clone = new char[array.length][];

        for (int i = 0; i < array.length; i++) {
            char[] chars = array[i];
            clone[i] = chars.clone();
        }

        return clone;
    }
}
