package Objects;

import Interfaces.DragonInterface;
import Interfaces.DragonType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameBoard extends JFrame implements ActionListener, DragonInterface {

    public ArrayList<Player> playerArrayList = new ArrayList<>();
    public ArrayList<DragonCard> dragonCards = new ArrayList<>();
    public ArrayList<VolcanoCard> volcanoCards = new ArrayList<>();
    public ArrayList<CaveCard> caveCards = new ArrayList<>();

    private final String gameName;
    private final Font squareFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/MxPlus_IBM_BIOS.ttf"));
    private JFrame frame;
    private JTextPane boardView;
    private JPanel panel;
    private JTextField userInput;
    private JButton enter;
    private JTextPane res;


    public GameBoard(String gameName) throws IOException, FontFormatException{
        this.gameName = gameName;
        for (int i = 0; i < 3; i++){
            dragonCards.add(new DragonCard(DragonType.SALAMANDER, i+1));
        }
        for (int i = 0; i < 3; i++){
            dragonCards.add(new DragonCard(DragonType.BAT, i+1));
        }
        for (int i = 0; i < 3; i++){
            dragonCards.add(new DragonCard(DragonType.SPIDER, i+1));
        }
        for (int i = 0; i < 3; i++){
            dragonCards.add(new DragonCard(DragonType.BABY_DRAGON, i+1));
        }
        for (int i = 0; i < 2; i++){
            dragonCards.add(new DragonCard(DragonType.DRAGON_PIRATE, i+1));
            dragonCards.add(new DragonCard(DragonType.DRAGON_PIRATE, i+1));
        }
        addPlayer(new Player("Player 1", DragonType.BAT));

        startGame();
    }




    private void startGame() {
        DragonCard sample = new DragonCard(DragonType.BAT, 2);

        frame = new JFrame(gameName);
        frame.setLayout(new BorderLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        c.anchor = GridBagConstraints.FIRST_LINE_START;

        boardView = new JTextPane();
        boardView.setText(getASCII());
        boardView.setFont(squareFont.deriveFont(8f));
        frame.add(boardView, BorderLayout.WEST);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5,4));
//        panel.setSize(400, 800);


        for (int i = 0; i < 16; i++) {
            JTextPane pane = new JTextPane();
            String artASCII = sample.getCardback();
            artASCII = artASCII.replace("xx", String.format("%2s", i+1));
            pane.setText(artASCII);
            pane.setFont(squareFont.deriveFont(8f));
            panel.add(pane);
        }

        JTextPane actions = new JTextPane();
        actions.setFont(squareFont.deriveFont(8f));
        actions.setText("ACTIONS\n- Enter Card Value\n Enter:'End Game' to finish");

        panel.add(actions);

        userInput = new JTextField();
        enter = new JButton("Enter");
        enter.addActionListener(this);
        res = new JTextPane();

        res.setFont(squareFont.deriveFont(8f));

        panel.add(userInput);
        panel.add(enter);
        panel.add(res);
        panel.setPreferredSize(new Dimension(400, 800));
        frame.add(panel, BorderLayout.EAST);

        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);

        boardView.setBackground(Color.BLACK);
        boardView.setForeground(Color.WHITE);
        boardView.setSize(800, 800);

        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(squareFont);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = enter.getActionCommand();
        if (s.equals("Enter")) {
            try {
                int input = Integer.parseInt(userInput.getText());
                if (input != 0) {
                    DragonCard cardSelected = dragonCards.get(input - 1);
                    String output = cardSelected.getCardString();

                    res.setText(output);
                } else {
                    res.setText("fail");
                }

            } catch (Exception error) {
                String input = userInput.getText();
                if (input.equals("End Game")) {
                    endGame();
                }
            }

            userInput.setText("");
        }
    }

    public void endGame() {
        frame.setVisible(false);
        frame.dispose();

        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);

        JLabel endMessage = new JLabel();
        endMessage.setBackground(Color.BLACK);
        endMessage.setForeground(Color.WHITE);

        String endText = "Congratulations you are the Winner,"+ playerArrayList.get(0).getName() + "!";

        endMessage.setText(endText);
        endMessage.setFont(squareFont.deriveFont(20f));


        frame.add(endMessage, BorderLayout.CENTER);

    }

    public void addPlayer(Player player) {
        playerArrayList.add(player);
    }


    @Override
    public String getASCII() {
        return """

                      #@@@@@/                                                       /@@@@@#
                    %@(     #@(                                                   /@#     /@&
                   @#         &@                                                 &@         (@
                  @,           (@                                               &#            @
                 %#             &/                                             ,@             /&
                 @      / \\      @                                             @      .-_      @
                #(     // \\\\     &.                                            @     ' ● ‾;    ,%
                @      \\\\●//      &                                           %,    :.-_ ,-<    @
                @     /// \\\\\\     @                                           @     `. (        @
                @     ││   ││     @                *%&&@@@&&%*                @       \\ \\       @
                @     ││\\_/││     @            (@@# @       @ (&@(            @      .-\\ \\      @
                @     `│   │'     %         /@%     &       @     %@/         #*    ( __. .     @
                /#     `   '     @        (@#.      %       &       %@#        @    \\_ _ .'    /#
                 @               @      *@   @      *,  ●   (      @   @/      @               @
                 (&             @.     @/    &   ●   (     *   ●   @    *@      @             #%
                  @(           &%    *@       %      &     %      #       &/    #@           /@
                   @&    ●    @%    ##%       @      @     @      @       ##%    #@    ●    &@
                    /@&     @@.    &* %,   ●  /*     @  0  @      (  ●    & .&     @@.    %@(
                      .&@@@%      &,   @       @  S  @     @  *  @       @    @      %@@@&,
                                 &,     @      %    /@@@@@@@/    &      @      &
                                #*      ,&   0  &&@%         #@&%   0  #*      ,%
                               *#    ●   %/    @%               #@,   ,&   ●    (/
                               @%%        @  @#                   #@  @        #&&
                              @  ,@        @@                       @@        @/  @
                             */    @,   * #%                         #% w    @    ,(
                             @      &%   %(                           /&   #&      @
                            (        /@ #(                             /% @(        %
                            @    ●     @%                               #@     ●    @
                           /#          @                                 @          ((
                           @.@&     w @                                   @ *     &@,@
                           %   %@/    #                                   (*   *@&   #
                          (      *@% @                                     @ %@/      #
                          @         &%                                     #@         @
                          @   ●     &                                       @     ●   &
                          #       S @                                       @ *       /
                         *          %                                       (          (
                         %@&%*     /                                         #     *#&@&
                         &   ,(&@@@@                                         @@@@&(,   &
                         &         @                                         @         @
                         @         @                                         @         @
                         @   ●   0 @                                         @ w   ●   @
                         @         @                                         @         @
                         &         @                                         @         @
                         &    *%@@@@                                         @@@@%*    &
                         %@@&(     /                                         #     (&@@&
                         *          #                                       (          (
                          (       w @                                       @ S       /
                          &   ●     &                                       @     ●   &
                          @         %#                                     (%         @
                          (       @& @                                     @ &@,      %
                           %   #@(   ,#                                   //   (@#   (
                           @ @@       @                                   @       @@ @
                           /%       0  @                                 &  S       ##
                            @          @#                               (@          @
                            #    ●   ,@ %/                             ,& @*   ●    %
                             @      #&   &/                           ,@   %%      @
                             /*    @*   S %#                         (& *   ,@    .#
                              @   @        @@                       &@        @,  @
                               &#&        @  @(                   /@  @        &%&
                               /#    ●   #(   *@#               (@/   *%   ●    ((
                                %.       &   *  @@@(         (@@%   w  %,       &
                                 &      @      %    #@@@@@@@#    &      @      @
                       %@@@#      @    @       @  w  @     @  0  @       @    @      (@@@%
                    ,@@,   *@@     &, #/   ●  */     @  S  @     ,(   ●  ,&  @     @@/   .&@*
                   &@         @#    %(&       @      @     @      @       %(&    (@         @@
                  &#           @#    /&       &      &     %      #       &(    (@     )  (  (@
                 /@             @      @,    &   ●   (     *   ●   @    .@      @     (\\__/)  %#
                 @               @      (@   @      *,  ●   (      @   @#      @      /` (/    @
                /#               @        %@(,      #       &       #@%        @     |● ●/)    (#
                @    __     __   .%         (@#     &       @     (@#         (/     / / /|     @
                @    )_\\   /_(    @            #@&/ @       @ /&@%            @     o o_/ ,     @
                @     '.\\‼/.`     @                (&&@@@@@&&(                @     (^(  ;      @
                @       '(`       @                                           @      (_(/       @
                @        "        &                                           %.     ,          @
                #/               %,                                            &   ,//(        .&
                 @               @                                             @   ( ' )       @
                 &(             &(                                             *@   )         *@
                  @            /@                                               @(            @
                   @/    ●    #@                                                 @%    ●    *@
                    &@,     /@%                                                   #@(     .@@
                      %@@@@@#                                                       #@@@@@&

                """;
    }

    public DragonCard cardFactory(DragonType type, int value) {
        return new DragonCard(type, value);
    }
}
