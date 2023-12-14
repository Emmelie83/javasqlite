
import java.util.Scanner;

public class Customer {
     private Scanner scanner = new Scanner(System.in);

     private SqlQueries sqlQueries = new SqlQueries();

     public int testNumInput() {
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

     public void insertCustomer() {
        System.out.println("First Name:");
        String firstName = scanner.nextLine();
        System.out.println("Last Name:");
        String lastName = scanner.nextLine();
        System.out.println("Street address:");
        String streetAddress = scanner.nextLine();
        System.out.println("Postal code:");
        int postalCode = testNumInput();
        System.out.println("City:");
        String city = scanner.nextLine();
        System.out.println("Country:");
        String country = scanner.nextLine();
        System.out.println("Telephone:");
        String telephone = scanner.nextLine();
        System.out.println("Email:");
        String email = scanner.nextLine();
        sqlQueries.insertCustomer(firstName, lastName, streetAddress, postalCode, city, country, telephone, email);
    }


     public void updateCustomer() {
        System.out.println("Input customer number for the customer you want to update:");
        int customerNr = testNumInput();
        printUpdateCustomerMenu();
        int selection = testNumInput();
        switch (selection) {
            case 1:
                updateFirstName(customerNr);
                break;
            case 2:
                updateLastName(customerNr);
                break;
            case 3:
                updateAddress(customerNr);
                break;
            case 4:
                updateTelephone(customerNr);
                break;
            case 5:
                updateEmail(customerNr);
                break;
        }
    }

     private void printUpdateCustomerMenu() {
        System.out.println("\nWhat do you want to update?:\n");
        System.out.println("0  - Back\n" +
                "1  - First name\n" +
                "2  - Last name\n" +
                "3  - Address\n" +
                "4  - Telephone\n" +
                "5  - Email");
    }

     public void updateFirstName(int customerNr) {
        System.out.println("New first name:");
        String firstName = scanner.nextLine();
        sqlQueries.updateFirstName(firstName, customerNr);
    }

     public void updateLastName(int customerNr) {
        System.out.println("New last name:");
        String lastName = scanner.nextLine();
        sqlQueries.updateLastName(lastName, customerNr);
    }

     public void updateAddress(int customerNr) {
        System.out.println("New street address:");
        String streetAddress = scanner.nextLine();
        System.out.println("New postal code:");
        int postalCode = testNumInput();
        System.out.println("New city:");
        String city = scanner.nextLine();
        System.out.println("New country:");
        String country = scanner.nextLine();
        sqlQueries.updateAddress(streetAddress, postalCode, city, country, customerNr);
    }

     private void updateTelephone(int customerNr) {
        System.out.println("New telephone Number:");
        String telephone = scanner.nextLine();
        sqlQueries.updateTelephone(telephone, customerNr);
    }

     private void updateEmail(int customerNr) {
        System.out.println("New email address:");
        String email = scanner.nextLine();
        sqlQueries.updateEmail(email, customerNr);
    }

     public void deleteCustomer(){
        System.out.println("Insert customer number for the customer you want to delete: ");
        int customerNr = testNumInput();
        sqlQueries.deleteCustomer(customerNr);
    }

     public void showCustomer(){
        System.out.println("Insert phone number for the customer you want to see: ");
        String phone = scanner.nextLine();
        sqlQueries.showCustomer(phone);
    }

    public void markCustomerAsVip() {
        System.out.println("Insert customer number: ");
        int customerNr = testNumInput();
       sqlQueries.markCustomerAsVip(customerNr);
    }
}
