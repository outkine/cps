
/**
 * Anton Outkine
 * PasswordEnhancer
 * "Enhances" a password when really https://xkcd.com/936/
 */
public class PasswordEnhancer
{
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        p("Input a password: ");
        String input = scanner.nextLine();
        while (!input.equals("-999")) {
            pn("Your enhanced password is " + enhancePassword(input));
            p("Input a password: ");
            input = scanner.nextLine();
        }
    }

    public static String enhancePassword(String oldPassword) {
        String[] vowels = { "a", "e", "i", "o", "u" };
        String[] replacements = { "@", "3", "!", "0", "^" };
        for (int i = 0; i < vowels.length; i++) {
            int index = oldPassword.indexOf(vowels[i]);
            while (index != -1) {
                oldPassword = oldPassword.substring(0, index) + replacements[i] + oldPassword.substring(index + 1, oldPassword.length());
                index = oldPassword.indexOf(vowels[i]);
            }
        }
        return oldPassword;
    }

    public static <T> void p(T s) {
        System.out.print(s);
    }

    public static <T> void pn(T s) {
        System.out.println(s);
    }
}
/* OUTPUT
    Input a password: sadf
    Your enhanced password is s@df
    Input a password: sadf
    Your enhanced password is s@df
    Input a password: sdafu32wffff
    Your enhanced password is sdaf^32wffff
    Input a password: vasdk;i32ioj
    Your enhanced password is vasdk;i32i0j
    Input a password: -999
 */
