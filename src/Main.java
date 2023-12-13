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
                "4  - Add a booking\n" +
                "5  - Update a customer\n" +
                "6  - Update a booking\n" +
                "7  - Delete a booking\n" +
                "8  - Show invoice information\n" +
                "9  - Show a list of all options.");
    }

    private static int testInput() {
        int choice = -1;
        while (choice == -1) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Error: You have to input a number.");
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
        int postalCode = testInput();
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
        int customerNr = testInput();
        System.out.println("Booking date (YYYY-MM-DD):");
        String bookingDate = scanner.nextLine();
        System.out.println("Pickup date (YYYY-MM-DD):");
        String pickupDate = scanner.nextLine();
        System.out.println("Return date (YYYY-MM-DD):");
        String returnDate = scanner.nextLine();
        System.out.println("Price per day:");
        int pricePerDay = testInput();
        System.out.println("Car registration number:");
        String carRegNr = scanner.nextLine();
        insertBooking(bookingDate, pickupDate, returnDate, pricePerDay, customerNr, carRegNr);
    }

    private static void updateCustomer() {
        System.out.println("Input customer number for the customer you want to update:");
        int customerNr = testInput();
        printUpdateCustomerMenu();
        int selection = testInput();
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
                //updateTelephone();
                break;
            case 5:
                //updateEmail();
                break;
        }

    }

    private static void printUpdateCustomerMenu() {
        System.out.println("\nWhat do you want to update?:\n");
        System.out.println("0  - Quit\n" +
                "1  - First name\n" +
                "2  - Last name\n" +
                "3  - Address\n" +
                "4  - Telephone\n" +
                "5  - Email");
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
        System.out.println("Street address:");
        String streetAddress = scanner.nextLine();
        System.out.println("Postal code:");
        int postalCode = testInput();
        System.out.println("City:");
        String city = scanner.nextLine();
        System.out.println("Country:");
        String country = scanner.nextLine();
        updateAddress(streetAddress, postalCode, city, country, customerNr);
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




    private static void deleteBooking(){
        System.out.println("Bookning number for the booking you want to delete: ");
        int bookingNr = testInput();
        deleteBooking(bookingNr);
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
                        rs.getInt("vip"));

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
            System.out.println("You have added a new booking!");
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
            pstmt.setString(5, telephone);
            pstmt.setString(6, email);
            pstmt.executeUpdate();
            System.out.println("You have added a new customer!");
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


    public static void main(String[] args) {

        boolean quit = false;
        printMainMenu();
        while(!quit) {
            int selection = testInput();

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
                    //updateBooking();
                    break;
                case 7:
                    deleteBooking();
                    break;
                case 8:
                    //showInvoiceInformation();
                    break;
            }
        }

    }

}