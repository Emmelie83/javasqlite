import java.util.Scanner;

public class Booking {

    private   Scanner scanner = new Scanner(System.in);

    private   SqlQueries sqlQueries = new SqlQueries();

    private   int testNumInput() {
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

    public void insertBooking() {
        System.out.println("Customer number:");
        int customerNr = testNumInput();
        System.out.println("Booking date (YYYY-MM-DD):");
        String bookingDate = scanner.nextLine();
        System.out.println("Pickup date (YYYY-MM-DD):");
        String pickupDate = scanner.nextLine();
        System.out.println("Return date (YYYY-MM-DD):");
        String returnDate = scanner.nextLine();
        System.out.println("Price per day:");
        int pricePerDay = testNumInput();
        System.out.println("Car registration number:");
        String carRegNr = scanner.nextLine();
        sqlQueries.insertBooking(bookingDate, pickupDate, returnDate, pricePerDay, customerNr, carRegNr);
    }


    public void updateBooking() {
        System.out.println("Input booking number for the booking you want to update:");
        int bookingNr = testNumInput();
        printUpdateBookingMenu();
        int selection = testNumInput();
        switch (selection) {
            case 1:
                updatePickupDate(bookingNr);
                break;
            case 2:
                updateReturnDate(bookingNr);
                break;
            case 3:
                updatePricePerDay(bookingNr);
                break;
            case 4:
                updateCarRegNr(bookingNr);
                break;
            case 0:
                return;
        }
    }


    private void printUpdateBookingMenu() {
        System.out.println("\nWhat do you want to update?:\n");
        System.out.println("0  - Back\n" +
                "1  - Pickup date\n" +
                "2  - Return date\n" +
                "3  - Price per day\n" +
                "4  - Car registration number\n");
    }

    private void updatePickupDate(int bookingNr) {
        System.out.println("New pickup date:");
        String pickupDate = scanner.nextLine();
        sqlQueries.updatePickupDate(pickupDate, bookingNr);
    }

    private void updateReturnDate(int bookingNr) {
        System.out.println("New return date:");
        String returnDate = scanner.nextLine();
        sqlQueries.updateReturnDate(returnDate, bookingNr);
    }

    private void updatePricePerDay(int bookingNr) {
        System.out.println("New price per day:");
        int pricePerDay = testNumInput();
        sqlQueries.updatePricePerDay(pricePerDay, bookingNr);
    }

    private void updateCarRegNr(int bookingNr) {
        System.out.println("New car registration number:");
        String carRegNr = scanner.nextLine();
        sqlQueries.updateCarRegNr(carRegNr, bookingNr);
    }

    public void deleteBooking(){
        System.out.println("Insert booking number for the booking you want to delete: ");
        int bookingNr = testNumInput();
        sqlQueries.deleteBooking(bookingNr);
    }

    public void showAvailableCars() {
        System.out.println("Insert category name: ");
        String categoryName = scanner.nextLine();
        System.out.println("Insert start date: ");
        String startDate = scanner.nextLine();
        System.out.println("Insert end date: ");
        String endDate = scanner.nextLine();
        sqlQueries.showAvailableCars(categoryName, startDate, endDate);
    }
}
