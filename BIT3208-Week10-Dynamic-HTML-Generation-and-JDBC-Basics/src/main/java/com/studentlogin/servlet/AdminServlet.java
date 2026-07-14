package com.studentlogin.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.studentlogin.dao.LoginHistoryDAO;
import com.studentlogin.model.LoginHistoryEntry;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminServlet extends HttpServlet {

    private final LoginHistoryDAO loginHistoryDAO = new LoginHistoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<LoginHistoryEntry> logins = loginHistoryDAO.getAllLogins();
            request.setAttribute("logins", logins);
        } catch (SQLException e) {
            request.setAttribute("error", "Could not load login activity: " + e.getMessage());
        }
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }
}
