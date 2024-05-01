package characters;

import java.util.HashMap;

/**
 * Flyweight pattern for Chits
 */
public class ChitFlyweight {
    /**
     * The cache of Chits
     */
    static HashMap<String, Chit> cache = new HashMap<>();

    /**
     * Get a Chit from the cache or create a new one if it doesn't exist
     * @param name The name of the chit
     * @param displayChar The display character of the chit
     * @param cardArtSource The location of the card art for the chit
     * @return
     */
    public static Chit getChit(String name, char displayChar, String cardArtSource) {
        if (cache.containsKey(name)) {
            return cache.get(name);
        } else {
            Chit chit = new Chit(name, displayChar, cardArtSource);
            cache.put(name, chit);
            return chit;
        }
    }
}
