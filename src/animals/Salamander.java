package animals;

public class Salamander extends Animal {

    private Salamander() {
        super('S');
    }

    public static Animal getAnimal() {
        if (instance == null) {
            instance = new Salamander();
        }
        return instance;
    }
}
