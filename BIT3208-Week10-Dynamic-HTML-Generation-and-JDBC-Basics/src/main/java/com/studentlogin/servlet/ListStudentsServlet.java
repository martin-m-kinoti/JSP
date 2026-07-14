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

public class ListStudentsServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Student> students = studentDAO.getAllStudents();
            request.setAttribute("students", students);
        } catch (SQLException e) {
            request.setAttribute("error", "Could not load students: " + e.getMessage());
        }
        request.getRequestDispatcher("studentList.jsp").forward(request, response);
    }
}
