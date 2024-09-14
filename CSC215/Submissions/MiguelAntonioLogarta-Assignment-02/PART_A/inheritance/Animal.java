/*
 * This is an example to demonstrate what is learned during the lectures. 
 * Please pay attention to the package name as some programs may require multiple files. 
 * If you need help, please reach out.
 */
package inheritance;

/**
 *
 * @author TEAM CSC 210
 */

public abstract class Animal {

    private double weight;

    // protected constructor
    protected Animal() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // abstract method
    public abstract String sound();
}
