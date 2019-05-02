import java.awt.*;

public class DiagonalEnemy extends VerticalEnemy
{
    private int xSpeed;
    private int screenWidth;

    public DiagonalEnemy(int x, int y, int w, int h, int sH, int yS, int sW, int xS) {
        super(x, y, w, h, sH, yS);
        xSpeed = xS;
        screenWidth = sW;
    }

    public Color getColor() {
        return new Color(255, 255, 0);
    }

    public void move() {
        super.move();
        Rectangle rect = getRectangle();
        rect.x += xSpeed;
        if (rect.x > screenWidth - rect.width || rect.x <= 0) {
            xSpeed *= -1;
        }
    }

}
