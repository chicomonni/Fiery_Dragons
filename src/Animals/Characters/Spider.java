package Animals.Characters;

import Animals.Animal;
import Animals.Loyalty;

/**
 * Concrete class that extends Animal.
 * *points*.
 * ASCII art completed by: Tye Samuels
 *
 * @author Georgia Kanellis
 */
public class Spider extends Animal {
    public Spider() {
        this.loyalty = Loyalty.LOYAL;
        this.animalName = "Spider";
        this.animalSymbol = "*";
        this.animalASCII = new String[]{
                "┌─────────┐",
                "│┌───────┐│",
                "││xx     ││",
                "││       ││",
                "││  / \\  ││",
                "││ // \\\\ ││",
                "││ \\\\●// ││",
                "││/// \\\\\\││",
                "││││   ││││",
                "││││\\_/││││",
                "││`│   │'││",
                "││ `   ' ││",
                "││       ││",
                "││     xx││",
                "│└───────┘│",
                "└─────────┘",
                "           "
        };
    }
}
