package com.emmeliejohansson.repositories;

import com.emmeliejohansson.data.DatabaseManager;
import com.emmeliejohansson.data.entities.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerRepository {
    
    DatabaseManager databaseManager = new DatabaseManager();


    //region get
    public ArrayList<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer customer = mapResultSetToCustomer(rs);
                if (customer != null) customers.add(customer);
            }
        } catch (SQLException e){
            System.out.println("Error showing the VIP customers: " + e.getMessage());
        }
        return customers;
    }

    public Customer getCustomerById(int cId) {
        String sql = "SELECT * FROM customer WHERE customerId = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                return mapResultSetToCustomer(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customer information: " + e.getMessage());
            return null;
        }
        return null;
    }

    public ArrayList<Customer> getCustomersByVip(String vip) {
        String sql = "SELECT * FROM customer WHERE vip = ?";
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vip);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer customer = mapResultSetToCustomer(rs);
                if (customer != null) customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error showing the VIP customers: " + e.getMessage());
        }
        return customers;
    }


    public ArrayList<Customer> getCustomersByTelephone(String telephone) {
        String sql = "SELECT * FROM customer WHERE telephone = ?";
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, telephone);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer customer = mapResultSetToCustomer(rs);
                if (customer != null) customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error showing the VIP customers: " + e.getMessage());
        }
        return customers;
    }
    
    //endregion

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
            System.out.println("You have successfully updated customer " + c.getCustomerId() + "!");
        } catch (SQLException e) {
            System.out.println("Error updating the customer: " + e.getMessage());
        }
    }

    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM customer WHERE customerId = ?";

        try (Connection conn = databaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);

            pstmt.executeUpdate();
            System.out.println("You have successfully deleted customer " + customerId + "!");
        } catch (SQLException e) {
            System.out.println("Error deleting the customer: " + e.getMessage());
        }
    }

    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
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

}
