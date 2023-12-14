import java.sql.*;
import java.util.Scanner;

public class SqlQueries {
     private Scanner scanner = new Scanner(System.in);

     private Connection connect() {
        String url = "jdbc:sqlite:C:\\Users\\melie\\SQL\\biluthyrning.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


     public void selectAllCustomers() {
        String sql = "SELECT * FROM customer";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

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
            System.out.println("Error selecting all customers: " + e.getMessage());
        }
    }

     public void selectAllBookings() {
        String sql = "SELECT * FROM booking";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("bookingNr") + "\t" +
                        rs.getString("bookingDate") + "\t" +
                        rs.getString("pickupDate") + "\t" +
                        rs.getString("returnDate") + "\t" +
                        rs.getInt("pricePerDay") + "\t" +
                        rs.getInt("customerNr") + "\t" +
                        rs.getString("carRegNr"));

            }
        } catch (SQLException e) {
            System.out.println("Error selecting all bookings: " + e.getMessage());
        }
    }

     public void insertCustomer(String firstName, String lastName, String streetAddress, int postalCode, String city, String country, String telephone, String email) {
        String sql = "INSERT INTO customer (firstName, lastName, streetAddress, postalCode, city, country, telephone, email) VALUES(?,?,?,?,?,?,?,?)";

        try {
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
            System.out.println("\nYou have successfully added a new customer!");
        } catch (SQLException e) {
            System.out.println("\nError adding a new customer: " + e.getMessage());
        }
    }

     public void insertBooking(String bookingDate, String pickupDate, String returnDate, int pricePerDay, int customerNr, String carRegNr) {
        String sql = "INSERT INTO booking(bookingDate, pickupDate, returnDate, pricePerDay, customerNr, carRegNr) VALUES(?,?,?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookingDate);
            pstmt.setString(2, pickupDate);
            pstmt.setString(3, returnDate);
            pstmt.setInt(4, pricePerDay);
            pstmt.setInt(5, customerNr);
            pstmt.setString(6, carRegNr);
            pstmt.executeUpdate();
            System.out.println("\nYou have successfully added a new booking!");
        } catch (SQLException e) {
            System.out.println("\nError adding a new booking: " + e.getMessage());
        }
    }

     public void updateFirstName(String firstName, int customerNr) {
        String sql = "UPDATE customer SET firstName = ?"
                + "WHERE customerNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, firstName);
            pstmt.setInt(2, customerNr);

            pstmt.executeUpdate();
            System.out.println("\nYou have updated the customer's first name");
        } catch (SQLException e) {
            System.out.println("\nError updating the customer: " + e.getMessage());
        }
    }

     public void updateLastName(String lastName, int customerNr) {
        String sql = "UPDATE customer SET lastName = ?"
                + "WHERE customerNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lastName);
            pstmt.setInt(2, customerNr);

            pstmt.executeUpdate();
            System.out.println("\nYou have updated the customer's last name");
        } catch (SQLException e) {
            System.out.println("\nError updating the customer: " + e.getMessage());
        }
    }

     public void updateAddress(String streetAddress, int postalCode, String city, String country, int customerNr) {
        String sql = "UPDATE customer SET streetAddress = ? , "
                + "postalCode = ? , "
                + "city = ? ,"
                + "country = ? "
                + "WHERE customerNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, streetAddress);
            pstmt.setInt(2, postalCode);
            pstmt.setString(3, city);
            pstmt.setString(4, country);

            pstmt.executeUpdate();
            System.out.println("\nYou have successfully updated the customer's address!");
        } catch (SQLException e) {
            System.out.println("\nError updating the customer: " + e.getMessage());
        }
    }

     public void updateTelephone(String telephone, int customerNr) {
        String sql = "UPDATE customer SET telephone = ?"
                + "WHERE customerNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, telephone);
            pstmt.setInt(2, customerNr);

            pstmt.executeUpdate();
            System.out.println("\nYou have successfully updated the customer's telephone number");
        } catch (SQLException e) {
            System.out.println("\nError updating the customer: " + e.getMessage());
        }
    }

     public void updateEmail(String email, int customerNr) {
        String sql = "UPDATE customer SET email = ?"
                + "WHERE customerNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setInt(2, customerNr);

            pstmt.executeUpdate();
            System.out.println("\nYou have successfully updated the customer's email address!");
        } catch (SQLException e) {
            System.out.println("\nError updating the customer: " + e.getMessage());
        }
    }

     public void updatePickupDate(String pickupDate, int bookingNr) {
        String sql = "UPDATE customer SET pickupDate = ?"
                + "WHERE bookingNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pickupDate);
            pstmt.setInt(2, bookingNr);

            pstmt.executeUpdate();
            System.out.println("\nYou have successfully updated the pickup date!");
        } catch (SQLException e) {
            System.out.println("\nError updating the booking: " + e.getMessage());
        }
    }

     public void updateReturnDate(String returnDate, int bookingNr) {
        String sql = "UPDATE customer SET returnDate = ?"
                + "WHERE bookingNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, returnDate);
            pstmt.setInt(2, bookingNr);

            pstmt.executeUpdate();
            System.out.println("\nYou have successfully updated the return date!");
        } catch (SQLException e) {
            System.out.println("\nError updating the booking: " + e.getMessage());
        }
    }

     public void updatePricePerDay(int pricePerDay, int bookingNr) {
        String sql = "UPDATE customer SET pricePerDay = ?"
                + "WHERE bookingNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pricePerDay);
            pstmt.setInt(2, bookingNr);
            pstmt.executeUpdate();
            System.out.println("\nYou have successfully updated the price per day!");
        } catch (SQLException e) {
            System.out.println("\nError updating the booking: " + e.getMessage());
        }
    }

     public void updateCarRegNr(String carRegNr, int bookingNr) {
        String sql = "UPDATE customer SET carRegNr = ?"
                + "WHERE bookingNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, carRegNr);
            pstmt.setInt(2, bookingNr);

            pstmt.executeUpdate();
            System.out.println("\nYou have successfully updated the chosen car!");
        } catch (SQLException e) {
            System.out.println("\nError updating the booking: " + e.getMessage());
        }
    }

     public void deleteCustomer(int customerNr) {
        String sql = "DELETE * FROM booking WHERE customerNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerNr);

            pstmt.executeUpdate();
            System.out.println("\nYou have successfully deleted the customer number " + customerNr + "!");
        } catch (SQLException e) {
            System.out.println("\nError deleting the customer: " + e.getMessage());
        }
    }

     public void deleteBooking(int bookingNr) {
        String sql = "DELETE * FROM booking WHERE bookingNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookingNr);
            pstmt.executeUpdate();
            System.out.println("\nYou have successfully deleted the booking number" + bookingNr);
        } catch (SQLException e) {
            System.out.println("\nError deleting the booking: " + e.getMessage());
        }
    }

     public void showCustomer(String telephone) {
        String sql = "SELECT * FROM customer WHERE telephone = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, telephone);

            ResultSet rs = pstmt.executeQuery();

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
            System.out.println("\nError retrieving customer information: " + e.getMessage());
        }
    }

     public void markCustomerAsVip(int customerNr) {
        String sql = "UPDATE customer SET vip = ?"
                + "WHERE customerNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Yes");
            pstmt.setInt(2, customerNr);
            pstmt.executeUpdate();
            System.out.println("\nYou have successfully set the customer as VIP!");
        } catch (SQLException e) {
            System.out.println("\nError setting the customer as VIP: " + e.getMessage());
        }
    }

     public void showAllVipCustomers() {
        String sql = "SELECT * FROM customer WHERE vip = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "Yes");
            ResultSet rs = pstmt.executeQuery();

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
            System.out.println("\nError showing the VIP customers: " + e.getMessage());
        }
    }

     public void countVipCustomers() {
        String sql = "SELECT COUNT(*) as vipCount FROM customer WHERE vip = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "Yes");

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int vipCount = rs.getInt("vipCount");
                System.out.println("Number of VIP customers: " + vipCount);
            }
        } catch (SQLException e) {
            System.out.println("\nError counting the VIP customers: " + e.getMessage());
        }
    }

     public void showAvailableCars(String categoryName, String startDate, String endDate) {

        String sql = "SELECT rc.regNr, rc.brand, rc.color, cc.categoryName, cc.pricePerDay " +
                "FROM rentalCar rc " +
                "JOIN carCategory cc ON rc.carCategoryNr = cc.categoryNr " +
                "WHERE cc.categoryName = ? AND rc.regNr NOT IN (" +
                "    SELECT DISTINCT b.carRegNr " +
                "    FROM booking b " +
                "    WHERE (" +
                "        (b.pickupDate BETWEEN ? AND ?) " +
                "        OR (b.returnDate BETWEEN ? AND ?) " +
                "    ) " +
                "    OR (" +
                "        (? BETWEEN b.pickupDate AND b.returnDate) " +
                "        OR (? BETWEEN b.pickupDate AND b.returnDate) " +
                "    )" +
                ")";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoryName);
            pstmt.setString(2, startDate);
            pstmt.setString(3, endDate);
            pstmt.setString(4, startDate);
            pstmt.setString(5, endDate);
            pstmt.setString(6, startDate);
            pstmt.setString(7, endDate);

            ResultSet rs = pstmt.executeQuery();
            ;

            while (rs.next()) {
                System.out.println(
                        rs.getString("regNr") + "\t" +
                                rs.getString("brand") + "\t" +
                                rs.getString("color") + "\t" +
                                rs.getString("categoryName") + "\t" +
                                rs.getInt("pricePerDay")
                );
            }
        } catch (SQLException e) {
            System.out.println("\nError retrieving available cars: " + e.getMessage());
        }
    }
}
