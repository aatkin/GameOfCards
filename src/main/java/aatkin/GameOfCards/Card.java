package aatkin.GameOfCards;

/**
 * @author Anssi Kinnunen, aatkin@utu.fi
 * 
 * Class for representing a card in the Game Of Cards.
 */
public class Card implements Comparable<Card> {

    private final int VALUE;
    private final String SUIT;
    private final char SUIT_SHORT;

    public Card(int value, String suit) {
	this.VALUE = value;
	this.SUIT = suit;
	this.SUIT_SHORT = suit.charAt(0);
    }

    public int returnValue() {
	return VALUE;
    }

    public String returnSuit() {
	return SUIT;
    }

    public char returnSuitShort() {
	return SUIT_SHORT;
    }

    /**
     * Compares two cards by their values.
     */
    public int compareTo(Card c) {
	if (this.returnValue() < c.returnValue()) {
	    return -1;
	}
	else if (this.returnValue() == c.returnValue()) {
	    return 0;
	}
	else
	    return 1;
    }

    public String toString() {
	return VALUE + "" + SUIT;
    }
}