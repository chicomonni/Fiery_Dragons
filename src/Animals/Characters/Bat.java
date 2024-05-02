package Animals.Characters;

import Animals.Animal;
import Animals.Loyalty;

/**
 * Concrete class that extends Animal.
 * Nanananananana.
 * ASCII art completed by: Tye Samuels
 *
 * @author Georgia Kanellis
 */
public class Bat extends Animal {
    public Bat() {
        this.loyalty = Loyalty.LOYAL;
        this.animalName = "Bat";
        this.animalSymbol = "w";
        this.animalASCII = new String[]{
                "┌─────────┐",
                "│┌───────┐│",
                "││xx     ││",
                "││       ││",
                "││       ││",
                "││       ││",
                "│__     __│",
                "│)_\\   /_(│",
                "│ '.\\‼/.` │",
                "││  '(`  ││",
                "││   \"   ││",
                "││       ││",
                "││       ││",
                "││     xx││",
                "│└───────┘│",
                "└─────────┘",
                "           "
        };
    }

}
