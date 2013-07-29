package aatkin.GameOfCards;

import java.util.ArrayList;

/**
 * @author Anssi Kinnunen, aatkin@utu.fi
 * 
 *         A class for representing game rules for a "normal" game of Poker. Game consists of 2 or
 *         more players, who compete for the best hand. Each player draws five cards from the deck.
 *         The winner is the player, who has the best hand. Hands are as follows (from best ranking
 *         to lowest ranking):
 * 
 *         10 Straight flush 9 Four of a kind 8 Full house 7 Flush 6 Straight 5 Three of a kind 4
 *         Two pairs 3 Two of a kind 2 One pair 1 High card
 */
public class FiveCardDraw implements Game {

    private Deck              currentDeck;
    private GameScorer        scorer;
    private ArrayList<Player> players;

    public FiveCardDraw(GameScorer scorer) {
        currentDeck = new Deck();
        currentDeck.fillDeckWithStandardPokerCards();
        players = new ArrayList<Player>();
        this.scorer = scorer;
    }

    public void addPlayerToGame(Player player) {
        if(player == null) {
            throw new IllegalArgumentException("Cannot add a null player to the game");
        }
        int maxPlayers = (int) Math.ceil(52 / 5);
        if(players.size() >= maxPlayers) {
            throw new IllegalArgumentException(
            "Too many players added to the game, the max limit is " + maxPlayers);
        }
        players.add(player);
    }

    public void giveTopCardTo(Player player) {
        Card removed = currentDeck.removeCardFromTop();
        player.returnHand().addCardToDeck(removed);
    }

    public void loadNewPokerDeckIntoGame() {
        currentDeck = new Deck();
        currentDeck.fillDeckWithStandardPokerCards();
    }

    public void play(int maxRounds) {
        if(players.isEmpty()) {
            throw new IllegalArgumentException("No players added to the game");
        }
        int currentRound = 0;
        while (currentRound < maxRounds) {
            System.out.print("A new game of poker!\nPlayers today are: " + players + "\n");
            System.out.println("Are you ready to rumble?!\n");
            loadNewPokerDeckIntoGame();
            currentDeck.shuffleDeck();
            for (int j = 0; j < 5; j++) {
                for (Player p : players) {
                    giveTopCardTo(p);
                }
            }
            Player bestPlayer = null;
            int topCardValue = 0;
            int cardValue = 0;
            for (Player player : players) {
                cardValue = scorer.valueHand(player.returnHand());
                String handName = ((PokerScorer) scorer).returnHandName(cardValue);
                System.out.println("Player " + player + " shows his hand: " + handName + " "
                + cardValue);
                System.out.println(player.returnHand().getDeck() + "\n");
                player.discardCards();
                if(cardValue > topCardValue) {
                    topCardValue = cardValue;
                    bestPlayer = player;
                }
            }
            System.out.println("Winner is " + bestPlayer + "!\n");
            currentRound++;
        }
    }
}