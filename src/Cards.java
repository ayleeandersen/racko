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
    private ArrayList<Integer> drawPile = new ArrayList<>();
    /**
     * Holds the cards that have been taken from the draw pile or from the player's hand and discarded.
     */
    private ArrayList<Integer> discardPile = new ArrayList<>();

    public Cards() {
        for (int i = 0; i <= 60; i++) { drawPile.add(i); }
        Collections.shuffle(drawPile);
    }
}
