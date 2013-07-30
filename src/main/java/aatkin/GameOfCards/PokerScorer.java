package aatkin.GameOfCards;

/**
 * @author Anssi Kinnunen, aatkin@utu.fi
 * 
 *         Class for scoring different hands used in Poker, as specified in class Poker. Hand values
 *         are given a pre-fixed base value, which in this case, is [prev hand max value] - [current
 *         hand min value] + 1.
 */
public class PokerScorer implements GameScorer {

    private int[]            listOfCards;
    private static final int ONEPAIR_BASE_VALUE       = 11;
    private static final int TWOPAIRS_BASE_VALUE      = 30;
    private static final int THREEKIND_BASE_VALUE     = 79;
    private static final int STRAIGHT_BASE_VALUE      = 102;
    private static final int FLUSH_BASE_VALUE         = 161;
    private static final int FULLHOUSE_BASE_VALUE     = 170;
    private static final int FOUROFKIND_BASE_VALUE    = 205;
    // Min basevalue for straight flush is 242
    private static final int STRAIGHTFLUSH_BASE_VALUE = 666;

    public PokerScorer() {
    }

    public boolean validateDeck(Deck deck) {
        for (Card card : deck.getDeck()) {
            if(card.returnValue() < 2 || card.returnValue() > 14) {
                throw new IllegalArgumentException(
                "A poker deck cannot contain cards of value smaller than 2 or greater than 14");
            }
        }
        return true;
    }

    public int valueHighCard(Deck currentHand) {
        currentHand.sortDeck();
        return currentHand.getLastCard().returnValue();
    }

    public int valueOnePair(Deck currentHand) {
        listOfCards = new int[13];
        for (Card card : currentHand.getDeck()) {
            listOfCards[card.returnValue() - 2] += 1;
        }
        for (int i = listOfCards.length - 1; i >= 0; i--) {
            if(listOfCards[i] == 2) {
                return ONEPAIR_BASE_VALUE + ((i + 2) * 2);
            }
        }
        return -1;
    }

    public int valueTwoPairs(Deck currentHand) {
        listOfCards = new int[13];
        int firstPairValue = 0;
        int secondPairValue = 0;
        for (Card c : currentHand.getDeck()) {
            listOfCards[c.returnValue() - 2] += 1;
        }
        for (int i = 0; i < listOfCards.length; i++) {
            if(listOfCards[i] == 2 && firstPairValue == 0) {
                firstPairValue = ((i + 2) * 2);
            }
            else if(listOfCards[i] == 2 && secondPairValue == 0) {
                secondPairValue = ((i + 2) * 2);
                return TWOPAIRS_BASE_VALUE + firstPairValue + secondPairValue;
            }
        }
        return -1;
    }

    /**
     * Value for Three of a Kind is [BASEVALUE] + [sum of values of the three same value cards]
     */
    public int valueThreeOfKind(Deck currentHand) {
        listOfCards = new int[13];
        for (Card c : currentHand.getDeck()) {
            listOfCards[c.returnValue() - 2] += 1;
        }
        for (int i = 0; i < listOfCards.length; i++) {
            if(listOfCards[i] == 3) {
                return THREEKIND_BASE_VALUE + ((i + 2) * 3);
            }
        }
        return -1;
    }

    public int valueStraight(Deck currentHand) {
        int cardValueAccumulator = 0;
        currentHand.sortDeck();
        for (int i = 0; i < currentHand.getDeckSize() - 1; i++) {
            if(currentHand.getDeck().get(i + 1).returnValue()
            - currentHand.getDeck().get(i).returnValue() != 1) {
                return -1;
            }
            else if(i == currentHand.getDeckSize() - 2) {
                cardValueAccumulator += currentHand.getDeck().get(i).returnValue();
                cardValueAccumulator += currentHand.getDeck().get(i + 1).returnValue();
            }
            else {
                cardValueAccumulator += currentHand.getDeck().get(i).returnValue();
            }
        }
        return STRAIGHT_BASE_VALUE + cardValueAccumulator;
    }

    public int valueFlush(Deck currentHand) {
        String cardSuit = currentHand.getTopCard().returnSuit();
        for (Card card : currentHand.getDeck()) {
            if(card.returnSuit() != cardSuit) {
                return -1;
            }
        }
        int highCardValue = valueHighCard(currentHand);
        return FLUSH_BASE_VALUE + highCardValue;
    }

    public int valueFullHouse(Deck currentHand) {
        listOfCards = new int[13];
        int cardValueAccumulator = 0;
        boolean pairExists = false;
        for (Card card : currentHand.getDeck()) {
            listOfCards[card.returnValue() - 2] += 1;
        }
        for (int i = 0; i < listOfCards.length; i++) {
            if(listOfCards[i] == 3) {
                cardValueAccumulator = ((i + 2) * 3);
            }
            else if(listOfCards[i] == 2) {
                pairExists = true;
            }
            if(cardValueAccumulator != 0 && pairExists) {
                return FULLHOUSE_BASE_VALUE + cardValueAccumulator;
            }
        }
        return -1;
    }

    public int valueFourOfKind(Deck currentHand) {
        listOfCards = new int[13];
        for (Card card : currentHand.getDeck()) {
            listOfCards[card.returnValue() - 2] += 1;
        }
        for (int i = 0; i < listOfCards.length; i++) {
            if(listOfCards[i] == 4) {
                return FOUROFKIND_BASE_VALUE + ((i + 2) * 4);
            }
        }
        return -1;
    }

    public int valueStraightFlush(Deck currentHand) {
        String cardSuit = currentHand.getTopCard().returnSuit();
        for (Card card : currentHand.getDeck()) {
            if(card.returnSuit() != cardSuit) {
                return -1;
            }
        }
        int straightValue = valueStraight(currentHand);
        if(straightValue != -1) {
            return STRAIGHTFLUSH_BASE_VALUE + (straightValue - STRAIGHT_BASE_VALUE);
        }
        return -1;
    }

    public int valueHand(Deck currentHand) {
        int value = -1;
        
        if((value = valueStraightFlush(currentHand)) != -1)
            return value;
        else if((value = valueFourOfKind(currentHand)) != -1)
            return value;
        else if((value = valueFullHouse(currentHand)) != -1)
            return value;
        else if((value = valueFlush(currentHand)) != -1)
            return value;
        else if((value = valueStraight(currentHand)) != -1)
            return value;
        else if((value = valueThreeOfKind(currentHand)) != -1)
            return value;
        else if((value = valueTwoPairs(currentHand)) != -1)
            return value;
        else if((value = valueOnePair(currentHand)) != -1)
            return value;
        else
            return valueHighCard(currentHand);
    }

    public String returnHandName(int value) {
        if(value <= 14)
            return "High card";
        else if(value >= 15 && value <= 39)
            return "One pair";
        else if(value >= 40 && value <= 84)
            return "Two pairs";
        else if(value >= 85 && value <= 121)
            return "Three of a kind";
        else if(value >= 122 && value <= 162)
            return "Straight";
        else if(value >= 163 && value <= 175)
            return "Flush";
        else if(value >= 176 && value <= 212)
            return "Full house";
        else if(value >= 213 && value <= 261)
            return "Four of a kind";
        else
            return "Straight flush";
    }
}