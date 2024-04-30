import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import animals.Animal;
import animals.Salamander;

class CardsConfig {
    public static ArrayList<Integer> SALAMANDER_CARDS = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    public static ArrayList<Integer> BAT_CARDS = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    public static ArrayList<Integer> SPIDER_CARDS = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    public static ArrayList<Integer> BABY_DRAGON_CARDS = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    public static ArrayList<Integer> PIRATE_CARDS = new ArrayList<Integer>(Arrays.asList(1, 1, 2, 2));


    public static HashMap<Animal, ArrayList<Integer>> getConfig() {
        HashMap<Animal, ArrayList<Integer>> config = new HashMap<>();
        config.put(Salamander.getAnimal(), SALAMANDER_CARDS);
        return config;

    }
}