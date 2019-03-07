import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HandTester {
    public static void main(String[] args) {
        Hand hand = new Hand();
        System.out.println(hand.toString());
        System.out.println(hand.stateToString());
        hand.deal(new Card("Two" ,"Spades"));
        System.out.println(hand.toString());
        System.out.println(hand.stateToString());
        hand.reset();
        System.out.println(hand.toString());
        System.out.println(hand.stateToString());
    }
}
