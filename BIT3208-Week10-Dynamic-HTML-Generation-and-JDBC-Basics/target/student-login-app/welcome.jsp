<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <main class="card">
        <div class="card__badge" aria-hidden="true">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M20 6 9 17l-5-5"></path>
            </svg>
        </div>
        <h1>Welcome back</h1>
        <p class="welcome-text">You're signed in as <strong><c:out value="${sessionScope.username}"/></strong></p>

        <p class="welcome-text"><a href="ListStudentsServlet">View student records</a> &middot; <a href="AddStudentServlet">Add a student</a></p>

        <form action="LogoutServlet" method="post">
            <button type="submit" class="btn btn--danger">Log Out</button>
        </form>
    </main>
</body>
</html>
