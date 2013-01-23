package aatkin.GameOfCards;

import org.junit.Before;

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
}