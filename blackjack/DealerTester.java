import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.ArrayList;

public class DealerTester {
    public static void main(String[] args) {
        ArrayList<Deck> decks = IntStream
            .range(0, 6)
            .mapToObj(i -> new Deck())
            .collect(Collectors.toCollection(ArrayList::new));
        Shoe shoe = new Shoe(decks);
        shoe.shuffle();
        Dealer dealer = new Dealer();
        Player player = new Player("Anton");
        Scanner scan = new Scanner(System.in);

        dealer.startRound(scan, player, shoe);

        /*
# TRIAL ONE

How much are you betting this round?
> 5

Dealer hand:
Nine of Diamonds
Seven of Spades
Queen of Spades


The dealer is busted
You won!


# TRIAL TWO

How much are you betting this round?
> 10

Dealer hand:
Ten of Clubs
Nine of Diamonds

Your hand:
Queen of Hearts
Eight of Spades


Hit? Y/N:
> Y
You got a Queen of Diamonds

Dealer total: 19
Your total: 28

You lost!
         */
    }
}
