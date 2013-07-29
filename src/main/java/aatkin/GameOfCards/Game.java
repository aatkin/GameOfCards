package aatkin.GameOfCards;

/**
 * @author Anssi
 * @version 0.1
 * 
 *          A game of cards. Should support multiple card games with different sets of rules in the
 *          future, such as poker with it's variants.
 */
public interface Game {

    public void play(int rounds);
}