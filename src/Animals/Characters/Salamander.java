package Animals.Characters;

import Animals.AnimalCharacter;

/**
 * Concrete class that extends AnimalCharacter.
 * dont have a joke for this one.
 * ASCII art completed by: Tye Samuels
 * @author Georgia Kanellis
 */
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
