import com.emmeliejohansson.repositories.BookingRepository;
import com.emmeliejohansson.repositories.CustomerRepository;
import com.emmeliejohansson.services.AvailableCarsService;
import com.emmeliejohansson.services.BookingService;
import com.emmeliejohansson.services.CustomerService;
import com.emmeliejohansson.services.UserInputHandler;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static UserInputHandler userInputHandler = new UserInputHandler();


    private static void printMainMenu() {
        System.out.println("\nOptions:\n");
        System.out.println("0  - Quit\n" +
                "1  - Show all customers\n" +
                "2  - Show all bookings\n" +
                "3  - Add a customer\n" +
                "4  - Make a booking\n" +
                "5  - Update customer information\n" +
                "6  - Update a booking\n" +
                "7  - Delete a customer\n" +
                "8  - Delete a booking\n" +
                "9 -  Show available cars\n" +
                "10 - Show a customer\n" +
                "11 - Set a customer as VIP\n" +
                "12 - Show all VIP customers\n" +
                "13 - Count number of VIP customers\n" +
                "14 - Print main menu\n");
    }


    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        BookingService bookingService = new BookingService();
        CustomerRepository customerRepository = new CustomerRepository();
        BookingRepository bookingRepository = new BookingRepository();
        AvailableCarsService availableCarsService = new AvailableCarsService();
        boolean quit = false;
        printMainMenu();
        while(!quit) {
            System.out.println("\nWhat do you want to do?\n" +
                    "(Input 14 to see the menu options again.)\n" +
                    "Enter your choice:");
            int selection = userInputHandler.readIntInput();
            switch (selection) {
                case 0 -> {
                    System.out.println("\nQuitting...");
                    quit = true;
                }
                case 1 -> customerRepository.selectAllCustomers();
                case 2 -> bookingRepository.selectAllBookings();
                case 3 -> customerService.createAndInsertCustomer();
                case 4 -> bookingService.createAndInsertBooking();
                case 5 -> customerService.updateCustomer();
                case 6 -> bookingService.updateBooking();
                case 7 -> customerService.deleteCustomer();
                case 8 -> bookingService.deleteBooking();
                case 9 -> availableCarsService.showAvailableCars();
                case 10 -> customerService.showCustomer();
                case 11 -> customerService.markCustomerAsVip();
                case 12 -> customerRepository.showAllVipCustomers();
                case 13 -> customerRepository.countVipCustomers();
                case 14 -> printMainMenu();
                default -> System.out.println("You have to enter a number between 1 and 14, or 0 to quit.");
            }
        }
    }
}