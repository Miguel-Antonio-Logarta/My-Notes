//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static final double PI = 3.1415926;
    public static double getCircumference(double radius) {
        return 2 * PI * radius;
    }

    public static double getTrianglePerimeter(double a, double b, double c) {
        return a + b + c;
    }

    public static void main(String[] args) {

        // Perimeter of a Circle
        double radius = 5.4;
        double circlePerimeter = getCircumference(radius);

        // Perimeter of a triangle
        double sideA = 4.0;
        double sideB = 3.0;
        double sideC = 5.0;
        double trianglePerimeter = getTrianglePerimeter(sideA, sideB, sideC);

        System.out.printf("The perimeter of a Circle of radius %f is: %f\n", radius, circlePerimeter);
        System.out.printf("The perimeter of a Triangle with sides %f %f %f is: %f\n", sideA, sideB, sideC, trianglePerimeter);
    }
}