package PART_E;

public class Semester {
    private String[] courses;
    private int size = 0;

    public void createSemester(int numCourses) {
        courses = new String[numCourses];
    }

    public void addCourses(String[] courseList) {
        // courses[size] = course;
        // size++;
        for (String c : courseList) {
            this.courses[this.size] = c;
            this.size++;
        }
    }

    public void addClass(String course) {
        this.courses[this.size] = course;
        this.size++;
    }

    public void printSemester() {
        for (int i = 0; i < this.courses.length; i++) {
            System.out.printf(this.courses[i]);
            if (i != this.courses.length - 1) {
                System.out.print(", ");
            }
        }
    }
}
