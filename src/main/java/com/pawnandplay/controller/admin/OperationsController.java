package com.pawnandplay.controller.admin;

import com.pawnandplay.dao.OperationsDAO;
import com.pawnandplay.model.GamesModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

/**
 * @author 23048503 Sanskriti Agrahari
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/operations"})
public class OperationsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OperationsDAO dao;

	// Initialize DAO instance when servlet starts
	@Override
	public void init() throws ServletException {
		dao = new OperationsDAO();
	}

	// Handle GET requests - Load and display all games
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<GamesModel> games = dao.getAllGames(); // Fetch all games from DB
		request.setAttribute("games", games); // Pass game list to JSP
		request.getRequestDispatcher("/WEB-INF/pages/operations.jsp").forward(request, response); // Forward to view
	}

	// Handle POST requests for Add, Update, Delete, and Search actions
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action"); // Get action type from form
		String resultMessage = ""; // To hold feedback message for the user

		switch (action) {
			case "add":
				resultMessage = handleAdd(request); // Handle adding new game
				break;
			case "update":
				resultMessage = handleUpdate(request); // Handle updating existing game
				break;
			case "delete":
				resultMessage = handleDelete(request); // Handle deleting a game
				break;
			case "search":
				handleSearch(request); // Handle binary search by stock
				break;
		}

		// Add result message to request scope for feedback
		request.setAttribute("resultMessage", resultMessage);

		// Reload updated game list after operations
		List<GamesModel> games = dao.getAllGames();
		request.setAttribute("games", games);
		request.getRequestDispatcher("/WEB-INF/pages/operations.jsp").forward(request, response); // Forward to view
	}

	// Extract and add new game
	private String handleAdd(HttpServletRequest request) {
		GamesModel game = extractGameFromRequest(request, false); // Extract form data
		String resultMessage = dao.addGame(game); // Call DAO to add game
		return resultMessage;
	}

	// Extract and update game
	private String handleUpdate(HttpServletRequest request) {
		GamesModel game = extractGameFromRequest(request, true); // Extract data including ID
		String resultMessage = dao.updateGame(game); // Call DAO to update game
		return resultMessage;
	}

	// Delete game by ID
	private String handleDelete(HttpServletRequest request) {
		int gameID = Integer.parseInt(request.getParameter("gameID")); // Parse game ID
		String resultMessage = dao.deleteGame(gameID); // Call DAO to delete game
		return resultMessage;
	}

	// Perform binary search by stock
	private void handleSearch(HttpServletRequest request) {
		try {
			int stock = Integer.parseInt(request.getParameter("stock")); // Parse stock input
			GamesModel game = dao.binarySearchByStock(stock); // Search using binary search

			if (game != null) {
				request.setAttribute("searchResult", game); // If found, pass result
			} else {
				request.setAttribute("notFound", "Game with stock " + stock + " not found."); // Not found message
			}
		} catch (NumberFormatException e) {
			request.setAttribute("notFound", "Invalid stock input."); // Handle invalid input
		}
	}

	// Extract game details from request parameters
	private GamesModel extractGameFromRequest(HttpServletRequest request, boolean includeID) {
		GamesModel game = new GamesModel();

		// If update, include the game ID
		if (includeID) {
			game.setGameID(Integer.parseInt(request.getParameter("gameID")));
		}

		// Set all game fields from request
		game.setGamename(request.getParameter("gamename"));
		game.setLevel(request.getParameter("level"));
		game.setGenre(request.getParameter("genre"));
		game.setAge(Integer.parseInt(request.getParameter("age")));
		game.setPrice(Double.parseDouble(request.getParameter("price")));
		game.setStock(Integer.parseInt(request.getParameter("stock")));
		game.setBrand(request.getParameter("brand"));

		return game;
	}
}