import java.awt.*;

public class StalkerEnemy extends Enemy
{
    private Rectangle playerRect;

    public StalkerEnemy(int x, int y, int w, int h, Rectangle p) {
        super(x, y, w, h);

        playerRect = p;
    }

    public Color getColor() {
        return new Color(255, 0, 255);
    }

    public void move() {
        Rectangle rect = getRectangle();

        if (rect.x < playerRect.x) rect.x++;
        if (rect.y < playerRect.y) rect.y++;
        if (rect.x > playerRect.x) rect.x--;
        if (rect.y > playerRect.y) rect.y--;
    }
}
