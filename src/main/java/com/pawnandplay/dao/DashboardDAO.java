package com.pawnandplay.dao;

import com.pawnandplay.model.UserModel;
import com.pawnandplay.config.DbConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 23048503 Sanskriti Agrahari
 */
public class DashboardDAO {

    private Connection conn;

    public DashboardDAO() {
        try {
            conn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<UserModel> getRecentUsers(int limit) throws SQLException {
        List<UserModel> users = new ArrayList<>();
        String sql = "SELECT * FROM User ORDER BY UserID DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserModel u = new UserModel();
                u.setUserId(String.valueOf(rs.getInt("UserID"))); // match model type
                u.setFirstName(rs.getString("Firstname"));
                u.setLastName(rs.getString("Lastname"));
                u.setEmail(rs.getString("Email"));
                u.setUsername(rs.getString("Username"));
                u.setNumber(rs.getString("Phone")); 
                users.add(u);
            }
        }
        return users;
    }

    public double getTotalRevenue() throws SQLException {
        String sql = "SELECT SUM(g.Price * o.Quantity) AS revenue " +
                     "FROM `Order` o JOIN Game g ON o.GameID = g.GameID";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble("revenue");
            }
        }
        return 0.0;
    }

    public int getTotalUsers() throws SQLException {
        String sql = "SELECT COUNT(*) FROM User";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int getTotalOrders() throws SQLException {
        String sql = "SELECT COUNT(*) FROM `Order`";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int getTotalGames() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Game";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
}