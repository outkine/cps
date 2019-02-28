import java.util.Scanner;

public class Dealer extends Human {
    public void startRound(Scanner scan, Player player, Shoe shoe) {
        System.out.println("How much are you betting this round?");
        player.bet = scan.nextInt();

        while (hand.getState() == HandState.GOOD) {
            hand.deal(shoe.nextCard());
        }
        if (hand.isDoneState()) {
            System.out.println("\nDealer hand:\n" + hand.toString() + "\n");
            System.out.println("\nThe dealer " + hand.stateToString());

            if (hand.getState() == HandState.BLACKJACK) {
                player.lose();
            } else {
                player.win();
            }
        } else {
            player.hand.deal(shoe.nextCard());
            player.hand.deal(shoe.nextCard());
            System.out.println("\nDealer hand:\n" + hand.toString() + "\n\nYour hand:\n" + player.hand.toString() + "\n");

            while (!player.hand.isDoneState() && !player.stay) {
                System.out.println("\nHit? Y/N:");
                player.stay = scan.next().equals("N");
                Card card = shoe.nextCard();
                player.hand.deal(card);
                System.out.println("You got a " + card.toString());
            }

            System.out.println("\nDealer total: " + hand.getValue() + "\nYour total: " + player.hand.getValue() + "\n");
            if (player.hand.isDoneState()) {
                if (player.hand.getState() == HandState.BLACKJACK) {
                    player.win();
                } else {
                    player.lose();
                }
            } else {
                if (player.hand.getValue() == hand.getValue()) {
                    System.out.println("You have tied! You get your bet back.");
                } else if (player.hand.getValue() > hand.getValue()) {
                    player.win();
                } else {
                    player.lose();
                }
            }
        }
    }
}
