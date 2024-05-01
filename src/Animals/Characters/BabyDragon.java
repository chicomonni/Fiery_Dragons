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
    }

    @Override
    public String getAnimalSymbol() {
        return "0";
    }

    @Override
    public String[] getAnimalASCII() {
        return new String[] {
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

    @Override
    public String getAnimalName() {
        return "Baby Dragon";
    }
}

