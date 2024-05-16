package game.displays;

import game.Player;
import game.tiles.GameTile;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class PlayerDisplay {
    private final Map<Player, JTextArea> playerPlanes = new HashMap<>();
    private final int noSquare;
    private final int noCaves;

    public PlayerDisplay(int noSquare, int noCaves) {
        this.noSquare = noSquare;
        this.noCaves = noCaves;
    }

    public void update(Player player) {
        int[] location = calculateLocation(player.position);
        StringBuilder stringBuilder = new StringBuilder();
    }

    public int[] calculateLocation(GameTile tile) {
        return tile.calculateLocation(this);
    }

    /**
     * Double dispatch to calculate location of a specific GameTile subclass (Cave)
     *
     * @param cave the Cave to locate
     * @return an int array containing the coordinates (x, y)
     */
    public int[] calculateLocation(Cave cave) {
        List<Square> squares = volcano.getSquares();
        List<Cave> caves = volcano.getCaves();

        int numSquares = squares.size();
        int numCaves = caves.size();

        return new int[]{0, 0};
    }

    /**
     * Double dispatch to calculate location of a specific GameTile subclass (Square)
     *
     * @param square the Square to locate
     * @return an int array containing the coordinates (x, y)
     */
    public int[] calculateLocation(Square square) {
        List<Square> squares = volcano.getSquares();
        int numSquares = squares.size();
        int idx = squares.indexOf(square);

        int x = (int) round(27 * cos(idx * 2 * PI / numSquares) + 53);
        int y = (int) round(-27 * sin(idx * 2 * PI / numSquares) + 53);

        return new int[]{x, y};
    }

}
