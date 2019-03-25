import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    /**
     * Constructs the necessary instances and runs rounds until the player decides to quit or runs out of money
     * @param args Arguments passed through the CLI
     */
    public static void main(String[] args) {
        ArrayList<Deck> decks = IntStream
            .range(0, 6)
            .mapToObj(i -> new Deck())
            .collect(Collectors.toCollection(ArrayList::new));
        Shoe shoe = new Shoe(decks);
        shoe.shuffle();
        Dealer dealer = new Dealer();

        Scanner scan = new Scanner(System.in);

        System.out.println("Hello! Welcome to Blackjack TM. Start by entering your name.");
        String name = scan.nextLine();
        Player player = new Player(name);
        player.greet();

        boolean nextRound = true;
        while (nextRound) {
            dealer.startRound(scan, player, shoe);

            System.out.println("Your current balance is " + player.balance);
            if (player.balance <= 0) {
                System.out.println("You're out of money! Game over.");
                nextRound = false;
            } else {
                nextRound = Helpers.confirm(scan, "Play another round?");
                player.reset();
                dealer.reset();
            }
        }

        player.sayGoodbye();
    }
}
