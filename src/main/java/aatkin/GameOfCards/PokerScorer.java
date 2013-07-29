package aatkin.GameOfCards;

/**
 * @author Anssi Kinnunen, aatkin@utu.fi
 * 
 *         Class for scoring different hands used in Poker, as specified in class Poker. Hand values
 *         are given a pre-fixed base value, which in this case, is [prev hand max value] - [current
 *         hand min value] + 1. This implementation does not yet account for wildcard games, where
 *         multiple players could achieve the same high card combo.
 */

public class PokerScorer implements GameScorer {

    private int[]            amountOfValues;

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
        for(Card card : deck.getDeck()) {
            if(card.returnValue() < 2 || card.returnValue() > 14) {
                throw new IllegalArgumentException("A poker deck cannot contain cards of value smaller than 2 or greater than 14");
            }
        }
        return true;
    }

    public int valueHighCard(Deck currentHand) {
        currentHand.sortDeck();
        return currentHand.getLastCard().returnValue();
    }

    public int valueOnePair(Deck currentHand) {
        amountOfValues = new int[13];
        for (Card c : currentHand.getDeck()) {
            amountOfValues[c.returnValue() - 2] += 1;
        }
        for (int i = amountOfValues.length - 1; i >= 0; i--) {
            if (amountOfValues[i] == 2) {
                return ONEPAIR_BASE_VALUE + ((i + 2) * 2);
            }
        }
        return -1;
    }

    public int valueTwoPairs(Deck currentHand) {
        amountOfValues = new int[13];
        int firstPairValue = 0;
        int secondPairValue = 0;

        for (Card c : currentHand.getDeck()) {
            amountOfValues[c.returnValue() - 2] += 1;
        }
        for (int i = 0; i < amountOfValues.length; i++) {
            if (amountOfValues[i] == 2 && firstPairValue == 0) {
                firstPairValue = ((i + 2) * 2);
            }
            else if (amountOfValues[i] == 2 && secondPairValue == 0) {
                secondPairValue = ((i + 2) * 2);
                return TWOPAIRS_BASE_VALUE + firstPairValue + secondPairValue;
            }
        }
        return -1;
    }

    public int valueThreeOfKind(Deck currentHand) {
        amountOfValues = new int[13];

        for (Card c : currentHand.getDeck()) {
            amountOfValues[c.returnValue() - 2] += 1;
        }
        for (int i = 0; i < amountOfValues.length; i++) {
            if (amountOfValues[i] == 3) {
                return THREEKIND_BASE_VALUE + ((i + 2) * 3);
            }
        }
        return -1;
    }

    public int valueStraight(Deck currentHand) {
        int accumulator = 0;
        currentHand.sortDeck();

        for (int i = 0; i < currentHand.getDeckSize() - 1; i++) {
            if (currentHand.getDeck().get(i + 1).returnValue()
            - currentHand.getDeck().get(i).returnValue() != 1) {
                return -1;
            }
            else if (i == currentHand.getDeckSize() - 2) {
                accumulator += currentHand.getDeck().get(i).returnValue();
                accumulator += currentHand.getDeck().get(i + 1).returnValue();
            }
            else {
                accumulator += currentHand.getDeck().get(i).returnValue();
            }
        }
        return STRAIGHT_BASE_VALUE + accumulator;
    }

    public int valueFlush(Deck currentHand) {
        String color = currentHand.getTopCard().returnSuit();

        for (Card c : currentHand.getDeck()) {
            if (c.returnSuit() != color) {
                return -1;
            }
        }
        PokerScorer temp = new PokerScorer();
        int hiCardValue = temp.valueHighCard(currentHand);

        return FLUSH_BASE_VALUE + hiCardValue;
    }

    public int valueFullHouse(Deck currentHand) {
        amountOfValues = new int[13];
        int accumulator = 0;
        boolean hasPair = false;

        for (Card c : currentHand.getDeck()) {
            amountOfValues[c.returnValue() - 2] += 1;
        }
        for (int i = 0; i < amountOfValues.length; i++) {
            if (amountOfValues[i] == 3) {
                accumulator = ((i + 2) * 3);
            }
            else if (amountOfValues[i] == 2) {
                hasPair = true;
            }
            if (accumulator != 0 && hasPair) {
                return FULLHOUSE_BASE_VALUE + accumulator;
            }
        }
        return -1;
    }

    public int valueFourOfKind(Deck currentHand) {
        amountOfValues = new int[13];

        for (Card c : currentHand.getDeck()) {
            amountOfValues[c.returnValue() - 2] += 1;
        }

        for (int i = 0; i < amountOfValues.length; i++) {
            if (amountOfValues[i] == 4) {
                return FOUROFKIND_BASE_VALUE + ((i + 2) * 4);
            }
        }
        return -1;
    }

    public int valueStraightFlush(Deck currentHand) {
        String color = currentHand.getTopCard().returnSuit();

        for (Card c : currentHand.getDeck()) {
            if (c.returnSuit() != color) {
                return -1;
            }
        }
        PokerScorer temp = new PokerScorer();
        int straightValue = temp.valueStraight(currentHand);

        if (straightValue != -1) {
            return STRAIGHTFLUSH_BASE_VALUE + (straightValue - STRAIGHT_BASE_VALUE);
        }
        return -1;
    }

    public int valueHand(Deck currentHand) {

        if (valueStraightFlush(currentHand) != -1)
            return valueStraightFlush(currentHand);

        else if (valueFourOfKind(currentHand) != -1)
            return valueFourOfKind(currentHand);

        else if (valueFullHouse(currentHand) != -1)
            return valueFullHouse(currentHand);

        else if (valueFlush(currentHand) != -1)
            return valueFlush(currentHand);

        else if (valueStraight(currentHand) != -1)
            return valueStraight(currentHand);

        else if (valueThreeOfKind(currentHand) != -1)
            return valueThreeOfKind(currentHand);

        else if (valueTwoPairs(currentHand) != -1)
            return valueTwoPairs(currentHand);

        else if (valueOnePair(currentHand) != -1)
            return valueOnePair(currentHand);

        else
            return valueHighCard(currentHand);
    }

    public String returnHandName(int value) {

        if (value <= 14)
            return "High card";
        else if (value >= 15 && value <= 39)
            return "One pair";
        else if (value >= 40 && value <= 84)
            return "Two pairs";
        else if (value >= 85 && value <= 121)
            return "Three of a kind";
        else if (value >= 122 && value <= 162)
            return "Straight";
        else if (value >= 163 && value <= 175)
            return "Flush";
        else if (value >= 176 && value <= 212)
            return "Full house";
        else if (value >= 213 && value <= 261)
            return "Four of a kind";
        else
            return "Straight flush";
    }
}