import java.awt.print.Book;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);


    private static void printMainMenu() {
        System.out.println("\nOptions:\n");
        System.out.println("0  - Quit\n" +
                "1  - Show all customers\n" +
                "2  - Show all bookings\n" +
                "3  - Add a customer\n" +
                "4  - Make a booking\n" +
                "5  - Update customer information\n" +
                "6  - Update booking information\n" +
                "7  - Delete a customer\n" +
                "8  - Delete a booking\n" +
                "9 -  Show available cars of a certain category\n" +
                "10 - Show a customer\n" +
                "11 - Set a customer as VIP\n" +
                "12 - Show all VIP customers\n" +
                "13 - Count number of VIP customers\n" +
                "14 - Print main menu\n");
    }

    private static int testNumInput() {
        int choice = -1;
        while (choice == -1) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Wrong input: You have to input a number.");
            }
        }
        return choice;
    }

    public static void main(String[] args) {
        Booking booking = new Booking();
        Customer customer = new Customer();
        SqlQueries sqlQueries = new SqlQueries();
        boolean quit = false;
        printMainMenu();
        while(!quit) {
            System.out.println("\nWhat do you want to do?\n" +
                    "(Input 14 to see the menu options again.)\n" +
                    "Enter your choice:");
            int selection = testNumInput();
            switch (selection) {
                case 0 -> {
                    System.out.println("\nQuitting...");
                    quit = true;
                }
                case 1 -> sqlQueries.selectAllCustomers();
                case 2 -> sqlQueries.selectAllBookings();
                case 3 -> customer.insertCustomer();
                case 4 -> booking.insertBooking();
                case 5 -> customer.updateCustomer();
                case 6 -> booking.updateBooking();
                case 7 -> customer.deleteCustomer();
                case 8 -> booking.deleteBooking();
                case 9 -> booking.showAvailableCars();
                case 10 -> customer.showCustomer();
                case 11 -> customer.markCustomerAsVip();
                case 12 -> sqlQueries.showAllVipCustomers();
                case 13 -> sqlQueries.countVipCustomers();
                case 14 -> printMainMenu();
                default -> System.out.println("You have to enter a number between 1 and 14, or 0 to quit.");
            }
        }
    }
}