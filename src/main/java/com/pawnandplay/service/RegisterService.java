package com.pawnandplay.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.pawnandplay.config.DbConfig;
import com.pawnandplay.model.UserModel;

public class RegisterService {
    private Connection dbConn;

    public RegisterService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Boolean addUser(UserModel userModel) {
    	System.out.println(10);
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }
        
        System.out.println("Add bhairacha");
        String insertQuery = "INSERT INTO User (firstName, lastName, username, email, phone, dob, password, image) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {
        	System.out.println(11);

            insertStmt.setString(1, userModel.getFirstName());
            insertStmt.setString(2, userModel.getLastName());
            insertStmt.setString(3, userModel.getUsername());
            insertStmt.setString(4, userModel.getEmail());
            insertStmt.setString(5, userModel.getNumber());
            insertStmt.setDate(6, Date.valueOf(userModel.getDob()));
            insertStmt.setString(7, userModel.getPassword());
    		insertStmt.setString(8, userModel.getImage());

            return insertStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during registration: " + e.getMessage());
            // Check for duplicate username/email
            if (e.getMessage().contains("Duplicate entry")) {
                return false;
            }
            return null;
        }
    }
}