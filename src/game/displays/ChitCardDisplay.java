package game.displays;

import game.FieryDragons;
import game.chitCards.ChitCard;
import game.chitCards.ChitCardArray;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used to update the display of the ChitCards
 */
public class ChitCardDisplay {
    private final Map<ChitCard, JTextArea> chitCardPlanes = new HashMap<>();
    private final JPanel cardPane = new JPanel();

    /**
     * Constructor
     *
     * @param cardArray  the ChitCardArray instance used by the game
     * @param gameWindow the GameWindow instance this class affects
     */
    public ChitCardDisplay(ChitCardArray cardArray, GameWindow gameWindow) {
        JPanel cardContainer = gameWindow.getChitCardsComponent();
        initialiseComponent();

        cardContainer.add(cardPane);

        for (int i = 0; i < cardArray.getChitCards().size(); i++) {
            ChitCard chitCard = cardArray.getChitCards().get(i);
            update(chitCard);
        }
    }

    /**
     * Initializes the display component for the ChitCards.
     */
    private void initialiseComponent() {
        cardPane.setLayout(new FlowLayout(FlowLayout.CENTER, GameWindow.PADDING, GameWindow.PADDING));
        // TODO: make more extensible
        cardPane.setPreferredSize(new Dimension(
                (int) (FieryDragons.CARD_WIDTH * GameWindow.ASCII_FONT_SIZE * 4 + GameWindow.PADDING * 5),
                (int) (FieryDragons.CARD_HEIGHT * GameWindow.ASCII_FONT_SIZE * 4 + GameWindow.PADDING * 5)
        ));
        cardPane.setOpaque(false);
    }

    /**
     * Updates the display of a ChitCard.
     *
     * @param card the ChitCard to be updated
     */
    public void update(ChitCard card) {
        JTextArea cardPlane = getCardPlane(card);
        cardPlane.setText(getCardForDisplay(card));
    }

    /**
     * Retrieves the JTextArea associated with a ChitCard.
     *
     * @param card the ChitCard
     * @return the JTextArea associated with the ChitCard
     */
    private JTextArea getCardPlane(ChitCard card) {
        JTextArea cardPlane = chitCardPlanes.get(card);

        if (cardPlane == null) {
            cardPlane = new JTextArea();

            cardPlane.setEditable(false);
            cardPlane.setOpaque(false);
            cardPlane.setForeground(Color.WHITE);
            cardPlane.setSize(1, 1);
            cardPlane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            cardPane.add(cardPlane);

            chitCardPlanes.put(card, cardPlane);
        }
        return cardPlane;
    }

    /**
     * Generates the ASCII representation of a ChitCard for display.
     *
     * @param card the ChitCard
     * @return the ASCII representation of the ChitCard
     */
    private String getCardForDisplay(ChitCard card) {
        char[][] chars = card.getASCIIRep();

        List<String> cardASCIIRep = new ArrayList<>(chars.length);
        for (char[] aChar : chars) {
            cardASCIIRep.add(String.copyValueOf(aChar));
        }

        return String.join("\n", cardASCIIRep);
    }

    /**
     * Resets the display of all ChitCards.
     */
    public void reset() {
        for (ChitCard card : chitCardPlanes.keySet()) {
            card.reset();
            update(card);
        }
    }

}
