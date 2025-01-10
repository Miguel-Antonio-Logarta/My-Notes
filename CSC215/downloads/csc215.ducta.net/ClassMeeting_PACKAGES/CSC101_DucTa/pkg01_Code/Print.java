package pkg01_Code;

public class Print {
    public static void main(String[] args) {
        System.out.println("Ro");
        System.out.println("Sham");
        System.out.println("Bo");

        System.out.print("Ro");
        System.out.print("Sham");
        System.out.print("Bo");

        System.out.println();

        System.out.print("Ro\nSham\nBo\n");

        System.out.println("PointA\nPointB");
    }
}

/* OUTPUT:
Ro
Sham
Bo
RoShamBo
Ro
Sham
Bo
PointA
PointB

 */