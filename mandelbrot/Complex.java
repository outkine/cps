public class Complex {
    public double real;
    public double imaginary;

    public Complex() {
        real = 0.0;
        imaginary = 0.0;
    }
    public Complex(double real_) {
        real = real_;
        imaginary = 0.0;
    }
    public Complex(double real_, double imaginary_) {
        real = real_;
        imaginary = imaginary_;
    }

    public Complex add(Complex other) {
        return new Complex(real + other.real, imaginary + other.imaginary);
    }


    public Complex square() {
        return new Complex(real * real - imaginary * imaginary, 2 * real * imaginary);
    }
}
