package com.pawnandplay.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.pawnandplay.config.DbConfig;
import com.pawnandplay.model.UserModel;

/**
 * @author 23048503 Sanskriti Agrahari
 */

public class RegisterService {
    private Connection dbConn;

    // Constructor establishes the database connection
    public RegisterService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Adds a new user to the database.
     * @param userModel The user details from the registration form.
     * @return true if the user was added successfully,
     *         false if there's a duplicate entry (username/email),
     *         null if a different error occurred.
     */
    public Boolean addUser(UserModel userModel) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }

        String insertQuery = "INSERT INTO User (firstName, lastName, username, email, phone, dob, password, image) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {
            insertStmt.setString(1, userModel.getFirstName());
            insertStmt.setString(2, userModel.getLastName());
            insertStmt.setString(3, userModel.getUsername());
            insertStmt.setString(4, userModel.getEmail());
            insertStmt.setString(5, userModel.getNumber());
            insertStmt.setDate(6, Date.valueOf(userModel.getDob())); // Ensure dob is in YYYY-MM-DD format
            insertStmt.setString(7, userModel.getPassword());
            insertStmt.setString(8, userModel.getImage());

            return insertStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during registration: " + e.getMessage());

            // Handle duplicate entry (e.g., unique constraint on username/email)
            if (e.getMessage().contains("Duplicate entry")) {
                return false;
            }

            return null; // For other SQL exceptions
        }
    }
}