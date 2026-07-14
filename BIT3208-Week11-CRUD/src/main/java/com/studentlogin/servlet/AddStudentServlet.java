package com.studentlogin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.studentlogin.dao.StudentDAO;
import com.studentlogin.model.Student;
import com.studentlogin.util.StudentValidator;

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

        String validationError = StudentValidator.validate(firstName, lastName, email, course);
        if (validationError != null) {
            request.setAttribute("error", validationError);
            request.setAttribute("student", new Student(firstName, lastName, email, course));
            RequestDispatcher dispatcher = request.getRequestDispatcher("addStudent.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            Student student = new Student(firstName.trim(), lastName.trim(), email.trim(), course.trim());
            studentDAO.insertStudent(student);
            response.sendRedirect("ListStudentsServlet");
        } catch (SQLException e) {
            String message = e.getMessage() != null && e.getMessage().toLowerCase().contains("duplicate")
                    ? "A student with that email already exists."
                    : "Could not save student: " + e.getMessage();
            request.setAttribute("error", message);
            request.setAttribute("student", new Student(firstName, lastName, email, course));
            request.getRequestDispatcher("addStudent.jsp").forward(request, response);
        }
    }
}
