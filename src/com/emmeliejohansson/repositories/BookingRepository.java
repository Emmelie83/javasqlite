package com.emmeliejohansson.repositories;

import com.emmeliejohansson.data.DatabaseManager;
import com.emmeliejohansson.data.entities.Booking;
import com.emmeliejohansson.data.entities.Customer;

import java.sql.*;

public class BookingRepository {
    
    DatabaseManager databaseManager = new DatabaseManager();

    public Booking getBookingById(int bId) {
        String sql = "SELECT * FROM booking WHERE bookingId = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int bookingId = rs.getInt("bookingId");
                String bookingDate = rs.getString("bookingDate");
                String pickupDate = rs.getString("pickupDate");
                String returnDate = rs.getString("returnDate");
                int pricePerDay = rs.getInt("pricePerDay");
                int customerId = rs.getInt("customerId");
                String carRegNr = rs.getString("carRegNr");
                return new Booking(bookingDate, pickupDate, returnDate, pricePerDay, customerId, carRegNr);
            }
        } catch (SQLException e) {
            System.out.println("\nError retrieving booking by ID: " + e.getMessage());
            return null;
        }
        return null;
    }

    public void selectAllBookings() {
        String sql = "SELECT * FROM booking";

        try {
            Connection conn = databaseManager.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("bookingId") + "\t" +
                        rs.getString("bookingDate") + "\t" +
                        rs.getString("pickupDate") + "\t" +
                        rs.getString("returnDate") + "\t" +
                        rs.getInt("pricePerDay") + "\t" +
                        rs.getInt("customerId") + "\t" +
                        rs.getString("carRegNr"));

            }
        } catch (SQLException e) {
            System.out.println("Error selecting all bookings: " + e.getMessage());
        }
    }

    public void insertBooking(Booking b) {
        String sql = "INSERT INTO booking(bookingDate, pickupDate, returnDate, pricePerDay, customerId, carRegNr) VALUES(?,?,?,?,?,?)";

        try {
            Connection conn = databaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, b.getBookingDate());
            pstmt.setString(2, b.getPickupDate());
            pstmt.setString(3, b.getReturnDate());
            pstmt.setInt(4, b.getPricePerDay());
            pstmt.setInt(5, b.getCustomerId());
            pstmt.setString(6, b.getCarRegNr());
            pstmt.executeUpdate();
            System.out.println("\nYou have successfully added a new booking!");
        } catch (SQLException e) {
            System.out.println("\nError adding a new booking: " + e.getMessage());
        }
    }

    public void updateBooking(Booking b) {
        if (b == null) return;
        String sql = "UPDATE booking SET (bookingDate, pickupDate, returnDate, pricePerDay, customerId, carRegNr)  = (?,?,?,?,?,?)"
                + "WHERE customerId = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, b.getBookingDate());
            pstmt.setString(2, b.getPickupDate());
            pstmt.setString(3, b.getReturnDate());
            pstmt.setInt(4, b.getPricePerDay());
            pstmt.setInt(5, b.getCustomerId());
            pstmt.setString(6, b.getCarRegNr());

            pstmt.executeUpdate();
            System.out.println("\nYou have successfully updated the booking with ID " + b.getBookingId());
        } catch (SQLException e) {
            System.out.println("\nError updating the booking: " + e.getMessage());
        }
    }


    public void deleteBooking(int bookingId) {
        String sql = "DELETE * FROM booking WHERE bookingId = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookingId);
            pstmt.executeUpdate();
            System.out.println("\nYou have successfully deleted the booking number" + bookingId);
        } catch (SQLException e) {
            System.out.println("\nError deleting the booking: " + e.getMessage());
        }
    }

}
