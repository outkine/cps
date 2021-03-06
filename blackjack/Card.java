import java.util.Map;
import java.util.HashMap;

public class Card {
    public static String[] ranks = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
    public static String[] suits = {"Spades", "Hearts", "Clubs", "Diamonds"};

    public static Map<String, Integer> rankToValue = new HashMap<String, Integer>() {{
        put("Two", 2);
        put("Three", 3);
        put("Four", 4);
        put("Five", 5);
        put("Six", 6);
        put("Seven", 7);
        put("Eight", 8);
        put("Nine", 9);
        put("Ten", 10);
        put("Jack", 10);
        put("Queen", 10);
        put("King", 10);
        put("Ace", 11);
    }};



    // Map.ofEntries(
    //     Map.entry("Two", 2),
    //     Map.entry("Three", 3),
    //     Map.entry("Four", 4),
    //     Map.entry("Five", 5),
    //     Map.entry("Six", 6),
    //     Map.entry("Seven", 7),
    //     Map.entry("Eight", 8),
    //     Map.entry("Nine", 9),
    //     Map.entry("Ten", 10),
    //     Map.entry("Jack", 10),
    //     Map.entry("Queen", 10),
    //     Map.entry("King", 10),
    //     Map.entry("Ace", 11)
    // );

    public String rank;
    public String suit;

    /**
     * Contructs a Card object
     * @param rank the rank of the card
     * @param suit the suit of the card
     */
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Returns the value of the card
     * @return the value
     */
    public int getValue() {
        return Card.rankToValue.get(this.rank);
    }

    /**
     * Returns the string representation of the card
     * @return the string representation
     */
    public String toString() {
        return rank + " of " + suit;
    }
}
