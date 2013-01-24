package aatkin.GameOfCards;

import org.junit.Before;
import org.junit.Test;

public class PokerTest {
	
	Poker game;

	@Before
	public void setUp() throws Exception {
		game = new Poker();
		
	}
	
	@Test
	public void playOneRound() {
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		Player player3 = new Player("Player 3");
		
		game.addPlayerToGame(player1);
		game.addPlayerToGame(player2);
		game.addPlayerToGame(player3);
		
		game.play(5000);
	}
}