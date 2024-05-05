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

    public int[] calculateCaveLocation(int i) {
//        int x = (int) (44 * sin(i * 2 * PI / noSquare) + 41);
//        int y = (int) (44 * cos(i * 2 * PI / noSquare) + 41);

        return new int[]{0, 0};
    }

    public int[] calculateSquareLocation(int i) {
        int x = (int) (-27 * sin(i * 2 * PI / noSquare) + 40);
        int y = (int) (27 * cos(i * 2 * PI / noSquare) + 40);

        return new int[]{x, y};
    }

}
