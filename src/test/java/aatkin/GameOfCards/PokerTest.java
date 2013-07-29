package aatkin.GameOfCards;

import org.junit.Before;
import org.junit.Test;

public class PokerTest {

    FiveCardDraw game;

    @Before
    public void setUp() throws Exception {
        GameScorer scorer = new PokerScorer();
        game = new FiveCardDraw(scorer);
    }

}