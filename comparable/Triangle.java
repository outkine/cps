import TurtleGraphics.*;

public class Triangle implements Shape, Comparable {
    private double ax;
    private double ay;

    private double bx;
    private double by;

    private double cx;
    private double cy;

    public Triangle(double ax, double ay, double bx, double by, double cx, double xy) {
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
        this.cx = cx;
        this.cy = cy;
    }

    public double area() {
        return Math.abs((ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)) / 2);
    }

    public void draw(Pen p) {
        p.up();
        p.move(ax, ay);
        p.down();
        p.move(bx, by);
        p.move(cx, cy);
        p.move(ax, ay);
    }

    public double getXPos() {
        return ax;
    }
    public double getYPos() {
        return ay;
    }

    public void move(double xLoc, double yLoc) {
        double xTrans = xLoc - ax;
        double yTrans = yLoc - ay;

        ax += xTrans;
        ay += yTrans;

        bx += xTrans;
        by += yTrans;

        cx += xTrans;
        cy += yTrans;
    }

    public void stretchBy(double factor) {
        bx = ax + (bx - ax) * factor;
        by = ay + (by - ay) * factor;

        cx = ax + (cx - ax) * factor;
        cy = ay + (cy - ay) * factor;
    }

    public String toString() {
        return "Triangle ABC with points "
            + "A ("+ ax + "," + ay + ") "
            + "B ("+ bx + "," + by + ") and "
            + "C ("+ cx + "," + cy + "), "
            + "an area of " + area() + ", "
            + "and an origin of (" + getXPos() + "," + getYPos() + ").";
    }

    public int compareTo(Object object) {
        Triangle triangle = (Triangle) object;
        return (int) (area() - triangle.area());
    }
}
