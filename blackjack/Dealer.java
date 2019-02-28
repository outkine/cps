import java.util.Scanner;

public class Dealer extends Human {
    public void startRound(Scanner scan, Player player, Shoe shoe) {
        System.out.println("How much are you betting this round?");
        player.bet = scan.nextInt();

        while (hand.getState() == HandState.GOOD) {
            hand.deal(shoe.nextCard());
        }
        if (hand.isDoneState()) {
            System.out.println("The dealer " + hand.stateToString());

            if (hand.getState() == HandState.BLACKJACK) {
                player.lose();
            } else {
                player.win();
            }
        } else {
            while (!player.hand.isDoneState() && !player.stay) {
                player.hand.deal(shoe.nextCard());

                System.out.println("Dealer hand:\n" + hand.toString() + "Your hand:\n" + player.hand.toString());
                System.out.println("Hit? Y/N:");
                player.stay = scan.nextLine().equals("N");
            }

            if (player.hand.isDoneState()) {
                if (player.hand.getState() == HandState.BLACKJACK) {
                    player.win();
                } else {
                    player.lose();
                }
            }

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
