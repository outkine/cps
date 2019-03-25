public class Player extends Human {
    public int bet;
    public int insuranceBet;
    public boolean doingInsurance;
    public int balance;
    public boolean doubleDown;
    private String name;

    /**
     * Constructs a Player object
     * @param name the player's name
     */
    public Player(String name) {
        super();
        bet = 0;
        balance = 100;
        doubleDown = false;
        this.name = name;
    }

    /**
     * Handles a losing scenario
     */
    public void lose() {
        balance -= doubleDown ? bet * 2 : bet;
        System.out.println("You lost!\n");
    }

    /**
     * Handles a winning scenario
     */
    public void win() {
        balance += doubleDown ? bet * 2 : bet;
        System.out.println("You won!\n");
    }

    /**
     * Handles a Five Card Charlie
     */
    public void winCharlie() {
        balance += bet;
        System.out.println("You won with a five card charlie!\n");
    }

    /**
     * Resets the player's local state for a round
     */
    public void reset() {
        super.reset();
        bet = 0;
        insuranceBet = 0;
        doingInsurance = false;
        doubleDown = false;
    }

    /**
     * Returns the string representation
     * @return the string representation
     */
    public String toString() {
        return "Player " + name + " with a bet of " + bet + " and a balance of " + balance;
    }

    /**
     * Handles a winning insurance scenario
     */
    public void winInsurance() {
        if (doingInsurance) {
            balance += insuranceBet;
            System.out.println("You won your insurance!");
        }
    }

    /**
     * Handles a losing insurance scenario
     */
    public void loseInsurance() {
        if (doingInsurance) {
            balance -= insuranceBet;
            System.out.println("You lost your insurance!");
        }
    }

    /**
     * Sets insurance
     * @param insurance the insurance
     */
    public void setInsurance(int insurance) {
        this.insuranceBet = insurance;
        this.doingInsurance = true;
    }

    /**
     * Says goodbye to the player.
     */
    public void sayGoodbye() {
        System.out.println("\nGoodbye, " + name + "! Go enjoy some nature!");
    }

    /**
     * Displays a greeting for the player.
     */
    public void greet() {
        System.out.println("\nWelcome, " + name + "!");
    }
}

