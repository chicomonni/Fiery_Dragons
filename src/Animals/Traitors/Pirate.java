package Animals.Traitors;

/**
 * Concrete class that extends AnimalTraitor.
 * Is skully.
 * ASCII art completed by: Tye Samuels
 * @author Georgia Kanellis
 */
import Animals.AnimalTraitor;

public class Pirate extends AnimalTraitor {
    @Override
    public void betray() {
        int movement = -3;
    }

    @Override
    public String getAnimalName() {
        return "Pirate";
    }

    @Override
    public String[] getAnimalASCII() {
        return new String[] {
                "┌─────────┐",
                "│┌───────┐│",
                "││xx     ││",
                "││       ││",
                "││  .-.  ││",
                "││ /   \\ ││",
                "││ _   _ ││",
                "││|█   █|││",
                "││└‾ ^ ‾┘││",
                "││ )   ( ││",
                "││  uuu  ││",
                "││  └─┘  ││",
                "││       ││",
                "││     xx││",
                "│└───────┘│",
                "└─────────┘",
                "           "
        };
    }
}
