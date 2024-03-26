package spike;

import javax.swing.*;
import java.awt.*;

public class AsciiUtil {
    private static final char[] ASCII = new char[]{
            '@', '&', '%', '#', '(', '/', '*', ',', '.', ' '
    };

    private static char getCharAt(double x, double y, Graph graph) {
//        2 Seems to produce the best values
        int resolution = 4;

        for (int i = 0; i < ASCII.length - 1; i++) {
            if (graph.relation(x, y, resolution * (i + 1))) {
                return ASCII[i];
            }
        }

        return ASCII[ASCII.length - 1];
    }

    public static String graphToAscii(int width, int height, Graph graph) {
        StringBuilder result = new StringBuilder();

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                double x = i - width / 2d;
                double y = height / 2d - j;

                result.append(getCharAt(x, y, graph));
            }

            result.append("<br>");
        }

        result.insert(0, "<html><pre style=\"font-family: MxPlus IBM BIOS\">");
        result.append("</pre></html>");

        return result.toString();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JLabel label = new JLabel();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);

        label.setForeground(Color.WHITE);
        label.setSize(800, 800);

        label.setFont(new Font("MxPlus IBM BIOS", Font.PLAIN, 8));

        frame.add(label);
    }
}
