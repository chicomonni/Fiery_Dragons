package Animals;

/**
 * Concrete Factory that creates Characters
 *
 * @author Georgia Kanellis
 */
public class AnimalCharacterFactory extends AnimalFactory{
    @Override
    public AnimalCharacter createAnimal() {
        return new AnimalCharacter();
    }
}
