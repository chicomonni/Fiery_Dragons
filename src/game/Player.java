package game;

import game.actions.SelectCardAction;
import game.displays.DisplayManager;
import game.tiles.GameTile;

import java.awt.*;
import java.util.Random;

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

    private void executeAction(Board board) {
        int cardChoice = new Random().nextInt(1, 17);
        System.out.println("Card chosen was number " + cardChoice);
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

        //select card
        selectCard(board, display);

        //if can continue turn
        //pick turn
        //if pick to continue
        // new selectCard Action


        //else
        //end turn

    }

    public void selectCard(Board board, DisplayManager display) {
        display.startTurn(this, board);
        new SelectCardAction(this).execute(board, display);
    }
}
