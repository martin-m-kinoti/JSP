package com.studentlogin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.studentlogin.dao.LoginHistoryDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    private final LoginHistoryDAO loginHistoryDAO = new LoginHistoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("error", "Username is required.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Simple password validation: just check it was provided.
        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Password is required.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("username", username.trim());

        try {
            loginHistoryDAO.recordLogin(username.trim(), request.getRemoteAddr());
        } catch (SQLException e) {
            // Login still succeeds even if the audit record can't be written.
            getServletContext().log("Could not record login history", e);
        }

        response.sendRedirect("dashboard.jsp");
    }
}
