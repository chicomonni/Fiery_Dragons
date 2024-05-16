package game.displays;

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
    private final ChitCardArray cardArray;
    private final JPanel cardPane = new JPanel();

    /**
     * Constructor
     *
     * @param cardArray  the ChitCardArray instance used by the game
     * @param gameWindow the GameWindow instance this class affects
     */
    public ChitCardDisplay(ChitCardArray cardArray, GameWindow gameWindow) {
        this.cardArray = cardArray;

        JPanel cardContainer = gameWindow.getChitCardsComponent();
        initialiseComponent();

        cardContainer.add(cardPane);

        for (int i = 0; i < cardArray.getChitCards().size(); i++) {
            ChitCard chitCard = cardArray.getChitCards().get(i);
            update(chitCard);
        }
    }

    private void initialiseComponent() {
        cardPane.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        // TODO: make more extensible
        cardPane.setPreferredSize(new Dimension(11 * 5 * 4 + 30 * 3, 16 * 6 * 4 + 30 * 3));
        cardPane.setOpaque(false);
    }

    public void update(ChitCard card) {
        JTextArea cardPlane = getCardPlane(card);
        cardPlane.setText(getCardForDisplay(card));
    }

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

    private String getCardForDisplay(ChitCard card) {
        char[][] chars = card.getASCIIRep();

        List<String> cardASCIIRep = new ArrayList<>(chars.length);
        for (char[] aChar : chars) {
            cardASCIIRep.add(String.copyValueOf(aChar));
        }

        return String.join("\n", cardASCIIRep);
    }


}
