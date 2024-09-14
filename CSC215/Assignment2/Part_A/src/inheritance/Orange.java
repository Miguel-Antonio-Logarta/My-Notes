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

public class Orange extends Fruit {

    public Orange() {
    }

    @Override
    public String howToEat() {
        return "Orange: Make orange juice";
    }
}
