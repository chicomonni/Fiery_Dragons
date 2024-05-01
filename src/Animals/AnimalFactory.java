package Animals;

import Animals.Characters.*;
import Animals.Traitors.*;

/**
 * Factory method design pattern responsible for creating Animals
 *
 * @author Georgia Kanellis
 */
public class AnimalFactory {

    public Bat createBat() {
        return new Bat();
    }

    public Salamander createSalamander() {
        return new Salamander();
    }

    public BabyDragon createBabyDragon() {
        return new BabyDragon();
    }

    public Spider createSpider() {
        return new Spider();
    }

    public Pirate createPirate() {
        return new Pirate();
    }

}
