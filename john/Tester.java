public class Tester {
    public static void main(String[] args) {
        Letter letter = new Letter("Anton", "Christian");
        letter.addLine("You like to code;");
        letter.addLine("So do I;");
        letter.addLine("However, you do not like Pie;");
        System.out.println(letter.toString());

        Letter letter2 = new Letter("Anton", "Shark");
        letter2.addLine("You are a shark");
        letter2.addLine("So we cannot be");
        letter2.addLine("Dog");
        System.out.println(letter2.toString());
    }
}
