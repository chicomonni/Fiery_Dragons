package game;
import java.util.ArrayList;
import characters.Chit;
import characters.ChitFlyweight;

class BoardConfig {

    private final static Chit Salamander = ChitFlyweight.getChit("Salamander", 'S', "src/ascii/CardSalamander.txt");
    private final static Chit Bat = ChitFlyweight.getChit("Bat", 'w', "src/ascii/CardBat.txt");
    private final static Chit Spider = ChitFlyweight.getChit("Spider", '*', "src/ascii/CardSpider.txt");
    private final static Chit BabyDragon = ChitFlyweight.getChit("Baby Dragon", '0', "src/ascii/CardBabyDragon.txt");

    public static ArrayList<Chit> getCaveConfig() {
        ArrayList<Chit> config = new ArrayList<>();

        config.add(Salamander);
        config.add(Bat);
        config.add(Spider);
        config.add(BabyDragon);
        
        return config;
    }

    public static ArrayList<Chit> getSquareConfig() {
        ArrayList<Chit> config = new ArrayList<>();
        return config;
    }
}