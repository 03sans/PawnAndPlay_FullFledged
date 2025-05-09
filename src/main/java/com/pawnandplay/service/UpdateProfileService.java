package com.pawnandplay.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.pawnandplay.config.DbConfig;
import com.pawnandplay.model.UserModel;


/**
 * 
 * @author 23048503 Sanskriti Agrahari
 */
public class UpdateProfileService {

	public Boolean updateUser(UserModel userModel, String oldUsername) {
	    String updateQuery = "UPDATE User SET Firstname = ?, Lastname = ?, Username = ?, Email = ?, Phone = ?, Password = ?"
	            + (userModel.getDob() != null ? ", dob = ?" : "") + " WHERE Username = ?";

	    try (Connection dbConn = DbConfig.getDbConnection();
	         PreparedStatement updateStmt = dbConn.prepareStatement(updateQuery)) {

	        updateStmt.setString(1, userModel.getFirstName());
	        updateStmt.setString(2, userModel.getLastName());
	        updateStmt.setString(3, userModel.getUsername());
	        updateStmt.setString(4, userModel.getEmail());
	        updateStmt.setString(5, userModel.getNumber());

	        if (userModel.getDob() != null) {
	            updateStmt.setDate(6, Date.valueOf(userModel.getDob()));  // Set dob only if it's not null
	            updateStmt.setString(7, userModel.getPassword());
	            updateStmt.setString(8, oldUsername);
	        } else {
	            updateStmt.setString(6, userModel.getPassword());
	            updateStmt.setString(7, oldUsername);
	        }

	        int rowsAffected = updateStmt.executeUpdate();
	        return rowsAffected > 0;

	    } catch (SQLException | ClassNotFoundException e) {
	        System.err.println("Error updating user profile: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}
    
    public String getExistingPassword(String username) throws SQLException, ClassNotFoundException {
        String query = "SELECT Password FROM User WHERE Username = ?";
        try (Connection dbConn = DbConfig.getDbConnection();
             PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Password");
            }
        }
        return null;
    }
}