package aatkin.GameOfCards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	Player defPlayer;
	
	@Before
	public void setUp() throws Exception {
		defPlayer = new Player("Default");
	}

	@Test
	public void testPlayerHandNotNull() {
		assertNotNull(defPlayer.returnHand());
	}
	
	@Test
	public void addCardsToHand() {
		for(int i = 2; i < 7; i++) {
			defPlayer.returnHand().addCardToDeck(new Card(i, "Spades"));
		}
		assertEquals(5, defPlayer.returnHand().getDeckSize());
	}
	
}