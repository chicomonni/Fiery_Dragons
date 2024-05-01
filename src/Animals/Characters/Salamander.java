package Animals.Characters;

import Animals.Animal;
import Animals.Loyalty;

/**
 * Concrete class that extends Animal.
 * dont have a joke for this one.
 * ASCII art completed by: Tye Samuels
 * @author Georgia Kanellis
 */
public class Salamander extends Animal {
    public Salamander() {
        Loyalty loyalty = Loyalty.LOYAL;
    }

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
