package game.displays;

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

public class PlayerDisplay {
    private final Map<Player, JTextArea> playerPlanes = new HashMap<>();
    private final JLayeredPane volcanoContainer;
    private final Volcano volcano;

    public PlayerDisplay(Player[] players, Volcano volcano, GameWindow gameWindow) {
        this.volcano = volcano;
        this.volcanoContainer = gameWindow.getVolcanoComponent();

        for (Player player : players) {
            update(player);
        }
    }

    public void update(Player player) {
        int[] location = calculateLocation(player.getPosition());

        String[] strings = new String[location[1] + 1];
        Arrays.fill(strings, "");
        strings[location[1]] = " ".repeat(location[0]) + player.getDisplayChar();

        JTextArea playerPlane = getPlayerPlane(player);
        playerPlane.setText(String.join("\n", Arrays.asList(strings)));
    }

    private JTextArea getPlayerPlane(Player player) {
        JTextArea playerPlane = playerPlanes.get(player);

        if (playerPlane == null) {
            playerPlane = new JTextArea();

            playerPlane.setBounds(0, 0, volcanoContainer.getWidth(), volcanoContainer.getHeight());
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

        double angle = 2 * PI / numSquares * (2 * idx + 1) * numSquares / (2 * numCaves);
        int x = (int) round(44 * cos(angle) + 53);
        int y = (int) round(-44 * sin(angle) + 53 + 7);

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

        int x = (int) round(27 * cos(idx * 2 * PI / numSquares) + 53);
        int y = (int) round(-27 * sin(idx * 2 * PI / numSquares) + 53);

        return new int[]{x, y};
    }

}
