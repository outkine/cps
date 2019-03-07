public class Player extends Human {
    public int bet;
    public int balance;
    public boolean stay;

    /**
     * Constructs a Player object
     */
    public Player() {
        super();
        bet = 0;
        stay = false;
        balance = 100;
    }

    /**
     * Handles a losing scenario
     */
    public void lose() {
        balance -= bet;
        System.out.println("You lost!\n");
    }

    /**
     * Handles a winning scenario
     */
    public void win() {
        balance += bet;
        System.out.println("You won!\n");
    }

    /**
     * Resets the player's local state for a round
     */
    public void reset() {
        super.reset();
        bet = 0;
    }

    /**
     * Returns the string representation
     * @return the string representation
     */
    public String toString() {
        return "Player with a bet of " + bet + " and a balance of " + balance;
    }
}
