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

        hand.deal(shoe.nextCard(), false);

        Card visibleCard = shoe.nextCard();
        hand.deal(visibleCard);
        if (visibleCard.rank.equals("Ace")) {
            player.doingInsurance = Helpers.confirm(scan, "The Dealer got an Ace! Would you like to take insurance?");
            if (player.doingInsurance) {
                System.out.println("Please enter a bet.");
                int insuranceBet = Helpers.getInt(scan);
                while (player.bet > 1 && (insuranceBet > player.bet / 2.0 || insuranceBet < 0)) {
                    System.out.println("Bet must be positive and less than half the current bet (" + player.bet / 2.0 + ").");
                    insuranceBet = Helpers.getInt(scan);
                }
                player.setInsurance(insuranceBet);
            }
        }

        System.out.println("Dealer hand:\n" + hand.toString() + "\n");

        player.hand.deal(shoe.nextCard());
        player.hand.deal(shoe.nextCard());
        System.out.println("Your hand:\n" + player.hand.toString() + "\n");

        boolean stay = false;

        while (!player.hand.isDoneState() && !stay) {
            if (player.hand.cardNumber() == 5) {
                player.winCharlie();
                return;
            } else {
                if (player.hand.cardNumber() == 0) {
                    String action = Helpers.confirmOptions(scan, "Hit, stay, or double down?", java.util.Arrays.asList("H", "S", "D"));
                    player.doubleDown = action.equals("D");
                    stay = action.equals("S");
                } else {
                    stay = !Helpers.confirm(scan, "Hit?");
                }

                if (!stay) {
                    Card card = shoe.nextCard();
                    player.hand.deal(card);
                    System.out.println("You got a " + card.toString());
                    if (player.doubleDown) {
                        stay = true;
                    }
                }
            }
        }

        if (player.hand.isDoneState()) {
            System.out.println("You are " + player.hand.stateToString());
            if (player.hand.getState() == HandState.BLACKJACK) {
                player.win();
                player.loseInsurance();
            } else {
                player.lose();
                player.winInsurance();
            }
        } else {
            while (hand.getState() == HandState.GOOD) {
                Card card = shoe.nextCard();
                hand.deal(card, false);
                System.out.println("The dealer got a " + card.toString());
            }
            if (hand.isDoneState()) {
                System.out.println("The dealer is " + hand.stateToString());
                System.out.println("(the dealer's hidden card was a " + hand.cards.get(0).toString() + ")");

                if (hand.getState() == HandState.BLACKJACK) {
                    player.lose();
                    player.winInsurance();
                } else {
                    player.win();
                    player.loseInsurance();
                }
            } else {
                System.out.println("\nDealer total: " + hand.getValue() + "\nYour total: " + player.hand.getValue());
                System.out.println("(the dealer's hidden card was a " + hand.cards.get(0).toString() + ")");
                System.out.println();
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
