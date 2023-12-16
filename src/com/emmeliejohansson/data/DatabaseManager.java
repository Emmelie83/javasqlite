package com.emmeliejohansson.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    
    public Connection connect() {
        String url = "jdbc:sqlite:C:\\Users\\melie\\SQL\\biluthyrning.db";
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    

}
