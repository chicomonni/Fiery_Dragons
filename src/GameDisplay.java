import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Holds the JFrame GameDisplay implementation
 * Created by: Tye Samuels
 * Modified by: Georgia Kanellis
 *
 */

class GameDisplay {
    private static GameDisplay instance;

    private final String gameName;
    private final Font squareFont;

    private static JFrame frame;
    private static JLabel label;
    private JTextField inputField;
    private JTextField promptField;
    private JPanel promptPanel;
    private JPanel inputPanel;

    private GameDisplay(String gameName) throws IOException, FontFormatException {
        this.gameName = gameName;
        this.squareFont = Font.createFont(Font.TRUETYPE_FONT, new File("MxPlus_IBM_BIOS.ttf"));
        initialise();
    }

    public static GameDisplay getInstance(String gameName) throws IOException, FontFormatException {
        if (instance == null) {
            instance = new GameDisplay(gameName);
        }
        return instance;
    }

    public void setText(String text) {
        label.setText(text);
        label.setFont(new Font("MxPlus IBM BIOS", Font.PLAIN, 8));
    }

    public void clearInputField() {
        inputField.setText("");
    }

    private void initialise() {
        createFrame();
        label = new JLabel();
        label.setForeground(Color.WHITE);
        label.setSize(800, 900);
        createPromptField();
        createPromptPanel();
        createInputField();
        createInputPanel();
        addComponentsToFrame();
    }

    private void createFrame() {
        frame = new JFrame(gameName);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1200, 980);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(squareFont);
    }

    private void createPromptField() {
        promptField = new JTextField(20);
        promptField.setEditable(false);

        promptField.setPreferredSize(new Dimension(200, 60));
        promptField.setBackground(Color.BLACK);
        promptField.setForeground(Color.WHITE);

        Border border = BorderFactory.createLineBorder(Color.WHITE, 5);
        Border margin = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        promptField.setBorder(BorderFactory.createCompoundBorder(border, margin));

        promptField.setFont(new Font("MxPlus IBM BIOS", Font.PLAIN, 12));
    }

    private void createInputField() {
        inputField = new JTextField(20);
        inputField.setPreferredSize(new Dimension(200, 120));
        inputField.setBackground(Color.BLACK);
        inputField.setForeground(Color.WHITE);

        Border border = BorderFactory.createLineBorder(Color.WHITE, 5);

        Border margin = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        inputField.setBorder(BorderFactory.createCompoundBorder(border, margin));

        inputField.setFont(new Font("MxPlus IBM BIOS", Font.PLAIN, 12));

        inputField.addActionListener(e -> {
            String userInput = getUserInput();
            handleUserInput(userInput);
            clearInputField();
        });
    }

    private void createPromptPanel() {
        promptPanel = new JPanel(new BorderLayout());
        promptPanel.add(promptField, BorderLayout.CENTER);
        promptPanel.setBackground(Color.BLACK);
        promptPanel.setForeground(Color.WHITE);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        promptPanel.setBorder(paddingBorder);
    }


    private void createInputPanel() {
        inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.setBackground(Color.BLACK);
        inputPanel.setForeground(Color.WHITE);
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        inputPanel.setBorder(paddingBorder);
    }

    private void addComponentsToFrame() {
        //create a new panel to hold both promptPanel and inputPanel
        JPanel combinedPanel = new JPanel(new BorderLayout());

        //add promptPanel to the top of the combined panel
        createPromptPanel();
        combinedPanel.add(promptPanel, BorderLayout.NORTH);

        //add inputPanel to the bottom of the combined panel
        combinedPanel.add(inputPanel, BorderLayout.CENTER);

        //add the combined panel to the frame's SOUTH area
        frame.setLayout(new BorderLayout());
        frame.add(combinedPanel, BorderLayout.SOUTH);
        frame.add(label, BorderLayout.NORTH);
    }


    private String getUserInput() {
        return inputField.getText();
    }

    private void handleUserInput(String userInput) {
        System.out.println("User input: " + userInput);
    }

    public JTextField getPromptField() {
        return promptField;
    }

    public JTextField getInputField() {
        return inputField;
    }

    public static void clearGameDisplay() {
        if (instance != null) {
            instance.label.setText("");
        }
    }

}