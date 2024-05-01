import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Display {
    private final Font font = Font.createFont(Font.TRUETYPE_FONT, new File("MxPlus_IBM_BIOS.ttf"));
    private final Font plainFont = new Font("MxPlus IBM BIOS", Font.PLAIN, 8);
    private final String gameName = "Fiery Dragons";

    private final FieryDragons game;

    private JFrame frame;
    private JLabel board;
    private JPanel cardPanel;
    private JLabel instructions;
    private JLabel currentPlayer;
    private JTextField input;
    private GridBagConstraints constraints;

    public Display(FieryDragons game) throws IOException, FontFormatException {
        this.game = game;
        initialise();
    }

    private void initialise() {
        frame = new JFrame(gameName);
        cardPanel = new JPanel();

        board = new JLabel("",SwingConstants.CENTER);
        instructions = new JLabel("");
        displayInstructions(null);
        currentPlayer = new JLabel("");
        input = new JTextField();

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
        
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(1200, 800);
        frame.setResizable(false);
        frame.setVisible(true);
        
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

    public void displayBoard(String text) {
        board.setText(text);
    }

    public void displayCards(ArrayList<String> cardDisplay) {  
        cardPanel.removeAll();  
        int cols = (int) Math.sqrt(cardDisplay.size());
        int rows = (int) Math.ceil(cardDisplay.size() / (double) cols);
        cardPanel.setLayout(new GridLayout(rows,cols));

        for (String card : cardDisplay) {
            cardPanel.add(getCardLabel(card));
        }
    }

    public JLabel getCardLabel(String cardDisplay) {
        JLabel label = new JLabel();
        label.setText(cardDisplay);
        label.setFont(plainFont);
        label.setForeground(Color.WHITE);
        label.setSize(200, 200);
        return label;
    }

    public void displayCurrentPlayer(String display) {
        currentPlayer.setText(display);
    }

    public void displayInstructions(String display) {
        if (display == null) {
            display = "Enter a card index to pick a card, 'X' to skip turn or 'Q' to quit:";
        }
        instructions.setText(display);
    }
}