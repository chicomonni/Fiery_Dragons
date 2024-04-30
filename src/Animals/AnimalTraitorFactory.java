package Animals;
import Animals.Traitors.Pirate;

/**
 * Concrete Factory that creates Traitors
 *
 * @author Georgia Kanellis
 */
public class AnimalTraitorFactory extends AnimalFactory {
    @Override
    public AnimalTraitor createAnimal() {
        return new Pirate(); // change when more traitors are implemented
    }
}
