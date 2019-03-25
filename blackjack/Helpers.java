import java.util.Scanner;
import java.util.List;

public class Helpers {
    /**
     * Confirms a prompt by asking the player for a "Y" or "N"
     * @param scan the scanner to use
     * @param prompt the prompt to display
     * @return whether the player confirmed
     */
    public static boolean confirm(Scanner scan, String prompt) {
        System.out.println("\n" + prompt + " Y/N");

        String input = scan.next();
        while (!input.equals("Y") && !input.equals("N")) {
            System.out.println("Please answer Y or N.");
            input = scan.next();
        }
        System.out.println();
        return input.equals("Y");
    }

    /**
     * Confirms a prompt by asking the player for one of a list of options
     * @param scan the scanner to use
     * @param prompt the prompt to display
     * @param options the list of options to use
     * @return the option confirmed
     */
    public static String confirmOptions(Scanner scan, String prompt, List<String> options) {
        System.out.println("\n" + prompt + " " + String.join("/", options));
        String input = scan.next();

        while (true) {
            for (String option : options) {
                if (option.equals(input)) {
                    System.out.println();
                    return input;
                }
            }
            System.out.println("Please answer " + String.join(" or ", options));
            input = scan.next();
        }
    }

    /**
     * Get an integer from the player
     * @param scan the scanner to use
     * @return the integer
     */
    public static int getInt(Scanner scan) {
        int result = 0;
        boolean done = false;
        while (!done) {
            try {
                result = scan.nextInt();
                done = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a number.");
                scan.next();
            }
        }
        System.out.println();
        return result;
    }
}
