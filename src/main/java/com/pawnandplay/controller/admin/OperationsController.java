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

		switch (action) {
			case "add":
				handleAdd(request);
				break;
			case "update":
				handleUpdate(request);
				break;
			case "delete":
				handleDelete(request);
				break;
			case "search":
				handleSearch(request);
				break;
		}

		List<GamesModel> games = dao.getAllGames();
		request.setAttribute("games", games);
		request.getRequestDispatcher("/WEB-INF/pages/operations.jsp").forward(request, response);
	}

	private void handleAdd(HttpServletRequest request) {
		GamesModel game = extractGameFromRequest(request, false);
		dao.addGame(game);
	}

	private void handleUpdate(HttpServletRequest request) {
		GamesModel game = extractGameFromRequest(request, true);
		dao.updateGame(game);
	}

	private void handleDelete(HttpServletRequest request) {
		int gameID = Integer.parseInt(request.getParameter("gameID"));
		dao.deleteGame(gameID);
	}

	private void handleSearch(HttpServletRequest request) {
		int stock = Integer.parseInt(request.getParameter("stock"));
		GamesModel game = dao.binarySearchByStock(stock);
		if (game != null) {
			request.setAttribute("searchResult", game);
		} else {
			request.setAttribute("notFound", "Game with stock " + stock + " not found.");
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