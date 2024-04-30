import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Display {
    private final Font font = Font.createFont(Font.TRUETYPE_FONT, new File("MxPlus_IBM_BIOS.ttf"));
    private final String gameName;

    private JFrame frame;
    private JLabel label;
    private JButton nextPlayerButton;
    private ArrayList<JButton> cardButtons = new ArrayList<>();

    Display(String gameName) throws IOException, FontFormatException {
        this.gameName = gameName;
        initialise();
    }

    void displayBoard(String text) {
        label.setText(text);
    }

    private void initialise() {
        frame = new JFrame(gameName);
        label = new JLabel("",SwingConstants.CENTER);
        label.setFont(new Font("MxPlus IBM BIOS", Font.PLAIN, 8));
        nextPlayerButton = new JButton("End Turn");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(1200, 800);
        frame.setResizable(false);
        frame.setVisible(true);

        label.setForeground(Color.WHITE);
        label.setSize(1200, 800);

        nextPlayerButton.setPreferredSize(new Dimension(150, 41));
        nextPlayerButton.setLocation(800, 800);

        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        frame.add(label);
        frame.add(nextPlayerButton);
        frame.setLayout(new FlowLayout());

        // ArrayList<String> string = new ArrayList<String>();
        // string.add("Card 1");
        // string.add("Card 2");
        // addCardButton(string);
    }

    public void addCardButton(ArrayList<String> cardDisplay) {
        StringBuilder string = new StringBuilder();
        for (String line : cardDisplay) {
            string.append(line);
            string.append("<br>");
        }
        string.insert(0, "<html>");
        string.append("</html>");
        JButton cardButton = new JButton(string.toString());
        cardButton.setPreferredSize(new Dimension(150, 200));
        cardButton.setLocation(800, 800);
        cardButtons.add(cardButton);
        frame.add(cardButton);
        cardButton.setEnabled(false);
    }
}
