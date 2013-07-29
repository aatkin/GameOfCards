package aatkin.GameOfCards;

public class GameOfCards {

    /**
     * @param args
     */
    public static void main(String[] args) {

        long timeBefore = System.currentTimeMillis();
        GameScorer scorer = new PokerScorer();
        Game game = new FiveCardDraw(scorer);
        ((FiveCardDraw) game).addPlayerToGame(new Player("Matti"));
        ((FiveCardDraw) game).addPlayerToGame(new Player("Teppo"));
        ((FiveCardDraw) game).addPlayerToGame(new Player("Seppo"));
        ((FiveCardDraw) game).addPlayerToGame(new Player("Leppo"));
        ((FiveCardDraw) game).addPlayerToGame(new Player("Raudo"));
        game.play(500);
        long totalTime = System.currentTimeMillis() - timeBefore;
        System.out.println(totalTime + "ms");
    }

}
