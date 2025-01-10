package pkg03_Code;

/**
 * @author Duc Ta
 */

public class Variable {
    public static void main(String[] args) {
        System.out.println("Hello World from SFSU");

        String location = "CSC210";
        System.out.println("Hello World from " + location);
        location = "CSC215";
        System.out.println("Hello World from " + location);

        System.out.println("Scooby-Doo, Where Are You!");

        String character, expression;
        expression = "Where Are you!";
        character = "Goofy";
        character = character + " " + character;
        System.out.println(character + ", " + expression);

        character ="Snoopy";
        character = character.repeat(2);
        System.out.println(character + ", " + expression);

        character = "Mommmmmmm....";
        expression = "food... please....";
        expression = character + ", " + expression;
        System.out.println(expression);
    }
}
