package game;

import game.chits.ChitFactory;

/**
 * Class representing Fiery Dragon Board
 */
public class Board {
    //    TODO: fix this
    private static final String squareSrc = "S0w*SSw**w0*0S0*wS0w0S*w";
    private static final String caveSrc = "S*w0";
    private static final String cardSrc = "S1S2S3w1w2w3*1*2*3010203P1P2";
    private Volcano volcano;

    private void createVolcano(ChitFactory factory) {
        volcano = new Volcano();
        volcano.createVolcano(squareSrc, caveSrc, factory);
    }
}
