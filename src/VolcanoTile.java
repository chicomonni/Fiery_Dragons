import Animals.Animal;

/**
 * The volcanoTile is where the player starts and finishes the game.
 *
 * @author Georgia Kanellis
 */
public class VolcanoTile {
    private Animal volcanoTileAnimal;
    private int volcanoTileNum;
    private boolean bIsOccupied;

    public void setVolcanoTileAnimal(Animal volcanoTileAnimal) {
        this.volcanoTileAnimal = volcanoTileAnimal;
    }

    public void setVolcanoTileNum(int volcanoTileNum) {
    }

    public void setState(boolean bIsOccupied) {
        this.bIsOccupied = bIsOccupied;
    }
}
