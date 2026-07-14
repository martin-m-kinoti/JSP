package com.studentlogin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.studentlogin.dao.StudentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteStudentServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            studentDAO.deleteStudent(id);
        } catch (NumberFormatException | SQLException e) {
            request.getSession().setAttribute("flashError", "Could not delete student: " + e.getMessage());
        }
        response.sendRedirect("ListStudentsServlet");
    }
}
