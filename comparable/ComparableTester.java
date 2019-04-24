public class ComparableTester {
    public static void main(String[] args) {
        Card card1 = new Card("Two", "Spades");
        Card card2 = new Card("Ten", "Spades");
        System.out.println(card1.compareTo(card2));

        Password pass1 = new Password("abcdefgh");
        Password pass2 = new Password("abc");
        System.out.println(pass1.compareTo(pass2));

        Triangle tri1 = new Triangle(0, 0, 10, 10, 20, 0);
        Triangle tri2 = new Triangle(0, 0, 20, 20, 30, 0);
        System.out.println(tri1.compareTo(tri2));
    }
}
