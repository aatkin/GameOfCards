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
		int value = game.checkHighCard(defPlayer);
		assertEquals(47, game.returnDeckSize());
		assertEquals(value, 6);
	}

	@Test
	public void testOnePair() {
		for(int i = 0; i < 4; i++) {
			game.giveTopCardTo(defPlayer);
		}
		defPlayer.addCard(new Card(4, "Spades"));
		int value = game.checkOnePair(defPlayer);	
		assertEquals(value, 19);	
	}
	
	@Test
	public void testOnePairForHighPair() {
		for(int i = 0; i < 2; i++) {
			defPlayer.addCard(new Card(13, "Spades"));
		}
		game.giveTopCardTo(defPlayer);
		defPlayer.addCard(new Card(14, "Hearts"));
		defPlayer.addCard(new Card(14, "Diamonds"));
		int value = game.checkOnePair(defPlayer);
		assertEquals(value, 39);
	}
	
	@Test
	public void testOnePairForLowPair() {
		defPlayer.addCard(new Card(2, "Spades"));
		for(int i = 0; i < 4; i++) {
			game.giveTopCardTo(defPlayer);
		}
		int value = game.checkOnePair(defPlayer);
		int highCardValue = game.checkHighCard(defPlayer);
		assertEquals(value, 15);
		assertTrue(value > highCardValue);
	}
	
	@Test
	public void testOnePairWithNoPairs() {
		for(int i = 0; i < 5; i++) {
			game.giveTopCardTo(defPlayer);
		}
		int value = game.checkOnePair(defPlayer);
		assertEquals(value, 0);
	}
}