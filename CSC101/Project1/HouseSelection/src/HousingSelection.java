import java.util.InputMismatchException;
import java.util.Scanner;

public class HousingSelection {
    public static int calculateDisabilityStatus() {
        Scanner scanner = new Scanner(System.in);
        String answer;

        while (true) {
            System.out.println("Are you disabled (Y/N)?");
            answer = scanner.nextLine();
            switch(answer.toLowerCase()) {
                case "y":
                    return 2;
                case "n":
                    return 0;
                default:
                    System.out.println("Please enter either a Y for Yes or an N for no");
                    break;
            }
        }
    }

    public static int calculateDistanceFromHome() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean validAnswer = false;

        while (!validAnswer) {
            try {
                System.out.println("How far is your current residence from the school? ");
                System.out.println("(1) <= 50 miles");
                System.out.println("(2) <= 100 miles");
                System.out.println("(3) <= 150 miles");
                System.out.println("(4) > 150 miles");
                System.out.println("(5) I’m from out of state");
                System.out.println("(6) I’m an international student");
                System.out.print("Your choice: ");

                choice = scanner.nextInt();

                if (choice > 0 && choice < 7) {
                    validAnswer = true;
                } else {
                    System.out.println("Please enter a valid choice");
                }

            } catch (InputMismatchException e) {
                System.out.println("Your input was an invalid number");
                scanner.nextLine();
            }
        }

        switch (choice) {
            case 1:
                return 1;
            case 2, 6:
                return 2;
            case 3, 5:
                return 3;
            case 4:
                return 4;
            default:
                return 0;
        }
    }

    public static int calculateFamilyIncome() {
        Scanner scanner = new Scanner(System.in);
        int income = 0;
        boolean validAnswer = false;

        while (!validAnswer) {
            try {
                System.out.println("What is your current household income level? Enter your answer in dollars.");
                System.out.print("$");

                income = scanner.nextInt();

                if (income > 0) {
                    validAnswer = true;
                } else {
                    System.out.println("Please enter a number greater than 0");
                }

            } catch (InputMismatchException e) {
                System.out.println("Your input was an invalid number");
                scanner.nextLine();
            }
        }

         if (income <= 50000) {
             return 4;
         } else if (income <= 75000) {
             return 3;
         } else if (income <= 100000) {
             return 2;
         } else if (income <= 150000) {
             return 1;
         } else {
             return 0;
         }
    }

    public static int calculateAcademicStanding() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean validAnswer = false;

        while (!validAnswer) {
            try {
                System.out.println("What is your current academic standing?");
                System.out.println("(1) Dean's List");
                System.out.println("(2) Good Academic Standing");
                System.out.println("(3) On Academic Probation");
                System.out.println("(4) At risk for Academic Disqualification");
                System.out.print("Your choice: ");

                choice = scanner.nextInt();

                if (choice > 0 && choice < 5) {
                    validAnswer = true;
                } else {
                    System.out.println("Please enter a valid choice");
                }

            } catch (InputMismatchException e) {
                System.out.println("Your input was an invalid number");
                scanner.nextLine();
            }
        }

        switch (choice) {
            case 1:
                return 3;
            case 2:
                return 1;
            case 3:
                return -1;
            case 4:
                return -2;
            default:
                return 0;
        }
    }

    public static int calculateStudentType() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean validAnswer = false;

        while (!validAnswer) {
            try {
                System.out.println("Are you a full-time student or a part-time student? ");
                System.out.println("(1) Full-time student");
                System.out.println("(2) Part-time student");
                System.out.print("Your choice: ");

                choice = scanner.nextInt();

                if (choice > 0 && choice < 3) {
                    validAnswer = true;
                } else {
                    System.out.println("Please enter a valid choice");
                }

            } catch (InputMismatchException e) {
                System.out.println("Your input was an invalid number");
                scanner.nextLine();
            }
        }

        switch (choice) {
            case 1:
                return 2;
            case 2:
                return 1;
            default:
                return 0;
        }
    }

    public static int calculateStudentSeniority() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean validAnswer = false;

        while (!validAnswer) {
            try {
                System.out.println("What is your seniority level in college?");
                System.out.println("(1) I am a Freshman");
                System.out.println("(2) I am a Sophomore");
                System.out.println("(3) I am a Junior");
                System.out.println("(4) I am a Senior");
                System.out.println("(5) I am a Graduate/Master’s/PhD student");
                System.out.print("Your choice: ");

                choice = scanner.nextInt();

                if (choice > 0 && choice < 6) {
                    validAnswer = true;
                } else {
                    System.out.println("Please enter a valid choice");
                }

            } catch (InputMismatchException e) {
                System.out.println("Your input was an invalid number");
                scanner.nextLine();
            }
        }

        switch (choice) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int points = 0;
        String name;

        System.out.println("Hello! Welcome to your housing application program!");
        System.out.print("Enter your name: ");
        name = scanner.nextLine();
        System.out.println("Hello " + name + ". We're going to ask you a few questions.");

        points += calculateDisabilityStatus();
        System.out.println("points = " + points);
        points += calculateDistanceFromHome();
        System.out.println("points = " + points);
        points += calculateFamilyIncome();
        System.out.println("points = " + points);
        points += calculateAcademicStanding();
        System.out.println("points = " + points);
        points += calculateStudentType();
        System.out.println("points = " + points);
        points += calculateStudentSeniority();
        System.out.println("points = " + points);

        System.out.println("You have " + points + " point(s) for housing.");
    }
}
