package com.pawnandplay.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pawnandplay.config.DbConfig;
import com.pawnandplay.model.UserModel;
import com.pawnandplay.util.PasswordUtil;

public class LoginService {
	
	private Connection dbConn;
	private boolean isConnectionError = false;
	
	public LoginService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	public Boolean loginUser(UserModel userModel) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "SELECT username, password FROM User WHERE username = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, userModel.getUsername());
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return validatePassword(result, userModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return false;
	}
	
	private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
		String dbUsername = result.getString("username");
		String dbPassword = result.getString("password");
		
		String decryptedPassword = PasswordUtil.decrypt(dbPassword, dbUsername);
		
		System.out.println("DB Username: " + dbUsername);
		System.out.println("Encrypted Password: " + dbPassword);
		System.out.println("Decrypted Password: " + decryptedPassword);
		System.out.println("User Input Password: " + userModel.getPassword());

		return dbUsername.equals(userModel.getUsername())
				&& decryptedPassword != null
				&& decryptedPassword.equals(userModel.getPassword());
	}
}
