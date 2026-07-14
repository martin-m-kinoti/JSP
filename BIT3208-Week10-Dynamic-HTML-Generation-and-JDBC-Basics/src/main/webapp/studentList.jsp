<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String pageTitle = "Students";
    String activeNav = "students";
%>
<%@ include file="WEB-INF/jspf/shell-top.jspf" %>

<c:if test="${not empty requestScope.error}">
    <div class="alert" role="alert"><span><c:out value="${requestScope.error}"/></span></div>
</c:if>

<p class="page-lede">Every student currently on file, ordered by enrollment.</p>

<div class="table-wrap">
    <table class="table">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Course</th>
                <th>Enrolled On</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty students}">
                    <tr>
                        <td colspan="6" class="table__empty">No students found.</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td><c:out value="${student.id}"/></td>
                            <td><c:out value="${student.firstName}"/></td>
                            <td><c:out value="${student.lastName}"/></td>
                            <td><c:out value="${student.email}"/></td>
                            <td><c:out value="${student.course}"/></td>
                            <td><c:out value="${student.formattedEnrollmentDate}"/></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

<a class="btn btn--secondary" href="AddStudentServlet">Add another student</a>

<%@ include file="WEB-INF/jspf/shell-bottom.jspf" %>
