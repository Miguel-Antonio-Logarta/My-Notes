import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static int getUserBudget() {
        int budget = 0;
        Scanner sc = new Scanner(System.in);

        do {
            try {
                System.out.print("What is your budget in $? ");
                budget = sc.nextInt();

                if (budget < 0) {
                    System.out.println("Please enter a number greater than 0");
                }
            } catch (InputMismatchException e) {
                System.out.println("Your input was an invalid number");
                sc.nextLine();
            }
        } while (budget <= 0);

//        sc.close();
        return budget;
    }

    public static String getUserCategory() {
        System.out.println("What kind of item do you want to buy?");
        System.out.println("* Electronics");
        System.out.println("* Clothes");
        System.out.println("* Book");
        System.out.println("* Gift Card");
        System.out.print("Type your choice: ");

        String category;
        Scanner sc = new Scanner(System.in);
        category = sc.nextLine();

        while (true) {
            if(category.equalsIgnoreCase("Electronics")
                    || category.equalsIgnoreCase("Clothes")
                    || category.equalsIgnoreCase("Book")
                    || category.equalsIgnoreCase("Gift Card")) {
//                sc.close();
                return category;
            } else {
                System.out.print("Please enter a valid choice: ");
                category = sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        String category = getUserCategory();
        int budget = getUserBudget();

        if (budget < 10) {
            if (category.equalsIgnoreCase("Electronics")) {
                System.out.println("You should buy a Phone Charger");
            } else if (category.equalsIgnoreCase("Clothes")) {
                System.out.println("You should buy a Giants Cap");
            } else if (category.equalsIgnoreCase("Book")) {
                System.out.println("You should buy \"The Book Thief\"");
            } else if (category.equalsIgnoreCase("Gift Card")) {
                System.out.println("You should buy a Starbucks Gift Card");
            }
        } else if (budget < 30 ) {
            if (category.equalsIgnoreCase("Electronics")) {
                System.out.println("You should buy a Phone Case");
            } else if (category.equalsIgnoreCase("Clothes")) {
                System.out.println("You should buy an SFSU t-shirt");
            } else if (category.equalsIgnoreCase("Book")) {
                System.out.println("You should buy \"Flowers for Algernon\"");
            } else if (category.equalsIgnoreCase("Gift Card")) {
                System.out.println("You should buy a Target Gift Card");
            }
        } else if (budget < 50 ) {
            if (category.equalsIgnoreCase("Electronics")) {
                System.out.println("You should buy a Camera Lens for a Phone");
            } else if (category.equalsIgnoreCase("Clothes")) {
                System.out.println("You should buy an SFSU Hoodie");
            } else if (category.equalsIgnoreCase("Book")) {
                System.out.println("You should buy the \"Lord of the Rings\" trilogy");
            } else if (category.equalsIgnoreCase("Gift Card")) {
                System.out.println("You should buy a Visa Gift Card");
            }
        } else {
            System.out.println("Instead of a gift, you should donate this money towards charity");
        }
    }
}