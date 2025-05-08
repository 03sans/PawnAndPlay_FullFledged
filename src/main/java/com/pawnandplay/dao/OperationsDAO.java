package com.pawnandplay.dao;

import java.sql.*;
import java.util.*;

import com.pawnandplay.model.GamesModel;
import com.pawnandplay.config.DbConfig; // Utility class to get DB connection

public class OperationsDAO {

    private Connection conn;

    public OperationsDAO() {
        try {
            conn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Add Game
    public boolean addGame(GamesModel game) {
        String sql = "INSERT INTO Game (Gamename, Level, Genre, Age, Price, Stock, Brand) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, game.getGamename());
            stmt.setString(2, game.getLevel());
            stmt.setString(3, game.getGenre());
            stmt.setInt(4, game.getAge());
            stmt.setDouble(5, game.getPrice());
            stmt.setInt(6, game.getStock());
            stmt.setString(7, game.getBrand());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle better in production
            return false;
        }
    }

    // Update Game by GameID
    public boolean updateGame(GamesModel game) {
        String sql = "UPDATE Game SET Gamename=?, Level=?, Genre=?, Age=?, Price=?, Stock=?, Brand=? WHERE GameID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, game.getGamename());
            stmt.setString(2, game.getLevel());
            stmt.setString(3, game.getGenre());
            stmt.setInt(4, game.getAge());
            stmt.setDouble(5, game.getPrice());
            stmt.setInt(6, game.getStock());
            stmt.setString(7, game.getBrand());
            stmt.setInt(8, game.getGameID());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete Game by GameID
    public boolean deleteGame(int gameID) {
        String sql = "DELETE FROM Game WHERE GameID=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gameID);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // View All Games
    public List<GamesModel> getAllGames() {
        List<GamesModel> gamesList = new ArrayList<>();
        String sql = "SELECT * FROM Game ORDER BY Stock"; // Sorted by stock for binary search
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                GamesModel game = new GamesModel();
                game.setGameID(rs.getInt("GameID"));
                game.setGamename(rs.getString("Gamename"));
                game.setLevel(rs.getString("Level"));
                game.setGenre(rs.getString("Genre"));
                game.setAge(rs.getInt("Age"));
                game.setPrice(rs.getDouble("Price"));
                game.setStock(rs.getInt("Stock"));
                game.setBrand(rs.getString("Brand"));
                gamesList.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gamesList;
    }

    // Binary Search by Stock
    public GamesModel binarySearchByStock(int stock) {
        List<GamesModel> games = getAllGames(); // Must be sorted by Stock
        int low = 0;
        int high = games.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int currentStock = games.get(mid).getStock();

            if (currentStock == stock) {
                return games.get(mid);
            } else if (currentStock < stock) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null; // Not found
    }
}