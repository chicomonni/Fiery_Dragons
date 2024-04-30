package Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Typing {

    public static void animateTyping(JTextField textField, String text, int delayBetweenChars) {
//        textField.setText("");


        //create a timer with the specified delay between characters
        //for fun :))
        Timer timer = new Timer(delayBetweenChars, new ActionListener() {
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
