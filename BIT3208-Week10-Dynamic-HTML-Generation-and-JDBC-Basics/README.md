# Student Management System (JSP + JDBC)

A Student Login and Management web app extending the Week 9 login system with MySQL-backed student records via JDBC.

## Features

- **Database** — `studentdb` MySQL database with a `students` table (`sql/studentdb.sql`).
- **JDBC connectivity** — `DBConnection` opens connections using `mysql-connector-j`; `StudentDAO` and `LoginHistoryDAO` handle all queries via `PreparedStatement`.
- **Add students** — `AddStudentServlet` + `addStudent.jsp` insert a new student record through a web form.
- **Dynamic listing** — `ListStudentsServlet` + `studentList.jsp` render all student records from the database using JSTL (`<c:forEach>`).
- **App shell** — every authenticated page shares a sidebar/topbar layout (`dashboard.jsp`, `studentList.jsp`, `addStudent.jsp`, `admin.jsp`) guarded centrally by `AuthFilter`, instead of one-off pages.
- **Admin login activity** — every sign-in is recorded to a `login_history` table; `AdminServlet` + `admin.jsp` list who has accessed the system and when.

## Tech Stack

- Jakarta Servlet 6.0 / JSP, JSTL
- MySQL (JDBC via `mysql-connector-j`)
- Maven (packaged as a WAR)
- Tomcat 11

## Run Locally

```
mysql -u root < sql/studentdb.sql
mvn clean package
```

Update `src/main/resources/db.properties` with your MySQL credentials if not using `root` with no password. Deploy the generated `target/student-login-app.war` to Tomcat's `webapps/` folder, then start Tomcat.

## Access the System

- Login page: http://localhost:8080/student-login-app/login.jsp
- Dashboard (after login): http://localhost:8080/student-login-app/dashboard.jsp
- Students: http://localhost:8080/student-login-app/ListStudentsServlet
- Add student: http://localhost:8080/student-login-app/AddStudentServlet
- Login activity (admin): http://localhost:8080/student-login-app/AdminServlet
