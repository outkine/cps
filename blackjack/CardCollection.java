import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

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

    /**
     * Returns the string representation
     * @return the string representation
     */
    public String toString() {
        return cards.stream()
            .map(Object::toString)
            .collect(Collectors.joining("\n"));
    }
}
