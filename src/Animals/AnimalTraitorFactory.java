package Animals;

import Animals.Traitors.Pirate;

public class AnimalTraitorFactory extends AnimalFactory {
    @Override
    public AnimalTraitor createAnimal() {
        return new Pirate(); // change when more traitors are implemented
    }
}
