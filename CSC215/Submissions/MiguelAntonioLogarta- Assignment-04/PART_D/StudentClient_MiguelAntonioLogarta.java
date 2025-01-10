package PART_D;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Please implement class StudentClient by adding code to it.
 * Please DO NOT change the provided code. ONLY add more code.
 * 
 * Class:       StudentClient
 * File Name:   StudentClient_MiguelAntonioLogarta.java
 *
 * @author Duc Ta
 */
public class StudentClient_MiguelAntonioLogarta {

    public static int NUM_STUDENTS = 3;

    public static Student[] createStudents(int n) {
        Student[] students = new Student[n];
        Scanner sc = new Scanner(System.in);
        String studentName;
        double gpa;

        System.out.printf("[+] Creating %d students...\n", students.length);

        for (int i = 0; i < students.length; i++) {
            while (true) {
                try {
                    System.out.printf("- Enter a name for student #%d: ", i + 1);
                    studentName = sc.nextLine();

                    System.out.printf("- Enter a GPA for student #%d: ", i + 1);
                    gpa = sc.nextDouble();

                    Student student = new Student(studentName, gpa);
                    students[i] = student;

                    sc.nextLine(); // Flush the buffer
                    System.out.println();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Your input was not valid, please enter a string or a decimal number.");
                    sc.nextLine();
                }
            }
        }

        return students;
    }

    public static Student searchStudent(Student[] students) {
        Scanner sc = new Scanner(System.in);
        Student existingStudent = new Student();
        boolean studentFound = false;

        while (!studentFound) {
            String studentSearch;

            System.out.print("[-] Enter a student's full name to update the student: ");
            studentSearch = sc.nextLine().trim();

            for (Student student : students) {
                if (student.getName().equalsIgnoreCase(studentSearch)) {
                    existingStudent = student;
                    studentFound = true;
                    break;
                }
            }

            if (!studentFound) {
                System.out.println("Student not found!");
                sc.nextLine(); // Flush the buffer
            }
        }

        return existingStudent;
    }

    public static void updateStudent(Student student) {
        Scanner sc = new Scanner(System.in);
        String newStudentName;
        double newGpa;

        while (true) {

            try {
                System.out.print("[-] Enter new student name: ");
                newStudentName = sc.nextLine();
                System.out.print("[-] Enter new student gpa: ");
                newGpa = sc.nextDouble();
                System.out.println();

                student.setName(newStudentName);
                student.setGpa(newGpa);

                sc.nextLine(); // Flush the buffer

                break;
            } catch (InputMismatchException e) {
                System.out.println("Your input was not valid, please enter a string or a decimal number.");
                sc.nextLine(); // Flush the buffer
            }
        }
    }

    public static void main(String[] args) {

        Student[] students = createStudents(NUM_STUDENTS);

        System.out.printf("[+] The %d students created.\n", students.length);
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println();

        Student selectedStudent = searchStudent(students);

        updateStudent(selectedStudent);

        System.out.printf("[+] The %d students updated.\n", students.length);
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
