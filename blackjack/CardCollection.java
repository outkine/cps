import java.util.ArrayList;
import java.util.Collections;

public class CardCollection {
    protected ArrayList<Card> cards;

    /**
     * Shuffles the collection
     */
    public void shuffle () {
        Collections.shuffle(cards);
    }

    /**
     * Returns a card from the collection
     */
    public Card nextCard () {
        if (cards.isEmpty()) {
            cards = Deck.makeDeck();
        }
        return cards.remove(0);
    }
}
