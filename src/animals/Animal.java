package animals;

public abstract class Animal {
    protected static Animal instance = null;
    protected char displayChar;

    protected Animal(char displayChar) {
        this.displayChar = displayChar;
    }

    // abstract static Animal getAnimal();

    public char getDisplayChar() {
        return displayChar;
    }
}
