import java.sql.*;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:\\Users\\melie\\SQL\\biluthyrning.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static void printMainMenu() {
        System.out.println("\nOptions:\n");
        System.out.println("0  - Quit\n" +
                "1  - Show all customers\n" +
                "2  - Show all bookings\n" +
                "3  - Add a customer\n" +
                "4  - Create a booking\n" +
                "5  - Update customer information\n" +
                "6  - Update booking information\n" +
                "7  - Delete a customer\n" +
                "8  - Delete a booking\n" +
                "9 -  Show invoice information\n" +
                "10 - Show a customer\n" +
                "11 - Mark a customer as VIP\n" +
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


    private static void insertCustomer() {
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
        insertCustomer(firstName, lastName, streetAddress, postalCode, city, country, telephone, email);
    }


    private static void insertBooking() {
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
        insertBooking(bookingDate, pickupDate, returnDate, pricePerDay, customerNr, carRegNr);
    }

    private static void updateCustomer() {
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

    private static void printUpdateCustomerMenu() {
        System.out.println("\nWhat do you want to update?:\n");
        System.out.println("0  - Back\n" +
                "1  - First name\n" +
                "2  - Last name\n" +
                "3  - Address\n" +
                "4  - Telephone\n" +
                "5  - Email");
    }

    private static void updateBooking() {
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


    private static void printUpdateBookingMenu() {
        System.out.println("\nWhat do you want to update?:\n");
        System.out.println("0  - Back\n" +
                "1  - Pickup date\n" +
                "2  - Return date\n" +
                "3  - Price per day\n" +
                "4  - Car registration number\n");
    }

    private static void updatePickupDate(int bookingNr) {
        System.out.println("New pickup date:");
        String pickupDate = scanner.nextLine();
        updatePickupDate(pickupDate, bookingNr);
    }

    private static void updateReturnDate(int bookingNr) {
        System.out.println("New return date:");
        String returnDate = scanner.nextLine();
        updateReturnDate(returnDate, bookingNr);
    }

    private static void updatePricePerDay(int bookingNr) {
        System.out.println("New price per day:");
        int pricePerDay = testNumInput();
        updatePricePerDay(pricePerDay, bookingNr);
    }

    private static void updateCarRegNr(int bookingNr) {
        System.out.println("New car registration number:");
        String carRegNr = scanner.nextLine();
        updateCarRegNr(carRegNr, bookingNr);
    }



    private static void updateFirstName(int customerNr) {
        System.out.println("New first name:");
        String firstName = scanner.nextLine();
        updateFirstName(firstName, customerNr);
    }


    private static void updateLastName(int customerNr) {
        System.out.println("New last name:");
        String lastName = scanner.nextLine();
        updateLastName(lastName, customerNr);
    }

    private static void updateAddress(int customerNr) {
        System.out.println("New street address:");
        String streetAddress = scanner.nextLine();
        System.out.println("New postal code:");
        int postalCode = testNumInput();
        System.out.println("New city:");
        String city = scanner.nextLine();
        System.out.println("New country:");
        String country = scanner.nextLine();
        updateAddress(streetAddress, postalCode, city, country, customerNr);
    }

    private static void updateTelephone(int customerNr) {
        System.out.println("New telephone Number:");
        String telephone = scanner.nextLine();
        updateTelephone(telephone, customerNr);
    }

    private static void updateEmail(int customerNr) {
        System.out.println("New email address:");
        String email = scanner.nextLine();
        updateEmail(email, customerNr);
    }

    private static void deleteCustomer(){
        System.out.println("Insert customer number for the customer you want to delete: ");
        int customerNr = testNumInput();
        deleteCustomer(customerNr);
    }

    private static void deleteBooking(){
        System.out.println("Insert booking number for the booking you want to delete: ");
        int bookingNr = testNumInput();
        deleteBooking(bookingNr);
    }

    private static void showCustomer(){
        System.out.println("Insert phone number for the customer you want to see: ");
        String phone = scanner.nextLine();
        showCustomer(phone);
    }

    private static void markCustomerAsVip() {
        System.out.println("Insert customer number: ");
        int customerNr = testNumInput();
        markCustomerAsVip(customerNr);
    }


    private static void selectAllCustomers(){
        String sql = "SELECT * FROM customer";

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("customerNr") +  "\t" +
                        rs.getString("firstName") + "\t" +
                        rs.getString("lastName") + "\t" +
                        rs.getString("streetAddress") + "\t" +
                        rs.getString("postalCode") + "\t" +
                        rs.getString("city") + "\t" +
                        rs.getString("country") + "\t" +
                        rs.getString("telephone") + "\t" +
                        rs.getString("email") + "\t" +
                        rs.getString("vip"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void selectAllBookings(){
        String sql = "SELECT * FROM booking";

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("bookingNr") +  "\t" +
                        rs.getString("bookingDate") + "\t" +
                        rs.getString("pickupDate") + "\t" +
                        rs.getString("returnDate") + "\t" +
                        rs.getInt("pricePerDay") + "\t" +
                        rs.getInt("customerNr") + "\t" +
                        rs.getString("carRegNr"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertCustomer(String firstName, String lastName, String streetAddress, int postalCode, String city, String country, String telephone, String email) {
        String sql = "INSERT INTO customer (firstName, lastName, streetAddress, postalCode, city, country, telephone, email) VALUES(?,?,?,?,?,?,?,?)";

        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, streetAddress);
            pstmt.setInt(4, postalCode);
            pstmt.setString(5, city);
            pstmt.setString(6, country);
            pstmt.setString(7, telephone);
            pstmt.setString(8, email);
            pstmt.executeUpdate();
            System.out.println("You have successfully added a new customer!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    private static void insertBooking(String bookingDate, String pickupDate, String returnDate, int pricePerDay, int customerNr, String carRegNr) {
        String sql = "INSERT INTO booking(bookingDate, pickupDate, returnDate, pricePerDay, customerNr, carRegNr) VALUES(?,?,?,?,?,?)";

        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookingDate);
            pstmt.setString(2, pickupDate);
            pstmt.setString(3, returnDate);
            pstmt.setInt(4, pricePerDay);
            pstmt.setInt(5, customerNr);
            pstmt.setString(6, carRegNr);
            pstmt.executeUpdate();
            System.out.println("You have successfully added a new booking!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    private static void updateFirstName(String firstName, int customerNr) {
        String sql = "UPDATE customer SET firstName = ?"
                + "WHERE customerNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, firstName);
            pstmt.setInt(2, customerNr);
            // update
            pstmt.executeUpdate();
            System.out.println("You have updated the customer's first name");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateLastName(String lastName, int customerNr) {
        String sql = "UPDATE customer SET lastName = ?"
                + "WHERE customerNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, lastName);
            pstmt.setInt(2, customerNr);
            // update
            pstmt.executeUpdate();
            System.out.println("You have updated the customer's last name");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateAddress(String streetAddress, int postalCode, String city, String country, int customerNr) {
        String sql = "UPDATE customer SET streetAddress = ? , "
                + "postalCode = ? , "
                + "city = ? ,"
                + "country = ? "
                + "WHERE customerNr = ?";


        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, streetAddress);
            pstmt.setInt(2, postalCode);
            pstmt.setString(3, city);
            pstmt.setString(4, country);
            // update
            pstmt.executeUpdate();
            System.out.println("You have successfully updated the customer's address!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateTelephone(String telephone, int customerNr) {
        String sql = "UPDATE customer SET telephone = ?"
                + "WHERE customerNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, telephone);
            pstmt.setInt(2, customerNr);
            // update
            pstmt.executeUpdate();
            System.out.println("You have successfully updated the customer's telephone number");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateEmail(String email, int customerNr) {
        String sql = "UPDATE customer SET email = ?"
                + "WHERE customerNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, email);
            pstmt.setInt(2, customerNr);
            // update
            pstmt.executeUpdate();
            System.out.println("You have successfully updated the customer's email address!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updatePickupDate(String pickupDate, int bookingNr) {
        String sql = "UPDATE customer SET pickupDate = ?"
                + "WHERE bookingNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, pickupDate);
            pstmt.setInt(2, bookingNr);
            // update
            pstmt.executeUpdate();
            System.out.println("You have successfully updated the pickup date!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateReturnDate(String returnDate, int bookingNr) {
        String sql = "UPDATE customer SET returnDate = ?"
                + "WHERE bookingNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, returnDate);
            pstmt.setInt(2, bookingNr);
            // update
            pstmt.executeUpdate();
            System.out.println("You have successfully updated the return date!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updatePricePerDay(int pricePerDay, int bookingNr) {
        String sql = "UPDATE customer SET pricePerDay = ?"
                + "WHERE bookingNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, pricePerDay);
            pstmt.setInt(2, bookingNr);
            // update
            pstmt.executeUpdate();
            System.out.println("You have successfully updated the price per day!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateCarRegNr(String carRegNr, int bookingNr) {
        String sql = "UPDATE customer SET carRegNr = ?"
                + "WHERE bookingNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, carRegNr);
            pstmt.setInt(2, bookingNr);

            pstmt.executeUpdate();
            System.out.println("You have successfully updated the chosen car!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void update(String forfattare, String titel, int pris, int id) {
        String sql = "UPDATE bok SET bokForfattare = ? , "
                + "bokTitel = ? , "
                + "bokPris = ? "
                + "WHERE bokId = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, forfattare);
            pstmt.setString(2, titel);
            pstmt.setInt(3, pris);
            pstmt.setInt(4, id);
            // update
            pstmt.executeUpdate();
            System.out.println("Du har uppdaterat vald bok");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteCustomer(int customerNr) {
        String sql = "DELETE * FROM booking WHERE customerNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, customerNr);
            // execute the delete statement
            pstmt.executeUpdate();
            System.out.println("You have successfully deleted the customer number " + customerNr + "!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteBooking(int bookingNr) {
        String sql = "DELETE * FROM booking WHERE bookingNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, bookingNr);
            // execute the delete statement
            pstmt.executeUpdate();
            System.out.println("You have successfully deleted the booking number" + bookingNr);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showCustomer(String telephone) {
        String sql = "SELECT * FROM customer WHERE telephone = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, telephone);

            // execute the query
            ResultSet rs = pstmt.executeQuery();

            // retrieve data from the result set
            while (rs.next()) {
                System.out.println(rs.getInt("customerNr") + "\t" +
                        rs.getString("firstName") + "\t" +
                        rs.getString("lastName") + "\t" +
                        rs.getString("streetAddress") + "\t" +
                        rs.getString("postalCode") + "\t" +
                        rs.getString("city") + "\t" +
                        rs.getString("country") + "\t" +
                        rs.getString("telephone") + "\t" +
                        rs.getString("email") + "\t" +
                        rs.getString("vip"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void markCustomerAsVip(int customerNr) {
        String sql = "UPDATE customer SET vip = ?"
                + "WHERE customerNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, "Yes");
            pstmt.setInt(2, customerNr);
            // update
            pstmt.executeUpdate();
            System.out.println("You have successfully set the customer as VIP!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showAllVipCustomers(){
        String sql = "SELECT * FROM customer WHERE vip = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, "Yes");

            // execute the query
            ResultSet rs = pstmt.executeQuery();

            // retrieve data from the result set
            while (rs.next()) {
                System.out.println(rs.getInt("customerNr") + "\t" +
                        rs.getString("firstName") + "\t" +
                        rs.getString("lastName") + "\t" +
                        rs.getString("streetAddress") + "\t" +
                        rs.getString("postalCode") + "\t" +
                        rs.getString("city") + "\t" +
                        rs.getString("country") + "\t" +
                        rs.getString("telephone") + "\t" +
                        rs.getString("email") + "\t" +
                        rs.getString("vip"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void countVipCustomers() {
        String sql = "SELECT COUNT(*) as vipCount FROM customer WHERE vip = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, "Yes");

            // execute the query
            ResultSet rs = pstmt.executeQuery();

            // retrieve data from the result set
            if (rs.next()) {
                int vipCount = rs.getInt("vipCount");
                System.out.println("Number of VIP customers: " + vipCount);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {

        boolean quit = false;
        printMainMenu();
        while(!quit) {
            System.out.println("\nWhat do you want to do?\n" +
                    "(Press 9 to see the menu options again.)\n" +
                    "Enter your choice:");
            int selection = testNumInput();
            switch (selection) {
                case 0:
                    System.out.println("\nQuitting...");
                    quit = true;
                    break;
                case 1:
                    selectAllCustomers();
                    break;
                case 2:
                    selectAllBookings();
                    break;
                case 3:
                    insertCustomer();
                    break;
                case 4:
                    insertBooking();
                    break;
                case 5:
                    updateCustomer();
                    break;
                case 6:
                    updateBooking();
                    break;
                case 7:
                    deleteCustomer();
                    break;
                case 8:
                    deleteBooking();
                    break;
                case 9:
                    //showInvoiceInformation();
                    break;
                case 10:
                    showCustomer();
                    break;
                case 11:
                    markCustomerAsVip();
                    break;
                case 12:
                    showAllVipCustomers();
                    break;
                case 13:
                    countVipCustomers();
                    break;
                case 14:
                    printMainMenu();
                    break;
            }
        }
    }

}