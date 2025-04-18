package com.pawnandplay.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.pawnandplay.config.DbConfig;
import com.pawnandplay.model.UserModel;

public class RegisterService {
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public Boolean addUser(UserModel userModel) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String insertQuery = "INSERT INTO User (firstName, lastName, username, email, phone, dob, password)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try(PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {

			// Insert user details
			insertStmt.setString(1, userModel.getFirstName());
			insertStmt.setString(2, userModel.getLastName());
			insertStmt.setString(3, userModel.getUsername());
			insertStmt.setString(4, userModel.getEmail());
			insertStmt.setString(5, userModel.getNumber());
			insertStmt.setDate(6, Date.valueOf(userModel.getDob()));
			insertStmt.setString(7, userModel.getPassword());

			return insertStmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error during student registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
