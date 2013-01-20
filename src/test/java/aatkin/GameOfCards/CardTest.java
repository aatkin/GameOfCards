package aatkin.GameOfCards;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void testReturnFiveAndSpade() {
		Card card = new Card(5, "Spade");
		assertEquals(5, card.returnValue());
		assertEquals("Spade", card.returnSuit());
	}
	
	@Test
	public void assertAceGreaterThanKing() {
		Card ace = new Card(14, "Spades");
		Card king = new Card(13, "Spades");
		
		assertTrue(ace.compareTo(king) == 1);
	}
	
	@Test
	public void assertQueensAreSame() {
		Card queenS = new Card(12, "Spades");
		Card queenD = new Card(12, "Diamonds");
		
		assertTrue(queenS.compareTo(queenD) == 0);
	}
}