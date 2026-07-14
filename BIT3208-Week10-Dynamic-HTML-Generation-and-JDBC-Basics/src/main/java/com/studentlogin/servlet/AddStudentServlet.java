package com.studentlogin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.studentlogin.dao.StudentDAO;
import com.studentlogin.model.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddStudentServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addStudent.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        if (isBlank(firstName) || isBlank(lastName) || isBlank(email) || isBlank(course)) {
            request.setAttribute("error", "All fields are required.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addStudent.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            Student student = new Student(firstName.trim(), lastName.trim(), email.trim(), course.trim());
            studentDAO.insertStudent(student);
            response.sendRedirect("ListStudentsServlet");
        } catch (SQLException e) {
            request.setAttribute("error", "Could not save student: " + e.getMessage());
            request.getRequestDispatcher("addStudent.jsp").forward(request, response);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
