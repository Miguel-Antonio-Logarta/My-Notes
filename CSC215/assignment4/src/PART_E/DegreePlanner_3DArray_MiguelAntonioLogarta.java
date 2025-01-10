package PART_E;

public class DegreePlanner_3DArray_MiguelAntonioLogarta {
    public static String[][] splitSemester(String[] semester) {
        // Splits a semester into 3 segments of 2 classes
        String[][] output = new String[3][2];

        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                output[i][j] = semester[i*2+j];
            }
        }

        return output;
    }

    public static void printDegreePlan(String[][][] degreePlan) {
        System.out.println("Printing data... from one 3D String[4][3][2] array containing 24 items:");
        for (int i = 0; i < degreePlan.length; i++) {
            System.out.printf("- Semester #%d: ", i + 1);
            for (int j = 0; j < degreePlan[i].length; j++) {
                for (int k = 0; k < degreePlan[i][j].length; k++) {
                    System.out.printf("%s", degreePlan[i][j][k]);

                    // De Morgan's law is so weird lol. not (A and B) = not A or not B
                    // if (j != degreePlan[i].length - 1 && k != degreePlan[i][j].length - 1) { // Not working
                    if (j != degreePlan[i].length - 1 || k != degreePlan[i][j].length - 1) {
                        System.out.print(", ");     // Print a comma to separate the classes
                    } else {
                        System.out.println();       // Print a new line since this is the last class of the semester
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] semester01 = {"csc101", "csc102", "csc103", "csc104", "csc105", "csc106"};
        String[] semester02 = {"csc201", "csc202", "csc203", "csc204", "csc205", "csc206"};
        String[] semester03 = {"csc301", "csc302", "csc303", "csc304", "csc305", "csc306"};
        String[] semester04 = {"csc401", "csc402", "csc403", "csc404", "csc405", "csc406"};


        // String[Semester][Class][String index]
        /*
        * Plan
        * Split into 4 semesters [4]
        * Split into 3 segments [3]
        * Each of those 3 segments will have 2 classes [2]
        * */

        String[][][] degreePlan = new String[4][3][2];
        degreePlan[0] = splitSemester(semester01);
        degreePlan[1] = splitSemester(semester02);
        degreePlan[2] = splitSemester(semester03);
        degreePlan[3] = splitSemester(semester04);

        printDegreePlan(degreePlan);
    }
}
