package characters;

import java.util.HashMap;

public class ChitFlyweight {
    static HashMap<String, Chit> cache = new HashMap<>();

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
