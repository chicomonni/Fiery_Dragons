package game.chits;

import game.chits.strategies.ChitStrategy;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Flyweight Factory for creating Chits
 */
public class ChitFactory {
    private final Map<Character, Chit> chits = new HashMap<>();

    /**
     * If this Chit already exits, it will be returned, otherwise a new Chit is created with the information provided.
     *
     * @param c        the character representation of the Chit
     * @param name     the name of this Chit
     * @param detailed the detailed representation of the Chit
     * @param card     the card representation og the Chit
     * @param strategy the ChitStrategy associated with the Chit
     * @return the corresponding Chit instance
     */
    public Chit setChit(char c, String name, char[][] detailed, char[][] card, ChitStrategy strategy) {
        return chits.computeIfAbsent(c, c1 -> new Chit(c1, name, detailed, card, strategy));
    }

    /**
     * If this Chit already exits, it will be returned, otherwise a new Chit is created with the information provided.
     * The detailed and cards representations are read from a file. File must have the same name as the one provided,
     * but all lowercase and no spaces (e.g. Chit name is "Hello World", file name is "helloworld.txt").
     * There must be a file matching this name in both the "detailed" and "cards" folders.
     *
     * @param c        the character representation of the Chit
     * @param name     the name of this Chit, used to locate files
     * @param strategy the ChitStrategy associated with the Chit
     * @return the corresponding Chit instance
     */
    public Chit setChit(char c, String name, ChitStrategy strategy) {
        String fileName = name.toLowerCase().replaceAll(" ", "") + ".txt";
        return setChit(
                c,
                name,
                getStrings("/assets/detailed/" + fileName),
                getStrings("/assets/cards/" + fileName),
                strategy
        );
    }

    /**
     * Retrieves the character array representation of a text file.
     *
     * @param path the path to the text file
     * @return the character array representation of the text file
     */
    private char[][] getStrings(String path) {
        InputStream inputStream = Objects.requireNonNull(getClass().getResourceAsStream(path));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> lines = bufferedReader.lines().toList();
        char[][] chars = new char[lines.size()][];

        for (int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);
            chars[i] = s.toCharArray();
        }
        return chars;
    }

    /**
     * Method to get an already existing Chit. If Chit doesn't exist, a KeyException is thrown
     *
     * @param c the display character of the Chit
     * @return the Chit corresponding to the display character
     * @throws KeyException if there is no Chit instance with the given display character
     */
    public Chit getChit(char c) throws KeyException {
        if (!chits.containsKey(c)) {
            throw new KeyException("This Chit does not exist");
        }

        return chits.get(c);
    }
}
