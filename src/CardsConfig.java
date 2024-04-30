import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import characters.Chit;
import characters.ChitFlyweight;

class CardsConfig {
    public static ArrayList<Integer> SALAMANDER_CARDS = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    public static ArrayList<Integer> BAT_CARDS = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    public static ArrayList<Integer> SPIDER_CARDS = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    public static ArrayList<Integer> BABY_DRAGON_CARDS = new ArrayList<Integer>(Arrays.asList(1, 2, 3));

    public static ArrayList<Integer> PIRATE_CARDS = new ArrayList<Integer>(Arrays.asList(1, 1, 2, 2));


    public static HashMap<Chit, ArrayList<Integer>> getAnimalConfig() {
        HashMap<Chit, ArrayList<Integer>> config = new HashMap<>();

        config.put(ChitFlyweight.getChit("Salamander", 'S', "src/ascii/CardSalamander.txt"), SALAMANDER_CARDS);
        config.put(ChitFlyweight.getChit("Bat", 'w', "src/ascii/CardBat.txt"), BAT_CARDS);
        config.put(ChitFlyweight.getChit("Spider", '*', "src/ascii/CardSpider.txt"), SPIDER_CARDS);
        config.put(ChitFlyweight.getChit("Baby Dragon", '0', "src/ascii/CardBabyDragon.txt"), BABY_DRAGON_CARDS);
        return config;
    }

    public static HashMap<Chit, ArrayList<Integer>> getPirateConfig() {
        HashMap<Chit, ArrayList<Integer>> config = new HashMap<>();

        config.put(ChitFlyweight.getChit("Pirate", 'P', "src/ascii/CardPirate.txt"), PIRATE_CARDS);
        return config;
    }
}