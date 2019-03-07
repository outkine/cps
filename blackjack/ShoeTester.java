import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShoeTester {
    public static void main(String[] args) {
        ArrayList<Deck> decks = IntStream
            .range(0, 6)
            .mapToObj(i -> new Deck())
            .collect(Collectors.toCollection(ArrayList::new));
        Shoe shoe = new Shoe(decks);
        System.out.println(shoe.toString());
        shoe.shuffle();
        System.out.println(shoe.toString());
    }
}
