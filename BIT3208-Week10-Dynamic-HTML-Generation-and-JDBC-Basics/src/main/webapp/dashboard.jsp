<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.studentlogin.dao.StudentDAO" %>
<%@ page import="com.studentlogin.dao.LoginHistoryDAO" %>
<%@ page import="com.studentlogin.model.LoginHistoryEntry" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String pageTitle = "Dashboard";
    String activeNav = "dashboard";

    int studentCount = 0;
    int signInCount = 0;
    LoginHistoryEntry lastLogin = null;
    String loadError = null;
    try {
        studentCount = new StudentDAO().getAllStudents().size();
        List<LoginHistoryEntry> logins = new LoginHistoryDAO().getAllLogins();
        signInCount = logins.size();
        if (!logins.isEmpty()) {
            lastLogin = logins.get(0);
        }
    } catch (Exception e) {
        loadError = "Could not load dashboard statistics: " + e.getMessage();
    }
    request.setAttribute("studentCount", studentCount);
    request.setAttribute("signInCount", signInCount);
    request.setAttribute("lastLogin", lastLogin);
    request.setAttribute("loadError", loadError);
%>
<%@ include file="WEB-INF/jspf/shell-top.jspf" %>

<c:if test="${not empty loadError}">
    <div class="alert" role="alert"><span><c:out value="${loadError}"/></span></div>
</c:if>

<p class="page-lede">A quick look at the registrar system: enrollment on file and recent sign-in activity.</p>

<div class="stat-grid">
    <div class="stat-tile">
        <span class="stat-tile__label">Students Enrolled</span>
        <span class="stat-tile__value"><c:out value="${studentCount}"/></span>
        <a class="stat-tile__link" href="ListStudentsServlet">View roster &rarr;</a>
    </div>
    <div class="stat-tile">
        <span class="stat-tile__label">Total Sign-ins</span>
        <span class="stat-tile__value"><c:out value="${signInCount}"/></span>
        <a class="stat-tile__link" href="AdminServlet">View activity &rarr;</a>
    </div>
    <div class="stat-tile">
        <span class="stat-tile__label">Most Recent Sign-in</span>
        <c:choose>
            <c:when test="${not empty lastLogin}">
                <span class="stat-tile__value stat-tile__value--sm"><c:out value="${lastLogin.username}"/></span>
                <span class="stat-tile__link"><c:out value="${lastLogin.formattedLoginTime}"/></span>
            </c:when>
            <c:otherwise>
                <span class="stat-tile__value stat-tile__value--sm">&mdash;</span>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<section class="panel">
    <h2 class="panel__title">Quick actions</h2>
    <div class="quick-actions">
        <a class="btn btn--secondary" href="AddStudentServlet">Add a student</a>
        <a class="btn btn--secondary" href="ListStudentsServlet">Browse students</a>
        <a class="btn btn--secondary" href="AdminServlet">Review login activity</a>
    </div>
</section>

<%@ include file="WEB-INF/jspf/shell-bottom.jspf" %>
