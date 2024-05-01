/**
 * Controls the rules pertaining to the game
 *
 * @author Georgia Kanellis
 */
public class GameMaster {
    //GameBoard print not working properly if numChit does not divide equally by maxCardPerRow
    //throws an error
    private static int numChitCards; //default number of chit cards
    private static int numVolcanoTiles; //default
    private static int numPlayers; //default


    public void pickSettings() {
        //default settings for now
        //so no implementation
        defaultSettings();
    }

    protected void defaultSettings() {
        setNumChitCards(16);
        setNumVolcanoTiles(24);
        setNumPlayers(4);
    }

    private void setNumPlayers(int numPlayers) {
        GameMaster.numPlayers = numPlayers;
    }

    public void setNumChitCards(int numChitCards){
        GameMaster.numChitCards = numChitCards;
    }

    public void setNumVolcanoTiles(int numVolcanoTiles) {
        GameMaster.numVolcanoTiles = numVolcanoTiles;
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
