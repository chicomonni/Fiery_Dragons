package Animals;

public class AnimalCharacterFactory extends AnimalFactory{
    @Override
    public AnimalCharacter createAnimal() {
        return new AnimalCharacter();
    }
}
