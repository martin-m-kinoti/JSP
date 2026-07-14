package com.studentlogin.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.studentlogin.dao.StudentDAO;
import com.studentlogin.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ListStudentsServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("q");
        try {
            List<Student> students = (query == null || query.trim().isEmpty())
                    ? studentDAO.getAllStudents()
                    : studentDAO.searchStudents(query.trim());
            request.setAttribute("students", students);
            request.setAttribute("query", query);
        } catch (SQLException e) {
            request.setAttribute("error", "Could not load students: " + e.getMessage());
        }

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("flashError") != null) {
            request.setAttribute("error", session.getAttribute("flashError"));
            session.removeAttribute("flashError");
        }

        request.getRequestDispatcher("studentList.jsp").forward(request, response);
    }
}
