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
    }
}

/* TESTER OUTPUT
[[TRIAL ONE]]

How much are you betting this round?
> 30

Dealer hand:
********
Ten of Hearts

Your hand:
Ten of Hearts
Six of Clubs


Hit, stay, or double down? H/S/D
> 3
Please answer H or S or D
> Y
Please answer H or S or D
> D

You got a Five of Clubs
You are in posession of a blackjack
You won!


[[TRIAL TWO]]

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
*/
