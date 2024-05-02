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
        this.animalName = "Pirate";
        this.animalSymbol = "X";
        this.animalASCII = new String[] {
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

    @Override
    public void betray() {
        // make movement backwards
    }

}
