package com.pawnandplay.servlets;

import com.pawnandplay.config.DbConfig;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/dbtest")
public class DbTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection conn = DbConfig.getDbConnection();
            if (conn != null) {
                out.println("<h2>✅ Database connection successful!</h2>");
                conn.close();
            } else {
                out.println("<h2>⚠️ Connection returned null!</h2>");
            }
        } catch (Exception e) {
            out.println("<h2>❌ Database connection failed:</h2>");
            out.println("<pre>" + e.getMessage() + "</pre>");
            e.printStackTrace(); // shows full stack trace in the console
        }
    }
}