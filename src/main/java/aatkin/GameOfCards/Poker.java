package aatkin.GameOfCards;

import java.util.ArrayList;

/**
 * @author Anssi Kinnunen, aatkin@utu.fi
 * 
 * A class for representing game rules for a "normal" game of Poker.
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
	private PokerScorer scorer;
	private ArrayList<Player> players;

	public Poker() {
		currentDeck = new Deck();
		currentDeck.fillWithStandardCards();
		players = new ArrayList<Player>();
		scorer = new PokerScorer();
	}

	public Deck returnDeck() {
		return currentDeck;
	}
	
	public ArrayList<Player> returnPlayers() {
		return players;
	}
	
	public void addPlayerToGame(Player player) {
		players.add(player);
	}
	
	public void giveTopCardTo(Player defPlayer) {
		Card removed = currentDeck.removeCardFromTop();
		defPlayer.returnHand().addCard(removed);
	}
	
	public void makeNewDeck() {
		currentDeck = new Deck();
		currentDeck.fillWithStandardCards();
	}

	public void play(int rounds) {
		int i = 0;
		while(i < rounds) {
			System.out.print("A new game of poker!\nPlayers today are: ");
			System.out.println(players + "\nAre you ready to rumble?!\n");
			
			makeNewDeck();
			currentDeck.shuffleDeck();
			
			for(int j = 0; j < 5; j++) {
				for(Player p : players) {
					giveTopCardTo(p);
				}
			}
			
			Player best = null;
			int topValue = 0;
			int temp = 0;
			
			for(Player p : players) {
				temp = scorer.valueHand(p.returnHand());
				String handName = scorer.returnHandName(temp);
				
				System.out.println("Player " + p + " shows his hand: " + handName + "\n");
				System.out.println(p.returnHand().returnDeck() + "\n");
				p.discardCards();
				
				if(temp > topValue) {
					topValue = temp;
					best = p;
				}
			}
			
			System.out.println("Winner is " + best + "!\n");
			i++;
		}
	}
}