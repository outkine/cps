import java.awt.*;
import javax.swing.*;

/* Anton Outkine
 * 11/13/18
 * Draws a mandelbrot thing
 */
public class MainFrame extends JComponent {
    int MAX_ITER = 255;
    double PRECISION = 100;

    public void paintComponent(Graphics g)  {
        for (double x = -10; x < 10; x += 1 / PRECISION) {
            for (double y = -10; y < 10; y += 1 / PRECISION) {
                plotPixel(g, new Complex(x, y));
            }
        }
    }

    public void plotPixel(Graphics g, Complex c) {
        Complex z = new Complex();
        int n = 0;
        while (z.real * z.real + z.imaginary * z.imaginary < 4 && n < MAX_ITER) {
            z = z.square().add(c);
            n++;
        }
        g.setColor(new Color(n * 20 > 255 ? 255 : n * 20, 255, 255));
        g.fillRect((int) (c.real * PRECISION) + 400, (int) (c.imaginary * PRECISION) + 400, 1, 1);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Component component = new MainFrame();
        frame.add(component);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
