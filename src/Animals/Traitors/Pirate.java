package Animals.Traitors;

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
