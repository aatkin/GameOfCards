package aatkin.GameOfCards;

/**
 * @author Anssi Kinnunen, aatkin@utu.fi
 * 
 * A class for representing game rules for a regular game of Poker.
 * Game consists of 2 or more players, who compete for the best hand.
 * Each player draws five cards from the deck. The winner is the player,
 * who has the best hand. Hands are as follows (from best ranking to lowest ranking):
 * 
 * 10 	Straight flush
 * 9 	Four of a kind
 * 8 	Full house
 * 7 	Flush
 * 6 	Straight
 * 5 	Three of a kind
 * 4 	Two pairs
 * 3 	Two of a kind
 * 2 	One pair
 * 1 	High card
 */
public class Poker {
	
	private Deck currentDeck;

	public Poker() {
		
	}

	public void addDeck(Deck testDeck) {
		currentDeck = testDeck;
	}

	public void giveTopCardTo(Player defPlayer) {
		assert(currentDeck.returnDeckSize() >= 1) : "Cannot remove cards from empty deck";
		Card removed = currentDeck.removeCardFromTop();
		defPlayer.addCard(removed);
	}

	public int checkHighCard(Player defPlayer) {
		Deck hand = defPlayer.returnHand();
		hand.sortDeck();
		int high = hand.returnLastCard().returnValue();
		return high;
	}

	public Deck returnDeck() {
		return currentDeck;
	}
	
	public int returnDeckSize() {
		return currentDeck.returnDeckSize();
	}
}