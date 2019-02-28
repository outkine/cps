import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        ArrayList<Deck> decks = IntStream
            .range(0, 6)
            .mapToObj(i -> new Deck())
            .collect(Collectors.toCollection(ArrayList::new));
        Shoe shoe = new Shoe(decks);
        Dealer dealer = new Dealer();
        Player player = new Player();

        Scanner scan = new Scanner(System.in);
        boolean nextRound = true;
        while (nextRound) {
            dealer.startRound(scan, player, shoe);

            System.out.println("Your current balance is " + player.balance);
            System.out.println("Play another round? Y/N");
            nextRound = scan.nextLine().equals("Y");
            player.reset();
        }
    }
}
