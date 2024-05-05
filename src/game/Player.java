package game;

import game.tiles.GameTile;

import java.awt.*;
import java.util.Random;

public class Player {
    public final Color colour = new Color(Color.HSBtoRGB(new Random().nextInt(360), 100, 50));
    public final char displayChar = '@';
    private final String name;
    public GameTile position;
    private Player nextPlayer;

    public Player(String name, GameTile position) {
        this.name = name;
        this.position = position;
    }

    private void executeAction(Board board) {
        int cardChoice = new Random().nextInt(1, 17);
        System.out.println("Card chosen was number " + cardChoice);
    }
}
