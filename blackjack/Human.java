import java.util.ArrayList;

public class Human {
    Hand hand;

    /**
     * Constructs a Human object
     */
    public Human () {
        hand = new Hand();
    }

    /**
     * Resets the human's local state for a round
     */
    public void reset () {
        hand.reset();
    }
}
