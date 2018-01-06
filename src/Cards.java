import java.util.ArrayList;
import java.util.Collections;

/**
 * Add a description of the class here
 *
 * @author aylee
 * @version 1.0
 */
public class Cards {
    /**
     * Holds the cards that are in the draw pile.
     */
    private static ArrayList<Integer> drawPile = new ArrayList<>();
    /**
     * Holds the cards that have been taken from the draw pile or from the player's hand and discarded.
     */
    private static ArrayList<Integer> discardPile = new ArrayList<>();

    public static void createDrawPile() {
        for (int i = 0; i <= 60; i++) { drawPile.add(i); }
        Collections.shuffle(drawPile);
    }

    public static int getCardFromDrawPile(int index) {
        return drawPile.get(index);
    }
    public static void removeCardFromDrawPile(int index) {
        drawPile.remove(index);
    }
    public static void addCardToDrawPile(int card) {
        drawPile.add(card);
    }
    public static int getCardFromDiscardPile(int index) {
        return discardPile.get(index);
    }
    public static void removeCardFromDiscardPile(int index) {
        discardPile.remove(index);
    }
    public static void addCardToDiscardPile(int card) {
        discardPile.add(card);
    }
    public static int discardPileSize() {
        return discardPile.size();
    }
}
