package animals;

public class BabyDragon extends Animal {

    private BabyDragon() {
        super('0');
    }

    public static Animal getAnimal() {
        if (instance == null) {
            instance = new BabyDragon();
        }
        return instance;
    }
    
}
