package Animals.Traitors;

import Animals.Animal;
import Animals.Loyalty;

/**
 * Concrete class that extends Animal.
 * Is skully.
 * ASCII art completed by: Tye Samuels
 *
 * @author Georgia Kanellis
 */

public class Pirate extends Animal implements Traitor {

    public Pirate() {
        this.loyalty = Loyalty.TRAITOR;
    }
    @Override
    public void betray() {
        int movement = -3;
    }

    @Override
    public String getAnimalName() {
        return "Pirate";
    }

    @Override
    public String getAnimalSymbol() {
        return "x";
    }

    @Override
    public String[] getAnimalASCII() {
        return new String[] {
                "┌─────────┐",
                "│┌───────┐│",
                "││xx     ││",
                "││       ││",
                "││  .-.  ││",
                "││ /   \\ ││",
                "││ _   _ ││",
                "││|█   █|││",
                "││└‾ ^ ‾┘││",
                "││ )   ( ││",
                "││  uuu  ││",
                "││  └─┘  ││",
                "││       ││",
                "││     xx││",
                "│└───────┘│",
                "└─────────┘",
                "           "
        };
    }
}
