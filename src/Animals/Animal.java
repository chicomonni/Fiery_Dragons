package Animals;

/**
 * Abstract class that establishes common variables and methods for subclasses.
 *
 * @author Georgia Kanellis
 */
public abstract class Animal {
    protected String[] animalASCII;
    protected String animalName;
    protected String animalSymbol;
    protected Loyalty loyalty;

    public String getAnimalSymbol() {
        return animalSymbol;
    }

    public String[] getAnimalASCII() {
        return animalASCII;
    };
    public String getAnimalName() {
        return animalName;
    };

    public Loyalty getLoyalty() {
        return loyalty;
    }

}
