package aatkin.GameOfCards;

public class PokerScorer {
	
	private int[] amountOfValues;
	
	private static final int ONEPAIR_BASE_VALUE = 11;
	private static final int TWOPAIRS_BASE_VALUE = 29;
	private static final int THREEKIND_BASE_VALUE = 76;
	private static final int STRAIGHT_BASE_VALUE = 99;
	private static final int FLUSH_BASE_VALUE = 146;
	private static final int FULLHOUSE_BASE_VALUE = 189;
	private static final int FOUROFKIND_BASE_VALUE = 224;
	private static final int STRAIGHTFLUSH_BASE_VALUE = 666;
	
	public PokerScorer() {
	}
	
	public int valueHighCard(Deck currentHand) {
		currentHand.sortDeck();
		return currentHand.returnLastCard().returnValue();
	}
	
	public int valueOnePair(Deck currentHand) {
		amountOfValues = new int[13];	
		for(Card c : currentHand.returnDeck()) {
			amountOfValues[c.returnValue() - 2] += 1;
		}	
		for(int i = amountOfValues.length - 1; i >= 0; i--) {
			if(amountOfValues[i] == 2) {
				return ONEPAIR_BASE_VALUE + ((i + 2) * 2);
			}
		}
		return -1;
	}
	
	public int valueTwoPairs(Deck currentHand) {
		amountOfValues = new int[13];
		int firstPairValue = 0;
		int secondPairValue = 0;
		
		for(Card c : currentHand.returnDeck()) {
			amountOfValues[c.returnValue() - 2] += 1;
		}
		for(int i = 0; i < amountOfValues.length; i++) {
			if(amountOfValues[i] == 2 && firstPairValue == 0) {
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
		
		for(Card c : currentHand.returnDeck()) {
			amountOfValues[c.returnValue() - 2] += 1;
		}
		for(int i = 0; i < amountOfValues.length; i++) {
			if(amountOfValues[i] == 3) {
				return THREEKIND_BASE_VALUE + ((i + 2) * 3);
			}
		}
		return -1;
	}
	
	public int valueStraight(Deck currentHand) {
		int accumulator = 0;
		currentHand.sortDeck();
		
		for(int i = 0; i < currentHand.returnDeckSize() - 1; i++) {
			if(currentHand.returnDeck().get(i+1).returnValue() - currentHand.returnDeck().get(i).returnValue() != 1) {
				return - 1;
			}
			else if (i == currentHand.returnDeckSize() - 2){
				accumulator += currentHand.returnDeck().get(i).returnValue();
				accumulator += currentHand.returnDeck().get(i+1).returnValue();
			}
			else {
				accumulator += currentHand.returnDeck().get(i).returnValue();
			}
		}
		return STRAIGHT_BASE_VALUE + accumulator;
	}
	
	public int valueFlush(Deck currentHand) {
		String color = currentHand.returnTopCard().returnSuit();
		
		for(Card c : currentHand.returnDeck()) {
			if(c.returnSuit() != color) {
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
		
		for(Card c : currentHand.returnDeck()) {
			amountOfValues[c.returnValue() - 2] += 1;
		}
		for (int i = 0; i < amountOfValues.length; i++) {
			if(amountOfValues[i] == 3) {
				accumulator = ((i + 2) * 3);
			}
			else if(amountOfValues[i] == 2) {
				hasPair = true;
			}
			if(accumulator != 0 && hasPair) {
				return FULLHOUSE_BASE_VALUE + accumulator;
			}
		}
		return -1;
	}
	
	public int valueFourOfKind(Deck currentHand) {
		amountOfValues = new int[13];
		
		for(Card c : currentHand.returnDeck()) {
			amountOfValues[c.returnValue() - 2] += 1;
		}
		
		for(int i = 0; i < amountOfValues.length; i++) {
			if(amountOfValues[i] == 4) {
				return FOUROFKIND_BASE_VALUE + ((i + 2) * 4);
			}
		}
		return -1;
	}
	
	public int valueStraightFlush(Deck currentHand) {
		String color = currentHand.returnTopCard().returnSuit();
		
		for(Card c : currentHand.returnDeck()) {
			if(c.returnSuit() != color) {
				return -1;
			}
		}
		PokerScorer temp = new PokerScorer();
		int straightValue = temp.valueStraight(currentHand);
		
		if(straightValue != -1) {
			return STRAIGHTFLUSH_BASE_VALUE + (straightValue - STRAIGHT_BASE_VALUE);
		}
		return -1;
	}
}