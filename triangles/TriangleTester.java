import TurtleGraphics.*;

public class TriangleTester {
    public static void main(String[] args)
    {
        Triangle t1 = new Triangle(10,10,20,10,10,20);
        StandardPen p = new StandardPen();
        t1.draw(p);

        Triangle t2 = new Triangle(30,40,40,50,30,35);
        t2.draw(p);

        Triangle t3 = new Triangle(2, 5, 10, 23, 50, 3);
        t3.draw(p);

        System.out.println(t1.toString());
        t1.move(300, 400);
        t1.draw(p);
        System.out.println(t1.toString());
        t1.stretchBy(3);
        t1.draw(p);
        System.out.println(t1.toString());
    }
}
