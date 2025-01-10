package PART_D;

/**
 * DO NOT CHANGE "Student.java" 
 * Please use this class in class StudentClient
 * 
 * Class:       Student
 * File Name:   Student.java
 * 
 * @author Duc Ta
 */

 // DO NOT CHANGE "Student.java"

public class Student {

    // DO NOT CHANGE "Student.java"

    // Data fields
    private String name;
    private double gpa;

    // Constructor, no-argument constructor
    public Student() {
    }

    // Constructor, 2-argument constructor
    public Student(String name, double pga) {
        this.name = name;
        this.gpa = pga;
    }

    // Method, Getter
    public String getName() {
        return this.name;
    }

    // Method, Setter
    public void setName(String name) {
        this.name = name;
    }

    // Method, Getter
    public double getGpa() {
        return this.gpa;
    }

    // Method, Setter
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    // Method
    public void sayHi() {
        System.out.println("Hi!");
    }

    // Method
    public void sayBye() {
        System.out.println("Bye!");
    }

    // Method
    // from java.lang.Object
    // Returns a string representation of the object.
    @Override
    public String toString() {
        return "\'this\' student---\nname: " + this.name + "\ngpa:  " + this.gpa;
    }
}

// DO NOT CHANGE "Student.java"
