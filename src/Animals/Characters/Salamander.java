package Animals.Characters;

import Animals.Animal;
import Animals.Loyalty;

/**
 * Concrete class that extends Animal.
 * dont have a joke for this one.
 * ASCII art completed by: Tye Samuels
 *
 * @author Georgia Kanellis
 */
public class Salamander extends Animal {
    public Salamander() {
        this.loyalty = Loyalty.LOYAL;
        this.animalName = "Salamander";
        this.animalSymbol = "S";
        this.animalASCII = new String[]{
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
}
