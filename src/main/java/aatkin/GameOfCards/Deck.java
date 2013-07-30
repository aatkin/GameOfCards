package aatkin.GameOfCards;

import java.util.*;

/**
 * @author Anssi Kinnunen, aatkin@utu.fi
 * 
 *         A class for representing a deck data structure in the Game Of Cards. Each deck consists of cards, which
 *         are represented by the class Card.
 */
public class Deck {

    private List<Card>     cards;
    private final String[] SUITS = { "Spades", "Diamonds", "Hearts", "Clubs" };

    public Deck() {
        cards = new ArrayList<Card>();
    }

    public List<Card> getDeck() {
        return cards;
    }

    public int getDeckSize() {
        return cards.size();
    }

    /**
     * Does not allow null Card objects to be added to the data structure, for sake of consistency
     */
    public void addCardToDeck(Card card) {
        if(card != null) {
            cards.add(card);
        }
    }

    public Card getTopCard() {
        return cards.get(0);
    }

    public Card getLastCard() {
        return cards.get(cards.size() - 1);
    }

    /**
     * Fills the current deck with standard set of 52 cards, each with a value from range 2-14 and
     * suit from 4 different suits: Spades, Diamonds, Hearts and Clubs.
     */
    public void fillDeckWithStandardPokerCards() {
        for (int suit = 0; suit < 4; suit++) {
            for (int value = 2; value <= 14; value++) {
                addCardToDeck(new Card(value, SUITS[suit]));
            }
        }
    }

    public void sortDeck() {
        Collections.sort(cards);
    }

    public Card removeCardFromTop() {
        Card removed = cards.remove(0);
        return removed;
    }

    public Card removeCardFromIndex(int index) {
        Card removed = cards.remove(index);
        return removed;
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }
}