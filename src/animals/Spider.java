package animals;

public class Spider extends Animal {

    private Spider() {
        super('*');
    }

    public static Animal getAnimal() {
        if (instance == null) {
            instance = new Spider();
        }
        return instance;
    }
}
