package aatkin.GameOfCards;

import static org.junit.Assert.*;

import org.junit.*;

public class DeckTest {

	private Deck testDeck;
	
	@Before
	public void setUp() {
		testDeck = new Deck();
	}
	
	@Test
	public void notNullDeckCheck() {
		assertNotNull(testDeck.getDeck());
	}
	
	@Test
	public void emptyDeckCheck() {
		assertEquals(0, testDeck.getDeckSize());
	}
	
	@Test
	public void addCardTest() {
		Card testCard = new Card(5, "");
		
		testDeck.addCardToDeck(testCard);
		
		assertEquals(1, testDeck.getDeckSize());
		assertEquals(testCard, testDeck.getTopCard());
		assertEquals(testCard, testDeck.getLastCard());
	}
	
	@Test
	public void addMoreCards() {
		Card firstCard = new Card(1, "");
		Card secondCard = new Card(2, "");
		Card thirdCard = new Card(10, "");
		
		testDeck.addCardToDeck(firstCard);
		testDeck.addCardToDeck(secondCard);
		testDeck.addCardToDeck(thirdCard);
		
		assertEquals(3, testDeck.getDeckSize());
		assertEquals(firstCard, testDeck.getTopCard());
		assertEquals(thirdCard, testDeck.getLastCard());
	}
	
	@Test
	public void fillTheDeck() {
		for(int i = 0; i < 52; i++) {
			testDeck.addCardToDeck(new Card(i, "Spades"));
		}
		
		assertEquals(52, testDeck.getDeckSize());
		assertEquals(0, testDeck.getTopCard().returnValue());
		assertEquals(51, testDeck.getLastCard().returnValue());
	}
	
	@Test
	public void fillDeckWithStandardCards() {
		testDeck.fillDeckWithStandardPokerCards();
		
		assertEquals(52, testDeck.getDeckSize());
		
		assertEquals(2, testDeck.getTopCard().returnValue());
		assertEquals("Spades", testDeck.getTopCard().returnSuit());
		
		assertEquals(14, testDeck.getLastCard().returnValue());
		assertEquals("Clubs", testDeck.getLastCard().returnSuit());
	}
	
	@Test
	public void checkValueBoundariesOnFullDeck() {
		testDeck.fillDeckWithStandardPokerCards();
		
		for(Card c : testDeck.getDeck()) {
			assertTrue(c.returnValue() >= 2 && c.returnValue() <= 14);
		}
	}
	
	@Test
	public void sortDeck() {
		for(int i = 5; i > 0; i--) {
			testDeck.addCardToDeck(new Card(i, "Spades"));
		}
		testDeck.sortDeck();
		assertTrue(testDeck.getTopCard().returnValue() == 1);
		assertTrue(testDeck.getLastCard().returnValue() == 5);
	}
	
	@Test
	public void removeCardFromDeck() {
		testDeck.fillDeckWithStandardPokerCards();
		testDeck.removeCardFromTop();
		assertEquals(51, testDeck.getDeckSize());
	}
	
	@Test
	public void shuffleDeck() {
		testDeck.fillDeckWithStandardPokerCards();
		testDeck.shuffleDeck();
		assertEquals(52, testDeck.getDeckSize());
	}
}