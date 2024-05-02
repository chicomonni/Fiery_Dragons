package Animals.Characters;

import Animals.Animal;
import Animals.Loyalty;

/**
 * Concrete class that extends Animal.
 * this one's art is super cool.
 * ASCII art completed by: Tye Samuels
 *
 * @author Georgia Kanellis
 */
public class BabyDragon  extends Animal {
    public BabyDragon() {
        this.loyalty = Loyalty.LOYAL;
        this.animalName = "Baby Dragon";
        this.animalSymbol = "0";
        this.animalASCII = new String[]{
                "┌─────────┐",
                "│┌─────   │",
                "││xx )  ( │",
                "││  (\\__/)│",
                "││  /` (/ │",
                "││ |● ●/)││",
                "││ / / /|││",
                "││o o_/ ,││",
                "││(^(  ; ││",
                "││ (_(/  ││",
                "│  ,     ││",
                "│,//(    ││",
                "│( ' )   ││",
                "│ )    xx││",
                "│└───────┘│",
                "└─────────┘",
                "           "
        };
    }

}

