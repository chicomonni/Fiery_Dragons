package Animals.Characters;

import Animals.AnimalCharacter;

/**
 * Concrete class that extends AnimalCharacter.
 * Nanananananana.
 * ASCII art completed by: Tye Samuels
 * @author Georgia Kanellis
 */
public class Bat extends AnimalCharacter {

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
