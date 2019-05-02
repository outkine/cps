import java.awt.*;

public class VerticalEnemy extends Enemy
{
    private int ySpeed;
    private int screenHeight;

    public VerticalEnemy(int x, int y, int w, int h, int sH, int yS) {
        super(x, y, w, h);
        ySpeed = yS;
        screenHeight = sH;
    }

    public void move() {
        Rectangle rect = getRectangle();
        rect.y += ySpeed;
        if (rect.y > screenHeight || rect.y <= 0) {
            ySpeed *= -1;
        }
    }

    public Color getColor() {
        return new Color(255, 0, 0);
    }
}
