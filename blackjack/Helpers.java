import java.util.Scanner;
import java.util.List;

public class Helpers {
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

    public static String confirmOptions(Scanner scan, String prompt, List<String> options) {
        System.out.println("\n" + prompt + String.join("/", options));
        String input = scan.next();

        while (true) {
            for (String option : options) {
                if (option.equals(input)) {
                    return input;
                }
            }
            System.out.println("Please answer one of " + String.join(" or ", options));
            input = scan.next();
        }
    }

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
