import java.util.Scanner;

public class Dealer extends Human {
    /**
     * Starts a round
     * @param scan A scanner object
     * @param player A player object
     * @param shoe A shoe object
     */
    public void startRound(Scanner scan, Player player, Shoe shoe) {
        System.out.println("How much are you betting this round?");
        int bet = Helpers.getInt(scan);
        while (bet > player.balance || bet < 0) {
            System.out.println("Bet must be positive and less than the current balance.");
            bet = Helpers.getInt(scan);
        }
        player.bet = bet;

        Card firstCard = shoe.nextCard();
        hand.deal(firstCard);
        if (firstCard.rank.equals("Ace")) {
            boolean insurance = Helpers.confirm(scan, "The Dealer got an Ace! Would you like to take insurance?");
            if (insurance) {
                int insuranceBet = Helpers.getInt(scan);
                while (insuranceBet > player.insuranceBet / 2 || insuranceBet < 0) {
                    System.out.println("Bet must be positive and less than half the current bet.");
                    insuranceBet = Helpers.getInt(scan);
                }
                player.setInsurance(insuranceBet);
            }
        }
        hand.deal(shoe.nextCard(), false);

        while (hand.getState() == HandState.GOOD) {
        }

        if (hand.isDoneState()) {
            System.out.println("\nDealer hand:\n" + hand.toString() + "\n");
            System.out.println("\nThe dealer " + hand.stateToString());

            if (hand.getState() == HandState.BLACKJACK) {
                player.winInsurance();
                player.lose();
            } else {
                player.loseInsurance();
                player.win();
            }
        } else {
            player.hand.deal(shoe.nextCard());
            player.hand.deal(shoe.nextCard());
            System.out.println("\nDealer hand:\n" + hand.toString() + "\n\nYour hand:\n" + player.hand.toString() + "\n");

            while (!player.hand.isDoneState() && !player.stay) {
                player.stay = Helpers.confirm(scan, "Hit?");
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
