package Animals;

/**
 * Abstract class that establishes common variables and methods for subclasses.
 *
 * @author Georgia Kanellis
 */
public abstract class Animal {
    private String[] animalASCII;
    private String animalName;

    public String[] getAnimalASCII() {
        return animalASCII;
    }
    public  String getAnimalName() {
        return animalName;
    }

}
