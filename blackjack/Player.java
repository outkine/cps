public class Player extends Human {
    public int bet;
    public int balance;
    public boolean stay;

    public Player() {
        super();
        bet = 0;
        stay = false;
        balance = 100;
    }

    public void lose() {
        balance -= bet;
        System.out.println("You lost!\n");
    }

    public void win() {
        balance += bet;
        System.out.println("You won!\n");
    }

    public void reset() {
        super.reset();
        bet = 0;
    }
}
