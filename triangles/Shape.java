import TurtleGraphics.*;

//fundamental idea underlying an interface:
//it is a CONTRACT between the interface and any class
//that IMPLEMENTS it.
//the CONTRACT that must be honored by any class
//IMPLEMENTING this interface is that it must provide
//a definition for every single method.
public interface Shape
{
    //nothing but a list of method signatures.
    public double area();
    public void draw(Pen p);
    public double getXPos();
    public double getYPos();
    public void move(double xLoc, double yLoc);
    public void stretchBy(double factor);
    public String toString();
}
