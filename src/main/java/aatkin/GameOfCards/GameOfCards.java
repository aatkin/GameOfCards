package aatkin.GameOfCards;

public class GameOfCards {

    /**
     * @param args
     */
    public static void main(String[] args) {

        long timeBefore = System.currentTimeMillis();
        GameScorer scorer = new PokerScorer();
        Game game = new FiveCardDraw(scorer);
        game.addPlayerToGame(new Player("Matti"));
        game.addPlayerToGame(new Player("Teppo"));
        game.addPlayerToGame(new Player("Seppo"));
        game.addPlayerToGame(new Player("Leppo"));
        game.addPlayerToGame(new Player("Raudo"));
        game.play(500);
        long totalTime = System.currentTimeMillis() - timeBefore;
        System.out.println(totalTime + "ms");
    }

}
