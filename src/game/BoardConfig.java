package game;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import characters.Chit;
import characters.ChitFlyweight;

class BoardConfig {

    /**
     * The chits that are used in the cave configuration.
     */
    private final static Chit Salamander = ChitFlyweight.getChit("Salamander", 'S', "src/ascii/CardSalamander.txt");
    private final static Chit Bat = ChitFlyweight.getChit("Bat", 'w', "src/ascii/CardBat.txt");
    private final static Chit Spider = ChitFlyweight.getChit("Spider", '*', "src/ascii/CardSpider.txt");
    private final static Chit BabyDragon = ChitFlyweight.getChit("Baby Dragon", '0', "src/ascii/CardBabyDragon.txt");

    /**
     * The cave configuration.
     * @return The cave configuration with the chits and their display coords.
     */
    public static HashMap<Chit, ArrayList<Integer>> getCaveConfig() {
        HashMap<Chit, ArrayList<Integer>> config = new HashMap<>();

        config.put(Salamander, new ArrayList<>(Arrays.asList(9, 17)));
        config.put(Bat, new ArrayList<>(Arrays.asList(9, 79)));
        config.put(Spider, new ArrayList<>(Arrays.asList(71, 17)));
        config.put(BabyDragon, new ArrayList<>(Arrays.asList(71, 79)));
        
        return config;
    }

    /**
     * The square configuration.
     * @return The square configuration with the chits.
     */
    public static ArrayList<Chit> getSquareConfig() {
        ArrayList<Chit> config = new ArrayList<>();
        return config;
    }
}