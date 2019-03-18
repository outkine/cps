import java.util.Scanner;

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
