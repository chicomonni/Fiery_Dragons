package Animals.Characters;

import Animals.AnimalCharacter;

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
