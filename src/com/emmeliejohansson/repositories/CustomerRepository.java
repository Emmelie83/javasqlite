package com.emmeliejohansson.repositories;

import com.emmeliejohansson.data.DatabaseManager;
import com.emmeliejohansson.data.entities.Customer;

import java.sql.*;

public class CustomerRepository {
    
    DatabaseManager databaseManager = new DatabaseManager();

    public Customer getCustomerById(int cId) {
        String sql = "SELECT * FROM customer WHERE customerId = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                        int customerId = rs.getInt("customerId");
                        String firstName = rs.getString("firstName");
                        String lastName = rs.getString("lastName");
                        String streetAddress = rs.getString("streetAddress");
                        String postalCode = rs.getString("postalCode");
                        String city = rs.getString("city");
                        String country = rs.getString("country");
                        String telephone = rs.getString("telephone");
                        String email = rs.getString("email");
                        String vip = rs.getString("vip");
                        return new Customer(customerId, firstName, lastName, streetAddress, postalCode, city, country, telephone, email, vip);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customer information: " + e.getMessage());
            return null;
        }
        return null;
    }
    
    public void selectAllCustomers() {
        String sql = "SELECT * FROM customer";

        try {
            Connection conn = databaseManager.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt("customerId") + "\t" +
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

    

    public void insertCustomer(Customer c) {
        String sql = "INSERT INTO customer (firstName, lastName, streetAddress, postalCode, city, country, telephone, email) VALUES(?,?,?,?,?,?,?,?)";

        try {
            Connection conn = databaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, c.getFirstName());
            pstmt.setString(2, c.getLastName());
            pstmt.setString(3, c.getStreetAddress());
            pstmt.setString(4, c.getPostalCode());
            pstmt.setString(5, c.getCity());
            pstmt.setString(6, c.getCountry());
            pstmt.setString(7, c.getTelephone());
            pstmt.setString(8, c.getEmail());
            pstmt.executeUpdate();
            System.out.println("You have successfully added a new customer!");
        } catch (SQLException e) {
            System.out.println("Error adding a new customer: " + e.getMessage());
        }
    }


    public void updateCustomer(Customer c) {
        if (c == null) return;
        String sql = "UPDATE customer SET (firstName, lastName, streetAddress, postalCode, city, country, telephone, email, vip)  = (?,?,?,?,?,?,?,?,?)"
                + "WHERE customerId = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, c.getFirstName());
            pstmt.setString(2, c.getLastName());
            pstmt.setString(3, c.getStreetAddress());
            pstmt.setString(4, c.getPostalCode());
            pstmt.setString(5, c.getCity());
            pstmt.setString(6, c.getCountry());
            pstmt.setString(7, c.getTelephone());
            pstmt.setString(8, c.getEmail());
            pstmt.setString(9, c.getVip());
            pstmt.setInt(10,c.getCustomerId());

            pstmt.executeUpdate();
            System.out.println("You have successfully updated customer " + c.getCustomerId());
        } catch (SQLException e) {
            System.out.println("Error updating the customer: " + e.getMessage());
        }
    }




    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM booking WHERE customerId = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);

            pstmt.executeUpdate();
            System.out.println("You have successfully deleted customer " + customerId + "!");
        } catch (SQLException e) {
            System.out.println("Error deleting the customer: " + e.getMessage());
        }
    }



    public void showCustomer(String telephone) {
        String sql = "SELECT * FROM customer WHERE telephone = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, telephone);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("customerId") + "\t" +
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
            System.out.println("Error retrieving customer information: " + e.getMessage());
        }
    }

    public void markCustomerAsVip(int customerId) {
        String sql = "UPDATE customer SET vip = ?"
                + "WHERE customerId = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Yes");
            pstmt.setInt(2, customerId);
            pstmt.executeUpdate();
            System.out.println("You have successfully set customer " + customerId + " as VIP!");
        } catch (SQLException e) {
            System.out.println("Error setting the customer as VIP: " + e.getMessage());
        }
    }

    public void showAllVipCustomers() {
        String sql = "SELECT * FROM customer WHERE vip = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "Yes");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("customerId") + "\t" +
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
            System.out.println("Error showing the VIP customers: " + e.getMessage());
        }
    }

    public void countVipCustomers() {
        String sql = "SELECT COUNT(*) as vipCount FROM customer WHERE vip = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "Yes");

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int vipCount = rs.getInt("vipCount");
                System.out.println("Number of VIP customers: " + vipCount);
            }
        } catch (SQLException e) {
            System.out.println("Error counting the VIP customers: " + e.getMessage());
        }
    }

    
}
