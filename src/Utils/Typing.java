package Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Responsible for a little bit of flourish in the UI
 * I just thought it was neat :)
 *
 * @author Georgia Kanellis
 */
public class Typing {
    private static Timer timer;

    public static void animateTyping(String starterText, JTextField textField, String text, int delayBetweenChars) {
        textField.setText(starterText);


        //create a timer with delay between characters
        //for fun :))
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timer = new Timer(delayBetweenChars, new ActionListener() {
            int currentIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //append next char to the text field
                textField.setText(textField.getText() + text.charAt(currentIndex));

                //increment the index for the next character
                currentIndex++;

                //stop timer once whole string is printed
                if (currentIndex >= text.length()) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        //start the timer
        timer.start();
    }
}
