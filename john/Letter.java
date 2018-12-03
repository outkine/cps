public class Letter {
    private String recipient;
    private String writer;
    private java.util.ArrayList<String> lines;

    public Letter(String writer, String recipient) {
        this.writer = writer;
        this.recipient = recipient;
        this.lines = new java.util.ArrayList<String>();
    }

    public void addLine(String line) {
        lines.add(line);
    }

    public String toString() {
        return "Dear " + recipient + ":\n\n" + String.join("\n", lines) + "\n\nSincerely,\n\n" + writer + "\n\n";
    }
}
