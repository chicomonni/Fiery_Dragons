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
    private JPanel buttonPanel;
    private JPanel cardPanel;
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
        
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(1200, 800);
        frame.setResizable(false);
        frame.setVisible(true);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20,20,20,20);

        label.setForeground(Color.WHITE);
        label.setSize(1200, 600);

        frame.setLayout(new GridBagLayout());
        frame.add(label, c);

        nextPlayerButton = new JButton("End Turn");
        nextPlayerButton.setPreferredSize(new Dimension(150, 41));
        nextPlayerButton.setLocation(800, 800);
        
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(5,4));
        cardPanel.setBackground(Color.BLACK);
        frame.add(cardPanel, c);

        ArrayList<String> string = new ArrayList<String>();
        string.add("this is a dragoncard");

        for (int i = 0; i < 16; i++) {
            addCardButton(string);
        }
        cardPanel.add(nextPlayerButton, c);
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
        cardButton.setPreferredSize(new Dimension(150, 300));
        cardButton.setLocation(800, 800);
        cardButtons.add(cardButton);
        cardPanel.add(cardButton);
    }
}
