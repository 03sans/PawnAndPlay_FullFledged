package com.pawnandplay.controller.admin;

import com.pawnandplay.dao.DashboardDAO;
import com.pawnandplay.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 23048503 Sanskriti Agrahari
 */

@WebServlet(asyncSupported = true, urlPatterns = {"/dashboard"})
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DashboardDAO dashboardDAO;

	public DashboardController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		dashboardDAO = new DashboardDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Fetch stats
			List<UserModel> recentUsers = dashboardDAO.getRecentUsers(5);
			double totalRevenue = dashboardDAO.getTotalRevenue();
			int totalUsers = dashboardDAO.getTotalUsers();
			int totalOrders = dashboardDAO.getTotalOrders();
			int totalGames = dashboardDAO.getTotalGames();

			// Set attributes to pass to the dashboard JSP
			request.setAttribute("recentUsers", recentUsers);
			request.setAttribute("totalRevenue", totalRevenue);
			request.setAttribute("totalUsers", totalUsers);
			request.setAttribute("totalOrders", totalOrders);
			request.setAttribute("totalGames", totalGames);

			request.getRequestDispatcher("/WEB-INF/pages/dashboard.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Something went wrong loading dashboard data.");
			request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}