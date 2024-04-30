package Animals.Characters;

import Animals.AnimalCharacter;

public class Salamander extends AnimalCharacter {

    @Override
    public String getAnimalSymbol() {
        return "S";
    }

    @Override
    public String[] getAnimalASCII() {
        return new String[] {
                "┌─────────┐",
                "│┌───────┐│",
                "││xx     ││",
                "││       ││",
                "││  .-_  ││",
                "││ ' ● ‾; │",
                "││:.-_ ,-<|│",
                "││`. (    │",
                "││  \\ \\  ││",
                "││ .-\\ \\ ││",
                "││( __. .││",
                "││\\_ _ .'││",
                "││       ││",
                "││     xx││",
                "│└───────┘│",
                "└─────────┘",
                "           "
        };
    }

    @Override
    public String getAnimalName() {
        return "Salamander";
    }
}
