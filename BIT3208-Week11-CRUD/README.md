# Student Management System — Week 11 (Full CRUD)

This week completes the Student Management Web Application by adding the remaining CRUD
operations, search, and stronger input validation on top of the login (Week 9) and
add/list/JDBC foundation (Week 10).

## What's New This Week

- **Edit student** — `EditStudentServlet` + `editStudent.jsp` load a student by ID, prefill
  a form, and save changes with `StudentDAO.updateStudent(...)`.
- **Delete student** — `DeleteStudentServlet` removes a student via
  `StudentDAO.deleteStudent(...)`; each row's Delete button is a POST form guarded by a
  JavaScript confirmation dialog (built safely from a `data-student-name` attribute, not
  string-concatenated into inline JS).
- **Search** — `ListStudentsServlet` accepts an optional `q` parameter and calls
  `StudentDAO.searchStudents(...)`, which matches first name, last name, email, or course
  with a `LIKE` query. The search box on `studentList.jsp` is sticky (keeps the last query)
  and offers a "Clear" link.
- **Input validation** — a shared `StudentValidator` utility enforces required fields,
  column-length limits (matching the `VARCHAR` sizes in the schema), and a proper email
  format, reused by both the Add and Edit servlets. Duplicate emails (the `students.email`
  unique constraint) are caught and shown as a friendly message instead of a raw SQL error.
  Forms are "sticky" — invalid input is redisplayed instead of cleared.


## Full Feature Set

- User login with session management, backed by `AuthFilter` (Week 9).
- MySQL connectivity via JDBC (`DBConnection`, `mysql-connector-j`).
- Dynamic JSP pages sharing a common sidebar/topbar shell.
- Student registration, listing, editing, deletion, and search.
- Server-side input validation with sticky forms and friendly error messages.
- Login activity log (`AdminServlet` / `admin.jsp`) and a dashboard with summary stats.

## Tech Stack

- Jakarta Servlet 6.0 / JSP, JSTL
- MySQL (JDBC via `mysql-connector-j`)
- Maven (packaged as a WAR)
- Apache Tomcat 11

## Access the System

- Login page: http://localhost:8080/student-login-app/login.jsp
- Dashboard (after login): http://localhost:8080/student-login-app/dashboard.jsp
- Students (list/search): http://localhost:8080/student-login-app/ListStudentsServlet
- Add student: http://localhost:8080/student-login-app/AddStudentServlet
- Edit student: `ListStudentsServlet` → "Edit" on any row
- Login activity (admin): http://localhost:8080/student-login-app/AdminServlet