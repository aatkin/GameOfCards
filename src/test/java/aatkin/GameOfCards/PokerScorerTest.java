package aatkin.GameOfCards;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PokerScorerTest {

    Player      defPlayer;
    PokerScorer testScorer;

    @Before
    public void setUp() throws Exception {
        defPlayer = new Player("Default");
        testScorer = new PokerScorer();
    }

    @Test
    public void testHighCard() {
        defPlayer.returnHand().addCardToDeck(new Card(10, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(11, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(12, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(13, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        int value = testScorer.valueHighCard(defPlayer.returnHand());
        assertEquals(14, value);
    }

    /**
     * Basevalue = 11
     */
    @Test
    public void testOnePair() {
        defPlayer.returnHand().addCardToDeck(new Card(2, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(3, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(4, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(6, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(6, "Spades"));
        int value = testScorer.valueOnePair(defPlayer.returnHand());
        assertEquals(23, value);
    }

    /**
     * Basevalue = 30
     */
    @Test
    public void testTwoPairs() {
        defPlayer.returnHand().addCardToDeck(new Card(3, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(3, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(5, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(5, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(13, "Hearts"));
        int value = testScorer.valueTwoPairs(defPlayer.returnHand());
        assertEquals(46, value);
    }

    /**
     * Basevalue = 30
     */
    @Test
    public void testTwoHighPairs() {
        defPlayer.returnHand().addCardToDeck(new Card(12, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(12, "Spades"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Spades"));
        defPlayer.returnHand().addCardToDeck(new Card(13, "Hearts"));
        int value = testScorer.valueTwoPairs(defPlayer.returnHand());
        assertEquals(82, value);
    }

    /**
     * Basevalue = 79
     */
    @Test
    public void testThreeOfKind() {
        defPlayer.returnHand().addCardToDeck(new Card(4, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(5, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(6, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(4, "Spades"));
        defPlayer.returnHand().addCardToDeck(new Card(4, "Diamonds"));
        int value = testScorer.valueThreeOfKind(defPlayer.returnHand());
        assertEquals(91, value);
    }

    /**
     * Basevalue = 79
     */
    @Test
    public void testHighThreeOfKind() {
        defPlayer.returnHand().addCardToDeck(new Card(12, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(13, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Spades"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Diamonds"));
        int value = testScorer.valueThreeOfKind(defPlayer.returnHand());
        assertEquals(121, value);
    }

    /**
     * Basevalue = 99
     */
    @Test
    public void testLowStraight() {
        defPlayer.returnHand().addCardToDeck(new Card(2, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(3, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(4, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(5, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(6, "Hearts"));
        int value = testScorer.valueStraight(defPlayer.returnHand());
        assertEquals(122, value);
    }

    /**
     * Basevalue = 102
     */
    @Test
    public void testHiStraight() {
        defPlayer.returnHand().addCardToDeck(new Card(10, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(11, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(12, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(13, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        int value = testScorer.valueStraight(defPlayer.returnHand());
        assertEquals(162, value);
    }

    /**
     * Basevalue = 161
     */
    @Test
    public void testLowFlush() {
        defPlayer.returnHand().addCardToDeck(new Card(2, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(3, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(4, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(5, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(6, "Hearts"));
        int value = testScorer.valueFlush(defPlayer.returnHand());
        assertEquals(167, value);
    }

    /**
     * Basevalue = 161
     */
    @Test
    public void testHiFlush() {
        defPlayer.returnHand().addCardToDeck(new Card(10, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(11, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(12, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(13, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        int value = testScorer.valueFlush(defPlayer.returnHand());
        assertEquals(175, value);
    }

    /**
     * Basevalue = 170
     */
    @Test
    public void testFullHouse() {
        defPlayer.returnHand().addCardToDeck(new Card(2, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(2, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(2, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(3, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(3, "Hearts"));
        int value = testScorer.valueFullHouse(defPlayer.returnHand());
        assertEquals(176, value);
    }

    /**
     * Basevalue = 170
     */
    @Test
    public void testHiFullHouse() {
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(13, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(13, "Hearts"));
        int value = testScorer.valueFullHouse(defPlayer.returnHand());
        assertEquals(212, value);
    }

    /**
     * Basevalue = 205
     */
    @Test
    public void testLowFourOfKind() {
        defPlayer.returnHand().addCardToDeck(new Card(2, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(2, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(2, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(2, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Spades"));
        int value = testScorer.valueFourOfKind(defPlayer.returnHand());
        assertEquals(213, value);
    }

    /**
     * Basevalue = 205
     */
    @Test
    public void testHiFourOfKind() {
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(2, "Spades"));
        int value = testScorer.valueFourOfKind(defPlayer.returnHand());
        assertEquals(261, value);
    }

    /**
     * Basevalue = 666
     */
    @Test
    public void testLowStraightFlush() {
        defPlayer.returnHand().addCardToDeck(new Card(2, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(3, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(4, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(5, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(6, "Hearts"));
        int value = testScorer.valueStraightFlush(defPlayer.returnHand());
        assertEquals(686, value);
    }

    /**
     * Basevalue = 666
     */
    @Test
    public void testHiStraightFlush() {
        defPlayer.returnHand().addCardToDeck(new Card(10, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(11, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(12, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(13, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        int value = testScorer.valueStraightFlush(defPlayer.returnHand());
        assertEquals(726, value);
    }

    /**
     * Test automatic hand valuer
     */
    @Test
    public void testAutoValuer() {
        defPlayer.returnHand().addCardToDeck(new Card(10, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(11, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(12, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(13, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        int value = testScorer.valueHand(defPlayer.returnHand());
        assertEquals(726, value);
    }

    /**
     * Test automatic hand valuer even more intensively
     */
    @Test
    public void testAutoValuerInsanely() {
        Player scndPlayer = new Player("Rival");
        scndPlayer.returnHand().addCardToDeck(new Card(6, "Hearts"));
        scndPlayer.returnHand().addCardToDeck(new Card(6, "Hearts"));
        scndPlayer.returnHand().addCardToDeck(new Card(6, "Hearts"));
        scndPlayer.returnHand().addCardToDeck(new Card(4, "Hearts"));
        scndPlayer.returnHand().addCardToDeck(new Card(4, "Hearts"));

        defPlayer.returnHand().addCardToDeck(new Card(5, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(5, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(5, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(4, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(4, "Hearts"));

        int defPvalue = testScorer.valueHand(defPlayer.returnHand());
        int scndPvalue = testScorer.valueHand(scndPlayer.returnHand());
        
        assertTrue(scndPvalue > defPvalue);
    }
    
    /**
     * Test that a regular deck does not contain cards that do not belong to a proper deck
     */
    @Test
    public void assureThatRegularDeckContainsValidCards() {
        boolean deckIsGood = testScorer.validateDeck(defPlayer.returnHand());
        assertTrue(deckIsGood);
    }
    
    /**
     * Test that deck cant contain cards that do not belong to a poker deck
     */
    @Test(expected = IllegalArgumentException.class)
    public void assureThatPokerDeckHasNoInvalidCards() {
        defPlayer.returnHand().addCardToDeck(new Card(2, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(14, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(0, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(-1, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(Integer.MAX_VALUE, "Hearts"));
        defPlayer.returnHand().addCardToDeck(new Card(Integer.MIN_VALUE, "Hearts"));
        
        testScorer.validateDeck(defPlayer.returnHand());     
    }
}