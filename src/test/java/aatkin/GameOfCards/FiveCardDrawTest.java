package aatkin.GameOfCards;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FiveCardDrawTest {

    FiveCardDraw game;
    GameScorer scorer;

    @Before
    public void setUp() throws Exception {
        scorer = new PokerScorer();
        game = new FiveCardDraw(scorer);
    }
    
    @Test
    public void assertGameIsNotADraw() {
        int[] handValues = {10, 9, 5, 9, 3};
        boolean isDraw = game.gameIsADraw(handValues);
        assertEquals(isDraw, false);
    }

}