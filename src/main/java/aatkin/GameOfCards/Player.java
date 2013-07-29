package aatkin.GameOfCards;

/**
 * @author Anssi Kinnunen, aatkin@utu.fi
 *
 * A class for representing a player in the Game Of Cards.
 * Each player has a hand, which is represented by the class Deck.
 */
public class Player {

    private final String NAME;
    private Deck hand;

    public Player(String name) {
	NAME = name;
	this.hand = new Deck();
    }

    public Deck returnHand() {
	return hand;
    }

    public void discardCards() {
	hand = new Deck();
    }

    public String toString() {
	return NAME;
    }
}