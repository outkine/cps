import java.util.ArrayList;
import java.util.stream.Collectors;

public class Hand {
    ArrayList<Card> cards;

    /**
     * Constructs a Hand object
     */
    public Hand() {
        cards = new ArrayList<Card>();
    }

    /**
     * Gets the total value of a hand
     * @return the value
     */
    public int getValue() {
        return cards.stream()
            .map(card -> card.getValue())
            .reduce(0, Integer::sum);
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
     * Returns a string representation of the hand
     * @return the state
     */
    public String toString() {
        return cards.stream()
            .map(Object::toString)
            .collect(Collectors.joining("\n"));
    }

    /**
     * Returns a string representation of the hand's state
     * @return the state
     */
    public String stateToString() {
        switch (getState()) {
            case BLACKJACK: return "has a blackjack";
            case BUST: return "is busted";
            case STAY:
            case GOOD: return "is still good";
            default: assert false; return "";
        }
    }

    /**
     * Resets this hand's cards
     */
    public void reset() {
        cards = new ArrayList<Card>();
    }
}
