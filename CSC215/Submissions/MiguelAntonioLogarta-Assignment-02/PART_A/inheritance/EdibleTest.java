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

/****************************************************************
 *
 * File: EdibleTest.java
 * By:   Miguel Antonio Logarta
 * Date: 09-03-2024
 *
 * Description: This is a code reference tutorial where I
 * have to fix the package and reference errors. At the end of the
 * tutorial, I have to run this file to demonstrate that I did the
 * tutorial correctly.
 *
 ****************************************************************/


public class EdibleTest {

    public static void main(String[] args) {
        Object[] objects = {new Tiger(), new Chicken(),
            new Apple(), new Orange()};

        for (Object object : objects) {
            if (object instanceof Edible) {
                System.out.println(((Edible) object).howToEat());
            }
            if (object instanceof Animal) {
                System.out.println(((Animal) object).sound());
            }
        }
    }
}

/*
Tiger: RROOAARR
Chicken: Fry it
Chicken: cock-a-doodle-doo
Apple: Make apple cider
Orange: Make orange juice
*/
