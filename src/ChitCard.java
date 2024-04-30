import Animals.Animal;
import Animals.AnimalCharacter;

import java.util.Random;

public class ChitCard {


    private Animal chitAnimal;
    private int chitNum;
    private int cardNum;
    private boolean chitState;

    private static final String[] ChitCoveredASCII = {
            "┌─────────┐",
            "│┌───────┐│",
            "││xx     ││",
            "││   ◊   ││",
            "││  / \\  ││",
            "││ ( ● ) ││",
            "││{ )∙( }││",
            "││{ )∙( }││",
            "││ ( ● ) ││",
            "││  \\ /  ││",
            "││   ◊   ││",
            "││       ││",
            "││     xx││",
            "│└───────┘│",
            "└─────────┘",
            "           "
    };

    public void setChitAnimal(Animal[] possibleAnimals) {
        // randomise which animal type
        Random random = new Random();
        int randomIndex = random.nextInt(possibleAnimals.length);
        this.chitAnimal = possibleAnimals[randomIndex];
    }

    public void setChitNum() {
        //randomise number of animals on chit card 1-3
        Random random = new Random();
        this.chitNum = random.nextInt(3) + 1;
    }

    public void setCardNum(int cardNum) {
        // is the index of chitCardArray
        this.cardNum = cardNum;
    }

    public void setChitState(boolean chitState) {
        this.chitState = chitState;
    }

    public static String printChitCardCovered(ChitCard currentChitCard, int row) {
        if (row < 0 || row >= ChitCoveredASCII.length) {
            return ""; //return empty string for out-of-bounds rows
        }

        //make a copy of the chit card ASCII
        String[] chitCardASCII = ChitCoveredASCII.clone();

        //replace "xx" with the chit card number in the ASCII representation
        String cardNumString = String.valueOf(currentChitCard.cardNum);

        if (cardNumString.length() == 1) {
            chitCardASCII[2] = chitCardASCII[2].replace("xx", cardNumString + " ");
            chitCardASCII[12] = chitCardASCII[12].replace("xx", " " + cardNumString);
        } else {
            chitCardASCII[2] = chitCardASCII[2].replace("xx", cardNumString);
            chitCardASCII[12] = chitCardASCII[12].replace("xx", cardNumString);
        }

        //return the modified ASCII representation for the specified row
        return chitCardASCII[row];
    }


//    public static String printChitCardUncovered(int row) {
//        if (row < 0 || row >= ChitCoveredASCII.length) {
//            return ""; //return empty string for out-of-bounds rows
//        }
//        return ChitUncoveredASCII[row];
//    }

}
