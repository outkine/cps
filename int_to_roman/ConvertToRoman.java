/**
 * Anton Outkine
 * ConvertToRoman
 * Convert integers to Roman numerals.
 */

public class ConvertToRoman
{
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        p("Input a number: ");
        int input = scanner.nextInt();
        while (input >= 0) {
            if (input <= 500) pn(ConvertToRoman(input));
            else pn("Number must be less than 500");
            p("Input a number: ");
            input = scanner.nextInt();
        }
        pn("Goodbye.");
    }

    public static String ConvertToRoman(int number) {
        int[] values = {1, 5, 10, 50, 100, 500, 1000};
        String[] numerals = {"I", "V", "X", "L", "C", "D", "M"};

        int[] result = new int[values.length];
        for (int i = values.length - 1; i >= 0; i--) {
            if (number - values[i] >= 0) {
                int times = (int) java.lang.Math.floor(number / values[i]);
                result[i] = times;
                number -= times * values[i];
            }
        }

        java.util.ArrayList<String> finalResult = new java.util.ArrayList<String>();
        for (int i = 0; i < values.length; i += 2)  {
            if (i != 0 && result[i - 2] == 4 && result[i - 1] != 0) {
                finalResult.add(numerals[i]);
                finalResult.add(numerals[i - 2]);
            }

            if (result[i] == 4 && result[i + 1] == 0) {
                finalResult.add(numerals[i + 1]);
                finalResult.add(numerals[i]);
            } else if (result[i] != 4) {
                if (result[i] != 0) {
                    finalResult.add(repeat(numerals[i], result[i]));
                }
                if (result.length - 1 != i && result[i + 1] != 0) {
                    finalResult.add(repeat(numerals[i + 1], result[i + 1]));
                }
            }
        }
        java.util.Collections.reverse(finalResult);
        return String.join(" ", finalResult);
    }

    /* because these are not found in the standard library (?) */

    public static String repeat(String s, int n) {
        return new String(new char[n]).replace("\0", s);
    }

    public static void p(String s) {
        System.out.print(s);
    }

    public static void pn(String s) {
        System.out.println(s);
    }
}
/* OUTPUT
    Input a number: 140
    C X L
    Input a number: 32
    XXX II
    Input a number: 15
    X V
    Input a number: 501
    Number must be less than 500
    Input a number: -3
    Goodbye.
 */
