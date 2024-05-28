//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    /**
     * Returns the volume of a sphere.
     * @param radius the radius of the sphere.
     * @return a <code>double</code> containing the volume of the sphere.
     */
    public static double getSphereVolume(double radius) {
        return (4.0/3.0) * Math.PI * Math.pow(radius, 3);
    }

    /**
     * Returns the volume of a triangular prism.
     * @param base the base of the triangular prism.
     * @param height the height of the triangular prism.
     * @param length the length of the triangular prism.
     * @return a <code>double</code> containing the volume of the triangular prism.
     */
    public static double getTriangularPrismVolume(double base, double height, double length) {
        return (1.0/2.0) * base * height * length;
    }


    public static void main(String[] args) {
        // Get volume of sphere
        double radius = 5.4;
        double sphereVolume = getSphereVolume(radius);

        // Get volume of triangular prism
        double base = 4.0;
        double height = 3.0;
        double length = 5.0;
        double triangularPrismVolume = getTriangularPrismVolume(base, height, length);

        System.out.printf("Volume of a sphere of radius %f is : %f\n", radius, sphereVolume);
        System.out.printf("The volume of a triangular prism with base: %f height: %f length: %f is: %f\n",
                base, height, length, triangularPrismVolume);
    }
}