package animals;

public class Bat extends Animal {

    private Bat() {
        super('w');
    }

    public static Animal getAnimal() {
        if (instance == null) {
            instance = new Bat();
        }
        return instance;
    }
}
