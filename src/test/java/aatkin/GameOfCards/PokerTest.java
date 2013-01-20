package aatkin.GameOfCards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PokerTest {
	
	Poker game;
	Player defPlayer;

	@Before
	public void setUp() throws Exception {
		game = new Poker();
		Deck testDeck = new Deck();
		testDeck.fillWithStandardCards();
		game.addDeck(testDeck);
			
		defPlayer = new Player("Default");
	}

	@Test
	public void testHighCardInHand() {
		for(int i = 0; i < 5; i++) {
			game.giveTopCardTo(defPlayer);
		}
		int rank = game.checkHighCard(defPlayer);
		assertEquals(47, game.returnDeckSize());
		assertEquals(rank, 6);
	}

	@Test
	public void testMoreHiCards() {
		
	}
}