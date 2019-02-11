import java.awt.*;
import javax.swing.*;

public class MainFrame extends JComponent {
    public void paintComponent(Graphics g)  {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Component component = new MainFrame();
        frame.add(component);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
