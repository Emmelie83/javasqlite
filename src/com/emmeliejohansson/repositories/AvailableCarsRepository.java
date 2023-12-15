package com.emmeliejohansson.repositories;

import com.emmeliejohansson.data.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvailableCarsRepository {
    
    DatabaseManager databaseManager = new DatabaseManager();
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

        try (Connection conn = databaseManager.connect();
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
