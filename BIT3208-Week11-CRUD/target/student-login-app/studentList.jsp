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

<form class="search-bar" action="ListStudentsServlet" method="get" role="search">
    <input type="search" name="q" placeholder="Search by name, email, or course"
           value="<c:out value="${query}"/>" aria-label="Search students">
    <button type="submit" class="btn btn--secondary">Search</button>
    <c:if test="${not empty query}">
        <a class="btn btn--secondary" href="ListStudentsServlet">Clear</a>
    </c:if>
</form>

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
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty students}">
                    <tr>
                        <td colspan="7" class="table__empty">No students found.</td>
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
                            <td class="table__actions">
                                <a class="btn-link" href="EditStudentServlet?id=<c:out value="${student.id}"/>">Edit</a>
                                <form class="table__delete-form" action="DeleteStudentServlet" method="post"
                                      data-confirm-delete
                                      data-student-name="<c:out value="${student.firstName} ${student.lastName}"/>">
                                    <input type="hidden" name="id" value="<c:out value="${student.id}"/>">
                                    <button type="submit" class="btn-link btn-link--danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

<a class="btn btn--secondary" href="AddStudentServlet">Add another student</a>

<script>
    document.querySelectorAll('form[data-confirm-delete]').forEach(function (form) {
        form.addEventListener('submit', function (event) {
            var name = form.dataset.studentName || 'this student';
            if (!confirm('Delete ' + name + '? This cannot be undone.')) {
                event.preventDefault();
            }
        });
    });
</script>

<%@ include file="WEB-INF/jspf/shell-bottom.jspf" %>
