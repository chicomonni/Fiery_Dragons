package game.chitCards;

import game.chits.Chit;
import game.tiles.GameTile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

/**
 * Class used to represent a ChitCard in the game.
 */
public class ChitCard {
    private final char[][] back;
    private final char[][] front;
    private final Chit chit;
    private final int value;
    private boolean isUncovered;

    /**
     * Constructor
     *
     * @param chit   the Chit associated with the card
     * @param value  the value of the card
     * @param index  the index of the card
     */
    public ChitCard(Chit chit, int value, int index) {
        this.chit = chit;
        this.value = value;
        this.back = getBack();
        this.front = chit.getDisplayCard();

        addNumber(back, index + 1);
        addNumber(front, value);
    }

    /**
     * Flips the card.
     */
    public void flip() {
        isUncovered = true;
    }

    /**
     * Resets the card to its original covered state.
     */
    public void reset() {
        isUncovered = false;
    }

    /**
     * Retrieves the ASCII representation of this card.
     *
     * @return the ASCII representation of this card
     */
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
     * Adds the specified number to the ASCII representation of this card.
     *
     * @param card   the ASCII representation of the card
     * @param number the number to add
     */
    private void addNumber(char[][] card, int number) {
        String topNum = String.format("%-2s", number);
        String bottomNum = String.format("%2s", number);

        card[2][2] = topNum.charAt(0);
        card[2][3] = topNum.charAt(1);

        card[13][7] = bottomNum.charAt(0);
        card[13][8] = bottomNum.charAt(1);
    }

    /**
     * Checks if the card has been flipped.
     *
     * @return true if the card has been flipped, false otherwise
     */
    public boolean isCardUncovered() {
        return isUncovered;
    }

    /**
     * Checks if the card matches the Chit of the given GameTile.
     *
     * @param tile the GameTile to check against
     * @return true if the card matches, false otherwise
     */
    public boolean isMatch(GameTile tile) {
        return chit.validate(tile.getChit());
    }

    /**
     * Retrieves the modified value of the card based on its Chit.
     *
     * @return the modified value of the card
     */
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
