/**
 * Controls the rules pertaining to the game
 *
 * @author Georgia Kanellis
 */
public class GameMaster {
    //GameBoard print not working properly if numChit does not divide equally by maxCardPerRow
    //throws an error
    private static final int numChitCards = 16; //default number of chit cards
    private static final int numVolcanoTiles = 24; //default
    private static final int numPlayers = 4; //default


    public void pickSettings() {
        //default settings for now
        //so no implementation
    }

    public static int getNumChitCards() {
        return numChitCards;
    }

    public static int getNumVolcanoTiles() {
        return numVolcanoTiles;
    }

    public static int getNumPlayers() {
        return numPlayers;
    }
}
