package com.emmeliejohansson.repositories;

import com.emmeliejohansson.data.DatabaseManager;
import com.emmeliejohansson.data.entities.AvailableCar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AvailableCarsRepository {
    
    DatabaseManager databaseManager = new DatabaseManager();
    public ArrayList<AvailableCar> getAvailableCars(String categoryName, String startDate, String endDate) {
        ArrayList<AvailableCar> availableCars = new ArrayList<>();
        String sql = "SELECT rc.regNr, rc.brand, rc.color, cc.categoryName, cc.pricePerDay " +
                "FROM rentalCar rc " +
                "JOIN carCategory cc ON rc.carCategoryId = cc.categoryId " +
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
            while (rs.next()) {
                AvailableCar availableCar = mapResultSetToAvailableCar(rs);
                if (availableCar != null) availableCars.add(availableCar);
            }
        } catch (SQLException e) {
            System.out.println("Error selecting all available cars: " + e.getMessage());
        }
        return availableCars;
    }

    private AvailableCar mapResultSetToAvailableCar(ResultSet rs) throws SQLException {
        String regNr = rs.getString("regNr");
        String brand = rs.getString("brand");
        String color = rs.getString("color");
        String categoryName = rs.getString("categoryName");
        int pricePerDay = rs.getInt("pricePerDay");
        return new AvailableCar(regNr, brand, color, categoryName, pricePerDay);
    }
}
