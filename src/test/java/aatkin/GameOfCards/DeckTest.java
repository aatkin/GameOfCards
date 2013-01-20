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
		assertNotNull(testDeck.returnDeck());
	}
	
	@Test
	public void emptyDeckCheck() {
		assertEquals(0, testDeck.returnDeckSize());
	}
	
	@Test
	public void addCardTest() {
		Card testCard = new Card(5, "");
		
		testDeck.addCard(testCard);
		
		assertEquals(1, testDeck.returnDeckSize());
		assertEquals(testCard, testDeck.returnTopCard());
		assertEquals(testCard, testDeck.returnLastCard());
	}
	
	@Test
	public void addMoreCards() {
		Card firstCard = new Card(1, "");
		Card secondCard = new Card(2, "");
		Card thirdCard = new Card(10, "");
		
		testDeck.addCard(firstCard);
		testDeck.addCard(secondCard);
		testDeck.addCard(thirdCard);
		
		assertEquals(3, testDeck.returnDeckSize());
		assertEquals(firstCard, testDeck.returnTopCard());
		assertEquals(thirdCard, testDeck.returnLastCard());
	}
	
	@Test
	public void fillTheDeck() {
		for(int i = 0; i < 52; i++) {
			testDeck.addCard(new Card(i, "Spades"));
		}
		
		assertEquals(52, testDeck.returnDeckSize());
		assertEquals(0, testDeck.returnTopCard().returnValue());
		assertEquals(51, testDeck.returnLastCard().returnValue());
	}
	
	@Test
	public void fillDeckWithStandardCards() {
		testDeck.fillWithStandardCards();
		
		assertEquals(52, testDeck.returnDeckSize());
		
		assertEquals(2, testDeck.returnTopCard().returnValue());
		assertEquals("Spades", testDeck.returnTopCard().returnSuit());
		
		assertEquals(14, testDeck.returnLastCard().returnValue());
		assertEquals("Clubs", testDeck.returnLastCard().returnSuit());
	}
	
	@Test
	public void checkValueBoundariesOnFullDeck() {
		testDeck.fillWithStandardCards();
		
		for(Card c : testDeck.returnDeck()) {
			assertTrue(c.returnValue() >= 2 && c.returnValue() <= 14);
		}
	}
	
	@Test
	public void sortDeck() {
		for(int i = 5; i > 0; i--) {
			testDeck.addCard(new Card(i, "Spades"));
		}
		testDeck.sortDeck();
		assertTrue(testDeck.returnTopCard().returnValue() == 1);
		assertTrue(testDeck.returnLastCard().returnValue() == 5);
	}
	
	@Test
	public void removeCardFromDeck() {
		testDeck.fillWithStandardCards();
		testDeck.removeCardFromTop();
		assertEquals(51, testDeck.returnDeckSize());
	}
	
	@Test
	public void shuffleDeck() {
		testDeck.fillWithStandardCards();
		testDeck.shuffleDeck();
		assertEquals(52, testDeck.returnDeckSize());
	}
}