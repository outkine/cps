import java.util.ArrayList;
import java.util.stream.Collectors;

public class Hand {
    ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<Card>();
    }

    public int getValue() {
        return cards.stream()
            .map(card -> card.getValue())
            .reduce(0, Integer::sum);
    }

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

    public boolean isDoneState() {
        return getState() == HandState.BUST || getState() == HandState.BLACKJACK;
    }

    public void deal(Card card) {
        cards.add(card);
    }

    public String toString() {
        return cards.stream()
            .map(Object::toString)
            .collect(Collectors.joining("\n"));
    }

    public String stateToString() {
        switch (getState()) {
            case BLACKJACK: return "has a blackjack";
            case BUST: return "is busted";
            case STAY:
            case GOOD: return "is still good";
            default: assert false; return "";
        }
    }
}
