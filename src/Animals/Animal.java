package Animals;

/**
 * Abstract class that establishes common variables and methods for subclasses.
 *
 * @author Georgia Kanellis
 */
public abstract class Animal {
    private String[] animalASCII;
    private String animalName;
    private String animalSymbol;
    protected Loyalty loyalty;

    public abstract String getAnimalSymbol();
    public abstract String[] getAnimalASCII();
    public abstract String getAnimalName();

    public Loyalty getLoyalty() {
        return loyalty;
    }

}
