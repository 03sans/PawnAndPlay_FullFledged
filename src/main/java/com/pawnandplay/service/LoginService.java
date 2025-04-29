package com.pawnandplay.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.pawnandplay.config.DbConfig;
import com.pawnandplay.model.UserModel;
import com.pawnandplay.util.PasswordUtil;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    public UserModel getUserDetails(String username) {
        UserModel user = null;
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE username = ?")) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new UserModel();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setNumber(rs.getString("number"));

                String dobString = rs.getString("dob");
                if (dobString != null) {
                    try {
                        user.setDob(LocalDate.parse(dobString)); // Convert String to LocalDate
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format: " + dobString);
                        user.setDob(null);
                    }
                }

                user.setImage(rs.getString("image"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getUserRole(String username) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT role FROM User WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return result.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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