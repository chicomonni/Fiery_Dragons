package game;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Display {
    /**
     * The font used for the game.
     */
    private final Font font = Font.createFont(Font.TRUETYPE_FONT, new File("MxPlus_IBM_BIOS.ttf"));
    private final Font plainFont = new Font("MxPlus IBM BIOS", Font.PLAIN, 8);

    /**
     * The name of the game.
     */
    private final String gameName = "Fiery Dragons";

    /**
     * The game that the display is for.
     */
    private final FieryDragons game;

    /**
     * The frames, panels and labels for the display.
     */
    private JFrame frame;
    private JLabel board;
    private JPanel cardPanel;
    private JLabel instructions;
    private JLabel currentPlayer;
    private JTextField input;
    private GridBagConstraints constraints;

    /**
     * The constructor for the Display class.
     * @param game The game that the display is for.
     * @throws IOException
     * @throws FontFormatException
     */
    public Display(FieryDragons game) throws IOException, FontFormatException {
        this.game = game;
        initialise();
    }

    /**
     * The initialise method sets up the display for the game.
     */
    private void initialise() {
        frame = new JFrame(gameName);
        cardPanel = new JPanel();

        board = new JLabel("",SwingConstants.CENTER);
        instructions = new JLabel("");
        displayInstructions(null);
        currentPlayer = new JLabel("");
        input = new JTextField();

        // Add action listener to input field
        input.addActionListener(e -> {
            String text = input.getText();
            input.setText("");
            game.setInput(text);
        });

        board.setFont(plainFont);
        cardPanel.setFont(plainFont);

        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        
        // setting up frame
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(1200, 800);
        frame.setResizable(false);
        frame.setVisible(true);
        
        // setting up components
        cardPanel.setBackground(Color.BLACK);
        cardPanel.setForeground(Color.WHITE);
        cardPanel.setSize(1200, 200);
        
        board.setForeground(Color.WHITE);
        board.setSize(1200, 600);

        instructions.setForeground(Color.WHITE);
        instructions.setFont(plainFont);

        currentPlayer.setForeground(Color.WHITE);
        currentPlayer.setFont(plainFont);

        frame.setLayout(new GridBagLayout());

        constraints.gridx = 0;
        constraints.gridy = 0;
        frame.add(board, constraints);

        constraints.gridx = 1;
        frame.add(cardPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0,10,0,10);

        frame.add(instructions, constraints);

        constraints.gridy = 2;
        frame.add(currentPlayer, constraints);

        constraints.gridy = 3;
        frame.add(input, constraints);
    }

    /**
     * The displayBoard method displays the board for the game.
     * @param text
     */
    public void displayBoard(String text) {
        board.setText(text);
    }

    /**
     * The displayCards method displays the cards for the game.
     * @param cardDisplay
     */
    public void displayCards(ArrayList<String> cardDisplay) {  
        cardPanel.removeAll();  
        int cols = (int) Math.sqrt(cardDisplay.size());
        int rows = (int) Math.ceil(cardDisplay.size() / (double) cols);
        cardPanel.setLayout(new GridLayout(rows,cols));

        for (String card : cardDisplay) {
            cardPanel.add(getCardLabel(card));
        }
    }

    /**
     *  The getCardLabel method returns a label object for a card.
     * @param cardDisplay
     * @return
     */
    public JLabel getCardLabel(String cardDisplay) {
        JLabel label = new JLabel();
        label.setText(cardDisplay);
        label.setFont(plainFont);
        label.setForeground(Color.WHITE);
        label.setSize(200, 200);
        return label;
    }

    /**
     * The displayCurrentPlayer method displays the current player for the game.
     * @param display The display for the current player.
     */
    public void displayCurrentPlayer(String display) {
        currentPlayer.setText(display);
    }

    /**
     * The displayInstructions method displays the instructions for the game.
     * @param display The display for the instructions.
     */
    public void displayInstructions(String display) {
        if (display == null) {
            display = "Enter a card index to pick a card, 'X' to skip turn or 'Q' to quit:";
        }
        instructions.setText(display);
    }
}