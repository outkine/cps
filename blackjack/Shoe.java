import java.util.ArrayList;
import java.util.stream.Collectors;

public class Shoe extends CardCollection {
    /**
     * Constructs a Shoe object
     * @param decks a list of decks to pull cards from
     */
    public Shoe (ArrayList<Deck> decks) {
        cards = decks.stream()
            .flatMap(deck -> deck.cards.stream())
            .collect(Collectors.toCollection(ArrayList::new));
    }
}
