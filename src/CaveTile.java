import Animals.Animal;

/**
 * Holds the information of a single cave tile.
 *
 * @author Georgia Kanellis
 */

public class CaveTile {
    private int caveNum;
    private boolean bCanEnter;
    private boolean bIsOccupied;
    private Animal caveAnimal;

    public void setCaveNum(int caveNum) {
        this.caveNum = caveNum;
    }

    public void setCaveState(boolean bIsOccupied) {
        this.bIsOccupied = bIsOccupied;
    }

    public void setCaveAnimal(Animal caveAnimal) {
        this.caveAnimal = caveAnimal;
    }
}
