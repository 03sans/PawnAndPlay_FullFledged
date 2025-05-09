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

	@Override
	public void init() throws ServletException {
			dao = new OperationsDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<GamesModel> games = dao.getAllGames();
		request.setAttribute("games", games);
		request.getRequestDispatcher("/WEB-INF/pages/operations.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String resultMessage = "";

		switch (action) {
			case "add":
				resultMessage = handleAdd(request);
				break;
			case "update":
				resultMessage = handleUpdate(request);
				break;
			case "delete":
				resultMessage = handleDelete(request);
				break;
			case "search":
				handleSearch(request);
				break;
		}

		// Add result message to request
		request.setAttribute("resultMessage", resultMessage);
		
		// Reload the games list
		List<GamesModel> games = dao.getAllGames();
		request.setAttribute("games", games);
		request.getRequestDispatcher("/WEB-INF/pages/operations.jsp").forward(request, response);
	}

	private String handleAdd(HttpServletRequest request) {
		GamesModel game = extractGameFromRequest(request, false);
		String resultMessage = dao.addGame(game);
		return resultMessage;
	}

	private String handleUpdate(HttpServletRequest request) {
		GamesModel game = extractGameFromRequest(request, true);
		String resultMessage = dao.updateGame(game);
		return resultMessage;
	}

	private String handleDelete(HttpServletRequest request) {
		int gameID = Integer.parseInt(request.getParameter("gameID"));
		String resultMessage = dao.deleteGame(gameID);
		return resultMessage;
	}

	private void handleSearch(HttpServletRequest request) {
		try {
			int stock = Integer.parseInt(request.getParameter("stock"));
			GamesModel game = dao.binarySearchByStock(stock);
			if (game != null) {
				request.setAttribute("searchResult", game);
			} else {
				request.setAttribute("notFound", "Game with stock " + stock + " not found.");
			}
		} catch (NumberFormatException e) {
			request.setAttribute("notFound", "Invalid stock input.");
		}
	}

	private GamesModel extractGameFromRequest(HttpServletRequest request, boolean includeID) {
		GamesModel game = new GamesModel();
		if (includeID) {
			game.setGameID(Integer.parseInt(request.getParameter("gameID")));
		}
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