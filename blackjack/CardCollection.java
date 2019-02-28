import java.util.ArrayList;
import java.util.Collections;

public class CardCollection {
    protected ArrayList<Card> cards;

    public void shuffle () {
        Collections.shuffle(cards);
    }

    public Card nextCard () {
        if (cards.isEmpty()) {
            cards = Deck.makeDeck();
        }
        return cards.remove(0);
    }
}
