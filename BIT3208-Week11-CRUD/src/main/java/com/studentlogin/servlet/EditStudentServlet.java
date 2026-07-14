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

public class EditStudentServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = parseId(request.getParameter("id"));
        if (id <= 0) {
            response.sendRedirect("ListStudentsServlet");
            return;
        }

        try {
            Student student = studentDAO.getStudentById(id);
            if (student == null) {
                request.setAttribute("error", "No student found with that ID.");
                request.getRequestDispatcher("studentList.jsp").forward(request, response);
                return;
            }
            request.setAttribute("student", student);
            request.getRequestDispatcher("editStudent.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Could not load student: " + e.getMessage());
            request.getRequestDispatcher("studentList.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = parseId(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        if (id <= 0) {
            response.sendRedirect("ListStudentsServlet");
            return;
        }

        String validationError = StudentValidator.validate(firstName, lastName, email, course);
        if (validationError != null) {
            Student sticky = new Student(firstName, lastName, email, course);
            sticky.setId(id);
            request.setAttribute("error", validationError);
            request.setAttribute("student", sticky);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editStudent.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            Student student = new Student(firstName.trim(), lastName.trim(), email.trim(), course.trim());
            student.setId(id);
            studentDAO.updateStudent(student);
            response.sendRedirect("ListStudentsServlet");
        } catch (SQLException e) {
            String message = e.getMessage() != null && e.getMessage().toLowerCase().contains("duplicate")
                    ? "A student with that email already exists."
                    : "Could not update student: " + e.getMessage();
            Student sticky = new Student(firstName, lastName, email, course);
            sticky.setId(id);
            request.setAttribute("error", message);
            request.setAttribute("student", sticky);
            request.getRequestDispatcher("editStudent.jsp").forward(request, response);
        }
    }

    private int parseId(String rawId) {
        try {
            return Integer.parseInt(rawId);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
