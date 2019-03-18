import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    ArrayList<Card> cards;
    ArrayList<Card> invisibleCards;

    /**
     * Constructs a Hand object
     */
    public Hand() {
        cards = new ArrayList<Card>();
        invisibleCards = new ArrayList<Card>();
    }

    /**
     * Gets the total value of a hand
     * @return the value
     */
    public int getValue() {
        int value = cards.stream()
            .map(card -> card.getValue())
            .reduce(0, Integer::sum);
        if (value > 21) {
            List<Card> aces = cards.stream()
                .filter(card -> card.rank.equals("Ace"))
                .collect(Collectors.toList());
            value -= aces.size() * 10;
        }
        return value;
    }

    /**
     * Gets the state of the hand as it relates to winning or losing
     * @return the state
     */
    public HandState getState() {
        int value = getValue();
        if (value == 21) {
            return HandState.BLACKJACK;
        } else if (value > 21) {
            return HandState.BUST;
        } else if (value >= 17) {
            return HandState.STAY;
        } else {
            return HandState.GOOD;
        }
    }

    /**
     * Determines whether the hand is in an end state
     * @return the done state
     */
    public boolean isDoneState() {
        return getState() == HandState.BUST || getState() == HandState.BLACKJACK;
    }

    /**
     * Add a card to the hand
     * @param card the card to add
     */
    public void deal(Card card) {
        cards.add(card);
    }

    /**
     * Add a card to the hand
     * @param card the card to add
     * @param visible whether it should be visible
     */
    public void deal(Card card, boolean visible) {
        cards.add(card);
        if (!visible) {
            invisibleCards.add(card);
        }
    }

    /**
     * Returns a string representation of the hand
     * @return the string representation
     */
    public String toString() {
        return cards.stream()
            .map(card -> invisibleCards.contains(card) ? "********" : card.toString())
            .collect(Collectors.joining("\n"));
    }

    /**
     * Returns a string representation of the hand's state
     * @return the string representation
     */
    public String stateToString() {
        switch (getState()) {
            case BLACKJACK: return "in posession of a blackjack";
            case BUST: return "busted";
            case STAY:
            case GOOD: return "still good";
            default: assert false; return "";
        }
    }

    /**
     * Resets this hand's cards
     */
    public void reset() {
        cards = new ArrayList<Card>();
        invisibleCards = new ArrayList<Card>();
    }
}
