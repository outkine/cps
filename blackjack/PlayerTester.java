import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayerTester {
    public static void main(String[] args) {
        Player player = new Player("Anton");
        player.bet = 30;
        System.out.println(player.toString());
        player.lose();
        System.out.println(player.toString());
        player.reset();
        System.out.println(player.toString());
        player.bet = 30;
        player.win();
        System.out.println(player.toString());
    }
}

/* TESTER OUTPUT
Player Anton with a bet of 30 and a balance of 100
You lost!

Player Anton with a bet of 30 and a balance of 70
Player Anton with a bet of 0 and a balance of 70
You won!

Player Anton with a bet of 30 and a balance of 100
 */
