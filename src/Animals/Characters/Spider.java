package Animals.Characters;

import Animals.AnimalCharacter;

public class Spider extends AnimalCharacter {

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
