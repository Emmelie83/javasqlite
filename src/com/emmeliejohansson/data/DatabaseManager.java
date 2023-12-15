package com.emmeliejohansson.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    
    private Connection conn = null;
    
    public Connection connect() {
        String url = "jdbc:sqlite:C:\\Users\\melie\\SQL\\biluthyrning.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void closeConnection() {
        try {
            conn.close();
        } catch (Exception e) {
            
        }
    }
}
