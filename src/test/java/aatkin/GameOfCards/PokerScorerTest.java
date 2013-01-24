package aatkin.GameOfCards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PokerScorerTest {
	
	Player defPlayer;
	PokerScorer testScorer;

	@Before
	public void setUp() throws Exception {		
		defPlayer = new Player("Default");
		testScorer = new PokerScorer();
	}

	@Test
	public void testHighCard() {
		for(int i = 9; i < 15; i++) {
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
		}	
		int value = testScorer.valueHighCard(defPlayer.returnHand());
		assertEquals(14, value);
	}

	/**
	 * Basevalue = 11
	 */
	@Test
	public void testOnePair() {
		for(int i = 2; i < 5; i++) {
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
		}
		defPlayer.returnHand().addCard(new Card(6, "Hearts"));
		defPlayer.returnHand().addCard(new Card(6, "Spades"));
		int value = testScorer.valueOnePair(defPlayer.returnHand());
		assertEquals(23, value);
	}
	
	/**
	 * Basevalue = 29
	 */
	@Test
	public void testTwoPairs() {
		for (int i = 3; i < 6; i += 2) {
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
			defPlayer.returnHand().addCard(new Card(i, "Spades"));
		}
		defPlayer.returnHand().addCard(new Card(13, "Hearts"));
		int value = testScorer.valueTwoPairs(defPlayer.returnHand());
		assertEquals(45, value);
	}
	
	/**
	 * Basevalue = 29
	 */
	@Test
	public void testTwoHighPairs() {
		for (int i = 12; i < 15; i += 2) {
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
			defPlayer.returnHand().addCard(new Card(i, "Spades"));
		}
		defPlayer.returnHand().addCard(new Card(13, "Hearts"));		
		int value = testScorer.valueTwoPairs(defPlayer.returnHand());
		assertEquals(81, value);
	}
	
	/**
	 * Basevalue = 76
	 */
	@Test
	public void testThreeOfKind() {
		for (int i = 4; i < 7; i++) {
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
		}
		defPlayer.returnHand().addCard(new Card(4, "Spades"));
		defPlayer.returnHand().addCard(new Card(4, "Diamonds"));
		int value = testScorer.valueThreeOfKind(defPlayer.returnHand());
		assertEquals(88, value);
	}
	
	/**
	 * Basevalue = 76
	 */
	@Test
	public void testHighThreeOfKind() {
		for (int i = 12; i < 15; i++) {
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
		}
		defPlayer.returnHand().addCard(new Card(14, "Spades"));
		defPlayer.returnHand().addCard(new Card(14, "Diamonds"));
		int value = testScorer.valueThreeOfKind(defPlayer.returnHand());
		assertEquals(118, value);
	}
	
	/**
	 * Basevalue = 99
	 */
	@Test
	public void testLowStraight() {
		for (int i = 2; i < 7; i++) {
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
		}
		int value = testScorer.valueStraight(defPlayer.returnHand());
		assertEquals(119, value);
	}
	
	/**
	 * Basevalue = 99
	 */
	@Test
	public void testHiStraight() {
		for (int i = 10; i < 15; i++) {
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
		}
		int value = testScorer.valueStraight(defPlayer.returnHand());
		assertEquals(159, value);
	}
	
	/**
	 * Basevalue = 146
	 */
	@Test
	public void testLowFlush() {
		for (int i = 2; i < 7; i++) { 
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
		}
		int value = testScorer.valueFlush(defPlayer.returnHand());
		assertEquals(152, value);
	}
	
	/**
	 * Basevalue = 146
	 */
	@Test
	public void testHiFlush() {
		for (int i = 10; i < 15; i++) {
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
		}
		int value = testScorer.valueFlush(defPlayer.returnHand());
		assertEquals(160, value);
	}
	
	/**
	 * Basevalue = 189
	 */
	@Test
	public void testFullHouse() {
		for (int i = 0; i < 3; i++) {
			defPlayer.returnHand().addCard(new Card(2, "Hearts"));
		}
		for (int i = 0; i < 2; i++) {
			defPlayer.returnHand().addCard(new Card(3, "Hearts"));
		}
		int value = testScorer.valueFullHouse(defPlayer.returnHand());
		assertEquals(195, value);
	}
	
	/**
	 * Basevalue = 189
	 */
	@Test
	public void testHiFullHouse() {
		for (int i = 0; i < 3; i++) {
			defPlayer.returnHand().addCard(new Card(14, "Hearts"));
		}
		for (int i = 0; i < 2; i++) {
			defPlayer.returnHand().addCard(new Card(13, "Hearts"));
		}
		int value = testScorer.valueFullHouse(defPlayer.returnHand());
		assertEquals(231, value);
	}
	
	/**
	 * Basevalue = 224
	 */
	@Test
	public void testLowFourOfKind() {
		for (int i = 0; i < 4; i++) {
			defPlayer.returnHand().addCard(new Card(2, "Hearts"));
		}
		defPlayer.returnHand().addCard(new Card(14, "Spades"));
		int value = testScorer.valueFourOfKind(defPlayer.returnHand());
		assertEquals(232, value);
	}
	
	/**
	 * Basevalue = 224
	 */
	@Test
	public void testHiFourOfKind() {
		for (int i = 0; i < 4; i++) {
			defPlayer.returnHand().addCard(new Card(14, "Hearts"));
		}
		defPlayer.returnHand().addCard(new Card(2, "Spades"));
		int value = testScorer.valueFourOfKind(defPlayer.returnHand());
		assertEquals(280, value);
	}
	
	/**
	 * Basevalue = 666
	 */
	@Test
	public void testLowStraightFlush() {
		for (int i = 2; i < 7; i++) {
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
		}
		int value = testScorer.valueStraightFlush(defPlayer.returnHand());
		assertEquals(686, value);
	}
	
	/**
	 * Basevalue = 666
	 */
	@Test
	public void testHiStraightFlush() {
		for (int i = 10; i < 15; i++) {
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
		}
		int value = testScorer.valueStraightFlush(defPlayer.returnHand());
		assertEquals(726, value);
	}
	
	/**
	 * Test automatic hand valuer
	 */
	@Test
	public void testAutoValuer() {
		for (int i = 10; i < 15; i++) {
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
		}
		int value = testScorer.valueHand(defPlayer.returnHand());
		assertEquals(726, value);
	}
	
	/**
	 * Test automatic hand valuer some more
	 */
	@Test
	public void testAutoValuerMore() {
		for (int i = 3; i < 7; i++) {
			defPlayer.returnHand().addCard(new Card(i, "Hearts"));
		}
		defPlayer.returnHand().addCard(new Card(7, "Diamonds"));
		int value = testScorer.valueHand(defPlayer.returnHand());
		assertEquals(124, value);
	}
	
	/**
	 * Test automatic hand valuer even more intensively
	 */
	@Test
	public void testAutoValuerInsanely() {
		Player scndPlayer = new Player("Rival");
		for (int i = 0; i < 3; i++) {
			scndPlayer.returnHand().addCard(new Card(6, "Hearts"));
		}
		for (int i = 0; i < 2; i++) {
			scndPlayer.returnHand().addCard(new Card(4, "Hearts"));
		}
		
		for (int i = 0; i < 3; i++) {
			defPlayer.returnHand().addCard(new Card(5, "Hearts"));
		}
		for (int i = 0; i < 2; i++) {
			defPlayer.returnHand().addCard(new Card(4, "Hearts"));
		}

		int defPvalue = testScorer.valueHand(defPlayer.returnHand());
		int scndPvalue = testScorer.valueHand(scndPlayer.returnHand());
		assertTrue(scndPvalue > defPvalue);
	}
}