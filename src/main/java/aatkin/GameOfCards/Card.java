package aatkin.GameOfCards;

/**
 * @author Anssi Kinnunen
 * 
 * Class for representing a card in the Game Of Cards.
 */
public class Card implements Comparable<Card> {
	
	private final int VALUE;
	private final String SUIT;
	
	public Card(int value, String suit) {
		this.VALUE = value;
		this.SUIT = suit;
	}
	
	public int returnValue() {
		return VALUE;
	}
	
	public String returnSuit() {
		return SUIT;
	}

	/**
	 * Compares two cards by their values.
	 */
	public int compareTo(Card c) {
		if(this.returnValue() < c.returnValue()) {
			return -1;
		}
		else if(this.returnValue() == c.returnValue()) {
			return 0;
		}
		else return 1;
	}
}