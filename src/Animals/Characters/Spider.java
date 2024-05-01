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
        Loyalty loyalty = Loyalty.LOYAL;
    }

    @Override
    public String getAnimalSymbol() {
        return "*";
    }

    @Override
    public String[] getAnimalASCII() {
        return new String[] {
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

    @Override
    public String getAnimalName() {
        return "Spider";
    }
}
