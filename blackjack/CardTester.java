import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardTester {
    public static void main(String[] args) {
        Card card = new Card("Two", "Spades");
        System.out.println(card.toString());
        System.out.println(card.getValue());
    }
}


/* TESTER OUTPUT
Two of Spades
2
 */
