package game.displays;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Class used to update the display of the Volcano
 */
public class VolcanoDisplay {
    private final List<String> volcanoRep;
    private final JTextArea volcanoPane = new JTextArea();

    /**
     * Constructor
     *
     * @param volcanoRep the ASCII art representation of the Volcano
     */
    public VolcanoDisplay(List<String> volcanoRep, GameWindow gameWindow) {
        this.volcanoRep = volcanoRep;

        JLayeredPane volcanoContainer = gameWindow.volcano;
        initialiseComponent(volcanoContainer.getWidth(), volcanoContainer.getHeight());
        volcanoContainer.add(volcanoPane, Integer.valueOf(volcanoContainer.highestLayer() + 1));
    }

    private void initialiseComponent(int width, int height) {
        volcanoPane.setBounds(0, 0, width, height);
        volcanoPane.setEditable(false);
        volcanoPane.setOpaque(false);
        volcanoPane.setForeground(Color.WHITE);
        volcanoPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        volcanoPane.setText(String.join("\n", volcanoRep));
    }
}
