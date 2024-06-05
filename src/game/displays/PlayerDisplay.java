package game.displays;

import boardgenerator.BoardGenerator;
import game.Player;
import game.tiles.Cave;
import game.tiles.GameTile;
import game.tiles.Square;
import game.tiles.Volcano;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.*;

/**
 * Class used to update the display of the Players
 */
public class PlayerDisplay {
    private final Map<Player, JTextArea> playerPlanes = new HashMap<>();
    private final JLayeredPane volcanoContainer;
    private final Volcano volcano;

    /**
     * Constructor
     *
     * @param players     the array of players in the game
     * @param volcano     the Volcano instance used by the game
     * @param gameDisplay the GameDisplay instance this class affects
     */
    public PlayerDisplay(Player[] players, Volcano volcano, GameDisplay gameDisplay) {
        this.volcano = volcano;
        this.volcanoContainer = gameDisplay.getVolcanoComponent();

        for (Player player : players) {
            update(player);
        }
    }

    /**
     * Updates the display of a given player.
     *
     * @param player the player whose display is being updated
     */
    public void update(Player player) {
        int[] location = calculateLocation(player.getPosition());

        String[] strings = new String[location[1] + 1];
        Arrays.fill(strings, "");
        strings[location[1]] = " ".repeat(location[0]) + player.getDisplayChar();

        JTextArea playerPlane = getPlayerPlane(player);
        playerPlane.setText(String.join("\n", Arrays.asList(strings)));
    }

    /**
     * Gets the JTextArea associated with a player, creating it if necessary.
     *
     * @param player the player whose JTextArea is being retrieved
     * @return the JTextArea associated with the player
     */
    private JTextArea getPlayerPlane(Player player) {
        JTextArea playerPlane = playerPlanes.get(player);

        if (playerPlane == null) {
            playerPlane = new JTextArea();

            Dimension size = volcanoContainer.getSize();

            playerPlane.setBounds(0, 0, size.width, size.height);
            playerPlane.setEditable(false);
            playerPlane.setOpaque(false);
            playerPlane.setForeground(player.getColour());
            playerPlane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            volcanoContainer.add(playerPlane, Integer.valueOf(volcanoContainer.highestLayer() + 1));

            playerPlanes.put(player, playerPlane);
        }
        return playerPlane;
    }

    /**
     * Double dispatch to calculate location of a specific GameTile subclass
     *
     * @param tile the GameTile to locate
     * @return an int array containing the coordinates (x, y)
     */
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

        int idx = caves.indexOf(cave);

        int offset = BoardGenerator.VOLCANO_SIZE / 2;
        int radius = BoardGenerator.OUTER_RADIUS + BoardGenerator.CAVE_PADDING;

        double angle = 2 * PI / numSquares * (double) ((2 * idx + 1) * numSquares / (2 * numCaves));
        int x = (int) round(radius * cos(angle) + offset);
        int y = (int) round(-radius * sin(angle) + offset + 7);

        return new int[]{x, y};
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

        int offset = BoardGenerator.VOLCANO_SIZE / 2;
        int radius = BoardGenerator.OUTER_RADIUS - BoardGenerator.VOLCANO_PADDING;

        int x = (int) round(radius * cos(idx * 2 * PI / numSquares) + offset);
        int y = (int) round(-radius * sin(idx * 2 * PI / numSquares) + offset);

        return new int[]{x, y};
    }

}
