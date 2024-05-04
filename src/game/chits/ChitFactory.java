package game.chits;

import game.chits.strategies.ChitStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Flyweight Factory for creating Chits
 */
public class ChitFactory {
    private final Map<Character, Chit> chits = new HashMap<>();

    /**
     * If this Chit already exits, it will be returned, otherwise a new Chit is created with the information provided.
     *
     * @param c        the character representation of the Chit
     * @param detailed the detailed representation of the chit, for the ChitCards. Each entry in the List represents
     *                 a new line of the representation.
     * @param strategy the ChitStrategy associated with the Chit
     * @return the corresponding Chit instance
     */
    public Chit setChit(char c, List<String> detailed, ChitStrategy strategy) {
        Chit chit = chits.get(c);

        if (chits.get(c) == null) {
            chit = new Chit(c, detailed, strategy);
            chits.put(c, chit);
        }

        return chit;
    }

    /**
     * If this Chit already exits, it will be returned, otherwise a new Chit is created with the information provided.
     * The detailed representation is read from a file.
     *
     * @param c        the character representation of the Chit
     * @param path     the path to the file containing the detailed representation
     * @param strategy the ChitStrategy associated with the Chit
     * @return the corresponding Chit instance
     * @throws IOException if an I/O error occurs reading from the file or a malformed or unmappable byte sequence
     *                     is read
     */
    public Chit setChit(char c, String path, ChitStrategy strategy) throws IOException {
        List<String> detailed = Files.readAllLines(Paths.get(path));
        return setChit(c, detailed, strategy);
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
