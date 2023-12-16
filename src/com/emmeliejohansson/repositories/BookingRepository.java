package com.emmeliejohansson.repositories;

import com.emmeliejohansson.data.DatabaseManager;
import com.emmeliejohansson.data.entities.Booking;
import com.emmeliejohansson.data.entities.Customer;

import java.sql.*;
import java.util.ArrayList;

public class BookingRepository {
    
    DatabaseManager databaseManager = new DatabaseManager();

    //region get

    public ArrayList<Booking> getAllBookings() {
        String sql = "SELECT * FROM booking";
        ArrayList<Booking> bookings = new ArrayList<>();
        try {
            Connection conn = databaseManager.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Booking booking = mapResultSetToBooking(rs);
                if (booking != null) bookings.add(booking);
            }
        } catch (SQLException e) {
            System.out.println("Error selecting all bookings: " + e.getMessage());
        }
        return bookings;
    }

    public Booking getBookingById(int bId) {
        String sql = "SELECT * FROM booking WHERE bookingId = ?";
        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bId);

            ResultSet rs = pstmt.executeQuery();

                return mapResultSetToBooking(rs);
        } catch (SQLException e) {
            System.out.println("Error retrieving booking by ID: " + e.getMessage());
            return null;
        }
    }

    //endregion

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
            System.out.println("You have successfully created a new booking!");
        } catch (SQLException e) {
            System.out.println("Error adding a new booking: " + e.getMessage());
        }
    }

    public void updateBooking(Booking b) {
        if (b == null) return;
        String sql = "UPDATE booking SET (bookingDate, pickupDate, returnDate, pricePerDay, customerId, carRegNr)  = (?,?,?,?,?,?)"
                + "WHERE bookingId = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, b.getBookingDate());
            pstmt.setString(2, b.getPickupDate());
            pstmt.setString(3, b.getReturnDate());
            pstmt.setInt(4, b.getPricePerDay());
            pstmt.setInt(5, b.getCustomerId());
            pstmt.setString(6, b.getCarRegNr());
            pstmt.setInt(7, b.getBookingId());

            pstmt.executeUpdate();
            System.out.println("You have successfully updated booking " + b.getBookingId() + "!");
        } catch (SQLException e) {
            System.out.println("Error updating the booking: " + e.getMessage());
        }
    }

    public void deleteBooking(int bookingId) {
        String sql = "DELETE FROM booking WHERE bookingId = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookingId);
            pstmt.executeUpdate();
            System.out.println("You have successfully deleted booking " + bookingId + "!");
        } catch (SQLException e) {
            System.out.println("Error deleting the booking: " + e.getMessage());
        }
    }

    private Booking mapResultSetToBooking(ResultSet rs) throws SQLException {
        int bookingId = rs.getInt("bookingId");
        String bookingDate = rs.getString("bookingDate");
        String pickupDate = rs.getString("pickupDate");
        String returnDate = rs.getString("returnDate");
        int pricePerDay = rs.getInt("pricePerDay");
        int customerId = rs.getInt("customerId");
        String carRegNr = rs.getString("carRegNr");
        return new Booking(bookingId, bookingDate, pickupDate, returnDate, pricePerDay, customerId, carRegNr);
    }
}
