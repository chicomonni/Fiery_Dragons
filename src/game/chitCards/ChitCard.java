package game.chitCards;

import game.chits.Chit;

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
    private boolean isUncovered;
    private int cardNum;

    public ChitCard(Chit chit, int value) { //, int index
        this.chit = chit;
        this.value = value;
        this.back = getBack(cardNum);

        this.front = chit.getDisplayCard();
        this.replacePlaceHolderNum(this.front, this.value);

    }

    public void flip() {
        isUncovered = true;
    }

    private char[][] getBack(int cardNum) {
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
        return isUncovered ? front : back;
    }

    protected void setCardNum(int index) {
        this.cardNum = index + 1;
        this.replacePlaceHolderNum(back, this.cardNum);
    }

    /**
     * Method to replace the "xx" placeholder with the desired number on the ASCII representation of this card
     *
     */
    private void replacePlaceHolderNum(char[][] cardSide, int number) {
        String numString = String.valueOf(number);
        if (numString.length() == 1) {
            cardSide[2] = new String(cardSide[2]).replace("xx", numString + " ").toCharArray();
            cardSide[13] = new String(cardSide[13]).replace("xx", " " + numString).toCharArray();
        } else {
            cardSide[2] = new String(cardSide[2]).replace("xx", numString).toCharArray();
            cardSide[13] = new String(cardSide[13]).replace("xx", numString).toCharArray();
        }
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
}
