import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Deck extends CardCollection {
    /**
     * Constructs an ArrayList deck with all the right Cards
     * @return the ArrayList
     */
    public static ArrayList<Card> makeDeck() {
        return Arrays.stream(Card.ranks)
            .flatMap(rank -> Arrays.stream(Card.suits)
                    .map(suit -> new Card(rank, suit))
            )
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Constructs a Deck object
     */
    public Deck () {
        cards = Deck.makeDeck();
    }
}
