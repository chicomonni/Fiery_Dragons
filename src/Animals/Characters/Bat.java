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
    }

    @Override
    public String getAnimalSymbol() {
        return "w";
    }

    @Override
    public String[] getAnimalASCII() {
        return new String[] {
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

    @Override
    public String getAnimalName() {
        return "Bat";
    }
}
