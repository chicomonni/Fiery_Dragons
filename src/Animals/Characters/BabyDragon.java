package Animals.Characters;

import Animals.AnimalCharacter;

public class BabyDragon  extends AnimalCharacter {

    @Override
    public String getAnimalSymbol() {
        return "0";
    }

    @Override
    public String[] getAnimalASCII() {
        return new String[] {
                "┌─────────┐",
                "│┌─────   │",
                "││xx )  ( │",
                "││  (\\__/)│",
                "││  /` (/ │",
                "││ |● ●/)││",
                "││ / / /|││",
                "││o o_/ ,││",
                "││(^(  ; ││",
                "││ (_(/  ││",
                "│  ,     ││",
                "│,//(    ││",
                "│( ' )   ││",
                "│ )    xx││",
                "│└───────┘│",
                "└─────────┘",
                "           "
        };
    }

    @Override
    public String getAnimalName() {
        return "Baby Dragon";
    }
}
