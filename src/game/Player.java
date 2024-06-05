package game;

import game.actions.GameAction;
import game.chits.Chit;
import game.displays.DisplayManager;
import game.tiles.Cave;
import game.tiles.GameTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class Player implements Serializable {
/**
 * Player class represents a player in the game, managing their
 * position, color, name, and associated game chit. It also handles
 * the player's turn actions within the game.
 */
    private final Color colour;
    private final char displayChar = '@';
    private final String name;
    private GameTile position;
    private Cave cave;
    private Player nextPlayer;
    private Timer playTurnTimer;

    /**
     * Constructor. Initial position is inside the cave.
     *
     * @param name the name of the player
     * @param cave the player's cave
     * @param hue  the hue of the player's colour
     */
    public Player(String name, Cave cave, float hue) {
        this.name = name;
        this.position = cave;
        this.cave = cave;
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
        position.setOccupiedBy(this);
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
     * Get the Chit associated with this Player
     *
     * @return the Chit associated with this Player
     */
    public Chit getChit() {
        return cave.getChit();
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

    /**
     * Initiates the start of the Player's turn.
     *
     * @param board   the current game board
     * @param display the display manager handling game displays
     */
    public void startTurn(Board board, DisplayManager display) {
        display.startTurn(this, board);
    }

    /**
     * Executes the Player's turn by performing the provided game action.
     *
     * @param action  the game action to be executed
     * @param board   the current game board
     * @param display the display manager handling game displays
     */
    public void playTurn(GameAction action, Board board, DisplayManager display) {
        if (playTurnTimer != null && playTurnTimer.isRunning()) {
            playTurnTimer.stop();
        }
        playTurnTimer = new Timer(1000, new ActionListener() {
            GameAction currentAction = action;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentAction == null) {
                    ((Timer) e.getSource()).stop();
                    return;
                }

                currentAction = currentAction.execute(board, display);
            }
        });

        //start the timer
        playTurnTimer.start();
    }

    /**
     * Ends the Player's turn and starts the next Player's turn after a delay.
     *
     * @param board   the current game board
     * @param display the display manager handling game displays
     */
    public void endTurn(Board board, DisplayManager display) {
        display.endTurn();
        nextPlayer.startTurn(board, display);
    }
}
