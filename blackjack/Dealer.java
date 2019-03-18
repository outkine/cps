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

        System.out.println("Dealer hand:\n" + hand.toString() + "\n");

        player.hand.deal(shoe.nextCard());
        player.hand.deal(shoe.nextCard());
        System.out.println("Your hand:\n" + player.hand.toString() + "\n");

        player.stay = !Helpers.confirm(scan, "Hit?");

        while (!player.hand.isDoneState() && !player.stay) {
            Card card = shoe.nextCard();
            player.hand.deal(card);
            System.out.println("You got a " + card.toString());
            player.stay = !Helpers.confirm(scan, "Hit?");
        }

        if (player.hand.isDoneState()) {
            if (player.hand.getState() == HandState.BLACKJACK) {
                player.win();
            } else {
                player.lose();
            }
        } else {
            while (hand.getState() == HandState.GOOD) {
                Card card = shoe.nextCard();
                hand.deal(card, false);
                System.out.println("The dealer got a " + card.toString());
            }
            if (hand.isDoneState()) {
                System.out.println("The dealer " + hand.stateToString());

                if (hand.getState() == HandState.BLACKJACK) {
                    player.winInsurance();
                    player.lose();
                } else {
                    player.loseInsurance();
                    player.win();
                }
            } else {
                System.out.println("Dealer total: " + hand.getValue() + "\nYour total: " + player.hand.getValue() + "\n");
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
