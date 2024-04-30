import Animals.Animal;
import Animals.AnimalCharacter;
import Animals.AnimalTraitor;

import java.util.Random;

public class ChitCard {


    private Animal chitAnimal;
    private int chitNum;
    private int cardNum;
    private boolean bIsUncovered;

    private static final String[] ChitCoveredASCII = {
            "┌─────────┐",
            "│┌───────┐│",
            "││xx     ││",
            "││       ││",
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
        //default chitNum to 1
        this.chitNum = 1;

        Random random = new Random();
        if (this.chitAnimal instanceof AnimalTraitor) {
            //randomise number of animalTraitors on chit card 1-2
            this.chitNum = random.nextInt(2) + 1;
        } else if (this.chitAnimal instanceof AnimalCharacter) {
            //randomise number of animalCharacters on chit card 1-3
            this.chitNum = random.nextInt(3) + 1;
        }

    }

    public void setCardNum(int cardNum) {
        // is the index of chitCardArray
        this.cardNum = cardNum;
    }

    public void setChitState(boolean bIsUncovered) {
        this.bIsUncovered = bIsUncovered;
    }

    public boolean isChitUncovered() {
        return bIsUncovered;
    }

    public static String printChitCard(ChitCard currentChitCard, int row) {
        String[] chitCardASCII;
        String cardNumString;

        if (!currentChitCard.isChitUncovered()) {
            //use the covered ASCII representation
            chitCardASCII = ChitCoveredASCII.clone();
            cardNumString = String.valueOf(currentChitCard.cardNum);
        } else {
            //use the uncovered ASCII representation
            chitCardASCII = currentChitCard.chitAnimal.getAnimalASCII().clone();
            cardNumString = String.valueOf(currentChitCard.chitNum);
        }

        if (row < 0 || row >= chitCardASCII.length) {
            return ""; //return empty string for out-of-bounds rows
        }

        //replace "xx" with the chit / card number in the ASCII representation

        if (cardNumString.length() == 1) {
            chitCardASCII[2] = chitCardASCII[2].replace("xx", cardNumString + " ");
            chitCardASCII[13] = chitCardASCII[13].replace("xx", " " + cardNumString);
        } else {
            chitCardASCII[2] = chitCardASCII[2].replace("xx", cardNumString);
            chitCardASCII[13] = chitCardASCII[13].replace("xx", cardNumString);
        }

        return chitCardASCII[row];
    }


}
