package aatkin.GameOfCards;

import java.util.*;

/**
 * @author Anssi Kinnunen, aatkin@utu.fi
 * 
 * A class for representing a deck in the Game Of Cards
 */
public class Deck {
	
	private List<Card> currentDeck;
	private final String[] SUITS = {"Spades", "Diamonds", "Hearts", "Clubs"};
	
	public Deck() {
		currentDeck = new ArrayList<Card>();
	}

	public List<Card> returnDeck() {
		return currentDeck;
	}

	public int returnDeckSize() {
		return currentDeck.size();
	}

	public void addCard(Card card) {
		currentDeck.add(card);
	}

	public Card returnFirstCard() {
		return currentDeck.get(0);
	}

	public Card returnLastCard() {
		return currentDeck.get(currentDeck.size() - 1);
	}

	/**
	 * Fills the current deck with standard set of 52 cards, each
	 * with a value from range 2-14 and one suit from 4 different suits:
	 * Spades, Diamonds, Hearts and Clubs.
	 */
	public void fillStandardCardsDeck() {
		for(int suit = 0; suit < 4; suit++) {
			for(int value = 2; value <= 14; value++) {
				addCard(new Card(value, SUITS[suit]));
			}
		}
	}
}