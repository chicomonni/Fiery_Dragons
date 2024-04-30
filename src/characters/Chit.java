package characters;

public class Chit {
    private char displayChar;
    private String name;
    private String cardArtSource;

    protected Chit(String name, char displayChar, String cartArtSource) {
        this.name = name;
        this.displayChar = displayChar;
        this.cardArtSource = cartArtSource;
    }

    public String getName(){
        return name;
    }

    public char getDisplayChar() {
        return displayChar;
    }

    public String getCardArtSource() {
        return cardArtSource;
    }
}
