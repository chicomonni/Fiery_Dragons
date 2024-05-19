package game;

import game.actions.GameAction;
import game.displays.DisplayManager;
import game.tiles.GameTile;

import javax.swing.*;
import java.awt.*;

public class Player {
    private final Color colour;
    private final char displayChar = '@';
    private final String name;
    private GameTile position;
    private Player nextPlayer;

    public Player(String name, GameTile position, float hue) {
        this.name = name;
        this.position = position;
        this.colour = new Color(Color.HSBtoRGB(hue, 1, 1));
    }

    /**
     * Get this Player's current position
     *
     * @return GameTile currently occupied by this Player
     */
    public GameTile getPosition() {
        return position;
    }

    /**
     * Set this Player's position
     *
     * @param position GameTile the Player now occupies
     */
    public void setPosition(GameTile position) {
        this.position = position;
    }

    /**
     * Set the Player who will have their turn after this Player
     *
     * @param nextPlayer the next Player instance
     */
    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    /**
     * Get colour associated with this Player
     *
     * @return colour associated with this Player
     */
    public Color getColour() {
        return colour;
    }

    /**
     * Get this Player's display character
     *
     * @return this Player's display character
     */
    public char getDisplayChar() {
        return displayChar;
    }

    /**
     * Get this Player's name
     *
     * @return this Player's name
     */
    public String getName() {
        return name;
    }

    public void startTurn(Board board, DisplayManager display) {
        display.startTurn(this, board);
    }

    public void playTurn(GameAction action, Board board, DisplayManager display) {
        while (action != null) {
            action = action.execute(board, display);
        }
    }

    public void endTurn(Board board, DisplayManager display) {
        Timer timer = new Timer(1500, e -> {
            display.endTurn();
            nextPlayer.startTurn(board, display);
        });
        timer.start();
        timer.setRepeats(false);
    }
}
