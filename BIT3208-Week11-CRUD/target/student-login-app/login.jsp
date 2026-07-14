<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Student Login</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <div class="auth-shell">
    <main class="card">
        <div class="card__badge" aria-hidden="true">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M22 10 12 5 2 10l10 5 10-5Z"></path>
                <path d="M6 12v5c0 1.1 2.7 2 6 2s6-.9 6-2v-5"></path>
            </svg>
        </div>
        <h1>Student Login</h1>
        <p class="card__subtitle">Sign in to access your student account</p>

        <c:if test="${not empty requestScope.error}">
            <div class="alert" role="alert">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true">
                    <circle cx="12" cy="12" r="10"></circle>
                    <line x1="12" y1="8" x2="12" y2="12"></line>
                    <line x1="12" y1="16" x2="12.01" y2="16"></line>
                </svg>
                <span><c:out value="${requestScope.error}"/></span>
            </div>
        </c:if>

        <form action="LoginServlet" method="post" novalidate>
            <div class="field">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Enter your username"
                       autocomplete="username" required autofocus>
            </div>

            <div class="field">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password"
                       autocomplete="current-password" required>
            </div>

            <button type="submit" class="btn">Log In</button>
        </form>
    </main>
    </div>
</body>
</html>
