import java.util.InputMismatchException;
import java.util.Scanner;

public class RestaurantCheckManager {
    static  final double KITCHEN_STAFF_TIP_PERCENTAGE = 0.3;
    static  final double CHEF_TIP_PERCENTAGE = 0.5;
    static  final double SOUS_CHEF_TIP_PERCENTAGE = 0.3;
    static  final double KITCHEN_AID_TIP_PERCENTAGE = 0.2;
    static final double HOSTESS_TIP_PERCENTAGE = 0.1;
    static final double BUSSER_TIP_PERCENTAGE = 0.1;
    static final double SERVER_TIP_PERCENTAGE = 0.5;
    static double totalSales = 0.0;
    static double totalTips = 0.0;
    static int numberOfChecks = 0;
    public static void distributeTips() {

        System.out.println("Calculating tip distribution...");

        // Calculate tips for kitchen staff
        double totalKitchenStaffTips = totalTips * KITCHEN_STAFF_TIP_PERCENTAGE;
        System.out.printf("Tips towards kitchen staff: $%.2f\n", totalKitchenStaffTips);
        System.out.printf("-> Chef tips: $%.2f\n", totalKitchenStaffTips * CHEF_TIP_PERCENTAGE);
        System.out.printf("-> Sous Chef tips: $%.2f\n", totalKitchenStaffTips * SOUS_CHEF_TIP_PERCENTAGE);
        System.out.printf("-> Kitchen Aid tips: $%.2f\n", totalKitchenStaffTips * KITCHEN_AID_TIP_PERCENTAGE);

        // Calculate tips for hostess and busser
        double totalHostessTips = totalTips * HOSTESS_TIP_PERCENTAGE;
        double totalBusserTips = totalTips * BUSSER_TIP_PERCENTAGE;
        System.out.printf("Hostesses tips: $%.2f\n", totalHostessTips);
        System.out.printf("Bussers tips: $%.2f\n", totalBusserTips);

        // Calculate tips for servers
        Scanner sc = new Scanner(System.in);
        int numberOfServers = 0;
        double totalHours = 0;
        double[] serverHours = new double[100];

        // Get the number of servers that work in the restaurant
        while (true) {
            try {
                System.out.print("How many servers work in your restaurant (limit = 100)? ");
                numberOfServers = sc.nextInt();

                if (numberOfServers < 0 || numberOfServers > 100) {
                    System.out.println("Please enter a number between 0 and 100");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
            }
        }

        // Get the number of hours each server worked this week
        for (int i = 0; i < numberOfServers; i++) {

            while (true) {
                try {
                    double hours;
                    System.out.printf("How many hours did server %d work this week? ", i + 1);
                    hours = sc.nextDouble();

                    if (hours < 0) {
                        System.out.println("Please enter a number above 0");
                    } else {
                        serverHours[i] = hours;
                        totalHours += hours;
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number");
                }
            }


        }

        // Calculate the tip amount for each server
        System.out.printf("Servers total tips: $%.2f\n", totalTips * SERVER_TIP_PERCENTAGE);
        for (int i = 0; i < numberOfServers; i++) {
            double individualServerTipPercentage = serverHours[i]/totalHours;
            System.out.printf("-> Server %d's tips: $%.2f\n", i + 1, individualServerTipPercentage * (totalTips * SERVER_TIP_PERCENTAGE));
        }
    }
    public static void processChecks() {
        System.out.println("Processing checks...");

        Scanner sc = new Scanner(System.in);
        boolean checksDone = false;
        while (!checksDone) {
            // Print the check number
            System.out.printf("%s Check #%d %s\n", "-".repeat(10), numberOfChecks + 1, "-".repeat(10));

            // Input tip, total, and check
            try {
                String choice;
                Check currentCheck = new Check();

                System.out.print("Total sale amount: $");
                currentCheck.setSaleAmount(sc.nextDouble());

                System.out.print("Tip amount: $");
                currentCheck.setTipAmount(sc.nextDouble());

                System.out.print("Total Amount: $");
                currentCheck.setTotalAmount(sc.nextDouble());

                // Fix any errors
                currentCheck.correctCheckErrors();

                // Add to totals
                totalSales += currentCheck.getSaleAmount();
                totalTips += currentCheck.getTipAmount();
                numberOfChecks++;

                // Summary
                System.out.printf("[Total sales so far: $%.2f] [Total tips: $%.2f] [Check count: %d]\n", totalSales, totalTips, numberOfChecks);
                sc.nextLine(); // Flush out the input buffer

                // Check if the user wants to continue entering checks
                System.out.print("Do you want to stop? (y/n): ");
                choice = sc.nextLine();
                if (choice.equals("Y") || choice.equals("y")) {
                    checksDone = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                sc.nextLine();
            }

            // Summary after all checks have been processed
            System.out.printf("%s Summary %s\n", "-".repeat(10), "-".repeat(10));
            System.out.printf("Total sales: $%.2f\nTotal tips: $%.2f\nNumber of checks processed: %d\n", totalSales, totalTips, numberOfChecks);

        }

    }
    public static void main(String[] args) {
        System.out.println("Welcome to the Restaurant Check Manager!");
        processChecks();
        distributeTips();
    }
}
